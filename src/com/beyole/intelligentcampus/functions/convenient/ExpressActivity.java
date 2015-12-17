package com.beyole.intelligentcampus.functions.convenient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.bean.Classroom;
import com.beyole.constant.APIConstant;
import com.beyole.constant.ClassroomConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.convenient.ui.SyncHorizonScrollView;
import com.beyole.intelligentcampus.functions.convenient.ui.SyncScrollView;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

/**
 * 教室查询
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class ExpressActivity extends Activity {

	private RelativeLayout courseContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_express);
		initViews();
		initEvents();
	}

	private void initEvents() {
		GridView head = (GridView) findViewById(R.id.gridWeek);
		String[] weeks = { "06:00-07:00", "07:00-08:00", "08:00-09:00", "09:00-10:00", "10:00-11:00", "11:00-12:00", "12:00-13:00", "13:00-14:00", "14:00-15:00", "15:00-16:00", "16:00-17:00", "17:00-18:00", "18:00-19:00", "19:00-20:00", "20:00-21:00", "21:00-22:00" };
		ArrayAdapter<String> headAdap = new ArrayAdapter<String>(this, R.layout.weekstyle, weeks);
		head.setAdapter(headAdap);
		getClassroomStatus();
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_express_top).findViewById(R.id.id_top_banner_title);
		tv.setText("教室查询");
	}

	private Button createCourse(int week, int firstCourseNum, int lastCourseNum, final Classroom c) {
		int leftMargin = 0;
		int topMargin = 0;
		int courseLength = 0;
		int courseWidth = (int) (getResources().getDimension(R.dimen.course_week_item_width));
		int courseHeight = (int) (getResources().getDimension(R.dimen.course_num_item_height));
		int week_item_height = (int) (getResources().getDimension(R.dimen.course_week_height));
		int courseNum_width = (int) (getResources().getDimension(R.dimen.course_num_width));
		int spacing = (int) (getResources().getDimension(R.dimen.spacing));
		leftMargin = (week - 1) * (courseWidth + spacing) + courseNum_width;
		topMargin = (firstCourseNum - 1) * (courseHeight + spacing) + week_item_height;
		courseLength = (lastCourseNum - firstCourseNum + 1) * (courseHeight + spacing) - spacing;

		Button course = new Button(this);
		RelativeLayout.LayoutParams marginParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		marginParams.setMargins(leftMargin, topMargin, 0, 0);
		course.setTag(week + "" + (lastCourseNum / 2));
		course.setLayoutParams(marginParams);
		course.setGravity(Gravity.CENTER);
		course.setWidth(courseWidth);
		course.setHeight(courseLength);
		if (c.getUsingStatus() == 0) {
			course.setBackgroundColor(getResources().getColor(R.color.courseColor));
			course.setText("空闲中");
		} else {
			course.setBackgroundColor(0XFFFFA3A3);
			course.setText("使用中");
		}
		course.setTextSize(15);
		course.setTextColor(Color.WHITE);
		return course;
	}

	public void setCourseContent(int week, int firstCourseNum, int lastCourseNum, Classroom c) {
		Button course = createCourse(week, firstCourseNum, lastCourseNum, c);
		courseContent.addView(course);

	}

	public void getClassroomStatus() {
		Map<String, String> map = new HashMap<String, String>();
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDCLASSROOMUSESTATUSINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				Classroom classroom = null;
				List<Classroom> classrooms = new ArrayList<Classroom>();
				try {
					if (response.getInt("code") == ClassroomConstant.QUERY_FOR_CLASSROOM_SUCCESS) {
						JSONArray array = response.getJSONArray("classroomStatusList");
						for (int i = 0; i < array.length(); i++) {
							JSONObject object = array.getJSONObject(i);
							classroom = JsonUtils.readJsonToObject(Classroom.class, object.toString());
							classrooms.add(classroom);
						}
						setData(classrooms);
					}
				} catch (JSONException e) {
					Toast.makeText(ExpressActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(ExpressActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}

	public void setData(final List<Classroom> classrooms) {
		GridView courseNum = (GridView) findViewById(R.id.gridCourseNum);
		int classroomNum = classrooms.size() / 16;
		String[] courseNums = new String[classroomNum];
		for (int i = 0; i < classroomNum; i++) {
			courseNums[i] = classrooms.get(i * 16).getClassroomName();
		}
		ArrayAdapter<String> leftAdap = new ArrayAdapter<String>(this, R.layout.coursenumstyle, courseNums);
		courseNum.setAdapter(leftAdap);

		courseContent = (RelativeLayout) findViewById(R.id.relativeCourseContent);
		for (int i = 1; i <= classroomNum; i++) {
			for (int j = 1; j <= 16; j++) {
				Button course = createCourse(j, i, i, classrooms.get((i - 1) * 16 + (j - 1)));
				courseContent.addView(course);
			}
		}
		SyncHorizonScrollView hCourseScoll = (SyncHorizonScrollView) findViewById(R.id.horizonScollviewOfCourse);
		SyncHorizonScrollView hWeekSColl = (SyncHorizonScrollView) findViewById(R.id.week);
		hCourseScoll.setAnotherView(hWeekSColl);
		hWeekSColl.setAnotherView(hCourseScoll);
		// sync vertical move
		SyncScrollView vCoursecoll = (SyncScrollView) findViewById(R.id.scollviewOfCourse);
		SyncScrollView vnumScoll = (SyncScrollView) findViewById(R.id.courseNum);
		vCoursecoll.setAnotherView(vnumScoll);
		vnumScoll.setAnotherView(vCoursecoll);
	}
}
