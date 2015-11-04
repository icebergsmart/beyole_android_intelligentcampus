package com.beyole.intelligentcampus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.beyole.adapter.ItemAdapter;
import com.beyole.intelligentcampus.functions.SportSwitchActivity;
import com.beyole.view.LineGridview;

/**
 * 功能 fragment
 * 
 * @author Iceberg
 * 
 */
public class FrdFragment extends Fragment {

	private LineGridview gridviews, gridviews1, gridviews2;
	private int mHeight;
	private int mHeight1;
	private int mHeight2;
	private View view;
	private static final int lineNumber = 4;
	private String[] titles = new String[] { "校内导航", "聊天", "云备份", "听音乐", "闹钟", "脑点子", "正在开发", "正在开发" };
	private int[] img = new int[] { R.drawable.more1, R.drawable.more2, R.drawable.more3, R.drawable.more4, R.drawable.more5, R.drawable.more6, R.drawable.more7, R.drawable.more7 };

	private String[] titles1 = new String[] { "校内导航", "聊天", "云备份", "听音乐" };
	private int[] img1 = new int[] { R.drawable.more1, R.drawable.more2, R.drawable.more3, R.drawable.more4 };

	private String[] titles2 = new String[] { "运动", "聊天", "云备份", "听音乐", "闹钟" };
	private int[] img2 = new int[] { R.drawable.more1, R.drawable.more2, R.drawable.more3, R.drawable.more4, R.drawable.more5 };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tab02, container, false);
		// 获取屏幕宽度和高度
		WindowManager wm = getActivity().getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		initViews();
		int lines = titles.length % 2 == 0 ? titles.length / lineNumber : titles.length / lineNumber + 1;
		mHeight = Math.round((width / lineNumber) * 1.2f);
		ItemAdapter itemAdapter = new ItemAdapter(titles, img, getActivity(), mHeight * titles.length);
		int lines1 = titles1.length % 2 == 0 ? titles1.length / lineNumber : titles1.length / lineNumber + 1;
		mHeight1 = Math.round((width / lineNumber) * 1.2f);
		ItemAdapter itemAdapter1 = new ItemAdapter(titles1, img1, getActivity(), mHeight1 * titles1.length);
		int lines2 = titles2.length % 2 == 0 ? titles2.length / lineNumber : titles2.length / lineNumber + 1;
		mHeight2 = Math.round((width / lineNumber) * 1.2f);
		ItemAdapter itemAdapter2 = new ItemAdapter(titles2, img2, getActivity(), mHeight2 * titles2.length);
		// 设置gridview的单元格宽度
		gridviews.setColumnWidth(width / lineNumber - 10);
		gridviews.setSelector(R.drawable.hidden_yellow);
		gridviews.setAdapter(itemAdapter);
		LayoutParams lp = gridviews.getLayoutParams();
		lp.height = mHeight * lines;
		Log.i("test", "gridviews的高度为:" + lp.height);
		gridviews2.setColumnWidth(width / lineNumber - 10);
		gridviews2.setSelector(R.drawable.hidden_yellow);
		gridviews2.setAdapter(itemAdapter2);
		gridviews2.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				switch (position) {
				case 0:
					Intent intent = new Intent(getActivity(), SportSwitchActivity.class);
					startActivity(intent);
					break;
				}
			}
		});

		LayoutParams lp2 = gridviews2.getLayoutParams();
		lp2.height = mHeight2 * lines2;
		Log.i("test", "gridviews2的高度为:" + lp2.height);
		gridviews1.setColumnWidth(width / lineNumber - 10);
		gridviews1.setSelector(R.drawable.hidden_yellow);
		gridviews1.setAdapter(itemAdapter1);
		LayoutParams lp1 = gridviews1.getLayoutParams();
		lp1.height = mHeight1 * lines1;
		return view;
	}

	private void initViews() {
		// 获取布局文件视图
		gridviews = (LineGridview) view.findViewById(R.id.gridviews);
		gridviews2 = (LineGridview) view.findViewById(R.id.gridviews2);
		gridviews1 = (LineGridview) view.findViewById(R.id.gridviews1);
	}
}
