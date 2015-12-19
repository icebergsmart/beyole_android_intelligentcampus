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

/**
 * 修改用户个性签名页面
 * 
 * @date 2015/12/18
 * @author Iceberg
 * 
 */
public class EditUserDescriptionActivity extends Activity {

	private EditText mContent;
	private Button mSubmitBtn;
	private GlobalParameterApplication application;
	private User currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_items_change_edit_userdescription_layout);
		application = (GlobalParameterApplication) getApplicationContext();
		currentUser = application.getUser();
		initViews();
		initEvents();
	}

	private void initEvents() {
		MyOnClickListener listener = new MyOnClickListener();
		mSubmitBtn.setOnClickListener(listener);
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_me_items_change_edit_user_description_top).findViewById(R.id.id_top_banner_title);
		tv.setText("个性签名");
		mContent = (EditText) findViewById(R.id.id_me_items_change_edit_user_description_content);
		mSubmitBtn = (Button) findViewById(R.id.id_me_items_change_edit_user_description_btn);
		mContent.setText(getIntent().getStringExtra("content"));
	}

	private void updateUserDescription() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", currentUser.getUserId() + "");
		map.put("userDescription", mContent.getText().toString());
		Request<JSONObject> request = new NormalPostRequest(APIConstant.UPDATEUSERDESCRIPTIONINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					int result = response.getInt("code");
					if (result == UpdateFieldsConstant.UPDATE_SPECIFIC_FIELD_SUCCESS) {
						Toast.makeText(EditUserDescriptionActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
						currentUser.setUserDescription(mContent.getText().toString());
						EditUserDescriptionActivity.this.finish();
					} else {
						Toast.makeText(EditUserDescriptionActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
					}

				} catch (JSONException e) {
					Toast.makeText(EditUserDescriptionActivity.this, "处理异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(EditUserDescriptionActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}

	class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.id_me_items_change_edit_user_description_btn:
				updateUserDescription();
				break;
			}
		}

	}
}
