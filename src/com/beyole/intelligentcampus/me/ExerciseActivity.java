package com.beyole.intelligentcampus.me;

import java.util.ArrayList;
import java.util.List;

import com.beyole.adapter.ExerciseAdapter;
import com.beyole.bean.UserExercise;
import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ExerciseActivity extends Activity {
	private LinearLayout mTabBackLinearLayout;
	private ListView mExerciseListView;
	private List<UserExercise> mUserExercises = new ArrayList<UserExercise>();
	private ExerciseAdapter mExerciseAdapter;

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
		mExerciseListView.setAdapter(mExerciseAdapter);
	}

	private void initViews() {
		int i = 0;
		UserExercise exercise = null;
		while (i < 5) {
			exercise = new UserExercise(i, i, "活动" + i, "活动描述" + i, 1, i, 1);
			mUserExercises.add(exercise);
			i++;
		}
		TextView tv = (TextView) findViewById(R.id.activity_exercise_top).findViewById(R.id.id_top_banner_title);
		tv.setText("活动");
		mTabBackLinearLayout = (LinearLayout) findViewById(R.id.activity_exercise_top).findViewById(R.id.id_me_fans_back_me_layout);
		mExerciseListView = (ListView) findViewById(R.id.activity_exercise_listview);
		mExerciseAdapter = new ExerciseAdapter(this, mUserExercises, mExerciseListView);
	}
}
