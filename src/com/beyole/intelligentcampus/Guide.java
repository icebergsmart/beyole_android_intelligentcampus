package com.beyole.intelligentcampus;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.beyole.adapter.ViewpaperAdapter;

/**
 * app引导界面
 * 
 * @author Iceberg
 * 
 */
public class Guide extends Activity implements OnPageChangeListener {

	// 定义viewpager对象
	private ViewPager vp;
	// 定义viewpager适配器
	private ViewpaperAdapter viewpaperAdapter;
	// 定义viewpager传入的view对象list
	private List<View> views;
	// 定义滑动时小白点的imageview对象数组
	private ImageView[] dots;
	// 定义小白点的id信息
	private int[] dotsId = { R.id.indicator_p1, R.id.indicator_p2, R.id.indicator_p3 };
	// 定义启动进入主页面的button信息
	private Button startBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.indicator_viewpager);
		// 初始化viewpager页面组件
		initViews();
		// 初始化小白点组件
		initDots();
	}

	private void initViews() {
		// 获取布局inflater
		LayoutInflater inflater = LayoutInflater.from(this);
		// 初始化view列表对象
		views = new ArrayList<View>();
		// 获取三个引导页面的view
		views.add(inflater.inflate(R.layout.indicator_one, null));
		views.add(inflater.inflate(R.layout.indicator_two, null));
		views.add(inflater.inflate(R.layout.indicator_three, null));
		// 初始化适配器
		viewpaperAdapter = new ViewpaperAdapter(views, this);
		// 实例化viewpager对象
		vp = (ViewPager) findViewById(R.id.viewPager);
		// 填充适配器
		vp.setAdapter(viewpaperAdapter);
		// 设置引导页滑动监听事件
		vp.setOnPageChangeListener(this);
		// 获取启动按钮
		startBtn = (Button) views.get(2).findViewById(R.id.startBtn);
		// 设置按钮监听事件
		startBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 从引导页跳转到主页面
				Intent intent = new Intent(Guide.this, MainActivity.class);
				startActivity(intent);
				// 结束当前activity
				finish();
			}
		});
	}

	// 初始化小白点方法
	private void initDots() {
		// 初始化小白点的对象数组
		dots = new ImageView[views.size()];
		for (int i = 0; i < views.size(); i++) {
			// 实例化小白点的对象数组
			dots[i] = (ImageView) findViewById(dotsId[i]);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		// 设置小白点的样式图片
		for (int i = 0; i < dotsId.length; i++) {
			if (arg0 == i) {
				dots[i].setImageResource(R.drawable.login_point_selected);
			} else {
				dots[i].setImageResource(R.drawable.login_point);
			}
		}
	}
}
