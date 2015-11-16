package com.beyole.intelligentcampus.settings;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class FunctionIntroduceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_functionintroduction_layout);
		initViews();
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.settings_functionintroduction_top).findViewById(R.id.id_top_banner_title);
		tv.setText("功能介绍");
	}
}
