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

import com.beyole.bean.AllCourseCategory;
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
	private List<Course> courses = new ArrayList<Course>();
	private List<AllCourseCategory> dataSet = new ArrayList<AllCourseCategory>();

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
		mAdapter = new CourseCategoryGridViewAdapter(getActivity(), dataSet);
		mDetailsAdapter = new CourseDetailsGridViewAdapter(getActivity(), courses);
		mGridView.setAdapter(mAdapter);
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), CourseListActivity.class);
				ArrayList list = new ArrayList();
				list.add(dataSet);
				Bundle bundle = new Bundle();
				bundle.putParcelableArrayList("categoryList", list);
				bundle.putInt("clickPosition", position);
				bundle.putString("categoryName", "推荐栏目");
				bundle.putInt("isRecommend", 1);
				intent.putExtras(bundle);
				getActivity().startActivity(intent);
			}
		});
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
		AllCourseCategory subCategoryInfo = null;
		for (int i = 0; i < 8; i++) {
			subCategoryInfo = new AllCourseCategory(i, "推荐栏目" + +i, i);
			dataSet.add(subCategoryInfo);
		}
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
