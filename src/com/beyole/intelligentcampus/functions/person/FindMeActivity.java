package com.beyole.intelligentcampus.functions.person;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.constant.APIConstant;
import com.beyole.constant.UserConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.me.CaptureActivity;
import com.beyole.intelligentcampus.me.FocusUserDetailsActivity;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;
import com.beyole.view.EditTextWithRightButton;

/**
 * 功能->findme界面
 * 
 * @author Iceberg
 * 
 */
public class FindMeActivity extends Activity {

	private PopupWindow popupWindow;
	private LayoutInflater inflater = null;
	private static final int CHANGE = 1;
	private static final int DISCHANGE = 0;
	private ImageView mPopWindowIv;
	private LinearLayout mHasResultLinearLayout;
	private TextView mInputResult;
	private TextView scannedResult;
	private EditTextWithRightButton mEditText;
	private int sendUserId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_findme_talk);
		initViews();
		initEvents();
	}

	private void initEvents() {
		// 搜索框监听器，动态显示查询
		TextWatcher mTextWatcher = new TextWatcher() {
			private CharSequence temp;
			private int editStart;
			private int editEnd;

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (s.length() > 0) {
					mHasResultLinearLayout.setVisibility(View.VISIBLE);
					mInputResult.setText(s);// 将输入的内容实时显示
				} else {
					mHasResultLinearLayout.setVisibility(View.GONE);
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		};
		mEditText.addTextChangedListener(mTextWatcher);
		mHasResultLinearLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent intent = new Intent(FindMeActivity.this,
				// PersonZoneActivity.class);
				getUserInformations(mEditText.getText().toString());
			}
		});
	}

	private void initViews() {
		initPopupwindowTools();
		mPopWindowIv = (ImageView) findViewById(R.id.findme_tools_popupwindow_iv);
		mPopWindowIv.setOnClickListener(new popAction(80));
		mEditText = (EditTextWithRightButton) findViewById(R.id.id_activity_findme_et_search_username);
		mHasResultLinearLayout = (LinearLayout) findViewById(R.id.id_person_findme_talk_search_hint_ll);

		mInputResult = (TextView) findViewById(R.id.id_person_findme_talk_search_hint_ll_tv);
		scannedResult = (TextView) findViewById(R.id.id_findme_search_tools_result_return_tv);
	}

	/**
	 * 初始化弹出工具栏
	 */
	private void initPopupwindowTools() {
		inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View popView = inflater.inflate(R.layout.findme_popupwindow_tool_layout, null);
		popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		popupWindow.setBackgroundDrawable(new ColorDrawable(0));
		popView.findViewById(R.id.id_findme_scanner_tools_ll).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(FindMeActivity.this, CaptureActivity.class);
				startActivityForResult(intent, 0);
			}
		});
	}

	// 显示弹出框
	public void showPop(View parent, int x, int y, int postion) {
		popupWindow.showAtLocation(parent, 0, x, y);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		// 当popupwindow被点击时，切换imagebutton的样式
		if (popupWindow.isShowing()) {
			myHandler.sendEmptyMessage(CHANGE);
		}
		popupWindow.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				myHandler.sendEmptyMessage(DISCHANGE);
			}
		});
	}

	// 设置点击更多按钮的handler处理事件改变imagebutton的样式
	private Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case CHANGE:
				mPopWindowIv.setBackgroundResource(R.drawable.findme_popupwindow_close);
				break;
			case DISCHANGE:
				mPopWindowIv.setBackgroundResource(R.drawable.findme_popupwindow_original);
				break;
			}
		};
	};

	// 设置popupwindow监听事件
	public class popAction implements OnClickListener {
		int position;

		public popAction(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			int[] arrayOfInt = new int[2];
			v.getLocationOnScreen(arrayOfInt);
			int x = arrayOfInt[0];
			int y = arrayOfInt[1];
			showPop(v, x, y + 70, position);
		}
	}

	/**
	 * 获取扫描二维码返回结果，跳转到查找用户的个人界面
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// 处理扫描结果（在界面上显示）
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			// 二维码扫描结果||用户名唯一|查询条件
			String scanResult = bundle.getString("result");
			getUserInformations(scanResult);
		}
	}

	public void getUserInformations(String resultUserName) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", resultUserName);
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDUSERIDBYUSERNAMEINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				try {
					if (response.getInt("code") == UserConstant.QUERY_USER_BY_USERNAME_SUCCESS) {
						sendUserId = response.getInt("userId");
						Intent intent = new Intent(FindMeActivity.this, FocusUserDetailsActivity.class);
						intent.putExtra("userId", sendUserId);
						startActivity(intent);
					} else if (response.getInt("code") == UserConstant.QUERY_USER_BY_USERNAME_ERROR_WITH_NO_SUCH_USER) {
						Toast.makeText(FindMeActivity.this, "不存在此用户!", Toast.LENGTH_LONG).show();
					} else if (response.getInt("code") == UserConstant.QUERY_USER_BY_USERNAME_ERROR_WITH_SYSTEM_EXCEPTION) {
						Toast.makeText(FindMeActivity.this, "服务器端异常!", Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					Toast.makeText(FindMeActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(FindMeActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(this.getApplicationContext()).addToRequestQueue(request);
	}
}
