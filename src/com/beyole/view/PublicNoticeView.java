package com.beyole.view;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.beyole.bean.Notification;
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
	public void bindNotices(List<String> data) {
		mViewFlipper.removeAllViews();
		if(data.size()>0){
			for (int j = 0; j < data.size(); j++) {
				String text = data.get(j);
				TextView textView = new TextView(mContext);
				textView.setText(text);
				LayoutParams layoutParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
				mViewFlipper.addView(textView, layoutParams);
			}
		}

	}

}
