package com.beyole.intelligentcampus.functions.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.bean.ActivityConstant;
import com.beyole.bean.ExerciseInfo;
import com.beyole.bean.ExerciseType;
import com.beyole.bean.GlobalParameterApplication;
import com.beyole.bean.User;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserExerciseConstant;
import com.beyole.intelligentcampus.LoginActivity;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.person.adapter.ExerciseListViewAdapter;
import com.beyole.intelligentcampus.me.PersonZoneActivity;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

public class ExerciseDetailsActivity extends Activity {

	private LinearLayout mScrollView;
	private LinearLayout mBottomLinearLayout, mUserLinearLayout;
	private TextView mTitle, mType, mStatus, mExerciseDate, mLocation, mDeliveryUserName, mOtherInfo, mRestDay;
	private GlobalParameterApplication application;
	private User currentUser;
	private ExerciseInfo exerciseInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function_person_exercise_item_details_layout);
		application = (GlobalParameterApplication) getApplicationContext();
		currentUser = application.getUser();
		initViews();
		initEvents();
	}

	private void initEvents() {
		// 设置通过活动页面查看发布者详情的点击事件
		mUserLinearLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (currentUser != null && currentUser.getUserId() > 0) {
					Intent intent = new Intent(ExerciseDetailsActivity.this, PersonZoneActivity.class);
					startActivity(intent);
				} else {
					Intent intent = new Intent(ExerciseDetailsActivity.this, LoginActivity.class);
					startActivity(intent);
				}
			}
		});
		// 设置点击关注按钮事件
		if (exerciseInfo.getStatus() == 0) {
			mBottomLinearLayout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (currentUser != null && currentUser.getUserId() > 0) {
						participateExercises();
					} else {
						Intent intent = new Intent(ExerciseDetailsActivity.this, LoginActivity.class);
						startActivity(intent);
					}
				}
			});
		}

	}

	private void initViews() {
		mScrollView = (LinearLayout) findViewById(R.id.id_function_person_exercise_item_details_sv_ll);
		mBottomLinearLayout = (LinearLayout) findViewById(R.id.id_function_person_details_exercise_bottom_ll);
		mUserLinearLayout = (LinearLayout) findViewById(R.id.id_function_person_exercise_item_details_delivery_username_ll);
		mTitle = (TextView) findViewById(R.id.function_person_exercise_item_title_details_tv);
		mType = (TextView) findViewById(R.id.function_person_exercise_item_type_details_tv);
		mStatus = (TextView) findViewById(R.id.function_person_exercise_item_status_details_tv);
		mExerciseDate = (TextView) findViewById(R.id.function_person_exercise_item_exercisedate_details_tv);
		mLocation = (TextView) findViewById(R.id.function_person_exercise_item_location_details_tv);
		mDeliveryUserName = (TextView) findViewById(R.id.function_person_exercise_item_delivery_username_details_tv);
		mOtherInfo = (TextView) findViewById(R.id.function_person_exercise_item_otherinfo_details_tv);
		mRestDay = (TextView) findViewById(R.id.function_person_exercise_item_restday_details_tv);
		exerciseInfo = (ExerciseInfo) getIntent().getSerializableExtra("exerciseInfo");
		// 设置活动名称
		mTitle.setText(exerciseInfo.getExerciseName());
		mType.setText(exerciseInfo.getExerciseType());
		mStatus.setText(exerciseInfo.getStatus() == 0 ? "正在进行" : exerciseInfo.getStatus() == 1 ? "已结束" : "正在审核");
		mExerciseDate.setText(exerciseInfo.getStartDate() + "-" + exerciseInfo.getEndDate());
		mLocation.setText(exerciseInfo.getExerciseLocation());
		mDeliveryUserName.setText(exerciseInfo.getChargeManName());
		mOtherInfo.setText(exerciseInfo.getExerciseDetails());
		mRestDay.setText("关注活动(剩余" + exerciseInfo.getRestDay() + "天)");
		if (exerciseInfo.getStatus() != 0) {
			mBottomLinearLayout.setClickable(false);
			mBottomLinearLayout.setBackgroundColor(0X66000000);
			mRestDay.setText("关注活动(已过期)");
		}
	}

	public void participateExercises() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", currentUser.getUserId() + "");
		map.put("exerciseId", exerciseInfo.getExerciseId() + "");
		Request<JSONObject> request = new NormalPostRequest(APIConstant.PARTICIPATREXERCISEINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					if (response.getInt("code") == UserExerciseConstant.USER_PARTICIPATE_EXERCISE_SUCCESS) {
						Toast.makeText(ExerciseDetailsActivity.this, "关注成功", Toast.LENGTH_LONG).show();
					} else if (response.getInt("code") == UserExerciseConstant.USER_PARTICIPATE_EXERCISE_ERROR) {
						Toast.makeText(ExerciseDetailsActivity.this, "关注失败", Toast.LENGTH_LONG).show();
					} else if (response.getInt("code") == UserExerciseConstant.USER_PARTICIPATE_EXERCISE_ERROR_WITH_EXCEPTION) {
						Toast.makeText(ExerciseDetailsActivity.this, "服务器异常", Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					Toast.makeText(ExerciseDetailsActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(ExerciseDetailsActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}
}
