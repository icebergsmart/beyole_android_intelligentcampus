package com.beyole.intelligentcampus.functions.life.adapter;

import java.util.ArrayList;

import com.beyole.bean.Words;
import com.beyole.intelligentcampus.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class WordsSearchListAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<Words> words;
	private LayoutInflater mInflater;

	public WordsSearchListAdapter(Context context, ArrayList<Words> wordsList) {
		this.words = wordsList;
		mInflater = LayoutInflater.from(context);
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return words == null ? 0 : words.size();
	}

	@Override
	public Object getItem(int position) {
		return words.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.function_life_search_words_listview_item_layout, null);
			viewHolder.words = (TextView) convertView.findViewById(R.id.id_function_life_words_search_words_tv);
			viewHolder.meanings = (TextView) convertView.findViewById(R.id.id_function_life_words_search_meaning_tv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.words.setText(words.get(position).getWords());
		viewHolder.meanings.setText(words.get(position).getMeanings());
		return convertView;
	}

	class ViewHolder {
		public TextView words;
		public TextView meanings;
	}

}
