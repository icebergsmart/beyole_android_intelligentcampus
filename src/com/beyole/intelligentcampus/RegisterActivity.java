package com.beyole.intelligentcampus;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.beyole.view.EditTextWithRightButton;
import com.beyole.view.EditTextWithRightButton.DrawableRightOnClickListener;

public class RegisterActivity extends Activity {

	// 用户名
	private EditTextWithRightButton mUsername;
	// 密码
	private EditTextWithRightButton mPassword;
	// 确认密码
	private EditTextWithRightButton mConfirmPassword;
	// 描述名言
	private TextView mRegisterDescription;

	private Button mRegisterButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initViews();
		initEvents();
	}

	/**
	 * 添加事件监听
	 */
	private void initEvents() {
		MyDrawableRightOnClickListener listener = new MyDrawableRightOnClickListener();
		mUsername.setDrawableRightListener(listener);
		mPassword.setDrawableRightListener(listener);
		mConfirmPassword.setDrawableRightListener(listener);
		MyOnClickListener clickListener = new MyOnClickListener();
		mRegisterButton.setOnClickListener(clickListener);
	}

	class MyOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.id_activity_register_btn_registerbutton:

				break;
			}
		}
	}

	/**
	 * 自定义EditText监听事件接口
	 * 
	 * @author Iceberg
	 * 
	 */
	class MyDrawableRightOnClickListener implements DrawableRightOnClickListener {
		@Override
		public void onDrawableRightClick(View view) {
			EditTextWithRightButton et = (EditTextWithRightButton) view;
			et.setText("");
		}
	}

	/**
	 * 初始化view,设置字体
	 */
	private void initViews() {
		mUsername = (EditTextWithRightButton) findViewById(R.id.id_activity_register_et_myusername);
		mPassword = (EditTextWithRightButton) findViewById(R.id.id_activity_register_et_mypassword);
		mConfirmPassword = (EditTextWithRightButton) findViewById(R.id.id_activity_register_et_myconfirmpassword);
		mRegisterDescription = (TextView) findViewById(R.id.id_activity_register_my_description);
		mRegisterButton = (Button) findViewById(R.id.id_activity_register_btn_registerbutton);
		AssetManager assets = getAssets();
		Typeface tf = Typeface.createFromAsset(assets, "fonts/default.otf");
		mRegisterDescription.setTypeface(tf);
	}
}
