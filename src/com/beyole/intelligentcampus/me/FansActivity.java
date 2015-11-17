package com.beyole.intelligentcampus.me;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.beyole.intelligentcampus.R;

public class FansActivity extends Activity {

	private LinearLayout mFansTopBackLine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me_fans_layout);
		initViews();
		initEvents();
	}

	private void initEvents() {
		MyFansActivityOnClickListener listener=new MyFansActivityOnClickListener();
		mFansTopBackLine.setOnClickListener(listener);
	}

	private void initViews() {
		mFansTopBackLine = (LinearLayout) findViewById(R.id.id_me_fans_back_me_layout);
	}

	class MyFansActivityOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.id_me_fans_back_me_layout:
				finish();
				break;
			}
		}

	}
}
