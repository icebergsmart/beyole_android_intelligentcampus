package com.beyole.intelligentcampus.functions.life.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beyole.adapter.NewsAdapter;
import com.beyole.intelligentcampus.R;
import com.beyole.view.MyImageView;
import com.beyole.view.MyImageView.OnViewClickListener;

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
			switch (view.getId()) {
			case R.id.function_life_practise_words_item_cet4:
				Toast.makeText(getActivity(), "您点击了英语四级按钮了！", Toast.LENGTH_LONG).show();
				break;
			case R.id.function_life_practise_words_item_cet6:
				Toast.makeText(getActivity(), "您点击了英语六级按钮了！", Toast.LENGTH_LONG).show();
				break;
			case R.id.function_life_practise_words_item_tem4:
				Toast.makeText(getActivity(), "您点击了专业四级按钮了！", Toast.LENGTH_LONG).show();
				break;
			case R.id.function_life_practise_words_item_tem8:
				Toast.makeText(getActivity(), "您点击了专业八级按钮了！", Toast.LENGTH_LONG).show();
				break;
			}
		}

	}
}
