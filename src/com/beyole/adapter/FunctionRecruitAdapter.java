package com.beyole.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyole.bean.UserLoss;
import com.beyole.bean.UserRecruit;
import com.beyole.bean.UserTalk;
import com.beyole.intelligentcampus.R;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class FunctionRecruitAdapter extends BaseAdapter {

	private List<UserRecruit> userRecruits;
	private LayoutInflater inflater;

	public FunctionRecruitAdapter(Context context, List<UserRecruit> data) {
		userRecruits = data;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return userRecruits.size();
	}

	@Override
	public Object getItem(int position) {
		return userRecruits.get(position);
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
			convertView = inflater.inflate(R.layout.function_convient_recruit_item_layout, null);
			viewHolder.userImage = (ImageView) convertView.findViewById(R.id.id_convenient_recruit_avator);
			viewHolder.userName = (TextView) convertView.findViewById(R.id.id_convenient_recruit_person_details_username);
			viewHolder.publishDate = (TextView) convertView.findViewById(R.id.id_convenient_recruit_deliverydate);
			viewHolder.statusYes = (TextView) convertView.findViewById(R.id.id_convenient_recruit_person_details_ing);
			viewHolder.statusNo = (TextView) convertView.findViewById(R.id.id_convenient_recruit_person_details_over);
			viewHolder.content = (TextView) convertView.findViewById(R.id.id_convenient_recruit_person_details_content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.userName.setText(userRecruits.get(position).getUserName());
		viewHolder.publishDate.setText(userRecruits.get(position).getDeliveryDate());
		if (userRecruits.get(position).getFinished() == 0) {
			viewHolder.statusYes.setVisibility(View.VISIBLE);
			viewHolder.statusNo.setVisibility(View.GONE);
		} else {
			viewHolder.statusYes.setVisibility(View.GONE);
			viewHolder.statusNo.setVisibility(View.VISIBLE);
		}
		viewHolder.content.setText(userRecruits.get(position).getContent());
		return convertView;
	}

	class ViewHolder {
		public ImageView userImage;
		public TextView userName;
		public TextView publishDate;
		public TextView statusYes;
		public TextView statusNo;
		public TextView content;
	}
}
