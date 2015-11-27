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

import com.beyole.adapter.FunctionSecondAdapter;
import com.beyole.bean.UserSecondHand;
import com.beyole.intelligentcampus.R;

/**
 * 二手市场
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class SecondHandActivity extends Activity {

	private ListView mListView;
	private FunctionSecondAdapter mAdapter;
	private List<UserSecondHand> mList = new ArrayList<UserSecondHand>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_secondhand);
		initViews();
		initEvents();
	}

	private void initEvents() {
		mAdapter = new FunctionSecondAdapter(this, mList);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(SecondHandActivity.this, SecondHandDetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("goodsList", mList.get(position));
				intent.putExtras(bundle);
				startActivity(intent);

			}
		});
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_secondhand_top).findViewById(R.id.id_top_banner_title);
		tv.setText("二手市场");
		mListView = (ListView) findViewById(R.id.id_function_second_listview);
		UserSecondHand secondHand = null;
		for (int i = 0; i < 9; i++) {
			secondHand = new UserSecondHand(i, "Iceberg" + i, "2015/11/27 15:49" + i, Math.random() >= 0.5 ? 0 : 1, "这是我测试的第" + i + "件商品，但是我不知道怎么去定价格，我只知道这是服务于大众的一个平台，希望大家能够踊跃的去投稿。", i, null, "薛佳伟" + i, "1571056365" + i, null, 0);
			mList.add(secondHand);
		}
	}
}
