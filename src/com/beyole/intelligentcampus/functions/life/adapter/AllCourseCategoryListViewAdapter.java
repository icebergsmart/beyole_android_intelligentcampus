package com.beyole.intelligentcampus.functions.life.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyole.bean.AllCourseCategory;
import com.beyole.bean.CourseCategory;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.fragment.CourseListActivity;
import com.beyole.intelligentcampus.functions.life.ui.NoScrollGridView;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class AllCourseCategoryListViewAdapter extends BaseAdapter {

	private List<CourseCategory> courseCategories;
	private LayoutInflater inflater;
	private Context mContext;

	public AllCourseCategoryListViewAdapter(Context context, List<CourseCategory> data) {
		courseCategories = data;
		inflater = LayoutInflater.from(context);
		mContext = context;
	}

	@Override
	public int getCount() {
		return courseCategories.size();
	}

	@Override
	public Object getItem(int position) {
		return courseCategories.get(position);
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
			convertView = inflater.inflate(R.layout.function_life_course_all_listview_item_layout, null);
			viewHolder.categoryName = (TextView) convertView.findViewById(R.id.function_life_all_course_item_description);
			viewHolder.gridView = (NoScrollGridView) convertView.findViewById(R.id.function_life_all_course_gridview);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		final String categoryName=courseCategories.get(position).getCategoryName();
		viewHolder.categoryName.setText(categoryName);
		final List<AllCourseCategory> categoryInfo = courseCategories.get(position).getSubCategoryInfo();
		viewHolder.gridView.setAdapter(new AllCourseCategoryGridViewAdapter(mContext, categoryInfo));
		viewHolder.gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(mContext, CourseListActivity.class);
				ArrayList list = new ArrayList();
				list.add(categoryInfo);
				Bundle bundle = new Bundle();
				bundle.putParcelableArrayList("categoryList", list);
				bundle.putInt("clickPosition", position);
				bundle.putString("categoryName", categoryName);
				intent.putExtras(bundle);
				mContext.startActivity(intent);
			}
		});
		return convertView;
	}

	class ViewHolder {
		public TextView categoryName;
		public ImageView categoryIcon;
		public NoScrollGridView gridView;
	}
}