package com.beyole.intelligentcampus.functions.life.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beyole.intelligentcampus.R;

@SuppressLint("ValidFragment")
public class TabFragment extends Fragment {
	private int pos;

	@SuppressLint("ValidFragment")
	public TabFragment(int pos) {
		this.pos = pos;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.function_life_course_delicate_fragment_layout, container, false);
		return view;
	}
}
