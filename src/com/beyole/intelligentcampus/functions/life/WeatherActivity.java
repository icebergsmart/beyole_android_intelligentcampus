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
import com.beyole.intelligentcampus.functions.life.adapter.WordsFragmentAdapter;
import com.beyole.util.indicator.PageIndicator;
import com.beyole.view.MyImageView;

/**
 * 记单词页面
 * 
 * @date 2015/12/06
 * @author Iceberg/
 * 
 */
public class WeatherActivity extends FragmentActivity {

	private RadioButton mDelicateBtn, mAllWordsBtn;
	private PageIndicator mPageIndicator;
	private ViewPager mViewPager;
	private WordsFragmentAdapter mAdapter;
	private MyImageView mCetFourImageView, mCetSixImageView, mTemFourImageView, mTemEightImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.life_weather);
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_life_weather_top).findViewById(R.id.id_top_banner_title);
		tv.setText("记单词");
		mDelicateBtn = (RadioButton) findViewById(R.id.activity_life_words_btn_delicate);
		mAllWordsBtn = (RadioButton) findViewById(R.id.activity_life_words_btn_all);
		mPageIndicator = (PageIndicator) findViewById(R.id.activity_life_words_pager_indicator);
		mAdapter = new WordsFragmentAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.activity_life_words_pager);
		mViewPager.setAdapter(mAdapter);
		mPageIndicator.setViewPager(mViewPager);
		mPageIndicator.setOnPageChangeListener(new MyPageChangeListener());
		MyWordsOnClickListener listener = new MyWordsOnClickListener();
		mDelicateBtn.setOnClickListener(listener);
		mAllWordsBtn.setOnClickListener(listener);
		
	}

	class MyWordsOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.activity_life_words_btn_delicate:
				mViewPager.setCurrentItem(0);
				break;
			case R.id.activity_life_words_btn_all:
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
				mDelicateBtn.setTextColor(0XFFFFFFFF);
				mAllWordsBtn.setTextColor(0X66000000);
			} else if (pos == 1) {
				mDelicateBtn.setTextColor(0X66000000);
				mAllWordsBtn.setTextColor(0XFFFFFFFF);
			}
		}
	}

}
