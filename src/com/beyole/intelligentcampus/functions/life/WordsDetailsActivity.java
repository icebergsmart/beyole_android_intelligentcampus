package com.beyole.intelligentcampus.functions.life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beyole.bean.Words;
import com.beyole.intelligentcampus.R;
import com.beyole.util.MyDatabaseHelper;

public class WordsDetailsActivity extends Activity {

	private LinearLayout previousBtn, nextBtn;
	private int currentIndex = 0;
	private int totalIndex = 0;
	private List<String> result = new ArrayList<String>();
	private MyDatabaseHelper dbHelper;
	private SQLiteDatabase dbReader;
	private TextView mWords, mPastTense, mPastParticiples, mPresentParticiples, mComplex, mMeanings, mExample, mBannerTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function_life_words_details_layout);
		dbHelper = new MyDatabaseHelper(this);
		dbReader = dbHelper.openDatabase();
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		mBannerTitle = (TextView) findViewById(R.id.id_top_banner_title);
		mWords = (TextView) findViewById(R.id.id_function_life_words_details_words_tv);
		mPastTense = (TextView) findViewById(R.id.id_function_life_words_details_pastTense_tv);
		mPastParticiples = (TextView) findViewById(R.id.id_function_life_words_details_pastParticiples_tv);
		mPresentParticiples = (TextView) findViewById(R.id.id_function_life_words_details_presentParticiples_tv);
		mComplex = (TextView) findViewById(R.id.id_function_life_words_details_complex_tv);
		mMeanings = (TextView) findViewById(R.id.id_function_life_words_details_meanings_tv);
		mExample = (TextView) findViewById(R.id.id_function_life_words_details_example_tv);
		previousBtn = (LinearLayout) findViewById(R.id.id_function_words_details_previous_ll);
		nextBtn = (LinearLayout) findViewById(R.id.id_function_words_details_next_ll);
		int isSearchDetails = getIntent().getIntExtra("isSearchDetails", 0);
		if (isSearchDetails == 0) {
			wordsTask();
		} else {
			wordsDetails();
		}
	}

	class MybtnOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.id_function_words_details_previous_ll:
				if (currentIndex > 0) {
					new MyAsyncTask().execute((currentIndex - 1));
					currentIndex = currentIndex - 1;
					mBannerTitle.setText("单词训练" + (currentIndex + 1) + "/" + totalIndex);
				} else {
					Toast.makeText(WordsDetailsActivity.this, "已经是第一个单词啦！", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.id_function_words_details_next_ll:
				if (currentIndex < totalIndex - 1) {
					new MyAsyncTask().execute((currentIndex + 1));
					currentIndex = currentIndex + 1;
					mBannerTitle.setText("单词训练" + (currentIndex + 1) + "/" + totalIndex);
				} else {
					Toast.makeText(WordsDetailsActivity.this, "已经是最后一个单词啦！", Toast.LENGTH_SHORT).show();
				}
				break;
			}
		}

	}

	class MyAsyncTask extends AsyncTask<Integer, Void, Words> {

		@Override
		protected Words doInBackground(Integer... params) {
			String id = result.get(params[0]);
			return queryForWords(id);
		}

		@Override
		protected void onPostExecute(Words result) {
			initContent(result);
		}

	}

	public void initContent(Words words) {
		mWords.setText(words.getWords());
		mPastTense.setText(words.getPastTense());
		mPastParticiples.setText(words.getPastParticiples());
		mPresentParticiples.setText(words.getPresentParticiples());
		mComplex.setText(words.getComplex());
		mMeanings.setText(words.getMeanings());
		if (words.getExample() != null) {
			mExample.setText(words.getExample().replace("/r", "\r").replace("    ", "").replace("/n", "\n"));
		} else {
			mExample.setText("sorry!没有例句哦.");
		}
	}

	// 将Cursor转换为Object
	public Words convertCursorToObject(Cursor cursor) {
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
		}
		return word;
	}

	public Words queryForWords(String id) {
		Cursor cursor = dbReader.rawQuery("select * from " + MyDatabaseHelper.TABLE_WORDS + " where " + MyDatabaseHelper.TABLE_WORDS_COLUMN_ID + " = ? ", new String[] { id });
		return convertCursorToObject(cursor);
	}

	public void wordsTask() {
		String[] temp = getIntent().getStringExtra("content").replace(" ", "").trim().split(",");
		result = Arrays.asList(temp);
		totalIndex = result.size();
		mBannerTitle.setText("单词训练" + (currentIndex + 1) + "/" + totalIndex);
		new MyAsyncTask().execute(currentIndex);
		MybtnOnClickListener listener = new MybtnOnClickListener();
		previousBtn.setOnClickListener(listener);
		nextBtn.setOnClickListener(listener);
	}

	public void wordsDetails() {
		mBannerTitle.setText("单词详细");
		previousBtn.setVisibility(View.GONE);
		nextBtn.setVisibility(View.GONE);
		new WordsDetailsAsyncTask().execute(getIntent().getStringExtra("wordsId"));
	}

	class WordsDetailsAsyncTask extends AsyncTask<String, Void, Words> {

		@Override
		protected Words doInBackground(String... params) {
			return queryForWords(params[0]);
		}

		@Override
		protected void onPostExecute(Words result) {
			initContent(result);
		}

	}
}
