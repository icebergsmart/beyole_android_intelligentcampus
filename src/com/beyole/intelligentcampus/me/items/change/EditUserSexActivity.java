package com.beyole.intelligentcampus.me.items.change;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

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

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 修改用户性别页面
 * 
 * @date 2015/12/18
 * @author Iceberg
 * 
 */
public class EditUserSexActivity extends Activity {

	private GlobalParameterApplication application;
	private User currentUser;
	private TableRow mMaleRow, mFemaleRow;
	private ImageView mMaleImageView, mFemaleImageView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_items_change_edit_user_sex_layout);
		application = (GlobalParameterApplication) getApplicationContext();
		currentUser = application.getUser();
		initViews();
		initEvents();
	}

	private void initEvents() {
		MyOnClickListener listener = new MyOnClickListener();
		mMaleRow.setOnClickListener(listener);
		mFemaleRow.setOnClickListener(listener);
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_me_items_change_edit_user_sex_top).findViewById(R.id.id_top_banner_title);
		tv.setText("选择性别");
		mMaleRow = (TableRow) findViewById(R.id.id_me_items_edit_user_sex_male_row);
		mFemaleRow = (TableRow) findViewById(R.id.id_me_items_edit_user_sex_female_row);
		mMaleImageView = (ImageView) findViewById(R.id.id_me_items_edit_user_sex_male_selected);
		mFemaleImageView = (ImageView) findViewById(R.id.id_me_items_edit_user_sex_female_selected);
		if (currentUser.getUserSex() == 0) {
			mMaleImageView.setVisibility(View.VISIBLE);
			mFemaleImageView.setVisibility(View.GONE);
		} else {
			mMaleImageView.setVisibility(View.GONE);
			mFemaleImageView.setVisibility(View.VISIBLE);
		}
	}

	class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.id_me_items_edit_user_sex_male_row:
				updateUserSex(0);
				break;
			case R.id.id_me_items_edit_user_sex_female_row:
				updateUserSex(1);
				break;
			}
		}

	}

	private void updateUserSex(final int userSex) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", currentUser.getUserId() + "");
		map.put("userSex", userSex + "");
		Request<JSONObject> request = new NormalPostRequest(APIConstant.UPDATEUSERSEXBYUSERIDINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					int result = response.getInt("code");
					if (result == UpdateFieldsConstant.UPDATE_SPECIFIC_FIELD_SUCCESS) {
						Toast.makeText(EditUserSexActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
						currentUser.setUserSex(userSex);
						EditUserSexActivity.this.finish();
					} else {
						Toast.makeText(EditUserSexActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
					}

				} catch (JSONException e) {
					Toast.makeText(EditUserSexActivity.this, "处理异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(EditUserSexActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}

}
