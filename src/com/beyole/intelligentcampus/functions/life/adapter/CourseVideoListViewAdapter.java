package com.beyole.intelligentcampus.functions.life.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyole.bean.CourseAlbum;
import com.beyole.intelligentcampus.R;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class CourseVideoListViewAdapter extends BaseAdapter {

	private List<CourseAlbum> courseAlbums;
	private LayoutInflater inflater;
	private Context mContext;

	public CourseVideoListViewAdapter(Context context, List<CourseAlbum> data) {
		courseAlbums = data;
		inflater = LayoutInflater.from(context);
		mContext = context;
	}

	@Override
	public int getCount() {
		return courseAlbums.size();
	}

	@Override
	public Object getItem(int position) {
		return courseAlbums.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.function_life_details_course_video_listview_item_layout, null);
			viewHolder.teacherName = (TextView) convertView.findViewById(R.id.id_function_life_course_details_video_teacher_tv);
			viewHolder.videoName = (TextView) convertView.findViewById(R.id.id_function_life_course_details_video_title_tv);
			viewHolder.videoImageUrl = (ImageView) convertView.findViewById(R.id.id_function_life_details_course_video_listview_iv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.teacherName.setText(courseAlbums.get(position).getTeacherName());
		viewHolder.videoName.setText(courseAlbums.get(position).getCourseName());
		return convertView;
	}

	class ViewHolder {
		public TextView videoName;
		public ImageView videoImageUrl;
		public TextView teacherName;
	}
}
