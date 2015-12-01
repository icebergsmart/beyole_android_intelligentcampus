package com.beyole.intelligentcampus.functions.life.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.beyole.bean.Course;
import com.beyole.bean.VideoInfo;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.adapter.CourseCategoryGridViewAdapter;
import com.beyole.intelligentcampus.functions.life.adapter.CourseDetailsGridViewAdapter;
import com.beyole.intelligentcampus.functions.life.ui.NoScrollGridView;

/**
 * 公开课下精选课程fragment详情页
 * 
 * @date 2015/12/1
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
		mDetailsGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), DetailsCourseActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("courseInfo", courses.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
			}

		});
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
			List<VideoInfo> videoList = new ArrayList<VideoInfo>();
			VideoInfo videoInfo = null;
			for (int j = 0; j < 9; j++) {
				videoInfo = new VideoInfo(j + i, j + i, "第" + (j + 1) + "课如何做好" + i + "个程序员" + j, "http://www.baidu.com", j + i);
				videoList.add(videoInfo);
			}
			course = new Course(i, i, i, "程序员的自我修养" + i, null, "薛佳伟" + i, "这是由薛佳伟" + i + "发布的视频，需要一定的android" + i + "的基础哦。", videoList);
			courses.add(course);
		}
	}
}
