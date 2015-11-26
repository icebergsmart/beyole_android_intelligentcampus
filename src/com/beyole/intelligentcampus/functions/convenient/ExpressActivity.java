package com.beyole.intelligentcampus.functions.convenient;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 快递联盟
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class ExpressActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_express);
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_express_top).findViewById(R.id.id_top_banner_title);
		tv.setText("快递联盟");
	}
}
