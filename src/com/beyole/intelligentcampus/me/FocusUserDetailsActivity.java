package com.beyole.intelligentcampus.me;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.bean.GlobalParameterApplication;
import com.beyole.bean.User;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserConstant;
import com.beyole.constant.UserFansConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

/**
 * 扫描二维码跳出的用户界面
 * 
 * @date 2015/12/05
 * @author Iceberg
 * 
 */
public class FocusUserDetailsActivity extends Activity {

	private int currentUserId;
	private Button mFocusBtn;
	private ImageView mAvatar, mNickNameDes;
	private TextView mNickName, mAccount, mAccountType, mDescription;
	private GlobalParameterApplication application;
	private User currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function_findme_focus_user_details_layout);
		application = (GlobalParameterApplication) getApplicationContext();
		currentUser=application.getUser();
		initViews();
		initEvents();
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_function_findme_focus_user_details_layout_top).findViewById(R.id.id_top_banner_title);
		tv.setText("详细资料");
		mFocusBtn = (Button) findViewById(R.id.id_focus_user_details_focus_user_btn);
		mAvatar = (ImageView) findViewById(R.id.id_focus_user_details_avatar_iv);
		mNickName = (TextView) findViewById(R.id.id_focus_user_details_nickname_tv);
		mNickNameDes = (ImageView) findViewById(R.id.id_focus_user_details_nickname_des_iv);
		mAccount = (TextView) findViewById(R.id.id_focus_user_details_account_tv);
		mAccountType = (TextView) findViewById(R.id.id_focus_user_details_account_type_tv);
		mDescription = (TextView) findViewById(R.id.id_focus_user_details_special_desc_tv);
		currentUserId = getIntent().getIntExtra("userId", 0);
	}

	private void initEvents() {
		getUserInformations();
		if (currentUser != null && currentUser.getUserId() > 0) {
			mFocusBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (currentUserId == currentUser.getUserId()) {
						Toast.makeText(FocusUserDetailsActivity.this, "自己不能关注自己啦！", Toast.LENGTH_LONG).show();
					} else {
						addRelationship(currentUser.getUserId() + "", currentUserId + "");
					}
				}
			});
		}
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
						mNickName.setText(user.getNickName());
						mAccount.setText(user.getUserName());
						if (user.getUserType().equals("commonuser")) {
							mAccountType.setText("普通用户");
							if (user.getUserSex() == 0) {
								mNickNameDes.setBackgroundResource(R.drawable.list_male);
							} else {
								mNickNameDes.setBackgroundResource(R.drawable.list_female);
							}
						} else if (user.getUserType().equals("adminuser")) {
							mAccountType.setText("管理员");
							mNickNameDes.setBackgroundResource(R.drawable.avatar_vip);
						} else if (user.getUserType().equals("orginazation")) {
							mAccountType.setText("校园组织");
							mNickNameDes.setBackgroundResource(R.drawable.avatar_enterprise_vip);
						}
						mDescription.setText(user.getUserDescription());
					}
				} catch (JSONException e) {
					Toast.makeText(FocusUserDetailsActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(FocusUserDetailsActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}

	private void addRelationship(String userId, String fansId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("fansId", fansId);
		Request<JSONObject> request = new NormalPostRequest(APIConstant.USERFOCUSFANSINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					int result = response.getInt("code");
					switch (result) {
					case UserFansConstant.USER_FOCUS_FANS_SUCCESS:
						Toast.makeText(FocusUserDetailsActivity.this, "关注成功", Toast.LENGTH_LONG).show();
						break;
					case UserFansConstant.USER_FOCUS_FANS_FAILURE:
					case UserFansConstant.USER_FOCUS_FANS_FAILURE_WITH_EXCEPTION:
						Toast.makeText(FocusUserDetailsActivity.this, "失败", Toast.LENGTH_LONG).show();
						break;
					}

				} catch (JSONException e) {
					Toast.makeText(FocusUserDetailsActivity.this, "服务器交互异常！", Toast.LENGTH_SHORT).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(FocusUserDetailsActivity.this, "服务器响应错误", Toast.LENGTH_SHORT).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(FocusUserDetailsActivity.this.getApplicationContext()).addToRequestQueue(request);
	}
}
