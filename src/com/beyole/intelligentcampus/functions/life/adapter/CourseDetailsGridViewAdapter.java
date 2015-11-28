package com.beyole.intelligentcampus.functions.life.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyole.bean.Course;
import com.beyole.intelligentcampus.R;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class CourseDetailsGridViewAdapter extends BaseAdapter {

	private List<Course> courses;
	private LayoutInflater inflater;

	public CourseDetailsGridViewAdapter(Context context, List<Course> data) {
		courses = data;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return courses.size();
	}

	@Override
	public Object getItem(int position) {
		return courses.get(position);
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
			convertView = inflater.inflate(R.layout.function_delicate_course_details_item_layout, null);
			viewHolder.courseName = (TextView) convertView.findViewById(R.id.id_function_delicate_details_items_tv);
			viewHolder.courseImage = (ImageView) convertView.findViewById(R.id.id_function_delicate_details_items_iv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.courseName.setText(courses.get(position).getCourseName());
		return convertView;
	}

	class ViewHolder {
		public TextView courseName;
		public ImageView courseImage;
	}
}
