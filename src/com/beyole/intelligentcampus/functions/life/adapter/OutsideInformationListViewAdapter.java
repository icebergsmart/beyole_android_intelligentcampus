package com.beyole.intelligentcampus.functions.life.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyole.bean.Article;
import com.beyole.intelligentcampus.R;
import com.squareup.picasso.Picasso;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class OutsideInformationListViewAdapter extends BaseAdapter {

	private List<Article> articles;
	private LayoutInflater inflater;
	private Context mContext;

	public OutsideInformationListViewAdapter(Context context, List<Article> data) {
		articles = data;
		inflater = LayoutInflater.from(context);
		mContext = context;
	}

	@Override
	public int getCount() {
		return articles.size();
	}

	@Override
	public Object getItem(int position) {
		return articles.get(position);
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
			convertView = inflater.inflate(R.layout.function_life_outside_listview_item_layout, null);
			viewHolder.newsTitle = (TextView) convertView.findViewById(R.id.id_function_life_outside_information_tv_title);
			viewHolder.newsDescription = (TextView) convertView.findViewById(R.id.id_function_life_outside_information_tv_content);
			viewHolder.newsImageUrl = (ImageView) convertView.findViewById(R.id.id_function_life_outside_information_iv_icon);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.newsTitle.setText(articles.get(position).getArticleName());
		viewHolder.newsDescription.setText(articles.get(position).getArticleDescription());
		showImage(viewHolder.newsImageUrl, articles.get(position).getArticlePicBig());
		return convertView;
	}

	class ViewHolder {
		public ImageView newsImageUrl;
		public TextView newsTitle;
		public TextView newsDescription;
	}
	public void showImage(ImageView mImageView, String url) {
		Picasso.with(mContext).load(url).into(mImageView);
	}
}
