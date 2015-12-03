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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.bean.ActivityConstant;
import com.beyole.bean.ExerciseInfo;
import com.beyole.bean.ExerciseType;
import com.beyole.constant.APIConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.person.adapter.ExerciseListViewAdapter;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

public class ExerciseActivity extends Activity {

	private ListView mListView;
	private ExerciseListViewAdapter mAdapter;
	private RelativeLayout mHasNoResultRl, isQueringRl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_exercise_talk);
		initViews();

	}

	private void initEvents(final List<ExerciseInfo> dataSet) {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(ExerciseActivity.this, ExerciseDetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("exerciseInfo", dataSet.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_person_exercise_talk_top).findViewById(R.id.id_top_banner_title);
		tv.setText("活动");
		mListView = (ListView) findViewById(R.id.id_person_exercise_talk_listview_main);
		mHasNoResultRl = (RelativeLayout) findViewById(R.id.id_function_exercise_no_result_rl);
		isQueringRl = (RelativeLayout) findViewById(R.id.id_function_exercise_is_quering_rl);
		initDatas();
	}

	private void initDatas() {
		getExercises();

	}

	public void getExercises() {
		Map<String, String> map = new HashMap<String, String>();
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDALLEXERCISEINTERCE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				isQueringRl.setVisibility(View.GONE);
				ExerciseInfo exerciseInfo = null;
				List<ExerciseInfo> exercisesList = new ArrayList<ExerciseInfo>();
				try {
					if (response.getInt("code") == ActivityConstant.FIND_ACTIVITY_SUCCESS) {
						JSONArray array = response.getJSONArray("activities");
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
						mListView.setVisibility(View.VISIBLE);
						mHasNoResultRl.setVisibility(View.GONE);
						mAdapter = new ExerciseListViewAdapter(ExerciseActivity.this, exercisesList);
						mListView.setAdapter(mAdapter);
						initEvents(exercisesList);
					} else if (response.getInt("code") == ActivityConstant.FIND_ACTIVITY_SUCCESS_WITH_NO_RESULT) {
						mListView.setVisibility(View.GONE);
						mHasNoResultRl.setVisibility(View.VISIBLE);
					}
				} catch (JSONException e) {
					Toast.makeText(ExerciseActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				isQueringRl.setVisibility(View.GONE);
				Toast.makeText(ExerciseActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}
}
