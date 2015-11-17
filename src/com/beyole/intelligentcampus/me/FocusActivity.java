package com.beyole.intelligentcampus.me;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FocusActivity extends Activity {

	private LinearLayout mTabBackLinearLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me_focus_layout);
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
		TextView tv = (TextView) findViewById(R.id.activity_focus_top).findViewById(R.id.id_top_banner_title);
		tv.setText("关注");
		mTabBackLinearLayout = (LinearLayout) findViewById(R.id.activity_focus_top).findViewById(R.id.id_me_fans_back_me_layout);
	}
}
