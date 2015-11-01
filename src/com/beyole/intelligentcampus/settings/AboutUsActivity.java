package com.beyole.intelligentcampus.settings;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutUsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_aboutus);
		TextView tv = (TextView) findViewById(R.id.id_settings_abouts_top).findViewById(R.id.id_top_banner_title);
		tv.setText("关于我们");
	}

}
