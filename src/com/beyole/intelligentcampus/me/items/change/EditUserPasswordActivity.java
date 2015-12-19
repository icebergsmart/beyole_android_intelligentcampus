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

/**
 * 修改密码页面
 * 
 * @date 2015/12/18
 * @author Iceberg
 * 
 */
public class EditUserPasswordActivity extends Activity {

	private EditText mOriginalPassword, mNewPassword, mConfirmPassword;
	private Button mEditPasswordBtn;
	private GlobalParameterApplication application;
	private User currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_items_change_edit_user_password_layout);
		application = (GlobalParameterApplication) getApplicationContext();
		currentUser = application.getUser();
		initViews();
		initEvent();
	}

	private void initEvent() {
		mEditPasswordBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mConfirmPassword.getText().toString().equals(mNewPassword.getText().toString())) {
					updateUserPassword();
				} else {
					Toast.makeText(EditUserPasswordActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	private void initViews() {
		mOriginalPassword = (EditText) findViewById(R.id.id_me_items_change_edit_user_orginial_password_content);
		mNewPassword = (EditText) findViewById(R.id.id_me_items_change_edit_user_password_content);
		mConfirmPassword = (EditText) findViewById(R.id.id_me_items_change_edit_user_confirm_password_content);
		mEditPasswordBtn = (Button) findViewById(R.id.id_me_items_change_edit_user_password_btn);
	}

	private void updateUserPassword() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", currentUser.getUserId() + "");
		map.put("originalPassword", mOriginalPassword.getText().toString());
		map.put("userPassword", mNewPassword.getText().toString());
		Request<JSONObject> request = new NormalPostRequest(APIConstant.UPDATEUSERPASSWORDINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					int result = response.getInt("code");
					if (result == UpdateFieldsConstant.UPDATE_SPECIFIC_FIELD_SUCCESS) {
						Toast.makeText(EditUserPasswordActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
						currentUser.setPassword(mNewPassword.getText().toString());
						EditUserPasswordActivity.this.finish();
					} else {
						Toast.makeText(EditUserPasswordActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
					}

				} catch (JSONException e) {
					Toast.makeText(EditUserPasswordActivity.this, "处理异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(EditUserPasswordActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}
}
