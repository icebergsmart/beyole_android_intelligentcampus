package com.beyole.intelligentcampus.functions.person.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyole.bean.ExerciseInfo;
import com.beyole.intelligentcampus.R;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class ExerciseListViewAdapter extends BaseAdapter {

	private List<ExerciseInfo> exerciseInfos;
	private LayoutInflater inflater;
	private Context mContext;

	public ExerciseListViewAdapter(Context context, List<ExerciseInfo> data) {
		exerciseInfos = data;
		inflater = LayoutInflater.from(context);
		mContext = context;
	}

	@Override
	public int getCount() {
		return exerciseInfos.size();
	}

	@Override
	public Object getItem(int position) {
		return exerciseInfos.get(position);
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
			convertView = inflater.inflate(R.layout.function_person_exercise_item_layout, null);
			viewHolder.exerciseImage = (ImageView) convertView.findViewById(R.id.id_function_person_exercise_item_zip_iv);
			viewHolder.exerciseTitle = (TextView) convertView.findViewById(R.id.id_function_person_exercise_item_title_tv);
			viewHolder.exerciseDeliveryName = (TextView) convertView.findViewById(R.id.id_function_person_exercise_item_organize_name_tv);
			viewHolder.exerciseType = (TextView) convertView.findViewById(R.id.id_function_person_exercise_item_type_name_tv);
			viewHolder.exerciseStatus = (TextView) convertView.findViewById(R.id.id_function_person_exercise_item_finished_name_tv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.exerciseTitle.setText(exerciseInfos.get(position).getExerciseName());
		viewHolder.exerciseDeliveryName.setText(exerciseInfos.get(position).getChargeManName());
		viewHolder.exerciseType.setText(exerciseInfos.get(position).getExerciseType());
		int status = exerciseInfos.get(position).getStatus();
		if (status == 0) {
			viewHolder.exerciseStatus.setTextColor(0XFF61B1F4);
			viewHolder.exerciseStatus.setText("正在进行");
		} else if (status == 1) {
			viewHolder.exerciseStatus.setTextColor(0XFFEA897E);
			viewHolder.exerciseStatus.setText("已结束");
		} else {
			viewHolder.exerciseStatus.setTextColor(0XFF66CCB6);
			viewHolder.exerciseStatus.setText("正在审核");
		}
		return convertView;
	}

	class ViewHolder {
		// 活动描述图片
		public ImageView exerciseImage;
		// 活动标题
		public TextView exerciseTitle;
		// 活动发起人
		public TextView exerciseDeliveryName;
		// 活动类型
		public TextView exerciseType;
		// 活动状态
		public TextView exerciseStatus;
	}
}
