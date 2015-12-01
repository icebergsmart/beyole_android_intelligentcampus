package com.beyole.intelligentcampus.functions.life.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.beyole.bean.AllCourseCategory;
import com.beyole.bean.VideoDetails;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.adapter.CourseVideoListViewAdapter;
import com.beyole.intelligentcampus.functions.life.adapter.SubCourseCategoryGridViewAdapter;
import com.beyole.intelligentcampus.functions.life.ui.NoScrollGridView;
import com.beyole.intelligentcampus.functions.life.ui.NoScrollListView;

/**
 * 详细课程栏目下的视频列表，有分类页面（可切换）
 * 
 * @date 2015/12/1
 * @author Iceberg
 * 
 */
public class CourseListActivity extends Activity {

	private NoScrollGridView mGridView;
	private SubCourseCategoryGridViewAdapter mAdapter;
	private List<AllCourseCategory> data = new ArrayList<AllCourseCategory>();
	private NoScrollListView mListView;
	private CourseVideoListViewAdapter mListViewAdapter;
	private List<VideoDetails> listDatas = new ArrayList<VideoDetails>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function_life_course_details_list_layout);
		initViews();
	}

	private void initViews() {
		mGridView = (NoScrollGridView) findViewById(R.id.function_life_details_list_gridview);
		mListView = (NoScrollListView) findViewById(R.id.function_life_details_list_listview);
		AllCourseCategory category = null;
		data.add(new AllCourseCategory(0, "全部", 0));
		for (int i = 0; i < 7; i++) {
			category = new AllCourseCategory(i, "计算机" + i, i);
			data.add(category);
		}
		mAdapter = new SubCourseCategoryGridViewAdapter(this, data);
		mGridView.setAdapter(mAdapter);
		VideoDetails details = null;
		for (int i = 0; i < 6; i++) {
			details = new VideoDetails(i, i, null, "这是一个测试的视频名称" + i, "薛佳伟" + i);
			listDatas.add(details);
		}
		mListViewAdapter = new CourseVideoListViewAdapter(this, listDatas);
		mListView.setAdapter(mListViewAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(CourseListActivity.this, DetailsCourseActivity.class);
				startActivity(intent);
			}
		});
	}
}
