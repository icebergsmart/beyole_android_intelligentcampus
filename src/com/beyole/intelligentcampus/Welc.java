package com.beyole.intelligentcampus;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

/**
 * app欢迎界面
 * 
 * @author Iceberg
 * 
 */
public class Welc extends Activity {

	// 用来判断是否为第一次进入此应用
	private boolean isFirstIn = false;
	// 设置跳转时间为2s
	private static final int TIME = 2000;
	// 设置跳转到主页面的handler消息为1000
	private static final int GO_HOME = 1000;
	// 设置跳转到引导页面的handler消息为1001
	private static final int GO_GUIDE = 1001;
	// 在handler事件中进行页面的跳转
	private Handler myHandler = new Handler() {
		// 处理传来的消息
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			// 跳转到主页面
			case GO_HOME:
				goHome();
				break;
			// 跳转到引导页面
			case GO_GUIDE:
				goGuide();
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
	// 调用初始化方法
			init();
	}
	private void init() {
		// 设定存储的权限
		SharedPreferences preferences = getSharedPreferences("intellligentCampus", MODE_PRIVATE);
		// 第一次取值是没有的，所以我们第一次赋值为true
		isFirstIn = preferences.getBoolean("isFirstIn", true);
		// 如果不是第一次进入应用，就发送跳转到主页面的消息
		if (!isFirstIn) {
			myHandler.sendEmptyMessage(GO_HOME);
		} else {
			// 如果是第一次进入应用，就发送跳转到引导页面的消息
			myHandler.sendEmptyMessage(GO_GUIDE);
			// 跳转完成之后，修改isFirstIn值为false
			Editor editor = preferences.edit();
			editor.putBoolean("isFirstIn", false);
			// 提交修改
			editor.commit();
		}
	}

	// 跳转到主页面的的方法
	private void goHome() {
		// 设定存储的权限
		SharedPreferences preferences = getSharedPreferences("intellligentCampus", MODE_PRIVATE);
		// 第一次取值是没有的，所以我们第一次赋值为true
		boolean isLockOn = preferences.getBoolean("isLockOn", false);
		Intent intent = null;
		if (isLockOn == false) {
			intent = new Intent(Welc.this, MainActivity.class);
		} else {
			intent = new Intent(Welc.this, AppLockActivity.class);
		}
		startActivity(intent);
		// 结束当前activity
		finish();
	}

	// 跳转到引导页面的方法
	private void goGuide() {
		Intent intent = new Intent(Welc.this, Guide.class);
		startActivity(intent);
		// 结束当前activity
		finish();
	}
}
