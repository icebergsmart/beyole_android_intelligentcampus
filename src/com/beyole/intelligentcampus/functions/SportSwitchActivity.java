package com.beyole.intelligentcampus.functions;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.beyole.intelligentcampus.R;

public class SportSwitchActivity extends FragmentActivity {

	private Button btn_message,btn_call;
	
	private SportWeekFragment callFragment;
    private SportTodayFragment messageFragment;
	
	public static final int MESSAGE_FRAGMENT_TYPE = 1;
	public static final int CALL_FRAGMENT_TYPE = 2;
	public int currentFragmentType = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_switch);
		
		btn_message = (Button)findViewById(R.id.btn_message);
		btn_call = (Button)findViewById(R.id.btn_call);
		btn_message.setOnClickListener(onClicker);
		btn_call.setOnClickListener(onClicker);
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		if (savedInstanceState != null) {
            int type = savedInstanceState.getInt("currentFragmentType");
            messageFragment = (SportTodayFragment)fragmentManager.findFragmentByTag("message");
            callFragment = (SportWeekFragment)fragmentManager.findFragmentByTag("call");
            if(type > 0)
            	loadFragment(type);
		} else {
			FragmentTransaction transaction = fragmentManager
					.beginTransaction();
			Fragment mainFragment = fragmentManager.findFragmentByTag("message");
			if (mainFragment != null) {
				transaction.replace(R.id.fl_content, mainFragment);
				transaction.commit();
			} else {
				loadFragment(MESSAGE_FRAGMENT_TYPE);
			}
		}
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("lastFragmentTag", currentFragmentType);
	}
	
	private void switchFragment(int type) {
		switch (type) {
		case MESSAGE_FRAGMENT_TYPE:
			loadFragment(MESSAGE_FRAGMENT_TYPE);
			break;
		case CALL_FRAGMENT_TYPE:
			loadFragment(CALL_FRAGMENT_TYPE);
			break;
		}
		
	}

	private void loadFragment(int type) {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		if (type == CALL_FRAGMENT_TYPE) {
			if (callFragment == null) {
				callFragment = new SportWeekFragment();

				transaction.add(R.id.fl_content, callFragment, "zhishi");
			} else {
				transaction.show(callFragment);
			}
			if (messageFragment != null) {
				transaction.hide(messageFragment);
			}
			currentFragmentType = MESSAGE_FRAGMENT_TYPE;
		} else {
			if (messageFragment == null) {
				messageFragment = new SportTodayFragment();
				transaction.add(R.id.fl_content, messageFragment, "wenda");
			} else {
				transaction.show(messageFragment);
			}
			if (callFragment != null) {
				transaction.hide(callFragment);
			}
			currentFragmentType = CALL_FRAGMENT_TYPE;
		}
		transaction.commitAllowingStateLoss();
	}
	
	private OnClickListener onClicker = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_message:
				btn_message.setTextColor(Color.parseColor("#106184"));
				btn_call.setTextColor(Color.WHITE);
				btn_message
						.setBackgroundResource(R.drawable.baike_btn_pink_left_f_96);
				btn_call
						.setBackgroundResource(R.drawable.baike_btn_trans_right_f_96);
				switchFragment(MESSAGE_FRAGMENT_TYPE);
				
				break;
			case R.id.btn_call:
				
				btn_message.setTextColor(Color.WHITE);
				btn_call.setTextColor(Color.parseColor("#106184"));
				btn_message
						.setBackgroundResource(R.drawable.baike_btn_trans_left_f_96);
				btn_call
						.setBackgroundResource(R.drawable.baike_btn_pink_right_f_96);
				switchFragment(CALL_FRAGMENT_TYPE);
				
				break;
			
			}
		}
	};

}
