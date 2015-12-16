package com.beyole.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.beyole.intelligentcampus.R;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	// sd卡下的目录
	private final String DATABASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/db_englishword";
	// 数据库名
	private final String DATABASE_FILENAME = "englishword.db";

	// 表名
	public static final String TABLE_WORDS = "words";
	public static final String TABLE_TASK = "task";
	public static final String TABLE_RESULT = "result";
	// task表所有列
	public static final String TABLE_TASK_COLUMN_TASKTYPE = "taskType";
	public static final String TABLE_TASK_COLUMN_ID = "ID";
	public static final String TABLE_TASK_COLUMN_CONTENT = "content";
	public static final String TABLE_TASK_COLUMN_TASKNAME = "taskName";
	public static final String TABLE_TASK_COLUMN_FINISHED = "finished";
	public static final String TABLE_TASK_COLUMN_FINISHDATE = "finishDate";
	public static final String TABLE_TASK_COLUMN_ORDER = "order";
	// words表所有列
	public static final String TABLE_WORDS_COLUMN_ID = "ID";
	public static final String TABLE_WORDS_COLUMN_WORD = "Word";
	public static final String TABLE_WORDS_COLUMN_PASTTENSE = "GQS";
	public static final String TABLE_WORDS_COLUMN_PASTPARTICIPLES = "GQFC";
	public static final String TABLE_WORDS_COLUMN_PRESENTPARTICIPLES = "XZFC";
	public static final String TABLE_WORDS_COLUMN_COMPLEX = "FS";
	public static final String TABLE_WORDS_COLUMN_MEANING = "meaning";
	public static final String TABLE_WORDS_COLUMN_EXAMPLE = "lx";

	private Context mContext;

	public MyDatabaseHelper(Context context) {
		super(context, "english", null, 1);
		mContext = context;
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	public SQLiteDatabase openDatabase() {
		try {
			String databaseFilename = DATABASE_PATH + "/" + DATABASE_FILENAME;
			File dir = new File(DATABASE_PATH);
			// 判断SD卡下是否存在存放数据库的目录，如果不存在，新建目录
			if (!dir.exists()) {
				dir.mkdir();
				Log.i("ReleaseDataBase", "dir made:" + DATABASE_PATH);
			} else {
				Log.i("ReleaseDataBase", "dir exist:" + DATABASE_PATH);
			}
			// 如果数据库已经在SD卡的目录下存在，那么不需要重新创建，否则创建文件，并拷贝/raw下的数据库文件
			if (!(new File(databaseFilename)).exists()) {
				Log.i("ReleaseDataBase", "file not exist:" + databaseFilename);
				// /res/raw数据库作为输出流
				InputStream is = mContext.getResources().openRawResource(R.raw.englishword);
				// 测试用
				int size = is.available();
				Log.i("ReleaseDataBase", "DATABASE_SIZE:" + 1);
				Log.i("ReleaseDataBase", "count:" + 0);
				// 用于存放数据库信息的数据流
				FileOutputStream fos = new FileOutputStream(databaseFilename);
				byte[] buffer = new byte[8192];
				int count = 0;
				Log.i("ReleaseDataBase", "count:" + count);
				// 把数据写入SD卡目录下
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count);
				}
				fos.flush();
				fos.close();
				is.close();
			}
			// 实例化sd卡上得数据库，database作为返回值，是后面所有插入，删除，查询操作的借口。
			SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
			return database;
		} catch (Exception e) {
		}
		return null;
	}

}
