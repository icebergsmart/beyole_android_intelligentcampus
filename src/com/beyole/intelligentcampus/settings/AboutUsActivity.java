package com.beyole.intelligentcampus.settings;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class AboutUsActivity extends Activity {

	private TextView mVersion;
	private TextView mPerson;
	private TextView mContactEmail;
	private TextView mAppDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings_aboutus);
		TextView tv = (TextView) findViewById(R.id.id_settings_abouts_top).findViewById(R.id.id_top_banner_title);
		tv.setText("关于我们");
		initViews();
	}

	private void initViews() {
		mVersion = (TextView) findViewById(R.id.id_contact_version);
		mPerson = (TextView) findViewById(R.id.id_contact_person);
		mContactEmail = (TextView) findViewById(R.id.id_contact_email);
		mAppDescription = (TextView) findViewById(R.id.id_app_description);
		AssetManager assets = getAssets();
		Typeface tf = Typeface.createFromAsset(assets, "fonts/default.otf");
		mVersion.setTypeface(tf);
		mPerson.setTypeface(tf);
		mContactEmail.setTypeface(tf);
		mAppDescription.setTypeface(tf);
	}

}
