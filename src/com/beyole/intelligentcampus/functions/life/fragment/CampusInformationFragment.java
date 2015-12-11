package com.beyole.intelligentcampus.functions.life.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.bean.Article;
import com.beyole.constant.APIConstant;
import com.beyole.constant.ArticleConstant;
import com.beyole.intelligentcampus.ArticleDetailsActivity;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.adapter.CampusInformationListViewAdapter;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

/**
 * 校内新闻展示页
 * 
 * @date 2015/12/10
 * @author Iceberg
 * 
 */
public class CampusInformationFragment extends Fragment {

	private View mView;
	private ListView mListView;
	private CampusInformationListViewAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.function_life_campus_information_layout, container, false);
		initViews();
		return mView;
	}

	private void initEvents(final List<Article> articlesList) {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), ArticleDetailsActivity.class);
				intent.putExtra("articleId", articlesList.get(position).getArticleId());
				startActivity(intent);
			}
		});
	}

	private void initViews() {
		mListView = (ListView) mView.findViewById(R.id.id_function_life_campus_information_listview_main);
		getCampusInformations();
	}

	public void getCampusInformations() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("articleType", "0");
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDARTICLESBYTYPEWITHPAGERANK, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				Article article = null;
				List<Article> articles = new ArrayList<Article>();
				try {
					if (response.getInt("code") == ArticleConstant.QUERY_FOR_ARTICLE_BY_TYPE_SUCCESS) {
						JSONArray array = response.getJSONArray("articleList");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							article = JsonUtils.readJsonToObject(Article.class, object.toString());
							articles.add(article);
						}
						mAdapter = new CampusInformationListViewAdapter(getActivity(), articles);
						mListView.setAdapter(mAdapter);
						initEvents(articles);
					}
				} catch (JSONException e) {
					Toast.makeText(getActivity(), "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(getActivity(), "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(getActivity().getApplicationContext()).addToRequestQueue(request);
	}
}
