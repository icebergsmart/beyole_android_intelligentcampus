package com.beyole.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.beyole.intelligentcampus.R;

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
	private Intent intent;

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				// bindNotices();
				break;

			case -1:
				bindNotices();
				break;
			}
		};
	};

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
		Message message = new Message();
		message.what = -1;
		mHandler.sendMessageDelayed(message, 1500);
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
		View v = mViewFlipper.getCurrentView();
	}

	/**
	 * 网络请求内容后进行适配
	 */
	protected void bindNotices() {
		mViewFlipper.removeAllViews();
		int i = 0;
		while (i < 5) {
			String text = "公告:恭喜您中了500W,赶紧去领取吧!";
			TextView textView = new TextView(mContext);
			textView.setText(text);
			LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
			mViewFlipper.addView(textView, layoutParams);
			i++;
		}
	}

}
