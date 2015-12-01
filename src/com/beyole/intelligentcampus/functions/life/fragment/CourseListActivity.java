package com.beyole.intelligentcampus.functions.life.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.beyole.bean.AllCourseCategory;
import com.beyole.bean.Course;
import com.beyole.bean.VideoDetails;
import com.beyole.bean.VideoInfo;
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
	private TextView mTopTitle;
	private List<Course> courses = new ArrayList<Course>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function_life_course_details_list_layout);
		initData();
		initViews();
	}

	private void initData() {
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

	private void initViews() {
		mGridView = (NoScrollGridView) findViewById(R.id.function_life_details_list_gridview);
		mListView = (NoScrollListView) findViewById(R.id.function_life_details_list_listview);
		mTopTitle = (TextView) findViewById(R.id.id_top_banner_title);
		mTopTitle.setText(getIntent().getStringExtra("categoryName"));
		AllCourseCategory category = null;
		ArrayList list = getIntent().getParcelableArrayListExtra("categoryList");
		// 判断是否是精选栏目中点进来的推荐栏目
		if (getIntent().getIntExtra("isRecommend", 0) != 1) {
			data.add(new AllCourseCategory(0, "全部", 0));
			data.addAll((List<AllCourseCategory>) list.get(0));
			mAdapter = new SubCourseCategoryGridViewAdapter(this, data);
			mAdapter.setClickItem(getIntent().getIntExtra("clickPosition", 0) + 1);
		} else {
			data.addAll((List<AllCourseCategory>) list.get(0));
			mAdapter = new SubCourseCategoryGridViewAdapter(this, data);
			mAdapter.setClickItem(getIntent().getIntExtra("clickPosition", 0));
		}
		mGridView.setAdapter(mAdapter);
		mListViewAdapter = new CourseVideoListViewAdapter(this, courses);
		mListView.setAdapter(mListViewAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(CourseListActivity.this, DetailsCourseActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("courseInfo", courses.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}
}
