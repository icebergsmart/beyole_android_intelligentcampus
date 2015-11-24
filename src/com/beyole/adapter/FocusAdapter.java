package com.beyole.adapter;

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
import com.beyole.bean.UserFans;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserExerciseConstant;
import com.beyole.constant.UserFansConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

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
	private int currentUserId;

	public FocusAdapter(Context context, List<UserFans> data, ListView listView, int userId) {
		mContext = context;
		userFans = data;
		currentUserId = userId;
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
		final int pos = position;
		viewHolder.ivIcon.setTag(url);
		viewHolder.tvTitle.setText(userFans.get(position).getFansUserName());
		viewHolder.tvContent.setText(userFans.get(position).getFansDescription());
		viewHolder.ibRelation.setImageResource(R.drawable.card_icon_remove);
		viewHolder.ibRelation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeRelationship(userId, fansId, pos);
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

	private void changeRelationship(String userId, String focusId, final int position) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("fansId", focusId);
		Request<JSONObject> request = new NormalPostRequest(APIConstant.USERCANCLEFOCUSFANSINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					int result = response.getInt("code");
					switch (result) {
					// 取消参与活动成功
					case UserFansConstant.USER_CANCLE_FOCUS_FANS_SUCCESS:
						Toast.makeText(mContext, "取消关注成功！", Toast.LENGTH_SHORT).show();
						userFans.remove(position);
						FocusAdapter.this.notifyDataSetChanged();
						break;
					case UserFansConstant.USER_CANCLE_FOCUS_FANS_FAILURE:
					case UserFansConstant.USER_CANCLE_FOCUS_FANS_FAILURE_WITH_EXCEPTION:
						Toast.makeText(mContext, "取消关注操作失败", Toast.LENGTH_SHORT).show();
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
