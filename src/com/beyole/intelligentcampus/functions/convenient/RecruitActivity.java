package com.beyole.intelligentcampus.functions.convenient;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.adapter.FunctionRecruitAdapter;
import com.beyole.bean.UserRecruit;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserRecruitConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

/**
 * 失物招领界面
 * 
 * @date 2015/12/08
 * @author Iceberg
 * 
 */
public class RecruitActivity extends Activity {

	private ListView mListView;
	private FunctionRecruitAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_recruit);
		initViews();
	}

	private void initEvents(final List<UserRecruit> list) {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(RecruitActivity.this, RecruitDetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("recruitList", list.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_recruit_top).findViewById(R.id.id_top_banner_title);
		tv.setText("兼职招聘");
		mListView = (ListView) findViewById(R.id.function_recruit_listview);
		getRecruits();
	}

	public void getRecruits() {
		Map<String, String> map = new HashMap<String, String>();
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDRECRUITINFORMATIONINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				UserRecruit userRecruit = null;
				List<UserRecruit> userRecruits = new ArrayList<UserRecruit>();
				try {
					if (response.getInt("code") == UserRecruitConstant.QUERY_FOR_RECRUIT_SUCCESS) {
						JSONArray array = response.getJSONArray("userRecruitsList");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							userRecruit = JsonUtils.readJsonToObject(UserRecruit.class, object.toString());
							userRecruits.add(userRecruit);
						}
						mAdapter = new FunctionRecruitAdapter(RecruitActivity.this, userRecruits);
						mListView.setAdapter(mAdapter);
						initEvents(userRecruits);
					}
				} catch (JSONException e) {
					Toast.makeText(RecruitActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(RecruitActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}
}
