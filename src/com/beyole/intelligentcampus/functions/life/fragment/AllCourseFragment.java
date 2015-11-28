package com.beyole.intelligentcampus.functions.life.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beyole.intelligentcampus.R;

/**
 * 会话列表
 * 
 * @author JerSuen
 */
public class AllCourseFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.function_life_course_all_fragment_layout, container, false);
	}
}
