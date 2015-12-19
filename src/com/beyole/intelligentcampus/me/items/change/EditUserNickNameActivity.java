package com.beyole.intelligentcampus.me.items.change;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.bean.GlobalParameterApplication;
import com.beyole.bean.User;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UpdateFieldsConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

public class EditUserNickNameActivity extends Activity {

	private EditText mNickNameContent;
	private Button mNickNameEditBtn;
	private GlobalParameterApplication application;
	private User currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_items_change_edit_user_nickname_layout);
		application = (GlobalParameterApplication) getApplicationContext();
		currentUser = application.getUser();
		initViews();
		initEvent();
	}

	private void initEvent() {
		mNickNameEditBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				updateUserNickName();
			}
		});
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_me_items_change_edit_user_nickname_top).findViewById(R.id.id_top_banner_title);
		tv.setText("修改昵称");
		mNickNameContent = (EditText) findViewById(R.id.id_me_items_change_edit_user_nickname_content);
		mNickNameEditBtn = (Button) findViewById(R.id.id_me_items_change_edit_user_nickname_btn);
		initData();
	}

	private void initData() {
		mNickNameContent.setText(currentUser.getNickName());
	}

	private void updateUserNickName() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", currentUser.getUserId() + "");
		map.put("nickName", mNickNameContent.getText().toString());
		Request<JSONObject> request = new NormalPostRequest(APIConstant.UPDATENICKNAMEBYUSERIDINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					int result = response.getInt("code");
					if (result == UpdateFieldsConstant.UPDATE_SPECIFIC_FIELD_SUCCESS) {
						Toast.makeText(EditUserNickNameActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
						currentUser.setNickName(mNickNameContent.getText().toString());
						EditUserNickNameActivity.this.finish();
					} else {
						Toast.makeText(EditUserNickNameActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
					}

				} catch (JSONException e) {
					Toast.makeText(EditUserNickNameActivity.this, "处理异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(EditUserNickNameActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}

}
