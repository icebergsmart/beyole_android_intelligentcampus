package com.beyole.intelligentcampus.functions.life.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.beyole.bean.Course;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.ui.SimpleViewPagerIndicator;

/**
 * 视频详情主Fragment跳转页，设置详细的简介和视频列表fragment
 * 
 * @date 2015/12/1
 * @author Iceberg
 * 
 */
public class DetailsCourseActivity extends FragmentActivity {
	private String[] mTitles = new String[] { "简介", "目录" };
	private SimpleViewPagerIndicator mIndicator;
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private Fragment[] mAllFragments = new Fragment[mTitles.length];
	private Course mCourse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function_life_details_course_re_layout);
		initViews();
		initDatas();
		initEvents();
	}

	private void initEvents() {
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				mIndicator.scroll(position, positionOffset);
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

	}

	private void initDatas() {
		mIndicator.setTitles(mTitles);
		mAllFragments[0] = new DetailsCourseIntroduceFragment();
		Bundle bundle1 = new Bundle();
		bundle1.putString("teacherName", mCourse.getTeacherName());
		bundle1.putString("videoDescription", mCourse.getDescription());
		mAllFragments[0].setArguments(bundle1);
		mAllFragments[1] = new DetailsCourseSheetFragment();
		Bundle bundle2 = new Bundle();
		ArrayList list = new ArrayList();
		list.add(mCourse.getVideoList());
		bundle2.putParcelableArrayList("videoList", list);
		mAllFragments[1].setArguments(bundle2);
		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			@Override
			public int getCount() {
				return mTitles.length;
			}

			@Override
			public Fragment getItem(int position) {
				return mAllFragments[position];
			}

		};

		mViewPager.setAdapter(mAdapter);
		mViewPager.setCurrentItem(0);
	}

	private void initViews() {
		mIndicator = (SimpleViewPagerIndicator) findViewById(R.id.id_stickynavlayout_indicator);
		mIndicator.setIndicatorColor(0XFF00B285);
		mViewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
		mCourse = (Course) getIntent().getSerializableExtra("courseInfo");
	}
}
