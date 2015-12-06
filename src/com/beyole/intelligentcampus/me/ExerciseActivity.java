package com.beyole.intelligentcampus.me;

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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.adapter.ExerciseAdapter;
import com.beyole.bean.ExerciseInfo;
import com.beyole.bean.ExerciseType;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserExerciseConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.person.ExerciseDetailsActivity;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

public class ExerciseActivity extends Activity {
	private LinearLayout mTabBackLinearLayout;
	private ListView mExerciseListView;
	private ExerciseAdapter mExerciseAdapter;
	private int currentUserId;
	private RelativeLayout mNoExerciseLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me_exercise_layout);
		initViews();

	}

	private void initEvents(final List<ExerciseInfo> exercisesList) {
		mExerciseListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(ExerciseActivity.this, ExerciseDetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("exerciseInfo", exercisesList.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
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
				ExerciseInfo exerciseInfo = null;
				List<ExerciseInfo> exercisesList = new ArrayList<ExerciseInfo>();
				try {
					if (response.getInt("code") == UserExerciseConstant.FIND_EXERCISE_SUCCESS) {
						JSONArray array = response.getJSONArray("activitylist");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							exerciseInfo = JsonUtils.readJsonToObject(ExerciseInfo.class, object.toString());
							if (exerciseInfo.getExerciseType().equals("0")) {
								exerciseInfo.setExerciseType(ExerciseType.TYPE1);
							} else if (exerciseInfo.getExerciseType().equals("1")) {
								exerciseInfo.setExerciseType(ExerciseType.TYPE2);
							}
							exercisesList.add(exerciseInfo);
						}
						mExerciseAdapter = new ExerciseAdapter(ExerciseActivity.this, exercisesList, mExerciseListView, currentUserId);
						mExerciseListView.setAdapter(mExerciseAdapter);
						initEvents(exercisesList);
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
