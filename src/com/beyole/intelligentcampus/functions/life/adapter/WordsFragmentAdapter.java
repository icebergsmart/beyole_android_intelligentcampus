package com.beyole.intelligentcampus.functions.life.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.beyole.intelligentcampus.functions.life.fragment.WordPractiseFragment;
import com.beyole.intelligentcampus.functions.life.fragment.WordSearchFragment;

/**
 * 记单词适配器
 * 
 * @author Iceberg
 */
public class WordsFragmentAdapter extends FragmentPagerAdapter {

	public WordsFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	public Fragment getItem(int position) {
		Fragment fragment = null;

		switch (position) {
		case 0:
			fragment = new WordPractiseFragment();
			break;
		case 1:
			fragment = new WordSearchFragment();
			break;
		}
		return fragment;
	}

	public int getCount() {
		return 2;
	}
}
