package com.beyole.intelligentcampus.me.items;

import com.beyole.bean.GlobalParameterApplication;
import com.beyole.bean.User;
import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 我的资料
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class InformationActivity extends Activity {

	private TextView mNickName, mAccount, mSex, mDescription;
	private GlobalParameterApplication application;
	private User currentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_items_information);
		application = (GlobalParameterApplication) getApplicationContext();
		currentUser = application.getUser();
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_me_items_information_top).findViewById(R.id.id_top_banner_title);
		tv.setText("我的资料");
		mNickName = (TextView) findViewById(R.id.id_me_item_information_nickname);
		mAccount = (TextView) findViewById(R.id.id_me_item_information_account);
		mSex = (TextView) findViewById(R.id.id_me_item_information_sex);
		mDescription = (TextView) findViewById(R.id.id_me_item_information_userdescription);
		initData();
	}

	private void initData() {
		mNickName.setText(currentUser.getNickName());
		mAccount.setText(currentUser.getUserName());
		mSex.setText(currentUser.getUserSex() == 0 ? "男" : "女");
		mDescription.setText(currentUser.getUserDescription());
	}
}
