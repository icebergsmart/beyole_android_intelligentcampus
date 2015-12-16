package com.beyole.intelligentcampus.functions.life.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.beyole.bean.Words;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.WordsDetailsActivity;
import com.beyole.intelligentcampus.functions.life.adapter.WordsSearchListAdapter;
import com.beyole.util.MyDatabaseHelper;
import com.beyole.view.EditTextWithRightButton;

public class WordSearchFragment extends Fragment {

	private View mView;
	private EditTextWithRightButton mSearchEt;
	private LinearLayout mResultLL;
	private ListView mResultList;
	private TextView mNoResultView;
	private MyDatabaseHelper dbHelper;
	private SQLiteDatabase dbReader;
	private WordsSearchListAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.function_life_search_words_layout, container, false);
		dbHelper = new MyDatabaseHelper(getActivity());
		dbReader = dbHelper.openDatabase();
		initViews();
		initEvents();
		return mView;
	}

	private void initEvents() {
		TextWatcher watcher = new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (s.length() > 0) {
					// 执行查询单词
					mResultLL.setVisibility(View.VISIBLE);
					final ArrayList<Words> arrayList = queryForFuzzyQuery(s.toString());
					if (arrayList.size() > 0) {
						mAdapter = new WordsSearchListAdapter(getActivity(), arrayList);
						mResultList.setAdapter(mAdapter);
						mResultList.setOnItemClickListener(new OnItemClickListener() {

							@Override
							public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
								Words words = arrayList.get(position);
								Intent intent = new Intent(getActivity(), WordsDetailsActivity.class);
								intent.putExtra("wordsId", words.getId()+"");
								intent.putExtra("isSearchDetails", 1);
								startActivity(intent);
							}
						});
						mResultList.setVisibility(View.VISIBLE);
						mNoResultView.setVisibility(View.GONE);
					} else {
						mResultList.setVisibility(View.GONE);
						mNoResultView.setVisibility(View.VISIBLE);
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		};
		// 添加字符改变监听器
		mSearchEt.addTextChangedListener(watcher);
	}

	private void initViews() {
		mSearchEt = (EditTextWithRightButton) mView.findViewById(R.id.id_function_search_words_et);
		mResultLL = (LinearLayout) mView.findViewById(R.id.id_function_search_result_ll);
		mResultList = (ListView) mView.findViewById(R.id.id_function_search_words_lv);
		mNoResultView = (TextView) mView.findViewById(R.id.id_function_search_words_no_result_tv);
	}

	// 将Cursor转换为list
	public ArrayList<Words> convertCursorToList(Cursor cursor) {
		ArrayList<Words> wordsList = new ArrayList<Words>();
		Words word = null;
		// 遍历Cursor结果集
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.TABLE_WORDS_COLUMN_ID));
			String words = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.TABLE_WORDS_COLUMN_WORD));
			String pastTense = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.TABLE_WORDS_COLUMN_PASTTENSE));
			String pastParticiples = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.TABLE_WORDS_COLUMN_PASTPARTICIPLES));
			String presentParticiples = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.TABLE_WORDS_COLUMN_PRESENTPARTICIPLES));
			String complex = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.TABLE_WORDS_COLUMN_COMPLEX));
			String meanings = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.TABLE_WORDS_COLUMN_MEANING));
			String example = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.TABLE_WORDS_COLUMN_EXAMPLE));
			word = new Words(id, words, pastTense, pastParticiples, presentParticiples, complex, meanings, example);
			wordsList.add(word);
		}
		return wordsList;
	}

	// 模糊查询单词
	public ArrayList<Words> queryForFuzzyQuery(String words) {
		Cursor cursor = dbReader.rawQuery("select * from " + MyDatabaseHelper.TABLE_WORDS + " where " + MyDatabaseHelper.TABLE_WORDS_COLUMN_WORD + " like ? ", new String[] { "%" + words + "%" });
		return convertCursorToList(cursor);
	}
}
