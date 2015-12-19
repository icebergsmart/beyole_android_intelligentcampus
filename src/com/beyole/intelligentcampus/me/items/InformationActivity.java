package com.beyole.intelligentcampus.me.items;

import com.beyole.bean.GlobalParameterApplication;
import com.beyole.bean.User;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.me.items.change.EditUserDescriptionActivity;
import com.beyole.intelligentcampus.me.items.change.EditUserNickNameActivity;
import com.beyole.intelligentcampus.me.items.change.EditUserPasswordActivity;
import com.beyole.intelligentcampus.me.items.change.EditUserSexActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;
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
	private TableRow mUserDescriptionTableRow, mUserSexTableRow, mUserNickNameTableRow, mUserPasswordTableRow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_items_information);
		application = (GlobalParameterApplication) getApplicationContext();
		currentUser = application.getUser();
		initViews();

	}

	private void initEvents() {
		MyOnClickListener listener = new MyOnClickListener();
		mUserDescriptionTableRow.setOnClickListener(listener);
		mUserSexTableRow.setOnClickListener(listener);
		mUserNickNameTableRow.setOnClickListener(listener);
		mUserPasswordTableRow.setOnClickListener(listener);
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_me_items_information_top).findViewById(R.id.id_top_banner_title);
		tv.setText("我的资料");
		mNickName = (TextView) findViewById(R.id.id_me_item_information_nickname);
		mAccount = (TextView) findViewById(R.id.id_me_item_information_account);
		mSex = (TextView) findViewById(R.id.id_me_item_information_sex);
		mDescription = (TextView) findViewById(R.id.id_me_item_information_userdescription);
		mUserDescriptionTableRow = (TableRow) findViewById(R.id.me_items_information_userdescription_row);
		mUserSexTableRow = (TableRow) findViewById(R.id.me_items_information_usersex_row);
		mUserNickNameTableRow = (TableRow) findViewById(R.id.me_items_information_nickname_row);
		mUserPasswordTableRow = (TableRow) findViewById(R.id.me_items_information_password_row);
	}

	class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.me_items_information_userdescription_row:
				Intent intent = new Intent(InformationActivity.this, EditUserDescriptionActivity.class);
				intent.putExtra("content", currentUser.getUserDescription());
				startActivity(intent);
				break;
			case R.id.me_items_information_usersex_row:
				Intent intent1 = new Intent(InformationActivity.this, EditUserSexActivity.class);
				startActivity(intent1);
				break;
			case R.id.me_items_information_nickname_row:
				Intent intent2 = new Intent(InformationActivity.this, EditUserNickNameActivity.class);
				startActivity(intent2);
				break;
			case R.id.me_items_information_password_row:
				Intent intent3 = new Intent(InformationActivity.this, EditUserPasswordActivity.class);
				startActivity(intent3);
				break;
			}
		}

	}

	private void initData() {
		mNickName.setText(currentUser.getNickName());
		mAccount.setText(currentUser.getUserName());
		mSex.setText(currentUser.getUserSex() == 0 ? "男" : "女");
		mDescription.setText(currentUser.getUserDescription());
	}

	@Override
	protected void onResume() {
		super.onResume();
		initData();
		initEvents();
	}
}
