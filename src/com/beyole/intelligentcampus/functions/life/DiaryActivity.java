package com.beyole.intelligentcampus.functions.life;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.beyole.intelligentcampus.R;

/**
 * 新生攻略
 * 
 * @author Iceberg
 * 
 */
public class DiaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.life_diary);
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_life_diary_top).findViewById(R.id.id_top_banner_title);
		tv.setText("新生攻略");
	}
	
}
