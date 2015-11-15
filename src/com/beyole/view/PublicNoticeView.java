package com.beyole.view;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.beyole.constant.APIConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.util.SyncHttp;

/**
 * 滚动公告栏
 * 
 * @author Iceberg
 * 
 */
public class PublicNoticeView extends LinearLayout {

	private static final String TAG = "PUBLICNOTICEVIEW";
	private Context mContext;
	private ViewFlipper mViewFlipper;
	private View mScrollTitleView;

	public PublicNoticeView(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public PublicNoticeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	private void init() {
		bindLinearLayout();
		bindNotices();
	}

	/**
	 * 初始化自定义的布局
	 */
	private void bindLinearLayout() {
		mScrollTitleView = LayoutInflater.from(mContext).inflate(R.layout.scrollnoticebar, null);
		LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		addView(mScrollTitleView, params);
		mViewFlipper = (ViewFlipper) mScrollTitleView.findViewById(R.id.id_scrollNoticeTitle);
		mViewFlipper.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom));
		mViewFlipper.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_top));
		mViewFlipper.startFlipping();
	}

	/**
	 * 网络请求内容后进行适配
	 */
	protected void bindNotices() {
		mViewFlipper.removeAllViews();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				SyncHttp http=new SyncHttp();
				try{
					String restr=http.httpGet(APIConstant.GETNOTIFICATIONNOTICE, null);
					Log.i("notice","返回值为："+restr);
					JSONObject object=new JSONObject(restr);
					JSONArray array=object.getJSONArray("notificationList");
					for (int j = 0; j < array.length(); j++) {
						JSONObject jsonObject=array.getJSONObject(j);
						String text = jsonObject.getString("notificationContent");
						TextView textView = new TextView(mContext);
						textView.setText(text);
						LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
						mViewFlipper.addView(textView, layoutParams);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
		
	}
	

}
