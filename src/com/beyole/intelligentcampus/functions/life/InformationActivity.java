package com.beyole.intelligentcampus.functions.life;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.adapter.InformationFragmentAdapter;
import com.beyole.util.indicator.PageIndicator;

public class InformationActivity extends FragmentActivity {

	private PageIndicator mPageIndicator;
	private ViewPager mViewPager;
	private InformationFragmentAdapter mFragmentAdapter;
	private RadioButton delicateBtn, allBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function_person_information_layout);
		initViews();
		initEvents();
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_function_information_item_top).findViewById(R.id.id_top_banner_title);
		tv.setText("校园资讯");
		delicateBtn = (RadioButton) findViewById(R.id.activity_life_information_btn_delicate);
		allBtn = (RadioButton) findViewById(R.id.activity_life_information_btn_all);
		mViewPager = (ViewPager) findViewById(R.id.activity_life_information_pager);
		mFragmentAdapter = new InformationFragmentAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mFragmentAdapter);
		mPageIndicator = (PageIndicator) findViewById(R.id.activity_life_information_pager_indicator);
		mPageIndicator.setViewPager(mViewPager);
		mPageIndicator.setOnPageChangeListener(new MyPageChangeListener());
		MyCourseOnClickListener listener = new MyCourseOnClickListener();
		allBtn.setOnClickListener(listener);
		delicateBtn.setOnClickListener(listener);
	}

	private void initEvents() {

	}

	class MyCourseOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.activity_life_information_btn_delicate:
				mViewPager.setCurrentItem(0);
				break;
			case R.id.activity_life_information_btn_all:
				mViewPager.setCurrentItem(1);
				break;
			}
		}

	}

	class MyPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int pos) {
			if (pos == 0) {
				delicateBtn.setTextColor(0XFF106184);
				allBtn.setTextColor(0XFF000000);
			} else if (pos == 1) {
				delicateBtn.setTextColor(0XFF000000);
				allBtn.setTextColor(0XFF106184);
			}
		}
	}
}
