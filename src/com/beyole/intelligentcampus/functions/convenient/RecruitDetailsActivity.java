package com.beyole.intelligentcampus.functions.convenient;

import com.beyole.bean.UserRecruit;
import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RecruitDetailsActivity extends Activity {

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
		setContentView(R.layout.convenient_recruit_details);
		initViews();
		UserRecruit recruit = (UserRecruit) getIntent().getSerializableExtra("recruitList");
		setValues(recruit);
	}

	private void setValues(UserRecruit recruit) {
		mUserName.setText(recruit.getUserName());
		mDeliveryDate.setText(recruit.getDeliveryDate());
		if (recruit.getFinished() == 0) {
			mStatusYes.setVisibility(View.VISIBLE);
			mStatusNo.setVisibility(View.GONE);
		} else {
			mStatusNo.setVisibility(View.VISIBLE);
			mStatusYes.setVisibility(View.GONE);
		}
		mContact.setText(recruit.getContact());
		mContent.setText(recruit.getContent());
		mContactInfo.setText(recruit.getContactInfo());
	}

	private void initViews() {
		mUserName = (TextView) findViewById(R.id.id_convenient_recruit_details_person_details_username);
		mDeliveryDate = (TextView) findViewById(R.id.id_convenient_recruit_details_deliverydate);
		mStatusNo = (TextView) findViewById(R.id.id_convenient_recruit_details_person_details_over);
		mStatusYes = (TextView) findViewById(R.id.id_convenient_recruit_details_person_details_ing);
		mContent = (TextView) findViewById(R.id.id_convenient_recruit_details_person_details_content);
		mContact = (TextView) findViewById(R.id.id_convenient_recruit_details_contact);
		mContactInfo = (TextView) findViewById(R.id.id_convenient_recruit_details_contactinfo);
	}
}
