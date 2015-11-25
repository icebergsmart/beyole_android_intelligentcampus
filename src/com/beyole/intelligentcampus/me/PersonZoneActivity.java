package com.beyole.intelligentcampus.me;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.beyole.adapter.PersonTalkAdapter;
import com.beyole.bean.UserTalk;
import com.beyole.intelligentcampus.R;
import com.beyole.view.pullscrollview.widget.PullScrollView;

public class PersonZoneActivity extends Activity implements PullScrollView.OnTurnListener {
	private PullScrollView mScrollView;
	private ImageView mHeadImg;
	private ListView mTalkList;
	private PersonTalkAdapter mAdapter;
	private List<UserTalk> data = new ArrayList<UserTalk>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_pull_down);
		initView();
		initEvent();
	}

	private void initEvent() {
		mTalkList.setAdapter(mAdapter);
		 //setListViewHeightBasedOnChildren(mTalkList);
		//Utility.setListViewHeightBasedOnChildren(mTalkList);
	}

	protected void initView() {
mScrollView = (PullScrollView) findViewById(R.id.scroll_view);
		mHeadImg = (ImageView) findViewById(R.id.background_img);
		mScrollView.setHeader(mHeadImg);
		mScrollView.setOnTurnListener(this);
		mTalkList = (ListView) findViewById(R.id.person_details_talklist);
		UserTalk talk = null;
		for (int i = 0; i < 10; i++) {
			talk = new UserTalk(i, "", "测试用户" + i, "2015-11-25 " + i + "时", "Story eventually, i can only one man looked at the memory of the nicety lost.Not tears can restore the lost, not everyone is worth your pay.Don’t and I said I’m sorry, I don’t want to and you it doesn’t." + i, i, i, i, 0, "小米" + i + "S", "");
			data.add(talk);
		}
		mAdapter = new PersonTalkAdapter(this, data);
	}

	@Override
	public void onTurn() {

	}
	public static void setListViewHeightBasedOnChildren(ListView listView) {
	    ListAdapter listAdapter = listView.getAdapter();
	    if (listAdapter == null) {
	        // pre-condition
	        return;
	    }

	    int totalHeight = 0;
	    for (int i = 0; i < listAdapter.getCount(); i++) {
	        View listItem = listAdapter.getView(i, null, listView);
	        listItem.measure(0, 0);
	        totalHeight += listItem.getMeasuredHeight();
	    }
	    Log.i("defineListView","count"+listAdapter.getCount());
	    ViewGroup.LayoutParams params = listView.getLayoutParams();
	    params.height = totalHeight
	            + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	    listView.setLayoutParams(params);
	      }
}
