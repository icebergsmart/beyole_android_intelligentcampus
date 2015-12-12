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

import com.beyole.bean.AllCourseCategoryItem;
import com.beyole.bean.CourseCategoryItem;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.fragment.CourseListActivity;
import com.beyole.intelligentcampus.functions.life.ui.NoScrollGridView;

/**
 * 公开课下全部课程列表适配器
 * 
 * @date 2015/10/19
 * @author Iceberg
 * 
 */
public class AllCourseCategoryListViewAdapter extends BaseAdapter {

	private List<AllCourseCategoryItem> allCourseCategoryItems;
	private LayoutInflater inflater;
	private Context mContext;

	public AllCourseCategoryListViewAdapter(Context context, List<AllCourseCategoryItem> data) {
		allCourseCategoryItems = data;
		inflater = LayoutInflater.from(context);
		mContext = context;
	}

	@Override
	public int getCount() {
		return allCourseCategoryItems.size();
	}

	@Override
	public Object getItem(int position) {
		return allCourseCategoryItems.get(position);
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
		final String categoryName = allCourseCategoryItems.get(position).getCategoryName();
		viewHolder.categoryName.setText(categoryName);
		final List<CourseCategoryItem> categoryInfo = allCourseCategoryItems.get(position).getCourseItems();
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
				bundle.putInt("categoryId", categoryInfo.get(position).getCategoryId());
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
