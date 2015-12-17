package com.beyole.intelligentcampus.me.items.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyole.bean.UserNotification;
import com.beyole.intelligentcampus.R;

/**
 * 我的通知适配器
 * 
 * @date 2015/12/17
 * @author Iceberg
 * 
 */
public class NoticeAdapter extends BaseAdapter {

	private List<UserNotification> notifications;
	private Context mContext;
	private LayoutInflater mInflater;

	public NoticeAdapter(Context context, List<UserNotification> notificationList) {
		this.mContext = context;
		notifications = notificationList;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return notifications == null ? 0 : notifications.size();
	}

	@Override
	public Object getItem(int position) {
		return notifications.get(position);
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
			convertView = mInflater.inflate(R.layout.function_me_notice_list_item_layout, null);
			viewHolder.mNoticeTime = (TextView) convertView.findViewById(R.id.function_me_notice_item_time);
			viewHolder.mNoticeContent = (TextView) convertView.findViewById(R.id.function_me_notice_item_content);
			viewHolder.mUserImage = (ImageView) convertView.findViewById(R.id.me_items_notice_photo);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.mNoticeTime.setText(notifications.get(position).getDeliveryDate());
		viewHolder.mNoticeContent.setText(notifications.get(position).getContent());
		return convertView;
	}

	class ViewHolder {
		public TextView mNoticeTime;
		public TextView mNoticeContent;
		public ImageView mUserImage;
	}
}
