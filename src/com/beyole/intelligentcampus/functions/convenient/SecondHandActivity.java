package com.beyole.intelligentcampus.functions.convenient;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 二手市场
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class SecondHandActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_secondhand);
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_secondhand_top).findViewById(R.id.id_top_banner_title);
		tv.setText("二手市场");
	}
}
