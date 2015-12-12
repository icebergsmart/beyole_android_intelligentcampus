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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.bean.CourseAlbum;
import com.beyole.bean.CourseCategoryItem;
import com.beyole.constant.APIConstant;
import com.beyole.constant.CourseAlbumConstant;
import com.beyole.constant.CourseCategoryConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.adapter.CourseCategoryGridViewAdapter;
import com.beyole.intelligentcampus.functions.life.adapter.CourseDetailsGridViewAdapter;
import com.beyole.intelligentcampus.functions.life.ui.NoScrollGridView;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

/**
 * 公开课下精选课程fragment详情页
 * 
 * @date 2015/12/1
 * @author Iceberg
 * 
 */
public class DelicateCourseFragment extends Fragment {
	private View mView;
	private NoScrollGridView mGridView;
	private NoScrollGridView mDetailsGridView;
	private CourseCategoryGridViewAdapter mAdapter;
	private CourseDetailsGridViewAdapter mDetailsAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.function_life_course_delicate_fragment_layout, container, false);
		initViews();
		return mView;
	}

	private void initViews() {
		mGridView = (NoScrollGridView) mView.findViewById(R.id.id_function_delicate_course_gridview);
		mDetailsGridView = (NoScrollGridView) mView.findViewById(R.id.id_function_delicate_course_details_gridview);
		getRecommendCategories();
		getRecommendCourseAlbums();
	}

	public void getRecommendCategories() {
		Map<String, String> map = new HashMap<String, String>();
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDRECOMMENDCOURSECATEGORYINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				CourseCategoryItem courseCategoryItem = null;
				List<CourseCategoryItem> courseCategoryItems = new ArrayList<CourseCategoryItem>();
				try {
					if (response.getInt("code") == CourseCategoryConstant.QUERY_FOR_RECOMMEND_CATEGORY_SUCCESS) {
						JSONArray array = response.getJSONArray("categoriesList");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							courseCategoryItem = JsonUtils.readJsonToObject(CourseCategoryItem.class, object.toString());
							courseCategoryItems.add(courseCategoryItem);
						}
						mAdapter = new CourseCategoryGridViewAdapter(getActivity(), courseCategoryItems);
						mGridView.setAdapter(mAdapter);
						initEvents(courseCategoryItems);
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

	public void initEvents(final List<CourseCategoryItem> courseCategoryItems) {
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), CourseListActivity.class);
				ArrayList list = new ArrayList();
				list.add(courseCategoryItems);
				Bundle bundle = new Bundle();
				bundle.putParcelableArrayList("categoryList", list);
				bundle.putInt("clickPosition", position);
				bundle.putInt("categoryId", courseCategoryItems.get(position).getCategoryId());
				bundle.putString("categoryName", "推荐栏目");
				bundle.putInt("isRecommend", 1);
				intent.putExtras(bundle);
				getActivity().startActivity(intent);
			}
		});
	}

	public void getRecommendCourseAlbums() {
		Map<String, String> map = new HashMap<String, String>();
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDALLRECOMMENDCOURSEALBUMINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				CourseAlbum courseAlbum = null;
				List<CourseAlbum> courseAlbums = new ArrayList<CourseAlbum>();
				try {
					if (response.getInt("code") == CourseAlbumConstant.QUERY_FOR_RECOMMEND_COURSE_ALBUM_SUCCESS) {
						JSONArray array = response.getJSONArray("recommendAlbumList");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							courseAlbum = JsonUtils.readJsonToObject(CourseAlbum.class, object.toString());
							courseAlbums.add(courseAlbum);
						}
						mDetailsAdapter = new CourseDetailsGridViewAdapter(getActivity(), courseAlbums);
						mDetailsGridView.setAdapter(mDetailsAdapter);
						initCourseEvents(courseAlbums);
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

	public void initCourseEvents(final List<CourseAlbum> courseAlbums) {
		mDetailsGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), DetailsCourseActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("courseInfo", courseAlbums.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
			}

		});
	}
}
