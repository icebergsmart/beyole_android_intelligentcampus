package com.beyole.intelligentcampus.functions.life.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.beyole.intelligentcampus.functions.life.fragment.AllCourseFragment;
import com.beyole.intelligentcampus.functions.life.fragment.DelicateCourseFragment;

/**
 * 主界面碎片适配器
 * 
 * @author JerSuen
 */
public class FragmentAdapter extends FragmentPagerAdapter {


	public FragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public Fragment getItem(int position) {
		Fragment fragment = null;

		switch (position) {
		case 0:
			fragment = new DelicateCourseFragment();
			break;
		case 1:
			fragment = new AllCourseFragment();
			break;
		}
		return fragment;
	}

	public int getCount() {
		return 2;
	}
}
