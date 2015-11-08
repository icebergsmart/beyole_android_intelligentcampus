package com.beyole.intelligentcampus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ComponentName;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.beyole.adapter.NewsAdapter;
import com.beyole.adapter.ViewpaperAdapter;
import com.beyole.bean.NewsBean;
import com.beyole.constant.Constant;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * 主页的fragment
 * 
 * @author Iceberg
 * 
 */
public class HomeFragment extends Fragment implements OnPageChangeListener {
	// 定义viewpager对象
	private ViewPager vp;
	// 定义viewpager适配器
	private ViewpaperAdapter viewpaperAdapter;
	private LinearLayout mWaittingForListview;
	private AVLoadingIndicatorView lv_waitting_annimation;
	private Button lv_setting_network;
	// 定义viewpager传入的view对象list
	private List<View> views;
	// 定义滑动时小白点的imageview对象数组
	private ImageView[] dots;
	// 定义小白点的id信息
	private int[] dotsId = { R.id.p1, R.id.p2, R.id.p3, R.id.p4 };
	private LayoutInflater mInflater;
	private View mView;
	// 存储新闻列表
	private ListView mListView;

	private TextView mRefreshNewsBtn;
	// 判断viewpager有没有触碰事件
	private boolean isTouching = false;
	private int viewPagerSelected = 1;
	private static final int MYVIEWPAGERTIME = 0x11122;

	Handler myViewPagerHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MYVIEWPAGERTIME:
				if (!isTouching)
					changeViewpagerItem(viewPagerSelected);
				break;
			}
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// android4.0之后请求http都会抛异常，这里是防止http请求被中断
		mView = inflater.inflate(R.layout.tab01, container, false);
		mInflater = inflater;
		// 初始化viewpager页面组件
		initViews();
		// 初始化小白点组件
		initDots();
		new NewsAsyncTask().execute(Constant.HOMEURL);
		return mView;
	}

	public void changeViewpagerItem(int viewPagerSelected2) {
		vp.setCurrentItem(viewPagerSelected2);
		viewPagerSelected = (viewPagerSelected + 1) % 4;
		myViewPagerHandler.sendEmptyMessageDelayed(MYVIEWPAGERTIME, 6000);
	}

	private void initViews() {
		mListView = (ListView) mView.findViewById(R.id.lv_main);
		mWaittingForListview = (LinearLayout) mView.findViewById(R.id.lv_waitting_for_listview);
		lv_waitting_annimation = (AVLoadingIndicatorView) mView.findViewById(R.id.lv_waitting_annimation);
		lv_setting_network = (Button) mView.findViewById(R.id.lv_setting_network);
		mRefreshNewsBtn = (TextView) mView.findViewById(R.id.home_activity_refresh_ib_news);
		lv_setting_network.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 启动设置网络页面
				setNetWorkMethod();
			}
		});
		mRefreshNewsBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new NewsAsyncTask().execute(Constant.HOMEURL);
			}
		});
		// 初始化view列表对象
		views = new ArrayList<View>();
		// 获取三个引导页面的view
		views.add(mInflater.inflate(R.layout.guide_one, null));
		views.add(mInflater.inflate(R.layout.guide_two, null));
		views.add(mInflater.inflate(R.layout.guide_three, null));
		views.add(mInflater.inflate(R.layout.guide_four, null));
		// 初始化适配器
		viewpaperAdapter = new ViewpaperAdapter(views, getActivity());
		// 实例化viewpager对象
		vp = (ViewPager) mView.findViewById(R.id.id_viewPager);
		// 填充适配器
		vp.setAdapter(viewpaperAdapter);
		// 设置引导页滑动监听事件
		vp.setOnPageChangeListener(this);
		// 获取启动按钮
		if (!isTouching) {
			myViewPagerHandler.sendEmptyMessageDelayed(MYVIEWPAGERTIME, 3000);
		}
	}

	protected void setNetWorkMethod() {
		Intent intent = null;
		// 判断手机系统的版本 即API大于10 就是3.0或以上版本
		if (android.os.Build.VERSION.SDK_INT > 10) {
			intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
		} else {
			intent = new Intent();
			ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
			intent.setComponent(component);
			intent.setAction("android.intent.action.VIEW");
		}
		startActivity(intent);

	}

	// 初始化小白点方法
	private void initDots() {
		// 初始化小白点的对象数组
		dots = new ImageView[views.size()];
		for (int i = 0; i < views.size(); i++) {
			// 实例化小白点的对象数组
			dots[i] = (ImageView) mView.findViewById(dotsId[i]);
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		// 设置小白点的样式图片
		for (int i = 0; i < dotsId.length; i++) {
			if (arg0 == i) {
				dots[i].setImageResource(R.drawable.login_point_selected);
			} else {
				dots[i].setImageResource(R.drawable.login_point);
			}
		}
		viewPagerSelected = arg0;
	}

	// 实现网络的异步访问
	class NewsAsyncTask extends AsyncTask<String, Void, List<NewsBean>> {

		@Override
		protected void onPreExecute() {
			mListView.setVisibility(View.GONE);
			mWaittingForListview.setVisibility(View.VISIBLE);
			lv_waitting_annimation.setVisibility(View.VISIBLE);
			lv_setting_network.setVisibility(View.GONE);
		}

		@Override
		protected List<NewsBean> doInBackground(String... params) {
			return getJsonData(params[0]);
		}

		@Override
		protected void onPostExecute(List<NewsBean> result) {
			super.onPostExecute(result);
			NewsAdapter adapter = new NewsAdapter(getActivity(), result, mListView);
			mListView.setAdapter(adapter);
			if (result.size() > 0) {
				mListView.setVisibility(View.VISIBLE);
				mWaittingForListview.setVisibility(View.GONE);
			} else {
				mWaittingForListview.setVisibility(View.VISIBLE);
				lv_waitting_annimation.setVisibility(View.GONE);
				lv_setting_network.setVisibility(View.VISIBLE);
			}
		}

	}

	/**
	 * 获取json类型的数据，返回给list
	 * 
	 * @param string
	 *           请求的url地址
	 * @return
	 */
	private List<NewsBean> getJsonData(String url) {
		List<NewsBean> newsBeans = new ArrayList<NewsBean>();
		try {
			String json = readStream(new URL(url).openStream());
			JSONObject jsonObject;
			NewsBean newsBean;
			try {
				jsonObject = new JSONObject(json);
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				for (int i = 0; i < jsonArray.length(); i++) {
					jsonObject = jsonArray.getJSONObject(i);
					newsBean = new NewsBean();
					newsBean.newsIconUrl = jsonObject.getString("picSmall");
					newsBean.newsTitle = jsonObject.getString("name");
					newsBean.newsContent = jsonObject.getString("description");
					newsBeans.add(newsBean);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newsBeans;
	}

	// 将输入流读出为json
	private String readStream(InputStream is) {
		InputStreamReader isr;
		String result = "";
		try {
			// 将字节流转换为字符流
			String line = "";
			isr = new InputStreamReader(is, "UTF-8");
			// 存入buffer中
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
