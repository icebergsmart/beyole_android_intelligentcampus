package com.beyole.intelligentcampus.functions.life.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyole.bean.CourseVideo;
import com.beyole.bean.VideoInfo;
import com.beyole.intelligentcampus.R;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class CourseDetailsSheetListViewAdapter extends BaseAdapter {

	private List<CourseVideo> courseVideos;
	private LayoutInflater inflater;
	private Context mContext;

	public CourseDetailsSheetListViewAdapter(Context context, List<CourseVideo> data) {
		courseVideos = data;
		inflater = LayoutInflater.from(context);
		mContext = context;
	}

	@Override
	public int getCount() {
		return courseVideos.size();
	}

	@Override
	public Object getItem(int position) {
		return courseVideos.get(position);
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
			convertView = inflater.inflate(R.layout.function_life_course_details_sheet_item_layout, null);
			viewHolder.videoName = (TextView) convertView.findViewById(R.id.id_function_life_course_details_category_item_title_tv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.videoName.setText(courseVideos.get(position).getVideoName());
		return convertView;
	}

	class ViewHolder {
		public TextView videoName;
	}
}
