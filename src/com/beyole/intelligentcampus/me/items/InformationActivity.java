package com.beyole.intelligentcampus.me.items;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 我的资料
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class InformationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_items_information);
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_me_items_information_top).findViewById(R.id.id_top_banner_title);
		tv.setText("我的资料");
	}
}
