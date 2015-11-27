package com.beyole.intelligentcampus.functions.convenient;

import com.beyole.bean.UserLoss;
import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LossDetailsActivity extends Activity {

	private TextView mUserName;
	private TextView mDeliveryDate;
	private TextView statusYes;
	private TextView statusNo;
	private TextView content;
	private TextView mContact;
	private TextView mContactInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_loss_details);
		UserLoss loss = (UserLoss) getIntent().getSerializableExtra("lossdetails");
		initViews();
		setValues(loss);
	}

	private void setValues(UserLoss loss) {
		mUserName.setText(loss.getUserName());
		mDeliveryDate.setText(loss.getDeliveryDate());
		if (loss.getFinished() == 0) {
			statusYes.setVisibility(View.VISIBLE);
			statusNo.setVisibility(View.GONE);
		} else {
			statusYes.setVisibility(View.GONE);
			statusNo.setVisibility(View.VISIBLE);
		}
		content.setText(loss.getContent());
		mContact.setText(loss.getContact());
		mContactInfo.setText(loss.getContactInfo());
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_loss_details_top).findViewById(R.id.id_top_banner_title);
		tv.setText("详细");
		mUserName = (TextView) findViewById(R.id.id_function_loss_details_username);
		mDeliveryDate = (TextView) findViewById(R.id.id_function_loss_details_deliverydate);
		statusNo = (TextView) findViewById(R.id.id_function_loss_details_over);
		statusYes = (TextView) findViewById(R.id.id_function_loss_details_ing);
		content = (TextView) findViewById(R.id.id_function_loss_details_content);
		mContact = (TextView) findViewById(R.id.id_function_loss_details_contactinfo);
		mContactInfo = (TextView) findViewById(R.id.id_function_loss_details_contact);
	}
}
