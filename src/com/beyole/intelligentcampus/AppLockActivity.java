package com.beyole.intelligentcampus;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.Toast;

import com.beyole.view.locker.GestureLockViewGroup;
import com.beyole.view.locker.GestureLockViewGroup.OnGestureLockViewListener;

/**
 * app应用锁
 * 
 * @date 2015/12/20
 * @author Iceberg
 * 
 */
public class AppLockActivity extends Activity {

	private GestureLockViewGroup gestureLockViewGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_lock_layout);
		boolean isSet = getIntent().getBooleanExtra("isSet", false);
		gestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.app_lock_gesturelocker);
		if (isSet == true) {
			gestureLockViewGroup.setGestureValueOn();
		} else {
			// 设定存储的权限
			SharedPreferences preferences = getSharedPreferences("intellligentCampus", MODE_PRIVATE);
			String resultValue = preferences.getString("locksecret", "1,2,3,4,5");
			String[] temp = resultValue.split(",");
			int[] temp1 = new int[temp.length];
			for (int i = 0; i < temp.length; i++) {
				temp1[i] = Integer.parseInt(temp[i]);
			}
			gestureLockViewGroup.setAnswer(temp1);
		}
		gestureLockViewGroup.setOnGestureLockViewListener(new OnGestureLockViewListener() {

			@Override
			public void onUnMatchedExceedBoundary() {
				Toast.makeText(AppLockActivity.this, "错误5次...", Toast.LENGTH_SHORT).show();
				gestureLockViewGroup.setOnUnMatchedExceedBoundary(5);
			}

			@Override
			public void onGestureEvent(boolean matched) {
				if (matched) {
					Intent intent = new Intent(AppLockActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				}
			}

			@Override
			public void onBlockSelected(int cId) {

			}

			@Override
			public void setGestureValue(List<Integer> result) {
				StringBuilder sb = new StringBuilder();
				if (result != null && result.size() > 0) {
					for (int i = 0; i < result.size(); i++) {
						if (i < result.size() - 1) {
							sb.append(result.get(i) + ",");
						} else {
							sb.append(result.get(i));
						}
					}
				}
				// 设定存储的权限
				SharedPreferences preferences = getSharedPreferences("intellligentCampus", MODE_PRIVATE);
				Editor editor = preferences.edit();
				editor.putString("locksecret", sb.toString());
				// 提交修改
				editor.commit();
				finish();
			}
		});
	}
}
