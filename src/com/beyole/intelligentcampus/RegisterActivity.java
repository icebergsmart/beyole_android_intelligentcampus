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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beyole.bean.User;
import com.beyole.constant.APIConstant;
import com.beyole.constant.LoginConstant;
import com.beyole.constant.RegisterConstant;
import com.beyole.intelligentcampus.LoginActivity.MyAsyncTask;
import com.beyole.util.JsonUtils;
import com.beyole.util.StringUtil;
import com.beyole.util.SyncHttp;
import com.beyole.view.EditTextWithRightButton;
import com.beyole.view.EditTextWithRightButton.DrawableRightOnClickListener;
import com.beyole.view.commondialog.LoginCommonDialog;
import com.google.zxing.common.StringUtils;

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

	private LoginCommonDialog dialog;

	private String userNameString;
	private String passwordString;
	private String params1;
	private User currentUser;

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
				if (checkUserRegister()) {
					if (checkUserRegisterName()) {
						MyAsyncTask asyncTask = new MyAsyncTask();
						asyncTask.execute();
					} else {
						Toast.makeText(RegisterActivity.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(RegisterActivity.this, "两次输入密码不一致！", Toast.LENGTH_SHORT).show();
				}
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
		currentUser = (User) getApplication();
	}

	public boolean checkUserRegisterName() {
		String checkUsername = mUsername.getText().toString().trim().replace(" ", "");
		if (StringUtil.isEmpty(checkUsername)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checkUserRegister() {
		String passwordCheck = mPassword.getText().toString();
		String confirmPasswordCheck = mConfirmPassword.getText().toString();
		if (passwordCheck.equals(confirmPasswordCheck)) {
			return true;
		} else {
			return false;
		}
	}

	private void showRegisterDialog() {
		dialog = new LoginCommonDialog(RegisterActivity.this);
		dialog.initDialog("正在注册中，请稍后...", "退出", "再看看", RegisterActivity.this).show();
	}

	class MyAsyncTask extends AsyncTask<Void, Void, Map<String, Object>> {

		@Override
		protected void onPreExecute() {
			showRegisterDialog();
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
				String retStr = http.httpGet(APIConstant.REGISTERINTERFACE, params1);
				map = JsonUtils.readJsonToMap(retStr);
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
				case RegisterConstant.REGISTER_SUCCESS_WITH_THIS_USER:
					Toast.makeText(RegisterActivity.this, "注册成功！" + resultCode, Toast.LENGTH_SHORT).show();
					findUserByUserName(userNameString);
					Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
					break;
				case RegisterConstant.REGISTER_ERROR_WITH_EXIST_USERNAME:
					Toast.makeText(RegisterActivity.this, "此用户名被占用！" + resultCode, Toast.LENGTH_SHORT).show();
					break;
				case RegisterConstant.REGISTER_ERROR_WITH_ILLEGAL_USERNAME:
					Toast.makeText(RegisterActivity.this, "用户名含有非法字符！" + resultCode, Toast.LENGTH_SHORT).show();
					break;
				case RegisterConstant.REGISTER_ERROR_WITH_ILLEGAL_PASSWORD:
					Toast.makeText(RegisterActivity.this, "密码含有非法字符！" + resultCode, Toast.LENGTH_SHORT).show();
					break;
				case RegisterConstant.REGISTER_ERROR_WITH_OTHER_EXCEPTION:
					Toast.makeText(RegisterActivity.this, "服务器异常！" + resultCode, Toast.LENGTH_SHORT).show();
					break;
				case RegisterConstant.REGISTER_ERROR_WITH_NETWORK_EXCEPTION:
					Toast.makeText(RegisterActivity.this, "网络异常！" + resultCode, Toast.LENGTH_SHORT).show();
					break;
				case RegisterConstant.REGISTER_ERROR_WITH_EMPTY_USERNAME_OR_PASSWORD:
					Toast.makeText(RegisterActivity.this, "用户名或密码不能为空！" + resultCode, Toast.LENGTH_SHORT).show();
					break;
				}
			} else {
				Toast.makeText(RegisterActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
			}
		}
	}

	private User findUserByUserName(String userName) {
		User user = null;
		return currentUser;
	}
}
