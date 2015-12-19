package com.beyole.intelligentcampus.me.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.bean.GlobalParameterApplication;
import com.beyole.bean.UserNotification;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserNotificationConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.me.items.adapter.NoticeAdapter;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

/**
 * 通知
 * 
 * @date 2015//11/26
 * @author Iceberg
 * 
 */
public class NoticeActivity extends Activity {

	private int currentUserId;
	private GlobalParameterApplication application;
	private ListView mListView;
	private NoticeAdapter mAdapter;
	private RelativeLayout mNoResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_items_notice);
		application = (GlobalParameterApplication) getApplicationContext();
		currentUserId = application.getUser().getUserId();
		initViews();
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_me_items_notice_top).findViewById(R.id.id_top_banner_title);
		tv.setText("通知");
		mListView = (ListView) findViewById(R.id.id_function_main_notice_lv);
		mNoResult = (RelativeLayout) findViewById(R.id.me_items_notice_no_result);
		getNotifications();
	}

	public void getNotifications() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", currentUserId + "");
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDALLNOTIFICATIONBYUSERID, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				UserNotification notification = null;
				List<UserNotification> notifications = new ArrayList<UserNotification>();
				try {
					if (response.getInt("code") == UserNotificationConstant.QUERY_FOR_INFORMATION_BY_USERID_SUCCESS) {
						JSONArray array = response.getJSONArray("notificationList");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							notification = JsonUtils.readJsonToObject(UserNotification.class, object.toString());
							notifications.add(notification);
						}
						mNoResult.setVisibility(View.GONE);
						mAdapter = new NoticeAdapter(NoticeActivity.this, notifications);
						mListView.setAdapter(mAdapter);
						mListView.setVisibility(View.VISIBLE);
					} else if (response.getInt("code") == UserNotificationConstant.QUERY_FOR_INFORMATION_BY_USERID_SUCCESS_WITHOUT_RESULT) {
						mNoResult.setVisibility(View.VISIBLE);
						mListView.setVisibility(View.GONE);
					}
				} catch (JSONException e) {
					Toast.makeText(NoticeActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(NoticeActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}

}
