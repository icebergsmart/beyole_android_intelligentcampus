package com.beyole.intelligentcampus.functions.convenient;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 失物招领
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class LossActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_loss);
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_loss_top).findViewById(R.id.id_top_banner_title);
		tv.setText("失物招领");
	}
}
