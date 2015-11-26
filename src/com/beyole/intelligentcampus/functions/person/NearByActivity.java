package com.beyole.intelligentcampus.functions.person;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 周边
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class NearByActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_nearby_talk);
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.person_nearby_talk_top).findViewById(R.id.id_top_banner_title);
		tv.setText("周边");
	}
}
