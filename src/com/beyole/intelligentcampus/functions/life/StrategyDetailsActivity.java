package com.beyole.intelligentcampus.functions.life;

import com.beyole.intelligentcampus.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 新生攻略详细页面
 * 
 * @date 2015/12/17
 * @author Iceberg
 * 
 */
public class StrategyDetailsActivity extends Activity {

	private TextView mContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.function_life_strategy_details_layout);
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		String title = getIntent().getStringExtra("detailsTitle");
		TextView tv = (TextView) findViewById(R.id.function_life_strategy_details_top).findViewById(R.id.id_top_banner_title);
		tv.setText(title);
		mContent = (TextView) findViewById(R.id.id_function_life_strategy_details_content_tv);
		int type = getIntent().getIntExtra("type", 1);
		switch (type) {
		case 1:
			mContent.setText("1.物品准备1\n2.物品准备2\n3.物品准备3\n4.物品准备4\n5.物品准备5\n");
			break;
		case 2:
			mContent.setText("1.手续办理1\n2.手续办理2\n3.手续办理3\n4.手续办理4\n5.手续办理5\n");
			break;
		case 3:
			mContent.setText("1.报到流程1\n2.报到流程2\n3.报到流程3\n4.报到流程4\n5.报到流程5\n");
			break;
		case 4:
			mContent.setText("1.新生军训1\n2.新生军训2\n3.新生军训3\n4.新生军训4\n5.新生军训5\n");
			break;
		case 5:
			mContent.setText("1.注意事项1\n2.注意事项2\n3.注意事项3\n4.注意事项4\n5.注意事项5\n");
			break;

		}
	}
}
