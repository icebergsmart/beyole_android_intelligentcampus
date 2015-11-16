package com.beyole.intelligentcampus.settings;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_help_layout);
		initViews();
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.settings_help_top).findViewById(R.id.id_top_banner_title);
		tv.setText("帮助");
	}
}
