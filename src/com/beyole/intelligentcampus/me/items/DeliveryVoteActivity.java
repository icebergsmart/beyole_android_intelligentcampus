package com.beyole.intelligentcampus.me.items;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 发布投票
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class DeliveryVoteActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_items_deliveryvote);
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_me_items_deliveryvote_top).findViewById(R.id.id_top_banner_title);
		tv.setText("发布投票");
	}
}
