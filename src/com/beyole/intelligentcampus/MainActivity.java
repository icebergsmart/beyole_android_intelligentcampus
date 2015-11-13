package com.beyole.intelligentcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beyole.notifydialog.widget.effectdialog.Effectstype;
import com.beyole.notifydialog.widget.effectdialog.NiftyDialogBuilder;

/**
 * 程序入口Activity
 * 
 * @author Iceberg
 * 
 */
public class MainActivity extends FragmentActivity implements OnClickListener {

	private LinearLayout mTabHome;
	private LinearLayout mTabFunction;
	private LinearLayout mTabMe;
	private LinearLayout mTabSettings;

	private ImageButton mHomeImg;
	private ImageButton mFunctionImg;
	private ImageButton mMeImg;
	private ImageButton mSettingsImg;

	private Fragment mTab01;
	private Fragment mTab02;
	private Fragment mTab03;
	private Fragment mTab04;

	private TextView mTextview;

	private Effectstype effect;
	private NiftyDialogBuilder dialogBuilder = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		initEvents();
		setSelected(0);
	}

	private void initEvents() {
		mTabHome.setOnClickListener(this);
		mTabFunction.setOnClickListener(this);
		mTabMe.setOnClickListener(this);
		mTabSettings.setOnClickListener(this);
	}

	private void initViews() {
		mTabHome = (LinearLayout) findViewById(R.id.id_tab_weixin);
		mTabFunction = (LinearLayout) findViewById(R.id.id_tab_frd);
		mTabMe = (LinearLayout) findViewById(R.id.id_tab_address);
		mTabSettings = (LinearLayout) findViewById(R.id.id_tab_settings);
		mHomeImg = (ImageButton) findViewById(R.id.id_tab_weixin_img);
		mFunctionImg = (ImageButton) findViewById(R.id.id_tab_frd_img);
		mMeImg = (ImageButton) findViewById(R.id.id_tab_address_img);
		mSettingsImg = (ImageButton) findViewById(R.id.id_tab_settings_img);
		mTextview = (TextView) findViewById(R.id.id_top_banner_title);
	}

	private void setSelected(int i) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		hideFragments(transaction);
		switch (i) {
		case 0:
			if (mTab01 == null) {
				mTab01 = new HomeFragment();
				transaction.add(R.id.id_content, mTab01);
			} else {
				transaction.show(mTab01);
			}
			mHomeImg.setImageResource(R.drawable.home_selected);
			mTextview.setText("首页");
			transaction.commit();
			break;
		case 1:
			if (mTab02 == null) {
				mTab02 = new FrdFragment();
				transaction.add(R.id.id_content, mTab02);
			} else {
				transaction.show(mTab02);
			}
			mFunctionImg.setImageResource(R.drawable.function_selected);
			mTextview.setText("功能");
			transaction.commit();
			break;
		case 2:
			if (mTab03 == null) {
				mTab03 = new MeFragment();
				transaction.add(R.id.id_content, mTab03);
			} else {
				transaction.show(mTab03);
			}
			mMeImg.setImageResource(R.drawable.me_selected);
			mTextview.setText("我");
			transaction.commit();
			break;
		case 3:
			if (mTab04 == null) {
				mTab04 = new SettingFragment();
				transaction.add(R.id.id_content, mTab04);
			} else {
				transaction.show(mTab04);
			}
			mSettingsImg.setImageResource(R.drawable.settings_selected);
			mTextview.setText("设置");
			transaction.commit();
			break;
		}
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (mTab01 != null) {
			transaction.hide(mTab01);
		}
		if (mTab02 != null) {
			transaction.hide(mTab02);
		}
		if (mTab03 != null) {
			transaction.hide(mTab03);
		}
		if (mTab04 != null) {
			transaction.hide(mTab04);
		}
	}

	@Override
	public void onClick(View v) {
		resetImages();
		switch (v.getId()) {
		case R.id.id_tab_weixin:
			setSelected(0);
			break;
		case R.id.id_tab_frd:
			setSelected(1);
			break;
		case R.id.id_tab_address:
			setSelected(2);
			break;
		case R.id.id_tab_settings:
			setSelected(3);
			break;
		}
	}

	private void resetImages() {
		mHomeImg.setImageResource(R.drawable.home_normal);
		mFunctionImg.setImageResource(R.drawable.function_normal);
		mMeImg.setImageResource(R.drawable.me_normal);
		mSettingsImg.setImageResource(R.drawable.settings_normal);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			dialogExitShow();
			return false;// 不向下分发事件
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	// 退出提示框
	private void dialogExitShow() {
		dialogBuilder = NiftyDialogBuilder.getInstance(MainActivity.this);
		effect = Effectstype.Slit;
		dialogBuilder.withTitle("提示框").withTitleColor("#FFFFFF").withDividerColor("#11000000").withMessage("您确定要退出吗？").withMessageColor("#FFFFFF").withIcon(getResources().getDrawable(R.drawable.ic_launcher)).isCancelableOnTouchOutside(true).withDuration(700).withEffect(effect).withButton1Text("确定")
				.withButton2Text("取消").setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(Intent.ACTION_MAIN);
						intent.addCategory(Intent.CATEGORY_HOME);
						startActivity(intent);
						finish();
						System.exit(0);
					}
				}).setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialogBuilder.dismiss();
					}
				}).show();

	}

	// 解决fragment重叠问题
	@Override
	public void onAttachFragment(Fragment fragment) {
		if (mTab01 == null && fragment instanceof HomeFragment) {
			mTab01 = (HomeFragment) fragment;
		} else if (mTab02 == null && fragment instanceof FrdFragment) {
			mTab02 = (FrdFragment) fragment;
		} else if (mTab03 == null && fragment instanceof MeFragment) {
			mTab03 = (MeFragment) fragment;
		} else if (mTab04 == null && fragment instanceof SettingFragment) {
			mTab04 = (SettingFragment) fragment;
		}
	}
}
