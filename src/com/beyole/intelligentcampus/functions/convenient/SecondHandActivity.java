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
import com.beyole.adapter.FunctionSecondAdapter;
import com.beyole.bean.UserSecondHand;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserSecondHandConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

/**
 * 二手市场
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class SecondHandActivity extends Activity {

	private ListView mListView;
	private FunctionSecondAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_secondhand);
		initViews();
	}

	private void initEvents(final List<UserSecondHand> list) {

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(SecondHandActivity.this, SecondHandDetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("goodsList", list.get(position));
				intent.putExtras(bundle);
				startActivity(intent);

			}
		});
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_secondhand_top).findViewById(R.id.id_top_banner_title);
		tv.setText("二手市场");
		mListView = (ListView) findViewById(R.id.id_function_second_listview);
		getUserSecondHand();
	}

	public void getUserSecondHand() {
		Map<String, String> map = new HashMap<String, String>();
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDSECONDHANDINFORMATIONINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				UserSecondHand userSecondHand = null;
				List<UserSecondHand> userSecondHands = new ArrayList<UserSecondHand>();
				try {
					if (response.getInt("code") == UserSecondHandConstant.QUERY_SECONDHAND_SUCCESS) {
						JSONArray array = response.getJSONArray("userSecondHandsList");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							userSecondHand = JsonUtils.readJsonToObject(UserSecondHand.class, object.toString());
							userSecondHands.add(userSecondHand);
						}
						mAdapter = new FunctionSecondAdapter(SecondHandActivity.this, userSecondHands);
						mListView.setAdapter(mAdapter);
						initEvents(userSecondHands);
					}
				} catch (JSONException e) {
					Toast.makeText(SecondHandActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(SecondHandActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}
}
