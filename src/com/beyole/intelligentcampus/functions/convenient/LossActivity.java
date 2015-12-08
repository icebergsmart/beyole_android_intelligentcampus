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
import com.beyole.adapter.FunctionLossAdapter;
import com.beyole.bean.UserLoss;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserLossConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

/**
 * 失物招领
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class LossActivity extends Activity {

	private ListView mListView;
	private FunctionLossAdapter mUserLossAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_loss);
		initViews();
	}

	private void initEvents(final List<UserLoss> userLosses) {

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(LossActivity.this, LossDetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("lossdetails", userLosses.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_loss_top).findViewById(R.id.id_top_banner_title);
		tv.setText("失物招领");
		mListView = (ListView) findViewById(R.id.function_loss_listview);
		getUserLosses();
	}

	public void getUserLosses() {
		Map<String, String> map = new HashMap<String, String>();
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDUSERLOSSINFORMATIONINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				UserLoss userLoss = null;
				List<UserLoss> userLosses = new ArrayList<UserLoss>();
				try {
					if (response.getInt("code") == UserLossConstant.QUERY_USERLOSS_SUCCESS) {
						JSONArray array = response.getJSONArray("userLossList");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							userLoss = JsonUtils.readJsonToObject(UserLoss.class, object.toString());
							userLosses.add(userLoss);
						}
						mUserLossAdapter = new FunctionLossAdapter(LossActivity.this, userLosses);
						mListView.setAdapter(mUserLossAdapter);
						initEvents(userLosses);
					}
				} catch (JSONException e) {
					Toast.makeText(LossActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(LossActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}
}
