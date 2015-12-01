package com.beyole.intelligentcampus.functions.life.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.beyole.bean.CourseSheetItem;
import com.beyole.bean.VideoInfo;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.adapter.CourseDetailsSheetListViewAdapter;

/**
 * 详细课程对应的视频url列表页，可进行播放视频
 * 
 * @date 2015/12/1
 * @author Iceberg
 * 
 */
public class DetailsCourseSheetFragment extends Fragment {

	private View mView;
	private ListView mVideoSheet;
	private CourseDetailsSheetListViewAdapter mAdapter;
	private List<CourseSheetItem> data = new ArrayList<CourseSheetItem>();
	private List<VideoInfo> dataSet = new ArrayList<VideoInfo>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.function_life_course_details_video_sheet_layout, container, false);
		initData();
		initViews();
		return mView;
	}

	private void initData() {
		CourseSheetItem courseSheetItem = null;
		for (int i = 0; i < 9; i++) {
			courseSheetItem = new CourseSheetItem(i, i, "第" + i + "课：Java程序设计的复用性" + i, null);
			data.add(courseSheetItem);
		}
	}

	private void initViews() {
		Bundle bundle = getArguments();
		ArrayList list = bundle.getParcelableArrayList("videoList");
		dataSet = (List<VideoInfo>) list.get(0);
		mAdapter = new CourseDetailsSheetListViewAdapter(getActivity(), dataSet);
		mVideoSheet = (ListView) mView.findViewById(R.id.id_stickynavlayout_innerscrollview);
		mVideoSheet.setAdapter(mAdapter);
	}
}
