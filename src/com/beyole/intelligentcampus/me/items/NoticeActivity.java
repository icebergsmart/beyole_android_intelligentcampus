package com.beyole.intelligentcampus.me.items;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 通知
 * 
 * @date 2015//11/26
 * @author Iceberg
 * 
 */
public class NoticeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_items_notice);
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_me_items_notice_top).findViewById(R.id.id_top_banner_title);
		tv.setText("通知");
	}
}
