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
import com.squareup.picasso.Picasso;

/**
 * 公开课下精选课程推荐课程adapter
 * 
 * @date 2015/10/19
 * @author Iceberg
 * 
 */
public class CourseDetailsGridViewAdapter extends BaseAdapter {

	private List<CourseAlbum> courses;
	private LayoutInflater inflater;
	private Context mContext;

	public CourseDetailsGridViewAdapter(Context context, List<CourseAlbum> data) {
		courses = data;
		inflater = LayoutInflater.from(context);
		mContext = context;
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
		if(courses.get(position).getCourseImage()!=null&&!"".equals(courses.get(position).getCourseImage())){
			showImage(viewHolder.courseImage,courses.get(position).getCourseImage());
		}else{
			viewHolder.courseImage.setImageResource(R.drawable.default_course_poster_banner);
		}
		return convertView;
	}

	class ViewHolder {
		public TextView courseName;
		public ImageView courseImage;
	}
	public void showImage(ImageView mImageView, String url) {
		Picasso.with(mContext).load(url).into(mImageView);
	}
}
