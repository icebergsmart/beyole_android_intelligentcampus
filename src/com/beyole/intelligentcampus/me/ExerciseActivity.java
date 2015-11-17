package com.beyole.intelligentcampus.me;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExerciseActivity extends Activity {
	private LinearLayout mTabBackLinearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me_exercise_layout);
		initViews();
		initEvents();
	}

	private void initEvents() {
		mTabBackLinearLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.activity_exercise_top).findViewById(R.id.id_top_banner_title);
		tv.setText("活动");
		mTabBackLinearLayout = (LinearLayout) findViewById(R.id.activity_exercise_top).findViewById(R.id.id_me_fans_back_me_layout);
		
	}
}
