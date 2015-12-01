package com.beyole.intelligentcampus.functions.life.fragment;

import com.beyole.intelligentcampus.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 详细课程对应的视频简介页，可查看课程详细介绍
 * 
 * @date 2015/12/1
 * @author Iceberg
 * 
 */
public class DetailsCourseIntroduceFragment extends Fragment {

	private View mView;
	private TextView videoDescription, videoTeacherName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.function_life_course_details_introduce_fragment_layout, container, false);
		initViews();
		initDatas();
		return mView;
	}

	private void initDatas() {
		Bundle bundle = getArguments();
		videoTeacherName.setText(bundle.getString("teacherName"));
		videoDescription.setText(bundle.getString("videoDescription"));
	}

	private void initViews() {
		videoDescription = (TextView) mView.findViewById(R.id.function_life_course_details_introduce_fragment_video_description_tv);
		videoTeacherName = (TextView) mView.findViewById(R.id.function_life_course_details_introduce_fragment_video_teachername_tv);
	}
}
