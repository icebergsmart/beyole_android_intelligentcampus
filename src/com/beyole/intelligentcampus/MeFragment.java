package com.beyole.intelligentcampus;

import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.beyole.adapter.ItemAdapter;
import com.beyole.bean.GlobalParameterApplication;
import com.beyole.bean.User;
import com.beyole.intelligentcampus.me.FindMeActivity;
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
	private String[] titles = new String[] { "活动", "动态", "通知", "个推", "探索", "脑点子", "Find Me", "智慧树" };
	private int[] img = new int[] { R.drawable.more1, R.drawable.more2, R.drawable.more3, R.drawable.more4, R.drawable.more5, R.drawable.more6, R.drawable.more7, R.drawable.more7 };

	private Button mRegisterBtn;
	private Button mLoginBtn;

	private GlobalParameterApplication application;
	private User currentUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.tab03, container, false);
		initViews();
		application = (GlobalParameterApplication) getActivity().getApplicationContext();
		currentUser = application.getUser();
		if (currentUser!=null&&currentUser.getUserId() > 0) {
			userImageDetailsView.setTitleText(currentUser.getUserName());
			mUserDesc.setText(currentUser.getUserDescription());
			Log.i("test","用户描述:"+currentUser.getUserDescription());
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
					case TITLE7:
						Intent intent = new Intent(getActivity(), FindMeActivity.class);
						startActivity(intent);
						break;
					case TITLE8:
						Intent intent1 = new Intent(getActivity(), LoginActivity.class);
						startActivity(intent1);
						break;
					}
				}
			});
		} else {
			id_me_hidden_loginform.setVisibility(View.VISIBLE);
			id_me_gridviews.setVisibility(View.GONE);
			id_me_gridview_scrollview.setVisibility(View.GONE);
			id_me_publicnotice.setVisibility(View.GONE);
		}

		return view;
	}

	private void initViews() {
		id_me_gridviews = (LineGridview) view.findViewById(R.id.id_me_gridviews);
		id_me_hidden_loginform = (RelativeLayout) view.findViewById(R.id.id_me_hidden_loginform);
		id_me_publicnotice = (PublicNoticeView) view.findViewById(R.id.id_me_publicnotice);
		id_me_gridview_scrollview = (ScrollView) view.findViewById(R.id.id_me_gridview_scrollview);
		mRegisterBtn = (Button) view.findViewById(R.id.id_me_login_register_form_btn_register);
		userImageDetailsView=(ImageDetailsView) view.findViewById(R.id.id_imageDetailsView);
		mUserDesc=(TextView) view.findViewById(R.id.id_me_user_desc);
		mLoginBtn = (Button) view.findViewById(R.id.id_me_login_register_form_btn_login);
		MyOnClickListener listener = new MyOnClickListener();
		mRegisterBtn.setOnClickListener(listener);
		mLoginBtn.setOnClickListener(listener);
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
			}
		}

	}
}
