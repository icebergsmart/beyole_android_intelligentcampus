package com.beyole.intelligentcampus.functions.convenient;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beyole.bean.ClassroomStatus;
import com.beyole.bean.Course1;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.convenient.ui.SpaceImageDetailActivity;
import com.beyole.intelligentcampus.functions.convenient.ui.SyncHorizonScrollView;
import com.beyole.intelligentcampus.functions.convenient.ui.SyncScrollView;
import com.beyole.ninegridviewexpand.Image;
import com.beyole.ninegridviewexpand.NineGridlayout;
import com.beyole.ninegridviewexpand.NineGridlayout.OnItemClickListerner;

/**
 * 教室查询
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class ExpressActivity extends Activity {
	private List<Image> imagesList;
	private List<String> imagesUrl;
	private NineGridlayout mNineGridLayout;

	private RelativeLayout courseContent;
	private List<Course1> courses = new ArrayList<Course1>();
	private String[][] images = new String[][] { { "http://img1.3lian.com/img013/v5/22/d/21.jpg", "250", "250" }, { "http://cdn.duitang.com/uploads/item/201307/22/20130722113725_E8Akc.jpeg", "250", "250" },
			{ "http://u.thsi.cn/fileupload/data/Input/2012/12/26/bd18a700e9b6b64db757605daf9784f8.jpg", "250", "250" }, { "http://b.zol-img.com.cn/desk/bizhi/image/7/1366x768/1447663398589.jpg", "250", "250" },
			{ "http://u.thsi.cn/fileupload/data/Input/2012/12/26/bd18a700e9b6b64db757605daf9784f8.jpg", "250", "250" }, { "http://b.zol-img.com.cn/desk/bizhi/image/7/1366x768/1447663398589.jpg", "250", "250" }, { "http://b.zol-img.com.cn/desk/bizhi/image/7/1366x768/1447663398589.jpg", "250", "250" },
			{ "http://img5.duitang.com/uploads/item/201408/15/20140815164331_XHart.jpeg", "1280", "800" }, { "http://u.thsi.cn/fileupload/data/Input/2012/12/26/bd18a700e9b6b64db757605daf9784f8.jpg", "1280", "800" } };

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

		GridView courseNum = (GridView) findViewById(R.id.gridCourseNum);
		String[] courseNums = { "A101", "A102", "A103", "A104", "A105", "A106", "A107", "A108", "A109", "A110", "A111", "A112" };
		ArrayAdapter<String> leftAdap = new ArrayAdapter<String>(this, R.layout.coursenumstyle, courseNums);
		courseNum.setAdapter(leftAdap);

		courseContent = (RelativeLayout) findViewById(R.id.relativeCourseContent);
		for (int i = 1; i <=16; i++) {
			for (int j = 1; j <=12; j++) {
				Button course = createCourse(i, j, j, new ClassroomStatus(1, Math.random()>=0.5?0:2, 1, 1));
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

	private void initViews() {
		initData();
		TextView tv = (TextView) findViewById(R.id.id_convenient_express_top).findViewById(R.id.id_top_banner_title);
		tv.setText("教室查询");
		mNineGridLayout = (NineGridlayout) findViewById(R.id.iv_ngrid_layout);
		mNineGridLayout.setImagesData(imagesList);
		mNineGridLayout.setOnItemClickListerner(new OnItemClickListerner() {

			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(ExpressActivity.this, SpaceImageDetailActivity.class);
				intent.putExtra("images", (ArrayList<String>) imagesUrl);
				intent.putExtra("position", position);
				int[] location = new int[2];
				view.getLocationOnScreen(location);
				intent.putExtra("locationX", location[0]);
				intent.putExtra("locationY", location[1]);

				intent.putExtra("width", view.getWidth());
				intent.putExtra("height", view.getHeight());
				startActivity(intent);
				// Toast.makeText(ExpressActivity.this, "您点击了第" + (position + 1) +
				// "张图片！", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private void initData() {
		imagesList = new ArrayList<Image>();
		imagesUrl = new ArrayList<String>();
		// 从一到9生成9条朋友圈内容，分别是1~9张图片
		for (int j = 0; j < 9; j++) {
			imagesList.add(new Image(images[j][0], Integer.parseInt(images[j][1]), Integer.parseInt(images[j][2])));
			imagesUrl.add(images[j][0]);
		}
	}

	private Button createCourse(int week, int firstCourseNum, int lastCourseNum, final ClassroomStatus c) {
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
		if (c.getStatus() == 0) {
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

	public void setCourseContent(int week, int firstCourseNum, int lastCourseNum, ClassroomStatus c) {
		Button course = createCourse(week, firstCourseNum, lastCourseNum, c);
		courseContent.addView(course);

	}
}
