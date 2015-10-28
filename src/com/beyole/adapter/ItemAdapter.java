package com.beyole.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyole.bean.Item;
import com.beyole.intelligentcampus.R;

public class ItemAdapter extends BaseAdapter {

	private LayoutInflater layoutInflater;
	private List<Item> items;
	private int allHieght;

	public ItemAdapter() {
		super();
	}

	public ItemAdapter(String[] titles, int[] images, Context context, int height) {
		super();
		items = new ArrayList<Item>();
		allHieght = height;
		layoutInflater = LayoutInflater.from(context);
		for (int i = 0; i < images.length; i++) {
			Item item = new Item(images[i], titles[i]);
			items.add(item);
		}
	}

	@Override
	public int getCount() {
		if (items != null) {
			return items.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.gridviewlayout, null);
			AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(LayoutParams.FILL_PARENT, allHieght / items.size());
			convertView.setLayoutParams(layoutParams);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.selectTitle);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.simplyfyImg);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.title.setText(items.get(position).getTitle());
		viewHolder.image.setImageResource(items.get(position).getImgId());
		return convertView;
	}

	class ViewHolder {
		public TextView title;
		public ImageView image;
	}

}
