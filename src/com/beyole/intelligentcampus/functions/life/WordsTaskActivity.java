package com.beyole.intelligentcampus.functions.life;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.beyole.bean.WordsTask;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.adapter.WordsTaskAdapter;
import com.beyole.util.MyDatabaseHelper;

/**
 * 单词任务列表
 * 
 * @date 2015/12/15
 * @author Iceberg
 * 
 */
public class WordsTaskActivity extends Activity {

	private ListView mContentList;
	private WordsTaskAdapter mAdapter;
	private List<WordsTask> mData;
	private int currentLevel;
	private String titleName;
	private MyDatabaseHelper dbHelper;
	private SQLiteDatabase dbReader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function_life_words_task_layout);
		dbHelper = new MyDatabaseHelper(this);
		dbReader = dbHelper.openDatabase();
		initViews();
		initEvents();
	}

	private void initEvents() {
		mContentList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(WordsTaskActivity.this, WordsDetailsActivity.class);
				String content = mData.get(position).getTaskContent();
				intent.putExtra("content", content);
				startActivity(intent);
			}
		});
	}

	private void initViews() {
		titleName = getIntent().getStringExtra("titleName");
		currentLevel = getIntent().getIntExtra("level", 4);
		TextView tv = (TextView) findViewById(R.id.id_function_life_words_task_top).findViewById(R.id.id_top_banner_title);
		tv.setText(titleName);
		mContentList = (ListView) findViewById(R.id.id_function_life_words_task_lv);
		initDatas();
		mAdapter = new WordsTaskAdapter(this, mData);
		mContentList.setAdapter(mAdapter);
	}

	public void initDatas() {
		StringBuilder titles = new StringBuilder("");
		if (currentLevel == 4) {
			titles.append("四级单词");
		} else if (currentLevel == 6) {
			titles.append("六级单词");
		} else if (currentLevel == 14) {
			titles.append("专业四级单词");
		} else if (currentLevel == 18) {
			titles.append("专业八级单词");
		}
		queryForTaskList();
	}

	public void queryForTaskList() {
		Cursor cursor = dbReader.rawQuery("select * from " + MyDatabaseHelper.TABLE_TASK + " where " + MyDatabaseHelper.TABLE_TASK_COLUMN_TASKTYPE + " = ? ", new String[] { "" + currentLevel });
		mData = convertCursorToList(cursor);
	}

	// 将Cursor转换为list
	public ArrayList<WordsTask> convertCursorToList(Cursor cursor) {
		ArrayList<WordsTask> wordsTasks = new ArrayList<WordsTask>();
		WordsTask task = null;
		// 遍历Cursor结果集
		while (cursor.moveToNext()) {
			int taskId = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.TABLE_TASK_COLUMN_ID));
			String taskName = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.TABLE_TASK_COLUMN_TASKNAME));
			int level = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.TABLE_TASK_COLUMN_TASKTYPE));
			String taskContent = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.TABLE_TASK_COLUMN_CONTENT));
			int finished = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.TABLE_TASK_COLUMN_FINISHED));
			String finishDate = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.TABLE_TASK_COLUMN_FINISHDATE));
			int order = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.TABLE_TASK_COLUMN_ORDER));
			task = new WordsTask(taskId, taskName, level, taskContent, finished, finishDate, order);
			wordsTasks.add(task);
		}
		return wordsTasks;
	}

}
