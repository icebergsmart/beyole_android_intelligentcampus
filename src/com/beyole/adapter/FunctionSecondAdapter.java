package com.beyole.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyole.bean.UserLoss;
import com.beyole.bean.UserSecondHand;
import com.beyole.intelligentcampus.R;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class FunctionSecondAdapter extends BaseAdapter {

	private List<UserSecondHand> userSecondHands;
	private LayoutInflater inflater;

	public FunctionSecondAdapter(Context context, List<UserSecondHand> data) {
		userSecondHands = data;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return userSecondHands.size();
	}

	@Override
	public Object getItem(int position) {
		return userSecondHands.get(position);
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
			convertView = inflater.inflate(R.layout.function_convient_secondhand_item_layout, null);
			viewHolder.userImage = (ImageView) convertView.findViewById(R.id.id_convenient_secondhand_avator);
			viewHolder.userName = (TextView) convertView.findViewById(R.id.id_convenient_secondhand_person_details_username);
			viewHolder.publishDate = (TextView) convertView.findViewById(R.id.id_convenient_secondhand_deliverydate);
			viewHolder.statusYes = (TextView) convertView.findViewById(R.id.id_convenient_secondhand_person_details_ing);
			viewHolder.statusNo = (TextView) convertView.findViewById(R.id.id_convenient_secondhand_person_details_over);
			viewHolder.content = (TextView) convertView.findViewById(R.id.id_convenient_secondhand_person_details_content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.userName.setText(userSecondHands.get(position).getUserName());
		viewHolder.publishDate.setText(userSecondHands.get(position).getDeliveryDate());
		if (userSecondHands.get(position).getFinished() == 0) {
			viewHolder.statusYes.setVisibility(View.VISIBLE);
			viewHolder.statusNo.setVisibility(View.GONE);
		} else {
			viewHolder.statusYes.setVisibility(View.GONE);
			viewHolder.statusNo.setVisibility(View.VISIBLE);
		}
		viewHolder.content.setText(userSecondHands.get(position).getContent());
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
