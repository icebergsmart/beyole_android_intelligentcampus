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

import com.beyole.adapter.FunctionRecruitAdapter;
import com.beyole.bean.UserRecruit;
import com.beyole.intelligentcampus.R;

public class RecruitActivity extends Activity {

	private ListView mListView;
	private FunctionRecruitAdapter mAdapter;
	private List<UserRecruit> mList = new ArrayList<UserRecruit>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_recruit);
		initViews();
		initEvents();
	}

	private void initEvents() {
		mAdapter = new FunctionRecruitAdapter(this, mList);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(RecruitActivity.this, RecruitDetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putSerializable("recruitList", mList.get(position));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_recruit_top).findViewById(R.id.id_top_banner_title);
		tv.setText("兼职招聘");
		mListView = (ListView) findViewById(R.id.function_recruit_listview);
		UserRecruit recruit = null;
		for (int i = 0; i < 9; i++) {
			recruit = new UserRecruit(i, "薛佳伟" + i, null, null, "2015/11/27", Math.random() >= 0.5 ? 0 : 1, "这是一家国企发布的招聘信息，具体工作地址在盐城海华广场B座405室。工作性质为火锅店服务员兼职，一天薪资80，待遇优厚，需要人员若干名。" + i, "薛佳伟" + i, "1570511635" + i, 0);
			mList.add(recruit);
		}
	}
}
