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
import com.beyole.bean.CourseVideo;
import com.beyole.constant.APIConstant;
import com.beyole.constant.CourseVideoConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.CoursePlayActivity;
import com.beyole.intelligentcampus.functions.life.adapter.CourseDetailsSheetListViewAdapter;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

/**
 * 详细课程对应的视频url列表页，可进行播放视频
 * 
 * @date 2015/12/1
 * @author Iceberg
 * 
 */
public class DetailsCourseSheetFragment extends Fragment {

	private View mView;
	private ListView mVideoSheet;
	private CourseDetailsSheetListViewAdapter mAdapter;
	private int currentAlbumId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.function_life_course_details_video_sheet_layout, container, false);
		initViews();
		return mView;
	}

	private void initViews() {
		Bundle bundle = getArguments();
		currentAlbumId = bundle.getInt("courseAlbumId");
		mVideoSheet = (ListView) mView.findViewById(R.id.id_stickynavlayout_innerscrollview);
		getCourseVideos();
	}

	public void getCourseVideos() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("courseId", currentAlbumId + "");
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDCOURSEVIDEOBYCOURSEIDINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				CourseVideo courseVideo = null;
				List<CourseVideo> courseVideos = new ArrayList<CourseVideo>();
				try {
					if (response.getInt("code") == CourseVideoConstant.QUERY_FOR_COURSE_VIDEO_BY_COURSEID_SUCCESS) {
						JSONArray array = response.getJSONArray("courseVideoList");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							courseVideo = JsonUtils.readJsonToObject(CourseVideo.class, object.toString());
							courseVideos.add(courseVideo);
						}
						mAdapter = new CourseDetailsSheetListViewAdapter(getActivity(), courseVideos);
						mVideoSheet.setAdapter(mAdapter);
						initEvents(courseVideos);
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

	public void initEvents(final List<CourseVideo> courseVideos) {
		mVideoSheet.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), CoursePlayActivity.class);
				intent.putExtra("videoUrl", courseVideos.get(position).getVideoUrl());
				startActivity(intent);
			}
		});
	}
}
