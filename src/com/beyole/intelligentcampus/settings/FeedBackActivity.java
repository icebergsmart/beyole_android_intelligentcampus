package com.beyole.intelligentcampus.settings;

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
import com.beyole.constant.APIConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

public class FeedBackActivity extends Activity {

	private EditText mFeedbackContent;
	private EditText mFeedbackContact;
	private Button mFeedbackSubmitBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_feedback_layout);
		initViews();
		initEvents();
	}

	private void initEvents() {
		MyBtnOnclickListener listener = new MyBtnOnclickListener();
		mFeedbackSubmitBtn.setOnClickListener(listener);
	}

	class MyBtnOnclickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.settings_feedback_btn:
				if (!mFeedbackContent.getText().toString().trim().replace(" ", "").equals("")) {
					addFeedback();
					finish();
				}else{
					Toast.makeText(FeedBackActivity.this, "意见内容不能为空！", Toast.LENGTH_LONG).show();
				}
				break;
			}
		}

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.settings_feedback_top).findViewById(R.id.id_top_banner_title);
		tv.setText("意见反馈");
		mFeedbackContact = (EditText) findViewById(R.id.settings_feedback_contact);
		mFeedbackContent = (EditText) findViewById(R.id.settings_feedback_content);
		mFeedbackSubmitBtn = (Button) findViewById(R.id.settings_feedback_btn);
	}

	private void addFeedback() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("feedbackContent", mFeedbackContent.getText().toString());
		map.put("feedbackContact", mFeedbackContact.getText().toString());
		Request<JSONObject> request = new NormalPostRequest(APIConstant.DEVICEFEEDBACKINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					String result = response.getString("result");
					Toast.makeText(FeedBackActivity.this, result.equals("success") ? "提交成功" : "提交失败", Toast.LENGTH_LONG).show();
				} catch (JSONException e) {
					Toast.makeText(FeedBackActivity.this, "提交异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(FeedBackActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}
}
