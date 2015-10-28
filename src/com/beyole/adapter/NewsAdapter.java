package com.beyole.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.beyole.bean.NewsBean;
import com.beyole.intelligentcampus.R;
import com.beyole.loader.ImageLoader;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class NewsAdapter extends BaseAdapter implements OnScrollListener {

	private List<NewsBean> newsBeans;
	private LayoutInflater inflater;
	private ImageLoader mImageLoader;
	private int mStart;
	private int mEnd;
	public static String URLS[];
	private boolean mFirstIn;

	public NewsAdapter(Context context, List<NewsBean> data, ListView listView) {
		newsBeans = data;
		inflater = LayoutInflater.from(context);
		mImageLoader = new ImageLoader(listView);
		URLS = new String[data.size()];
		for (int i = 0; i < data.size(); i++) {
			URLS[i] = data.get(i).newsIconUrl;
		}
		mFirstIn = true;
		listView.setOnScrollListener(this);
	}

	@Override
	public int getCount() {
		return newsBeans.size();
	}

	@Override
	public Object getItem(int position) {
		return newsBeans.get(position);
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
			convertView = inflater.inflate(R.layout.newsitem, null);
			viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
			viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.ivIcon.setImageResource(R.drawable.ic_launcher);
		String url = newsBeans.get(position).newsIconUrl;
		viewHolder.ivIcon.setTag(url);
		mImageLoader.showImageByAsyncTask(viewHolder.ivIcon, url);
		viewHolder.tvTitle.setText(newsBeans.get(position).newsTitle);
		viewHolder.tvContent.setText(newsBeans.get(position).newsContent);
		return convertView;
	}

	class ViewHolder {
		public TextView tvTitle;
		public TextView tvContent;
		public ImageView ivIcon;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (scrollState == SCROLL_STATE_IDLE) {
			mImageLoader.loadImages(mStart, mEnd);
		} else {
			mImageLoader.cancelAllTasks();
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
		mStart = firstVisibleItem;
		mEnd = firstVisibleItem + visibleItemCount;
		if (mFirstIn == true && visibleItemCount > 0) {
			mImageLoader.loadImages(mStart, mEnd);
			mFirstIn = false;
		}
	}

}
