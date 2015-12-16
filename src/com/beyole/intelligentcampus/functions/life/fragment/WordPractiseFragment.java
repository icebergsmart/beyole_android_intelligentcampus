package com.beyole.intelligentcampus.functions.life.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.life.WordsTaskActivity;
import com.beyole.view.MyImageView;
import com.beyole.view.MyImageView.OnViewClickListener;

/**
 * 记单词 单词联系fragment
 * 
 * @date 2015/12/14
 * @author Iceberg
 * 
 */
public class WordPractiseFragment extends Fragment {

	private View mView;
	private MyImageView mCetFourImageView, mCetSixImageView, mTemFourImageView, mTemEightImageView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.function_life_practise_words_layout, container, false);
		initViews();
		initEvents();
		return mView;
	}

	private void initEvents() {
		MyWordsItemOnClickListener listener = new MyWordsItemOnClickListener();
		mCetFourImageView.setOnClickIntent(listener);
		mCetSixImageView.setOnClickIntent(listener);
		mTemFourImageView.setOnClickIntent(listener);
		mTemEightImageView.setOnClickIntent(listener);

	}

	private void initViews() {
		mCetFourImageView = (MyImageView) mView.findViewById(R.id.function_life_practise_words_item_cet4);
		mCetSixImageView = (MyImageView) mView.findViewById(R.id.function_life_practise_words_item_cet6);
		mTemFourImageView = (MyImageView) mView.findViewById(R.id.function_life_practise_words_item_tem4);
		mTemEightImageView = (MyImageView) mView.findViewById(R.id.function_life_practise_words_item_tem8);
	}

	private void initDatas() {
	}

	class MyWordsItemOnClickListener implements OnViewClickListener {

		@Override
		public void onViewClick(MyImageView view) {
			Intent intent = new Intent(getActivity(), WordsTaskActivity.class);
			switch (view.getId()) {
			case R.id.function_life_practise_words_item_cet4:
				intent.putExtra("titleName", "四级单词任务");
				intent.putExtra("level", 4);
				break;
			case R.id.function_life_practise_words_item_cet6:
				intent.putExtra("titleName", "六级单词任务");
				intent.putExtra("level", 6);
				break;
			case R.id.function_life_practise_words_item_tem4:
				intent.putExtra("titleName", "专业四级单词任务");
				intent.putExtra("level", 14);
				break;
			case R.id.function_life_practise_words_item_tem8:
				intent.putExtra("titleName", "专业八级单词任务");
				intent.putExtra("level", 18);
				break;
			}
			startActivity(intent);
		}

	}
}
