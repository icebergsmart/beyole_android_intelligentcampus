package com.beyole.intelligentcampus.functions.person;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 新鲜事
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class FreshTalk extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_fresh_talk);
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.person_fresh_talk_top).findViewById(R.id.id_top_banner_title);
		tv.setText("新鲜事");
	}
}
