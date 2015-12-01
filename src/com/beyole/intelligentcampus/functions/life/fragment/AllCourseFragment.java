package com.beyole.intelligentcampus.functions.life.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.beyole.bean.AllCourseCategory;
import com.beyole.bean.CourseCategory;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.adapter.AllCourseCategoryListViewAdapter;
import com.beyole.intelligentcampus.functions.life.ui.NoScrollListView;

/**
 * 公开课下所有课程的fragment
 * 
 * @date 2015/12/1
 * @author Iceberg
 */
public class AllCourseFragment extends Fragment {

	private View mView;
	private NoScrollListView mListView;
	private AllCourseCategoryListViewAdapter mAdapter;
	private List<CourseCategory> data = new ArrayList<CourseCategory>();
	private ScrollView mContainer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.function_life_course_all_fragment_layout, container, false);
		initViews();
		return mView;
	}

	private void initViews() {
		mListView = (NoScrollListView) mView.findViewById(R.id.function_all_course_main_listview);
		mContainer = (ScrollView) mView.findViewById(R.id.function_all_course_container);
		CourseCategory category = null;
		for (int i = 0; i < 4; i++) {
			AllCourseCategory subCategoryInfo = null;
			List<AllCourseCategory> list = new ArrayList<AllCourseCategory>();
			for (int j = 0; j < 8; j++) {
				subCategoryInfo = new AllCourseCategory(i, "子栏目" + i + j, j + i);
				list.add(subCategoryInfo);
			}
			category = new CourseCategory(i, i, list, "计算机" + i, null);
			data.add(category);
		}
		mAdapter = new AllCourseCategoryListViewAdapter(getActivity(), data);
		mListView.setAdapter(mAdapter);
	}
}
