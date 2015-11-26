package com.beyole.intelligentcampus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.beyole.adapter.ItemAdapter;
import com.beyole.bean.GlobalParameterApplication;
import com.beyole.bean.User;
import com.beyole.constant.APIConstant;
import com.beyole.intelligentcampus.me.ExerciseActivity;
import com.beyole.intelligentcampus.me.FansActivity;
import com.beyole.intelligentcampus.me.FindMeActivity;
import com.beyole.intelligentcampus.me.FocusActivity;
import com.beyole.intelligentcampus.me.PersonZoneActivity;
import com.beyole.intelligentcampus.me.items.DeliveryExerciseActivity;
import com.beyole.intelligentcampus.me.items.DeliveryVoteActivity;
import com.beyole.intelligentcampus.me.items.InformationActivity;
import com.beyole.intelligentcampus.me.items.NoticeActivity;
import com.beyole.intelligentcampus.settings.QRActivity;
import com.beyole.util.JsonUtils;
import com.beyole.util.SyncHttp;
import com.beyole.view.ImageDetailsView;
import com.beyole.view.LineGridview;
import com.beyole.view.PublicNoticeView;

/**
 * 我 fragment
 * 
 * @author Iceberg
 * 
 */
public class MeFragment extends Fragment {

	private static final int TITLE1 = 0;
	private static final int TITLE2 = 1;
	private static final int TITLE3 = 2;
	private static final int TITLE4 = 3;
	private static final int TITLE5 = 4;
	private static final int TITLE6 = 5;
	private static final int TITLE7 = 6;
	private static final int TITLE8 = 7;
	private LineGridview id_me_gridviews;
	private RelativeLayout id_me_hidden_loginform;
	private PublicNoticeView id_me_publicnotice;
	private ScrollView id_me_gridview_scrollview;
	private static final int lineNumber = 4;
	private ImageDetailsView userImageDetailsView;
	private TextView mUserDesc;
	private int mHeight;
	private View view;
	// , "数据中心", "勋章墙", "积分商城", "发起投票"
	// , R.drawable.me_main06, R.drawable.me_main07, R.drawable.me_main08, R.drawable.me_main05
	private String[] titles = new String[] { "我的资料", "我的名片", "通知", "发布活动" };
	private int[] img = new int[] { R.drawable.me_main01, R.drawable.me_main02, R.drawable.me_main03, R.drawable.me_main04 };

	private Button mRegisterBtn;
	private Button mLoginBtn;

	private GlobalParameterApplication application;
	private User currentUser;

	private TextView mFansNumberTv;
	private TextView mActivityNumberTv;
	private TextView mFocusNumberTv;
	private ImageButton mFansNumberIb;
	private ImageButton mActivityNumberIb;
	private ImageButton mFocusNumberIb;
	private LinearLayout mTabFansLbtn;
	private LinearLayout mTabFocusLbtn;
	private LinearLayout mTabExerciseBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tab03, container, false);
		initViews();
		application = (GlobalParameterApplication) getActivity().getApplicationContext();
		currentUser = application.getUser();
		if (currentUser != null && currentUser.getUserId() > 0) {
			mFansNumberTv.setVisibility(View.VISIBLE);
			mActivityNumberTv.setVisibility(View.VISIBLE);
			mFocusNumberTv.setVisibility(View.VISIBLE);
			mFansNumberIb.setVisibility(View.GONE);
			mActivityNumberIb.setVisibility(View.GONE);
			mFocusNumberIb.setVisibility(View.GONE);
			userImageDetailsView.setTitleText(currentUser.getUserName());
			mUserDesc.setText(currentUser.getUserDescription());
			Log.i("test", "用户描述:" + currentUser.getUserDescription());
			// 获取屏幕宽度和高度
			WindowManager wm = getActivity().getWindowManager();
			int width = wm.getDefaultDisplay().getWidth();
			int lines = titles.length % 2 == 0 ? titles.length / lineNumber : titles.length / lineNumber + 1;
			mHeight = Math.round((width / lineNumber) * 1.2f);
			ItemAdapter itemAdapter = new ItemAdapter(titles, img, getActivity(), mHeight * titles.length);
			// 设置gridview的单元格宽度
			id_me_gridviews.setColumnWidth(width / lineNumber - 10);
			id_me_gridviews.setSelector(R.drawable.hidden_yellow);
			id_me_gridviews.setAdapter(itemAdapter);
			LayoutParams lp = id_me_gridviews.getLayoutParams();
			lp.height = mHeight * lines;
			id_me_gridviews.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					switch (position) {
					case TITLE1:
						Intent intent0 = new Intent(getActivity(), InformationActivity.class);
						startActivity(intent0);
						break;
					case TITLE2:
						Intent intent1 = new Intent(getActivity(), QRActivity.class);
						startActivity(intent1);
						break;
					case TITLE3:
						Intent intent2=new Intent(getActivity(),NoticeActivity.class);
						startActivity(intent2);
						break;
					case TITLE4:
						Intent intent3=new Intent(getActivity(),DeliveryExerciseActivity.class);
						startActivity(intent3);
						break;
					/*case TITLE5:
						Intent intent4=new Intent(getActivity(),DeliveryVoteActivity.class);
						//Intent intent = new Intent(getActivity(), FindMeActivity.class);
						startActivity(intent4);
						break;*/
					}
				}
			});
			new MyAsyncTask().execute();
			new MyAsyncNotificationTask().execute();
			new MyActivityUserAsyncTask().execute();
		} else {
			id_me_hidden_loginform.setVisibility(View.VISIBLE);
			id_me_gridviews.setVisibility(View.GONE);
			id_me_gridview_scrollview.setVisibility(View.GONE);
			id_me_publicnotice.setVisibility(View.GONE);
		}

		return view;
	}

	private void initViews() {
		mFansNumberTv = (TextView) view.findViewById(R.id.id_me_fans_number);
		mActivityNumberTv = (TextView) view.findViewById(R.id.id_me_activity_number);
		mFocusNumberTv = (TextView) view.findViewById(R.id.id_me_focus_number);
		mFansNumberIb = (ImageButton) view.findViewById(R.id.id_me_fans_img);
		mActivityNumberIb = (ImageButton) view.findViewById(R.id.id_me_activity_img);
		mFocusNumberIb = (ImageButton) view.findViewById(R.id.id_me_focus_img);
		id_me_gridviews = (LineGridview) view.findViewById(R.id.id_me_gridviews);
		id_me_hidden_loginform = (RelativeLayout) view.findViewById(R.id.id_me_hidden_loginform);
		id_me_publicnotice = (PublicNoticeView) view.findViewById(R.id.id_me_publicnotice);
		id_me_gridview_scrollview = (ScrollView) view.findViewById(R.id.id_me_gridview_scrollview);
		mRegisterBtn = (Button) view.findViewById(R.id.id_me_login_register_form_btn_register);
		userImageDetailsView = (ImageDetailsView) view.findViewById(R.id.id_imageDetailsView);
		mUserDesc = (TextView) view.findViewById(R.id.id_me_user_desc);
		mLoginBtn = (Button) view.findViewById(R.id.id_me_login_register_form_btn_login);
		mTabFansLbtn = (LinearLayout) view.findViewById(R.id.id_me_tab_fans);
		mTabFocusLbtn = (LinearLayout) view.findViewById(R.id.id_me_tab_focus);
		mTabExerciseBtn = (LinearLayout) view.findViewById(R.id.id_me_tab_exercise);
		MyOnClickListener listener = new MyOnClickListener();
		mRegisterBtn.setOnClickListener(listener);
		mLoginBtn.setOnClickListener(listener);
		mTabFansLbtn.setOnClickListener(listener);
		mTabFocusLbtn.setOnClickListener(listener);
		mTabExerciseBtn.setOnClickListener(listener);
		userImageDetailsView.setOnClickListener(listener);
	}

	class MyOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.id_me_login_register_form_btn_register:
				Intent intent = new Intent(getActivity(), RegisterActivity.class);
				startActivity(intent);
				break;
			case R.id.id_me_login_register_form_btn_login:
				Intent intent1 = new Intent(getActivity(), LoginActivity.class);
				startActivity(intent1);
				getActivity().finish();
				break;
			case R.id.id_me_tab_fans:
				Intent intent2 = null;
				if (currentUser != null && currentUser.getUserId() > 0) {
					intent2 = new Intent(getActivity(), FansActivity.class);
					intent2.putExtra("userId", currentUser.getUserId());
				} else {
					intent2 = new Intent(getActivity(), LoginActivity.class);
				}
				startActivity(intent2);
				break;
			case R.id.id_me_tab_focus:
				Intent intent3 = null;
				if (currentUser != null && currentUser.getUserId() > 0) {
					intent3 = new Intent(getActivity(), FocusActivity.class);
					intent3.putExtra("userId", currentUser.getUserId());
				} else {
					intent3 = new Intent(getActivity(), LoginActivity.class);
				}
				startActivity(intent3);
				break;
			case R.id.id_me_tab_exercise:
				Intent intent4 = null;
				if (currentUser != null && currentUser.getUserId() > 0) {
					intent4 = new Intent(getActivity(), ExerciseActivity.class);
					intent4.putExtra("userId", currentUser.getUserId());
				} else {
					intent4 = new Intent(getActivity(), LoginActivity.class);
				}
				startActivity(intent4);
				break;
			case R.id.id_imageDetailsView:
				Intent intent5 = null;
				if (currentUser != null && currentUser.getUserId() > 0) {
					intent5 = new Intent(getActivity(), PersonZoneActivity.class);
				} else {
					intent5 = new Intent(getActivity(), LoginActivity.class);
				}
				startActivity(intent5);
				break;
			}
		}

	}

	class MyAsyncTask extends AsyncTask<Void, Void, Map<String, String>> {

		@Override
		protected Map<String, String> doInBackground(Void... params) {
			SyncHttp http = new SyncHttp();
			Map<String, String> map = new HashMap<String, String>();
			String params1 = "userId=" + currentUser.getUserId();
			try {
				String restr = http.httpGet(APIConstant.FINDUSERSFANSANDFOCUSNUMBER, params1);
				map = JsonUtils.readJsonToMap(restr);
				Log.i("fans", "获取网络参数:" + map.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return map;
		}

		@Override
		protected void onPostExecute(Map<String, String> result) {
			String fans = result.get("fans");
			String focus = result.get("focus");
			Log.i("fans", "获取网络参数:" + "fans:" + fans + " focus:" + focus);
			mFansNumberTv.setText(fans);
			mFocusNumberTv.setText(focus);

		}
	}

	class MyAsyncNotificationTask extends AsyncTask<Void, Void, List<String>> {

		@Override
		protected List<String> doInBackground(Void... params) {
			SyncHttp http = new SyncHttp();
			List<String> list = new ArrayList<String>();
			try {
				String restr = http.httpGet(APIConstant.GETNOTIFICATIONNOTICE, null);
				JSONObject jsonObject = new JSONObject(restr);
				JSONArray array = jsonObject.getJSONArray("notificationList");
				for (int i = 0; i < array.length(); i++) {
					JSONObject object = array.getJSONObject(i);
					String content = object.getString("notificationContent");
					list.add(content);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

		@Override
		protected void onPostExecute(List<String> result) {
			id_me_publicnotice.bindNotices(result);
		}

	}

	class MyActivityUserAsyncTask extends AsyncTask<Void, Void, Map<String, String>> {

		@Override
		protected Map<String, String> doInBackground(Void... params) {
			SyncHttp http = new SyncHttp();
			Map<String, String> map = new HashMap<String, String>();
			String params1 = "userId=" + currentUser.getUserId();
			try {
				String restr = http.httpGet(APIConstant.GETUSERACTIVITYCOUNT, params1);
				map = JsonUtils.readJsonToMap(restr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return map;
		}

		@Override
		protected void onPostExecute(Map<String, String> result) {
			String activityUserCount = result.get("activityUserCount");
			mActivityNumberTv.setText(activityUserCount);

		}
	}
}
