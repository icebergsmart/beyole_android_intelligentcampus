package com.beyole.intelligentcampus.functions.life.fragment;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.bean.CourseAlbum;
import com.beyole.bean.CourseCategoryItem;
import com.beyole.constant.APIConstant;
import com.beyole.constant.CourseCategoryConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.adapter.CourseVideoListViewAdapter;
import com.beyole.intelligentcampus.functions.life.adapter.SubCourseCategoryGridViewAdapter;
import com.beyole.intelligentcampus.functions.life.ui.NoScrollGridView;
import com.beyole.intelligentcampus.functions.life.ui.NoScrollListView;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

/**
 * 详细课程栏目下的视频列表，有分类页面（可切换）
 * 
 * @date 2015/12/1
 * @author Iceberg
 * 
 */
public class CourseListActivity extends Activity {

	private NoScrollGridView mGridView;
	private SubCourseCategoryGridViewAdapter mAdapter;
	private List<CourseCategoryItem> data = new ArrayList<CourseCategoryItem>();
	private NoScrollListView mListView;
	private CourseVideoListViewAdapter mListViewAdapter;
	private TextView mTopTitle;
	private int currentCategoryId;
	private LinearLayout mHiddenNoResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function_life_course_details_list_layout);
		initViews();
	}

	private void initViews() {
		mGridView = (NoScrollGridView) findViewById(R.id.function_life_details_list_gridview);
		mListView = (NoScrollListView) findViewById(R.id.function_life_details_list_listview);
		mTopTitle = (TextView) findViewById(R.id.id_top_banner_title);
		mHiddenNoResult = (LinearLayout) findViewById(R.id.id_course_details_list_hidden_ll);
		mTopTitle.setText(getIntent().getStringExtra("categoryName"));
		ArrayList list = getIntent().getParcelableArrayListExtra("categoryList");
		currentCategoryId = getIntent().getIntExtra("categoryId", 0);
		// 判断是否是精选栏目中点进来的推荐栏目 增加全部查询接口
		/*if (getIntent().getIntExtra("isRecommend", 0) != 1) {
			data.add(new CourseCategoryItem(0, 0, "全部", 0));
			data.addAll((List<CourseCategoryItem>) list.get(0));
			mAdapter = new SubCourseCategoryGridViewAdapter(this, data);
			mAdapter.setClickItem(getIntent().getIntExtra("clickPosition", 0) + 1);
		} else {*/
			data.addAll((List<CourseCategoryItem>) list.get(0));
			mAdapter = new SubCourseCategoryGridViewAdapter(this, data);
			mAdapter.setClickItem(getIntent().getIntExtra("clickPosition", 0));
/*		}
*/		mGridView.setAdapter(mAdapter);
		getCourseAlbumsByCategoryId();
		mGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				currentCategoryId = data.get(position).getCategoryId();
				getCourseAlbumsByCategoryId();
				mAdapter.setClickItem(position);
				mAdapter.notifyDataSetChanged();
			}
		});
	}

	public void getCourseAlbumsByCategoryId() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("categoryId", currentCategoryId + "");
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDCOURSEALBUMBYCATEGORYIDINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				CourseAlbum courseAlbum = null;
				List<CourseAlbum> courseAlbums = new ArrayList<CourseAlbum>();
				try {
					if (response.getInt("code") == CourseCategoryConstant.QUERY_FOR_COURSE_ALBUM_SUCCESS) {
						JSONArray array = response.getJSONArray("courseAlbumList");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							courseAlbum = JsonUtils.readJsonToObject(CourseAlbum.class, object.toString());
							courseAlbums.add(courseAlbum);
						}
						mListViewAdapter = new CourseVideoListViewAdapter(CourseListActivity.this, courseAlbums);
						mListView.setAdapter(mListViewAdapter);
						mListView.setVisibility(View.VISIBLE);
						mHiddenNoResult.setVisibility(View.GONE);
						initCourseEvents(courseAlbums);
					} else if (response.getInt("code") == CourseCategoryConstant.QUERY_FOR_COURSE_ALBUM_SUCCESS_WITHOUT_RESULT) {
						mListView.setVisibility(View.GONE);
						mHiddenNoResult.setVisibility(View.VISIBLE);
					}
				} catch (JSONException e) {
					Toast.makeText(CourseListActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(CourseListActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(CourseListActivity.this.getApplicationContext()).addToRequestQueue(request);
	}

	public void initCourseEvents(final List<CourseAlbum> courseAlbums) {

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(CourseListActivity.this, DetailsCourseActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("courseInfo", courseAlbums.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

}
