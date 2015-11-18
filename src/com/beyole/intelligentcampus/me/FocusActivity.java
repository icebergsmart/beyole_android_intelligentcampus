package com.beyole.intelligentcampus.me;

import java.util.ArrayList;
import java.util.List;

import com.beyole.adapter.FansAdapter;
import com.beyole.adapter.FocusAdapter;
import com.beyole.bean.UserFans;
import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class FocusActivity extends Activity {

	private LinearLayout mTabBackLinearLayout;
	private ListView mFocusListView;
	private FocusAdapter mFocusAdapter;
	private List<UserFans> mUserFocusList = new ArrayList<UserFans>();

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
		mFocusListView.setAdapter(mFocusAdapter);
	}

	private void initViews() {
		int i = 0;
		UserFans fans = null;
		while (i < 5) {
			fans = new UserFans(i, i, i, "用户" + i, "这是用户描述" + i, Math.random() > 0.5 ? 0 : 1, "http://h.hiphotos.baidu.com/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/sign=ffcd92672bf5e0fefa1581533d095fcd/a686c9177f3e6709c6efd8813dc79f3df9dc55e4.jpg");
			mUserFocusList.add(fans);
			i++;
		}
		TextView tv = (TextView) findViewById(R.id.activity_focus_top).findViewById(R.id.id_top_banner_title);
		tv.setText("关注");
		mTabBackLinearLayout = (LinearLayout) findViewById(R.id.activity_focus_top).findViewById(R.id.id_me_fans_back_me_layout);
		mFocusListView = (ListView) findViewById(R.id.activity_focus_listview);
		mFocusAdapter = new FocusAdapter(this, mUserFocusList, mFocusListView);
	}
}
