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
import com.beyole.adapter.FansAdapter;
import com.beyole.bean.UserFans;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserFansConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

public class FansActivity extends Activity {

	private LinearLayout mFansTopBackLine;
	private ListView mFansListView;
	private FansAdapter mFansAdapter;
	public List<UserFans> mUserFansList = new ArrayList<UserFans>();
	private int currentUserId;
	private RelativeLayout mNoFansTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me_fans_layout);
		initViews();
		initEvents();
	}

	private void initEvents() {
		MyFansActivityOnClickListener listener = new MyFansActivityOnClickListener();
		mFansTopBackLine.setOnClickListener(listener);

	}

	private void initViews() {
		Bundle bundle = getIntent().getExtras();
		currentUserId = bundle.getInt("userId");
		mFansListView = (ListView) findViewById(R.id.activity_fans_listview);
		mFansTopBackLine = (LinearLayout) findViewById(R.id.id_me_fans_back_me_layout);
		getFans();
		mNoFansTextView = (RelativeLayout) findViewById(R.id.activity_fans_no_fans);
	}

	class MyFansActivityOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.id_me_fans_back_me_layout:
				finish();
				break;
			}
		}

	}

	public void getFans() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", currentUserId + "");
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDFANSLISTINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				UserFans fans = null;
				List<UserFans> fansList = new ArrayList<UserFans>();
				try {
					if (response.getInt("code") == UserFansConstant.FIND_FANS_SUCCESS) {
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
						mUserFansList = fansList;

						mFansAdapter = new FansAdapter(FansActivity.this, mUserFansList, mFansListView);
						mFansListView.setAdapter(mFansAdapter);
					} else {
						mFansListView.setVisibility(View.GONE);
						mNoFansTextView.setVisibility(View.VISIBLE);
					}
				} catch (JSONException e) {
					Toast.makeText(FansActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(FansActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
				Log.i("fans", "fans服务器交互错误");
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}
}
