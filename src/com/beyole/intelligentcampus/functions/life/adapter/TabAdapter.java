package com.beyole.intelligentcampus.functions.life.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.beyole.intelligentcampus.functions.life.fragment.TabFragment;

public class TabAdapter extends FragmentPagerAdapter {

	public static String[] TITLES = new String[] { "精选课程", "所有课程" };

	public TabAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		TabFragment fragment = new TabFragment(arg0);
		return fragment;
	}

	@Override
	public int getCount() {
		return TITLES.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return TITLES[position];
	}

}
