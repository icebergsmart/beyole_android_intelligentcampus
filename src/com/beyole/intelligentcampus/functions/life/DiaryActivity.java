package com.beyole.intelligentcampus.functions.life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beyole.intelligentcampus.R;

/**
 * 新生攻略
 * 
 * @author Iceberg
 * 
 */
public class DiaryActivity extends Activity {

	private LinearLayout mItemPreparation, mProcedure, mRegistrationProcess, mFreshmenMilitaryTraining, mMattersNeedingAttention;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.life_diary);
		initViews();
		initEvents();
	}

	private void initEvents() {

	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_life_diary_top).findViewById(R.id.id_top_banner_title);
		tv.setText("新生攻略");
		mItemPreparation = (LinearLayout) findViewById(R.id.id_function_strategy_item_preparation_ll);
		mProcedure = (LinearLayout) findViewById(R.id.id_function_strategy_procedure_ll);
		mRegistrationProcess = (LinearLayout) findViewById(R.id.id_function_strategy_registration_process_ll);
		mFreshmenMilitaryTraining = (LinearLayout) findViewById(R.id.id_function_strategy_freshmen_military_training_ll);
		mMattersNeedingAttention = (LinearLayout) findViewById(R.id.id_function_strategy_matters_needing_attention_ll);
		MyOnClickListener listener = new MyOnClickListener();
		mItemPreparation.setOnClickListener(listener);
		mProcedure.setOnClickListener(listener);
		mRegistrationProcess.setOnClickListener(listener);
		mFreshmenMilitaryTraining.setOnClickListener(listener);
		mMattersNeedingAttention.setOnClickListener(listener);
	}

	class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(DiaryActivity.this, StrategyDetailsActivity.class);
			switch (v.getId()) {
			case R.id.id_function_strategy_item_preparation_ll:
				intent.putExtra("detailsTitle", "物品准备");
				intent.putExtra("type", 1);
				break;
			case R.id.id_function_strategy_procedure_ll:
				intent.putExtra("detailsTitle", "手续办理");
				intent.putExtra("type", 2);
				break;
			case R.id.id_function_strategy_registration_process_ll:
				intent.putExtra("detailsTitle", "报到流程");
				intent.putExtra("type", 3);
				break;
			case R.id.id_function_strategy_freshmen_military_training_ll:
				intent.putExtra("detailsTitle", "新生军训");
				intent.putExtra("type", 4);
				break;
			case R.id.id_function_strategy_matters_needing_attention_ll:
				intent.putExtra("detailsTitle", "注意事项");
				intent.putExtra("type", 5);
				break;
			}
			startActivity(intent);
		}

	}
}
