package com.beyole.intelligentcampus.functions.life.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.beyole.intelligentcampus.functions.life.fragment.CampusInformationFragment;
import com.beyole.intelligentcampus.functions.life.fragment.OutsideInformationFragment;

/**
 * 主界面碎片适配器
 * 
 * @author Iceberg
 */
public class InformationFragmentAdapter extends FragmentPagerAdapter {

	public InformationFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public Fragment getItem(int position) {
		Fragment fragment = null;

		switch (position) {
		case 0:
			fragment = new CampusInformationFragment();
			break;
		case 1:
			fragment = new OutsideInformationFragment();
			break;
		}
		return fragment;
	}

	public int getCount() {
		return 2;
	}
}
