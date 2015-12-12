package com.beyole.intelligentcampus.functions.life.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.beyole.bean.CourseCategoryItem;
import com.beyole.intelligentcampus.R;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class SubCourseCategoryGridViewAdapter extends BaseAdapter {

	private List<CourseCategoryItem> courseCategoryItems;
	private LayoutInflater inflater;
	// 默认被点击的item为第一个
	private int clickedItem = 0;

	public SubCourseCategoryGridViewAdapter(Context context, List<CourseCategoryItem> data) {
		courseCategoryItems = data;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return courseCategoryItems.size();
	}

	@Override
	public Object getItem(int position) {
		return courseCategoryItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.function_course_details_item_category_layout, null);
			viewHolder.categoryName = (TextView) convertView.findViewById(R.id.function_course_details_item_category_btn);
			if (position == clickedItem) {
				viewHolder.categoryName.setTextColor(0XFF00B285);
				viewHolder.categoryName.setBackgroundResource(R.drawable.function_course_list_item_btn_selected);
			} else {
				viewHolder.categoryName.setBackgroundResource(R.drawable.function_course_list_item_btn_normal);
				viewHolder.categoryName.setTextColor(0XFF000000);
			}
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			if (position == clickedItem) {
				viewHolder.categoryName.setTextColor(0XFF00B285);
				viewHolder.categoryName.setBackgroundResource(R.drawable.function_course_list_item_btn_selected);
			} else {
				viewHolder.categoryName.setBackgroundResource(R.drawable.function_course_list_item_btn_normal);
				viewHolder.categoryName.setTextColor(0XFF000000);
			}
		}
		viewHolder.categoryName.setText(courseCategoryItems.get(position).getCourseCategoryName());
		/*viewHolder.categoryName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clickedItem = position;
				notifyDataSetChanged();
			}
		});*/
		return convertView;
	}

	class ViewHolder {
		public TextView categoryName;
	}

	public void setClickItem(int clickItem) {
		this.clickedItem = clickItem;
	}
}
