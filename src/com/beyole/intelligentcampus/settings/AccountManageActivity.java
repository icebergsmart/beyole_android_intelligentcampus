package com.beyole.intelligentcampus.settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beyole.bean.GlobalParameterApplication;
import com.beyole.bean.User;
import com.beyole.intelligentcampus.R;

/**
 * 帐号管理页面
 * 
 * @date 2015/12/20
 * @author Iceberg
 * 
 */
public class AccountManageActivity extends Activity {

	private LinearLayout mExitAccountLL;
	private Button mExitAccountBtn;
	private TextView mExitAccountNoResult;
	private GlobalParameterApplication application;
	private User currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_account_manage_layout);
		application = (GlobalParameterApplication) getApplicationContext();
		currentUser = application.getUser();
		initViews();
		initEvent();
	}

	private void initEvent() {
		if (currentUser != null && currentUser.getUserId() > 0) {
			mExitAccountLL.setVisibility(View.VISIBLE);
			mExitAccountBtn.setVisibility(View.VISIBLE);
			mExitAccountNoResult.setVisibility(View.GONE);
			mExitAccountBtn.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					application.setUser(null);
					Toast.makeText(AccountManageActivity.this, "退出帐号成功", Toast.LENGTH_SHORT).show();
					AccountManageActivity.this.finish();
				}
			});
		} else {
			mExitAccountLL.setVisibility(View.GONE);
			mExitAccountBtn.setVisibility(View.GONE);
			mExitAccountNoResult.setVisibility(View.VISIBLE);
		}
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.settings_account_manage_top).findViewById(R.id.id_top_banner_title);
		tv.setText("帐号管理");
		mExitAccountLL = (LinearLayout) findViewById(R.id.settings_account_manage_exit_account_ll);
		mExitAccountBtn = (Button) findViewById(R.id.settings_account_manage_exit_account_btn);
		mExitAccountNoResult = (TextView) findViewById(R.id.settings_account_manage_exit_account_no_result);
	}
}
