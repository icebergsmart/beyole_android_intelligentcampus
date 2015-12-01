package com.beyole.intelligentcampus.functions.life;

import io.vov.vitamio.LibsChecker;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.adapter.FragmentAdapter;
import com.beyole.util.indicator.PageIndicator;

public class CourseActivity extends FragmentActivity {

	private PageIndicator mPageIndicator;
	private ViewPager mViewPager;
	private FragmentAdapter mFragmentAdapter;
	private RadioButton delicateBtn, allBtn;

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
		delicateBtn = (RadioButton) findViewById(R.id.activity_life_course_btn_delicate);
		allBtn = (RadioButton) findViewById(R.id.activity_life_course_btn_all);
		mViewPager = (ViewPager) findViewById(R.id.activity_life_course_pager);
		mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
		mViewPager.setAdapter(mFragmentAdapter);
		mPageIndicator = (PageIndicator) findViewById(R.id.activity_life_course_pager_indicator);
		mPageIndicator.setViewPager(mViewPager);
		mPageIndicator.setOnPageChangeListener(new MyPageChangeListener());
		MyCourseOnClickListener listener = new MyCourseOnClickListener();
		TextView tv = (TextView) findViewById(R.id.id_life_course_top).findViewById(R.id.id_top_banner_title);
		tv.setText("公开课");
		allBtn.setOnClickListener(listener);
		delicateBtn.setOnClickListener(listener);
	}

	class MyCourseOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.activity_life_course_btn_delicate:
				mViewPager.setCurrentItem(0);
				break;
			case R.id.activity_life_course_btn_all:
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
				delicateBtn.setTextColor(0XFF00B285);
				allBtn.setTextColor(0XFF000000);
			} else if (pos == 1) {
				delicateBtn.setTextColor(0XFF000000);
				allBtn.setTextColor(0XFF00B285);
			}
		}
	}
}
