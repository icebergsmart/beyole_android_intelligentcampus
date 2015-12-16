package com.beyole.intelligentcampus.functions.life.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beyole.bean.WordsTask;
import com.beyole.intelligentcampus.R;

/**
 * 单词任务适配器
 * 
 * @date 2015/12/14
 * @author Iceberg
 * 
 */
public class WordsTaskAdapter extends BaseAdapter {

	private List<WordsTask> tasks;
	private LayoutInflater mInflater;

	public WordsTaskAdapter(Context context, List<WordsTask> taskList) {
		this.tasks = taskList;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return tasks == null ? 0 : tasks.size();
	}

	@Override
	public Object getItem(int position) {
		return tasks.get(position);
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
			convertView = mInflater.inflate(R.layout.function_life_words_task_item_layout, null);
			viewHolder.taskName = (TextView) convertView.findViewById(R.id.id_function_life_words_task_item_tv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.taskName.setText(tasks.get(position).getTaskName());
		return convertView;
	}

	class ViewHolder {
		public TextView taskName;
	}
}
