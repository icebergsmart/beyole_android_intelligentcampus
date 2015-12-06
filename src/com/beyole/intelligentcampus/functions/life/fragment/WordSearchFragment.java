package com.beyole.intelligentcampus.functions.life.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beyole.intelligentcampus.R;

public class WordSearchFragment extends Fragment {

	private View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.function_life_search_words_layout, container, false);
		initViews();
		initEvents();
		return mView;
	}

	private void initEvents() {

	}

	private void initViews() {
	}

	private void initDatas() {
	}
}
