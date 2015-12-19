package com.beyole.intelligentcampus.me.items;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.beyole.bean.GlobalParameterApplication;
import com.beyole.bean.User;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.convenient.LossDetailsActivity;
import com.beyole.intelligentcampus.functions.convenient.ui.SpaceImageDetailActivity;
import com.beyole.intelligentcampus.me.items.change.EditUserDescriptionActivity;
import com.beyole.intelligentcampus.me.items.change.EditUserNickNameActivity;
import com.beyole.intelligentcampus.me.items.change.EditUserPasswordActivity;
import com.beyole.intelligentcampus.me.items.change.EditUserSexActivity;
import com.beyole.ninegridviewexpand.CustomImageView;
import com.beyole.util.DensityUtil;
import com.squareup.picasso.Picasso;

/**
 * 我的资料
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class InformationActivity extends Activity {

	private static final int UPDATE_ME_PHOTO = 0X001;
	private TextView mNickName, mAccount, mSex, mDescription;
	private GlobalParameterApplication application;
	private User currentUser;
	private TableRow mUserDescriptionTableRow, mUserSexTableRow, mUserNickNameTableRow, mUserPasswordTableRow;
	private ImageView mPhoto;

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
		mPhoto.setOnClickListener(listener);
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
		mPhoto = (ImageView) findViewById(R.id.id_me_items_information_imgphoto_im);
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
			case R.id.id_me_items_information_imgphoto_im:
				Intent intent4 = new Intent(InformationActivity.this, SpaceImageDetailActivity.class);
				intent4.putExtra("images", currentUser.getUserImagePhoto());
				int[] location = new int[2];
				v.getLocationOnScreen(location);
				v.setDrawingCacheEnabled(true);
				intent4.putExtra("bitmap", v.getDrawingCache());
				intent4.putExtra("locationX", location[0]);
				intent4.putExtra("locationY", location[1]);
				intent4.putExtra("width", v.getWidth());
				intent4.putExtra("height", v.getHeight());
				startActivity(intent4);
				break;
			}
		}

	}

	private void initData() {
		myHandler.sendEmptyMessage(UPDATE_ME_PHOTO);
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

	private Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UPDATE_ME_PHOTO:
				Picasso.with(InformationActivity.this).load(currentUser.getUserImagePhoto()).resize(DensityUtil.dip2px(InformationActivity.this, 30),DensityUtil.dip2px(InformationActivity.this, 30)).centerCrop().into(mPhoto);
				break;
			}
		};
	};
}
