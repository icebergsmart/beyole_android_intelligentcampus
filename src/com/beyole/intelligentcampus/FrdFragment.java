package com.beyole.intelligentcampus;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
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

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.beyole.adapter.ItemAdapter;
import com.beyole.intelligentcampus.functions.SportSwitchActivity;
import com.beyole.util.SyncHttp;
import com.beyole.view.LineGridview;
import com.beyole.view.MyTextView;

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
	private String[] titles = new String[] { "新鲜事", "周边", "活动", "Find Me" };
	private int[] img = new int[] { R.drawable.more1, R.drawable.more2, R.drawable.more3, R.drawable.more4 };

	private String[] titles1 = new String[] { "快递联盟", "兼职招聘", "失物招领", "二手市场" };
	private int[] img1 = new int[] { R.drawable.more1, R.drawable.more2, R.drawable.more3, R.drawable.more4 };

	private String[] titles2 = new String[] { "计步器", "日记本", "公开课", "导航", "闹钟" };
	private int[] img2 = new int[] { R.drawable.more1, R.drawable.more2, R.drawable.more3, R.drawable.more4, R.drawable.more5 };

	public LocationClient mLocationClient = null;
	public BDLocationListener myListener = new MyLocationListener();
	// 存储当前定位位置
	private BDLocation mLocation;
	private boolean isLocated = false;
	public static final int CHANGE_WEATHER_TEXTVIEW = 0x001;
	private MyTextView mWeatherTextView;

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
		getLocationInformation();
		return view;
	}

	private void getLocationInformation() {
		mLocationClient.start();
		if (isLocated) {
			mLocationClient.stop();
		}
	}

	private void initViews() {
		// 获取布局文件视图
		gridviews = (LineGridview) view.findViewById(R.id.gridviews);
		gridviews2 = (LineGridview) view.findViewById(R.id.gridviews2);
		gridviews1 = (LineGridview) view.findViewById(R.id.gridviews1);
		mWeatherTextView = (MyTextView) view.findViewById(R.id.id_function_weather_information);
		mLocationClient = new LocationClient(getActivity()); // 声明LocationClient类
		initLocation();
		mLocationClient.registerLocationListener(myListener); // 注册监听函数

	}

	private void initLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
		option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
		// int span = 1000;
		// option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
		option.setScanSpan(0);
		option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
		option.setOpenGps(true);// 可选，默认false,设置是否使用gps
		option.setLocationNotify(false);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
		option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
		option.setIgnoreKillProcess(false);// 可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
		option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
		option.setEnableSimulateGps(false);// 可选，默认false，设置是否需要过滤gps仿真结果，默认需要
		mLocationClient.setLocOption(option);
	}

	/**
	 * 百度定位接口监听器
	 * 
	 * @author Iceberg
	 * 
	 */
	public class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			mLocation = location;
			new MyAsyncTask().execute();
			isLocated = true;
		}
	}

	class MyAsyncTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			return getLocationWeather(mLocation.getLongitude() + "", mLocation.getLatitude() + "");
		}

		@Override
		protected void onPostExecute(String result) {
			mWeatherTextView.setText(result);
		}

	}

	private String getLocationWeather(final String longitude, final String latitude) {

		String url = "http://api.map.baidu.com/telematics/v3/weather?location=" + longitude + "," + latitude + "&output=json&ak=V83jCerVdt5fZN8sFltbLtH4";
		String resultSet = "";
		SyncHttp http = new SyncHttp();
		// 以Get方式请求，并获得返回结果
		try {
			String retStr = http.httpGet(url, null);
			JSONObject jsonObject = new JSONObject(retStr);
			int code = jsonObject.getInt("error");
			String states = jsonObject.getString("status");
			if (code == 0 && states.equals("success")) {
				JSONArray results = jsonObject.getJSONArray("results");
				JSONObject resultObj = results.getJSONObject(0);
				String city = resultObj.getString("currentCity");
				String pm = resultObj.getString("pm25");
				JSONArray weatherArray = resultObj.getJSONArray("weather_data");
				JSONObject weatherObj = weatherArray.getJSONObject(0);
				String date = weatherObj.getString("date");
				String weather = weatherObj.getString("weather");
				String wind = weatherObj.getString("wind");
				String temperature = weatherObj.getString("temperature");
				resultSet =city + " " + weather + " " + wind + " " + temperature;
			} else {
				resultSet = "天气获取失败";
				Log.i("weather", "天气信息获取失败,code=" + code);
			}
		} catch (Exception e) {
			resultSet = "天气信息解析失败";
			Log.i("weather", "天气信息Json解析错误");
			e.printStackTrace();
		}
		return resultSet;
	}

}
