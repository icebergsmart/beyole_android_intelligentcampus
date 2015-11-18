package com.beyole.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.beyole.bean.UserFans;
import com.beyole.intelligentcampus.R;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class FocusAdapter extends BaseAdapter {

	private List<UserFans> userFans;
	private LayoutInflater inflater;
	private Context mContext;

	public FocusAdapter(Context context, List<UserFans> data, ListView listView) {
		mContext = context;
		userFans = data;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return userFans.size();
	}

	@Override
	public Object getItem(int position) {
		return userFans.get(position);
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
			convertView = inflater.inflate(R.layout.fansitem, null);
			viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
			viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_username);
			viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_userdescription);
			viewHolder.ibRelation = (ImageButton) convertView.findViewById(R.id.ib_focus);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.ivIcon.setImageResource(R.drawable.ic_launcher);
		String url = userFans.get(position).getFansImageUrl();
		final String relation = userFans.get(position).getUserFansRelationship() + "";
		final String userId = userFans.get(position).getUserId() + "";
		final String fansId = userFans.get(position).getFansId() + "";
		viewHolder.ivIcon.setTag(url);
		viewHolder.tvTitle.setText(userFans.get(position).getFansUserName());
		viewHolder.tvContent.setText(userFans.get(position).getFansDescription());
		viewHolder.ibRelation.setImageResource(R.drawable.card_icon_remove);
		viewHolder.ibRelation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "关系:" + relation + "userId:" + userId + "fansId" + fansId, Toast.LENGTH_LONG).show();
			}
		});
		return convertView;
	}

	class ViewHolder {
		public TextView tvTitle;
		public TextView tvContent;
		public ImageView ivIcon;
		public ImageButton ibRelation;
	}
}
