package com.beyole.intelligentcampus.functions.convenient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beyole.bean.UserSecondHand;
import com.beyole.intelligentcampus.R;

public class SecondHandDetailsActivity extends Activity {

	private TextView mUserName;
	private TextView mDeliveryDate;
	private TextView mStatusYes;
	private TextView mStatusNo;
	private TextView mContent;
	private TextView mContact;
	private TextView mContactInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_second_details);
		initViews();
		UserSecondHand secondHand = (UserSecondHand) getIntent().getSerializableExtra("goodsList");
		setValues(secondHand);
	}

	private void setValues(UserSecondHand secondHand) {
		mUserName.setText(secondHand.getUserName());
		mDeliveryDate.setText(secondHand.getDeliveryDate());
		if (secondHand.getFinished() == 0) {
			mStatusYes.setVisibility(View.VISIBLE);
			mStatusNo.setVisibility(View.GONE);
		} else {
			mStatusYes.setVisibility(View.GONE);
			mStatusNo.setVisibility(View.VISIBLE);
		}
		mContent.setText(secondHand.getContent());
		mContact.setText(secondHand.getContact());
		mContactInfo.setText(secondHand.getContactInfo());
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_second_details_top).findViewById(R.id.id_top_banner_title);
		tv.setText("详细");
		mUserName = (TextView) findViewById(R.id.id_convenient_secondhand_details_person_details_username);
		mDeliveryDate = (TextView) findViewById(R.id.id_convenient_secondhand_details_deliverydate);
		mStatusYes = (TextView) findViewById(R.id.id_convenient_secondhand_details_person_details_ing);
		mStatusNo = (TextView) findViewById(R.id.id_convenient_secondhand_details_person_details_over);
		mContent = (TextView) findViewById(R.id.id_convenient_secondhand_details_person_details_content);
		mContact = (TextView) findViewById(R.id.id_convenient_secondhand_details_contact);
		mContactInfo = (TextView) findViewById(R.id.id_convenient_secondhand_details_contactinfo);
	}
}
