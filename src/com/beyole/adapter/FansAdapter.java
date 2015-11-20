package com.beyole.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
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
import com.beyole.constant.UserFansConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.SyncHttp;
import com.beyole.util.VolleySingleton;

/**
 * @date 2015/10/19
 * @version 1.0
 * @author Iceberg
 * 
 */
public class FansAdapter extends BaseAdapter {

	private List<UserFans> userFans = new ArrayList<UserFans>();
	private LayoutInflater inflater;
	private Context mContext;
	private String currentUserId;

	public FansAdapter(Context context, List<UserFans> data, ListView listView) {
		mContext = context;
		userFans=data;
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
		final int relation = userFans.get(position).getUserFansRelationship();
		final String userId = userFans.get(position).getUserId() + "";
		final String fansId = userFans.get(position).getFansId() + "";
		viewHolder.ivIcon.setTag(url);
		viewHolder.tvTitle.setText(userFans.get(position).getFansUserName());
		viewHolder.tvContent.setText(userFans.get(position).getFansDescription());
		viewHolder.ibRelation.setImageResource(userFans.get(position).getUserFansRelationship() == UserFansConstant.FANS_FOCUS_USER ? R.drawable.card_icon_addattention : R.drawable.card_icon_arrow);
		viewHolder.ibRelation.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 如果是粉丝关注用户，点击则是相互关注
				// 如果是相互关注，点击则是用户取消对粉丝的关注
				changeRelationship((ImageButton) v, relation, userId, fansId);
				currentUserId = userId;
				new MyFanslistAsynctask().execute(APIConstant.FINDFANSLISTINTERFACE);
			}
		});
		return convertView;
	}

	protected void getFans(final String userId1) {

	}

	class MyFanslistAsynctask extends AsyncTask<String, Void, List<UserFans>> {

		@Override
		protected List<UserFans> doInBackground(String... params) {
			SyncHttp http = new SyncHttp();
			String params1 = "userId=" + currentUserId;
			try {
				String reStr = http.httpGet(params[0], params1);
				JSONObject jsonObject = new JSONObject(reStr);
				UserFans fans = null;
				userFans.clear();
				if (jsonObject.getInt("code") == UserFansConstant.FIND_FANS_SUCCESS) {
					JSONArray array = jsonObject.getJSONArray("relations");
					for (int i = 0; i < array.length(); i++) {
						JSONObject object = array.getJSONObject(i);
						String userName = object.getString("userName");
						int userId = object.getInt("userId");
						int relation = object.getInt("relation");
						String userImage = object.getString("userImage");
						String description = object.get("description") instanceof String ? object.getString("description") : "该用户还未设置个性签名";
						fans = new UserFans(i, Integer.parseInt(currentUserId), userId, userName, description, relation, "");
						userFans.add(fans);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return userFans;
		}

		@Override
		protected void onPostExecute(List<UserFans> result) {
			notifyDataSetChanged();
		}

	}

	class ViewHolder {
		public TextView tvTitle;
		public TextView tvContent;
		public ImageView ivIcon;
		public ImageButton ibRelation;
	}

	private void changeRelationship(final ImageButton v, int relation, String userId, String fansId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("fansId", fansId);
		String url = relation == UserFansConstant.FANS_FOCUS_USER ? APIConstant.USERFOCUSFANSINTERFACE : APIConstant.USERCANCLEFOCUSFANSINTERFACE;
		Request<JSONObject> request = new NormalPostRequest(url, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					int result = response.getInt("code");
					switch (result) {
					case UserFansConstant.USER_FOCUS_FANS_SUCCESS:
						v.setImageResource(R.drawable.card_icon_arrow);
						break;
					case UserFansConstant.USER_CANCLE_FOCUS_FANS_SUCCESS:
						v.setImageResource(R.drawable.card_icon_addattention);
						break;
					case UserFansConstant.USER_FOCUS_FANS_FAILURE:
					case UserFansConstant.USER_FOCUS_FANS_FAILURE_WITH_EXCEPTION:
					case UserFansConstant.USER_CANCLE_FOCUS_FANS_FAILURE:
					case UserFansConstant.USER_CANCLE_FOCUS_FANS_FAILURE_WITH_EXCEPTION:
						Toast.makeText(mContext, "操作失败", Toast.LENGTH_SHORT).show();
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
