package com.beyole.intelligentcampus.me;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.adapter.FocusAdapter;
import com.beyole.bean.UserFans;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserFocusConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

public class FocusActivity extends Activity {

	private LinearLayout mTabBackLinearLayout;
	private ListView mFocusListView;
	private FocusAdapter mFocusAdapter;
	private List<UserFans> mUserFocusList = new ArrayList<UserFans>();
	private int currentUserId;
	private RelativeLayout mNoFocusRelativeLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me_focus_layout);
		initViews();
		initEvents();
	}

	private void initEvents() {
		mTabBackLinearLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		mFocusListView.setAdapter(mFocusAdapter);
	}

	private void initViews() {
		mNoFocusRelativeLayout = (RelativeLayout) findViewById(R.id.activity_focus_no_focus);
		Bundle bundle = getIntent().getExtras();
		currentUserId = bundle.getInt("userId");
		TextView tv = (TextView) findViewById(R.id.activity_focus_top).findViewById(R.id.id_top_banner_title);
		tv.setText("关注");
		mTabBackLinearLayout = (LinearLayout) findViewById(R.id.activity_focus_top).findViewById(R.id.id_me_fans_back_me_layout);
		mFocusListView = (ListView) findViewById(R.id.activity_focus_listview);
		getFocus();
	}

	public void getFocus() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", currentUserId + "");
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDFOCUSLISTINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				UserFans fans = null;
				List<UserFans> fansList = new ArrayList<UserFans>();
				try {
					if (response.getInt("code") == UserFocusConstant.FIND_FOCUS_SUCCESS) {
						JSONArray array = response.getJSONArray("relations");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							String userName = object.getString("userName");
							int userId = object.getInt("userId");
							int relation = object.getInt("relation");
							String userImage = object.getString("userImage");
							String description = object.get("description") instanceof String ? object.getString("description") : "该用户还未设置个性签名";
							fans = new UserFans(i, currentUserId, userId, userName, description, relation, "");
							fansList.add(fans);
						}
						mUserFocusList = fansList;
						mFocusAdapter = new FocusAdapter(FocusActivity.this, mUserFocusList, mFocusListView,currentUserId);
						mFocusListView.setAdapter(mFocusAdapter);
					} else {
						mFocusListView.setVisibility(View.GONE);
						mNoFocusRelativeLayout.setVisibility(View.VISIBLE);
					}
				} catch (JSONException e) {
					Toast.makeText(FocusActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(FocusActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
				Log.i("fans", "fans服务器交互错误");
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}
}
