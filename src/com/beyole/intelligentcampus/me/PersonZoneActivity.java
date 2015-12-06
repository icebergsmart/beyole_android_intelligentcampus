package com.beyole.intelligentcampus.me;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.adapter.PersonTalkAdapter;
import com.beyole.bean.GlobalParameterApplication;
import com.beyole.bean.User;
import com.beyole.bean.UserTalk;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;
import com.beyole.view.pullscrollview.widget.PullScrollView;

public class PersonZoneActivity extends Activity implements PullScrollView.OnTurnListener {
	private PullScrollView mScrollView;
	private ImageView mHeadImg;
	private ListView mTalkList;
	private PersonTalkAdapter mAdapter;
	private List<UserTalk> data = new ArrayList<UserTalk>();
	private TextView mUserName, mDescription, mUserType, mUserSex;
	private int currentUserId;
	private GlobalParameterApplication application;
	private User currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_pull_down);
		application = (GlobalParameterApplication) getApplicationContext();
		currentUser = application.getUser();
		initView();
		initEvent();
	}

	private void initEvent() {
		getUserInformations();
		mTalkList.setAdapter(mAdapter);
		// setListViewHeightBasedOnChildren(mTalkList);
		// Utility.setListViewHeightBasedOnChildren(mTalkList);
	}

	protected void initView() {
		mUserName = (TextView) findViewById(R.id.id_me_personzone_user_name);
		mDescription = (TextView) findViewById(R.id.id_me_personzone_user_des);
		mUserType = (TextView) findViewById(R.id.id_me_personzone_user_type);
		mUserSex = (TextView) findViewById(R.id.id_me_personzone_user_sex);
		mScrollView = (PullScrollView) findViewById(R.id.scroll_view);
		mHeadImg = (ImageView) findViewById(R.id.background_img);
		mScrollView.setHeader(mHeadImg);
		mScrollView.setOnTurnListener(this);
		mTalkList = (ListView) findViewById(R.id.person_details_talklist);
		UserTalk talk = null;
		for (int i = 0; i < 10; i++) {
			talk = new UserTalk(i, "", "测试用户" + i, "2015-11-25 " + i + "时", "Story eventually, i can only one man looked at the memory of the nicety lost.Not tears can restore the lost, not everyone is worth your pay.Don’t and I said I’m sorry, I don’t want to and you it doesn’t." + i, i, i, i, 0,
					"小米" + i + "S", "");
			data.add(talk);
		}
		mAdapter = new PersonTalkAdapter(this, data);
		currentUserId = getIntent().getIntExtra("userId", 0);
	}

	@Override
	public void onTurn() {

	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		Log.i("defineListView", "count" + listAdapter.getCount());
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	public void getUserInformations() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", currentUserId + "");
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDSPECIFICUSERBYUSERIDINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				User user = null;
				try {
					if (response.getInt("code") == UserConstant.QUERY_FOR_USER_SUCCESS) {
						JSONObject object = response.getJSONObject("userInfo");
						user = JsonUtils.readJsonToObject(User.class, object.toString());
						mUserName.setText(user.getUserName());
						mDescription.setText(user.getUserDescription());
						mUserType.setText(user.getUserType().equals("adminuser") ? "超级管理员" : user.getUserType().equals("manager") ? "管理员" : "普通用户");
						mUserSex.setText(user.getUserSex() == 0 ? "男" : "女");
					}
				} catch (JSONException e) {
					Toast.makeText(PersonZoneActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(PersonZoneActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}

}
