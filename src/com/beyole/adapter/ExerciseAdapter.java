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

import com.beyole.bean.UserExercise;
import com.beyole.intelligentcampus.R;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class ExerciseAdapter extends BaseAdapter {

	private List<UserExercise> userExercises;
	private LayoutInflater inflater;
	private Context mContext;

	public ExerciseAdapter(Context context, List<UserExercise> data, ListView listView) {
		mContext = context;
		userExercises = data;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return userExercises.size();
	}

	@Override
	public Object getItem(int position) {
		return userExercises.get(position);
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
			convertView = inflater.inflate(R.layout.exerciseitem, null);
			viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
			viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_exercisename);
			viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_exercisedescription);
			viewHolder.ibRelation = (ImageButton) convertView.findViewById(R.id.ib_exercisepart);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.ivIcon.setImageResource(R.drawable.ic_launcher);
		final String type = userExercises.get(position).getActivityType() + "";
		final String exerciseId = userExercises.get(position).getActivityId() + "";
		final String status = userExercises.get(position).getActivityStatus() + "";
		viewHolder.tvTitle.setText(userExercises.get(position).getActivityName());
		viewHolder.tvContent.setText(userExercises.get(position).getActivityDescription());
		viewHolder.ibRelation.setImageResource(R.drawable.exercise_exit);
		viewHolder.ibRelation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "类型:" + type + "exerciseId:" + exerciseId + "status" + status, Toast.LENGTH_LONG).show();
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
