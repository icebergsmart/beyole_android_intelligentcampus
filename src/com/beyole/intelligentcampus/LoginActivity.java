package com.beyole.intelligentcampus;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.beyole.view.EditTextWithRightButton;
import com.beyole.view.EditTextWithRightButton.DrawableRightOnClickListener;

public class LoginActivity extends Activity {
	// 登录描述名言
	private TextView mLoginDescription;
	// 登录用户名
	private EditTextWithRightButton mUsername;
	// 登录密码
	private EditTextWithRightButton mPassword;
	// 登录按钮
	private Button mLoginButton;
	// 立即注册按钮
	private TextView mRegisterNow;
	// 忘记密码按钮
	private TextView mForgetPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initViews();
		initEvents();
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

	class MyOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.id_activity_login_btn_login:

				break;
			case R.id.id_activity_login__tv_login_registernow:
				Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
				finish();
				break;
			case R.id.id_activity_login_tv_forget_password:

				break;
			}
		}
	}

	/**
	 * 添加事件监听
	 */
	private void initEvents() {
		MyDrawableRightOnClickListener listener = new MyDrawableRightOnClickListener();
		mUsername.setDrawableRightListener(listener);
		mPassword.setDrawableRightListener(listener);
		MyOnClickListener onClickListener = new MyOnClickListener();
		mLoginButton.setOnClickListener(onClickListener);
		mRegisterNow.setOnClickListener(onClickListener);
		mForgetPassword.setOnClickListener(onClickListener);
	}

	/**
	 * 初始化view
	 */
	private void initViews() {
		mLoginDescription = (TextView) findViewById(R.id.id_activity_login_my_description);
		mUsername = (EditTextWithRightButton) findViewById(R.id.id_activity_login_et_myusername);
		mPassword = (EditTextWithRightButton) findViewById(R.id.id_activity_login_et_mypassword);
		mLoginButton = (Button) findViewById(R.id.id_activity_login_btn_login);
		mRegisterNow = (TextView) findViewById(R.id.id_activity_login__tv_login_registernow);
		mForgetPassword = (TextView) findViewById(R.id.id_activity_login_tv_forget_password);
		AssetManager assets = getAssets();
		Typeface tf = Typeface.createFromAsset(assets, "fonts/default.otf");
		mLoginDescription.setTypeface(tf);
	}
}
