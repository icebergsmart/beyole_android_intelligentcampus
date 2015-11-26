package com.beyole.intelligentcampus.functions.person;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ExerciseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_exercise_talk);
		initViews();
		initEvents();

	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_person_exercise_talk_top).findViewById(R.id.id_top_banner_title);
		tv.setText("活动");
	}
}
