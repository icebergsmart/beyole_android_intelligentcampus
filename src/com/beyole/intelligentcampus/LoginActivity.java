package com.beyole.intelligentcampus;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beyole.notifydialog.widget.effectdialog.Effectstype;
import com.beyole.notifydialog.widget.effectdialog.NiftyDialogBuilder;
import com.beyole.view.EditTextWithRightButton;
import com.beyole.view.EditTextWithRightButton.DrawableRightOnClickListener;
import com.beyole.view.commondialog.CommonDialog;
import com.beyole.view.commondialog.LoginCommonDialog;
import com.beyole.view.commondialog.CommonDialog.DialogNegativeListener;
import com.beyole.view.commondialog.CommonDialog.DialogPositiveListener;

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

	private LoginCommonDialog dialog;

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
				// 显示进度条
				// showLoginDialog();
				MyAsyncTask asyncTask = new MyAsyncTask();
				asyncTask.execute();
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

	private void showLoginDialog() {
		dialog = new LoginCommonDialog(LoginActivity.this);
		dialog.initDialog("正在登录中...", "退出", "再看看", LoginActivity.this).show();
	}

	class MyAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			showLoginDialog();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			dialog.dismissLoginDialog();
			Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
		}
	}

}
