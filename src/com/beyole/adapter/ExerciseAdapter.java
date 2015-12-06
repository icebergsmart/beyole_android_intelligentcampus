package com.beyole.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.bean.ExerciseInfo;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserExerciseConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class ExerciseAdapter extends BaseAdapter {

	public List<ExerciseInfo> userExercises = new ArrayList<ExerciseInfo>();
	private LayoutInflater inflater;
	private Context mContext;
	private int currentUserId;

	public ExerciseAdapter(Context context, List<ExerciseInfo> data, ListView listView, int userId) {
		mContext = context;
		userExercises = data;
		inflater = LayoutInflater.from(context);
		currentUserId = userId;
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
		final String type = userExercises.get(position).getExerciseType() + "";
		final String exerciseId = userExercises.get(position).getExerciseId() + "";
		final String status = userExercises.get(position).getStatus() + "";
		viewHolder.tvTitle.setText(userExercises.get(position).getExerciseName());
		final int pos = position;
		viewHolder.tvContent.setText(userExercises.get(position).getExerciseDetails());
		viewHolder.ibRelation.setImageResource(R.drawable.exercise_exit);
		viewHolder.ibRelation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeRelationship(currentUserId + "", exerciseId, pos);
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

	private void changeRelationship(String userId, String exerciseId, final int position) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("exerciseId", exerciseId);
		Request<JSONObject> request = new NormalPostRequest(APIConstant.DELETEUSERPARTICIPATEDEXERCISE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					int result = response.getInt("code");
					switch (result) {
					// 取消参与活动成功
					case UserExerciseConstant.EXIT_EXERCISE_SUCCESS:
						Toast.makeText(mContext, "退出活动成功！", Toast.LENGTH_SHORT).show();
						userExercises.remove(position);
						ExerciseAdapter.this.notifyDataSetChanged();
						break;
					case UserExerciseConstant.EXIT_EXERCISE_ERROR:
						Toast.makeText(mContext, "退出活动失败！", Toast.LENGTH_SHORT).show();
						break;
					case UserExerciseConstant.EXIT_EXERCISE_ERROR_WITH_EXCEPTION:
						Toast.makeText(mContext, "系统异常", Toast.LENGTH_SHORT).show();
						break;
					}

				} catch (JSONException e) {
					Toast.makeText(mContext, "服务器交互异常！", Toast.LENGTH_SHORT).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(mContext, "服务器响应错误", Toast.LENGTH_SHORT).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(mContext.getApplicationContext()).addToRequestQueue(request);
	}

}
