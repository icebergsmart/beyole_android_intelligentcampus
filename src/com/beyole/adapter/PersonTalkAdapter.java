package com.beyole.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.beyole.bean.UserTalk;
import com.beyole.intelligentcampus.R;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class PersonTalkAdapter extends BaseAdapter {

	private List<UserTalk> userTalks;
	private LayoutInflater inflater;

	public PersonTalkAdapter(Context context, List<UserTalk> data) {
		userTalks = data;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return userTalks.size();
	}

	@Override
	public Object getItem(int position) {
		return userTalks.get(position);
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
			convertView = inflater.inflate(R.layout.person_zone_details_item, null);
			viewHolder.userName = (TextView) convertView.findViewById(R.id.id_person_details_username);
			viewHolder.publishDate = (TextView) convertView.findViewById(R.id.id_person_details_deliverydate);
			viewHolder.content = (TextView) convertView.findViewById(R.id.id_person_details_content);
			viewHolder.postDelivery = (Button) convertView.findViewById(R.id.id_person_details_postdeliveryBtn);
			viewHolder.comment = (Button) convertView.findViewById(R.id.id_person_details_commontBtn);
			viewHolder.praise = (Button) convertView.findViewById(R.id.id_person_details_prasiseBtn);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.userName.setText(userTalks.get(position).getUserName());
		viewHolder.publishDate.setText(userTalks.get(position).getPublishDate());
		viewHolder.content.setText(userTalks.get(position).getTalkContent());
		viewHolder.postDelivery.setText("转发" + userTalks.get(position).getPostDelivery());
		viewHolder.comment.setText("评论" + userTalks.get(position).getComment());
		viewHolder.praise.setText("赞" + userTalks.get(position).getPraise());
		return convertView;
	}

	class ViewHolder {
		public TextView userName;
		public TextView publishDate;
		public TextView content;
		public Button comment;
		public Button praise;
		public Button postDelivery;
	}
}
