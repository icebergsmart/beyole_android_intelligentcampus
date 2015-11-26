package com.beyole.intelligentcampus.functions.life;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CourseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.life_course);
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_life_course_top).findViewById(R.id.id_top_banner_title);
		tv.setText("公开课");
	}
}
