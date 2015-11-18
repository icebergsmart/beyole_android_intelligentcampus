package com.beyole.intelligentcampus.me;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.beyole.adapter.FansAdapter;
import com.beyole.bean.UserFans;
import com.beyole.intelligentcampus.R;

public class FansActivity extends Activity {

	private LinearLayout mFansTopBackLine;
	private ListView mFansListView;
	private FansAdapter mFansAdapter;
	private List<UserFans> mUserFansList = new ArrayList<UserFans>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me_fans_layout);
		initViews();
		initEvents();
	}

	private void initEvents() {
		MyFansActivityOnClickListener listener = new MyFansActivityOnClickListener();
		mFansTopBackLine.setOnClickListener(listener);
		mFansListView.setAdapter(mFansAdapter);
	}

	private void initViews() {
		int i = 0;
		UserFans fans = null;
		while (i < 5) {
			fans = new UserFans(i, i, i, "用户" + i, "这是用户描述" + i, Math.random() > 0.5 ? 0 : 1, "http://h.hiphotos.baidu.com/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=ffcd92672bf5e0fefa1581533d095fcd/a686c9177f3e6709c6efd8813dc79f3df9dc55e4.jpg");
			mUserFansList.add(fans);
			i++;
		}
		mFansTopBackLine = (LinearLayout) findViewById(R.id.id_me_fans_back_me_layout);
		mFansListView = (ListView) findViewById(R.id.activity_fans_listview);
		mFansAdapter = new FansAdapter(this, mUserFansList, mFansListView);
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
