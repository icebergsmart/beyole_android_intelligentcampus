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
import com.beyole.bean.Information;
import com.beyole.intelligentcampus.R;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class CampusInformationListViewAdapter extends BaseAdapter {

	private List<Information> informations;
	private LayoutInflater inflater;
	private Context mContext;

	public CampusInformationListViewAdapter(Context context, List<Information> data) {
		informations = data;
		inflater = LayoutInflater.from(context);
		mContext = context;
	}

	@Override
	public int getCount() {
		return informations.size();
	}

	@Override
	public Object getItem(int position) {
		return informations.get(position);
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
			convertView = inflater.inflate(R.layout.function_life_campus_listview_item_layout, null);
			viewHolder.newsTitle = (TextView) convertView.findViewById(R.id.id_function_life_campus_lisview_item_tv_title);
			viewHolder.newsDescription = (TextView) convertView.findViewById(R.id.id_function_life_campus_lisview_item_tv_content);
			viewHolder.newsImageUrl = (ImageView) convertView.findViewById(R.id.id_function_life_campus_lisview_item_iv_icon);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.newsTitle.setText(informations.get(position).getNewsTitle());
		viewHolder.newsDescription.setText(informations.get(position).getNewsContent());
		return convertView;
	}

	class ViewHolder {
		public ImageView newsImageUrl;
		public TextView newsTitle;
		public TextView newsDescription;
	}
}
