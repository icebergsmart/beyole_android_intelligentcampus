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
import com.beyole.intelligentcampus.R;
import com.squareup.picasso.Picasso;

/**
 * 失物招领list adapter
 * 
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class FunctionLossAdapter extends BaseAdapter {

	private List<UserLoss> userLosses;
	private LayoutInflater inflater;
	private Context mContext;

	public FunctionLossAdapter(Context context, List<UserLoss> data) {
		userLosses = data;
		mContext = context;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return userLosses.size();
	}

	@Override
	public Object getItem(int position) {
		return userLosses.get(position);
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
			convertView = inflater.inflate(R.layout.function_convient_loss_item_layout, null);
			viewHolder.userImage = (ImageView) convertView.findViewById(R.id.id_convenient_loss_avator);
			viewHolder.userName = (TextView) convertView.findViewById(R.id.id_person_details_username);
			viewHolder.publishDate = (TextView) convertView.findViewById(R.id.id_convenient_loss_deliverydate);
			viewHolder.statusYes = (TextView) convertView.findViewById(R.id.id_convenient_loss_person_details_ing);
			viewHolder.statusNo = (TextView) convertView.findViewById(R.id.id_convenient_loss_person_details_over);
			viewHolder.content = (TextView) convertView.findViewById(R.id.id_convenient_loss_person_details_content);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.userName.setText(userLosses.get(position).getUserName());
		viewHolder.publishDate.setText(userLosses.get(position).getDeliveryDate());
		if (userLosses.get(position).getFinished() == 0) {
			viewHolder.statusYes.setVisibility(View.VISIBLE);
			viewHolder.statusNo.setVisibility(View.GONE);
		} else {
			viewHolder.statusYes.setVisibility(View.GONE);
			viewHolder.statusNo.setVisibility(View.VISIBLE);
		}
		viewHolder.content.setText(userLosses.get(position).getContent());
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
