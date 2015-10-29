package com.beyole.intelligentcampus.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.beyole.intelligentcampus.R;

public class FindMeActivity extends Activity {

	private Button id_me_findme_scanner;
	private TextView id_me_findme_result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me_findme_scanner);
		initViews();
		id_me_findme_scanner.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FindMeActivity.this, CaptureActivity.class);
				startActivityForResult(intent, 0);
			}
		});
	}

	private void initViews() {
		id_me_findme_scanner = (Button) findViewById(R.id.id_me_findme_scanner);
		id_me_findme_result = (TextView) findViewById(R.id.id_me_findme_result);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 处理扫描结果（在界面上显示）
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			id_me_findme_result.setText(scanResult);
		}
	}
}
