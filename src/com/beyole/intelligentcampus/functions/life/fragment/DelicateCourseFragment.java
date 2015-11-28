package com.beyole.intelligentcampus.functions.life.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.beyole.bean.Course;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.adapter.CourseCategoryGridViewAdapter;
import com.beyole.intelligentcampus.functions.life.adapter.CourseDetailsGridViewAdapter;
import com.beyole.intelligentcampus.functions.life.ui.NoScrollGridView;

/**
 * 精选课程
 * 
 * @author Iceberg
 * 
 */
public class DelicateCourseFragment extends Fragment {
	private View mView;
	private NoScrollGridView mGridView;
	private NoScrollGridView mDetailsGridView;
	private CourseCategoryGridViewAdapter mAdapter;
	private CourseDetailsGridViewAdapter mDetailsAdapter;
	private List<String> data = new ArrayList<String>();
	private List<Course> courses = new ArrayList<Course>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.function_life_course_delicate_fragment_layout, container, false);
		initViews();
		return mView;
	}

	private void initViews() {
		mGridView = (NoScrollGridView) mView.findViewById(R.id.id_function_delicate_course_gridview);
		mDetailsGridView = (NoScrollGridView) mView.findViewById(R.id.id_function_delicate_course_details_gridview);
		initDatas();
		mAdapter = new CourseCategoryGridViewAdapter(getActivity(), data);
		mDetailsAdapter = new CourseDetailsGridViewAdapter(getActivity(), courses);
		mGridView.setAdapter(mAdapter);
		mDetailsGridView.setAdapter(mDetailsAdapter);
	}

	private void initDatas() {
		data.add("编程语言");
		data.add("摄影摄像");
		data.add("办公技能");
		data.add("求职应聘");
		data.add("实用英语");
		data.add("财税投资");
		data.add("亲子育儿");
		data.add("更多...");
		Course course = null;
		for (int i = 0; i < 6; i++) {
			course = new Course(i, i, i, "如何学好编程技巧" + i, null, null, null);
			courses.add(course);
		}
	}
}
