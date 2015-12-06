package com.beyole.intelligentcampus.functions.life.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.beyole.bean.Information;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.adapter.CampusInformationListViewAdapter;
import com.beyole.intelligentcampus.functions.life.adapter.OutsideInformationListViewAdapter;

public class OutsideInformationFragment extends Fragment {

	private View mView;
	private List<Information> informations = new ArrayList<Information>();
	private ListView mListView;
	private OutsideInformationListViewAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.function_life_information_outside_layout, container, false);
		initViews();
		initEvents();
		return mView;
	}

	private void initEvents() {

	}

	private void initViews() {
		mListView = (ListView) mView.findViewById(R.id.id_function_life_outside_information_listview_main);
		initDatas();
		mAdapter = new OutsideInformationListViewAdapter(getActivity(), informations);
		mListView.setAdapter(mAdapter);
	}

	private void initDatas() {
		Information information = null;
		for (int i = 0; i < 9; i++) {
			information = new Information(i, "这是测试校外新闻标题" + i, "这是校外新闻的内容哦" + i, null);
			informations.add(information);
		}
	}
}
