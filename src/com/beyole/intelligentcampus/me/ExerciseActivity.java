package com.beyole.intelligentcampus.me;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.adapter.ExerciseAdapter;
import com.beyole.adapter.FansAdapter;
import com.beyole.bean.UserExercise;
import com.beyole.bean.UserFans;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserExerciseConstant;
import com.beyole.constant.UserFansConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

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

public class ExerciseActivity extends Activity {
	private LinearLayout mTabBackLinearLayout;
	private ListView mExerciseListView;
	private List<UserExercise> mUserExercises = new ArrayList<UserExercise>();
	private ExerciseAdapter mExerciseAdapter;
	private int currentUserId;
	private RelativeLayout mNoExerciseLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me_exercise_layout);
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
	}

	private void initViews() {
		mNoExerciseLayout = (RelativeLayout) findViewById(R.id.activity_fans_no_exercise);
		Bundle bundle = getIntent().getExtras();
		currentUserId = bundle.getInt("userId");
		TextView tv = (TextView) findViewById(R.id.activity_exercise_top).findViewById(R.id.id_top_banner_title);
		tv.setText("活动");
		mTabBackLinearLayout = (LinearLayout) findViewById(R.id.activity_exercise_top).findViewById(R.id.id_me_fans_back_me_layout);
		mExerciseListView = (ListView) findViewById(R.id.activity_exercise_listview);
		getExercises();
	}

	public void getExercises() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", currentUserId + "");
		Request<JSONObject> request = new NormalPostRequest(APIConstant.USERPARTICIPATEDEXERCISEINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				UserExercise exercise = null;
				List<UserExercise> exercisesList = new ArrayList<UserExercise>();
				try {
					if (response.getInt("code") == UserExerciseConstant.FIND_EXERCISE_SUCCESS) {
						JSONArray array = response.getJSONArray("activitylist");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							String activityName = object.getString("activityName");
							int activityId = object.getInt("activityId");
							int activityType = object.getInt("activityType");
							int activityStatus = object.getInt("activityStatus");
							String activityDescription = object.get("activityDescription") instanceof String ? object.getString("activityDescription") : "该活动没有任何描述";
							exercise = new UserExercise(activityId, activityType, activityName, activityDescription, activityStatus);
							exercisesList.add(exercise);
						}
						mUserExercises = exercisesList;
						mExerciseAdapter = new ExerciseAdapter(ExerciseActivity.this, mUserExercises, mExerciseListView);
						mExerciseListView.setAdapter(mExerciseAdapter);
					} else {
						mExerciseListView.setVisibility(View.GONE);
						mNoExerciseLayout.setVisibility(View.VISIBLE);
					}
				} catch (JSONException e) {
					Toast.makeText(ExerciseActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(ExerciseActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
				Log.i("fans", "fans服务器交互错误");
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}
}
