package com.beyole.intelligentcampus.functions.convenient;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.beyole.adapter.FunctionLossAdapter;
import com.beyole.bean.UserLoss;
import com.beyole.intelligentcampus.R;

/**
 * 失物招领
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class LossActivity extends Activity {

	private ListView mListView;
	private List<UserLoss> mUserLossList = new ArrayList<UserLoss>();
	private FunctionLossAdapter mUserLossAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_loss);
		initViews();
		initEvents();
	}

	private void initEvents() {
		mUserLossAdapter = new FunctionLossAdapter(this, mUserLossList);
		mListView.setAdapter(mUserLossAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(LossActivity.this, LossDetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("lossdetails", mUserLossList.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_loss_top).findViewById(R.id.id_top_banner_title);
		tv.setText("失物招领");
		mListView = (ListView) findViewById(R.id.function_loss_listview);
		UserLoss loss = null;
		for (int i = 0; i < 8; i++) {
			loss = new UserLoss(i, "用户" + i, "2015/11/27 14:18" + i, null, null, "我已经丢失了很多东西，差点丢失了自己，希望那个找到我的人，能够好好的把那个我归还给我，我爱你，哈哈哈，突然感觉自己好文艺" + i, 0, Math.random() >= 0.5 ? 0 : 1, "薛佳伟" + i, "1570511635" + i);
			mUserLossList.add(loss);
		}

	}
}
