package com.beyole.intelligentcampus;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beyole.bean.GlobalParameterApplication;
import com.beyole.bean.User;
import com.beyole.constant.APIConstant;
import com.beyole.constant.LoginConstant;
import com.beyole.util.JsonUtils;
import com.beyole.util.StringUtil;
import com.beyole.util.SyncHttp;
import com.beyole.view.EditTextWithRightButton;
import com.beyole.view.EditTextWithRightButton.DrawableRightOnClickListener;
import com.beyole.view.commondialog.LoginCommonDialog;

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

	private String userNameString;
	private String passwordString;
	private String params1;

	private GlobalParameterApplication application;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		application = (GlobalParameterApplication) getApplicationContext();
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
				if (checkLoginUsernameOrPassword()) {
					MyAsyncTask asyncTask = new MyAsyncTask();
					asyncTask.execute();
				} else {
					Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.id_activity_login__tv_login_registernow:
				Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
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

	public boolean checkLoginUsernameOrPassword() {
		String loginUsernameCheck = mUsername.getText().toString().trim().replace(" ", "");
		String loginUserPasswordCheck = mPassword.getText().toString().trim().replace(" ", "");
		if (StringUtil.isEmpty(loginUsernameCheck) || StringUtil.isEmpty(loginUserPasswordCheck)) {
			return false;
		} else {
			return true;
		}

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

	class MyAsyncTask extends AsyncTask<Void, Void, Map<String, Object>> {

		@Override
		protected void onPreExecute() {
			showLoginDialog();
			userNameString = mUsername.getText().toString().trim().replace(" ", "");
			passwordString = mPassword.getText().toString().trim().replace(" ", "");
			params1 = "userName=" + userNameString + "&password=" + passwordString;
		}

		@Override
		protected Map<String, Object> doInBackground(Void... params) {
			SyncHttp http = new SyncHttp();
			Map<String, Object> map = new HashMap<String, Object>();
			// 以Get方式请求，并获得返回结果
			try {
				String retStr = http.httpGet(APIConstant.LOGININTERFACE, params1);
				map = JsonUtils.readJsonToMap(retStr);
				Log.i("text", map + "");
			} catch (Exception e) {
				e.printStackTrace();
				map = null;
			}
			return map;
		}

		@Override
		protected void onPostExecute(Map<String, Object> result) {
			dialog.dismissLoginDialog();
			// 为了减轻服务器压力，以及短时间的重复登录，在此线程休眠2秒
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (result != null) {
				int resultCode = Integer.valueOf(result.get("requestCode").toString().trim().replace(" ", ""));
				switch (resultCode) {
				case LoginConstant.LOGIN_SUCCESS_WITH_THIS_USER:

					Toast.makeText(LoginActivity.this, "登录成功！" + resultCode, Toast.LENGTH_SHORT).show();
					break;
				case LoginConstant.LOGIN_ERROR_WITH_WRONGPASSWORD_OR_USERNAME:
					Toast.makeText(LoginActivity.this, "错误的用户名或密码" + resultCode, Toast.LENGTH_SHORT).show();
					break;
				case LoginConstant.LOGIN_SUCCESS:
					User user = JsonUtils.readJsonToObject(User.class, result.get("userinfo") + "");
					application.setUser(user);
					Toast.makeText(LoginActivity.this, "登录成功" + resultCode, Toast.LENGTH_SHORT).show();
				/*	Intent intent = new Intent(LoginActivity.this, MainActivity.class);
					startActivity(intent);*/
					finish();
					break;
				case LoginConstant.LOGIN_ERROR_WITH_NETWORK_EXCEPTION:
					Toast.makeText(LoginActivity.this, "网络异常" + resultCode, Toast.LENGTH_SHORT).show();
					break;
				case LoginConstant.LOGIN_ERROR_WITH_NO_SUCH_USER:
					Toast.makeText(LoginActivity.this, "不存在此用户" + resultCode, Toast.LENGTH_SHORT).show();
					break;
				case LoginConstant.LOGIN_ERROR_WITH_EMPTY_USERNAME_OR_PASSWORD:
					Toast.makeText(LoginActivity.this, "用户名或密码不能为空" + resultCode, Toast.LENGTH_SHORT).show();
					break;
				}
			} else {
				Toast.makeText(LoginActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
			return false;// 不向下分发事件
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
}
