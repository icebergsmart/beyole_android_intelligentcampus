package com.beyole.intelligentcampus.functions.life.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.bean.AllCourseCategory;
import com.beyole.bean.AllCourseCategoryItem;
import com.beyole.bean.CourseCategory;
import com.beyole.bean.CourseCategoryItem;
import com.beyole.constant.APIConstant;
import com.beyole.constant.CourseCategoryConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.adapter.AllCourseCategoryListViewAdapter;
import com.beyole.intelligentcampus.functions.life.adapter.CourseCategoryGridViewAdapter;
import com.beyole.intelligentcampus.functions.life.ui.NoScrollListView;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

/**
 * 公开课下所有课程的fragment
 * 
 * @date 2015/12/1
 * @author Iceberg
 */
public class AllCourseFragment extends Fragment {

	private View mView;
	private NoScrollListView mListView;
	private AllCourseCategoryListViewAdapter mAdapter;
	private ScrollView mContainer;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.function_life_course_all_fragment_layout, container, false);
		initViews();
		return mView;
	}

	private void initViews() {
		mListView = (NoScrollListView) mView.findViewById(R.id.function_all_course_main_listview);
		mContainer = (ScrollView) mView.findViewById(R.id.function_all_course_container);
		getAllCategories();
	}

	public void getAllCategories() {
		Map<String, String> map = new HashMap<String, String>();
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDALLCOURSECATEGORIESINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				AllCourseCategoryItem allCourseCategoryItem = null;
				List<AllCourseCategoryItem> allCourseCategoryItems = new ArrayList<AllCourseCategoryItem>();
				try {
					if (response.getInt("code") == CourseCategoryConstant.QUERY_FOR_ALL_CATEGORY_SUCCESS) {
						JSONArray array = response.getJSONArray("allCategoriesList");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							allCourseCategoryItem = JsonUtils.readJsonToObject(AllCourseCategoryItem.class, object.toString());
							allCourseCategoryItems.add(allCourseCategoryItem);
						}
						mAdapter = new AllCourseCategoryListViewAdapter(getActivity(), allCourseCategoryItems);
						mListView.setAdapter(mAdapter);
						initEvents(allCourseCategoryItems);
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

	public void initEvents(final List<AllCourseCategoryItem> allCourseCategoryItems) {

	}
}
