package com.beyole.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;

import com.beyole.intelligentcampus.R;

/**
 * 图片加textview 详细介绍自定义view的实现
 * 
 * @author Iceberg
 * 
 */
public class ImageDetailsView extends View implements OnClickListener {
	private String mTitleText="";
	private int mTitleSize;
	private int mTitleColor;
	private Bitmap mImage;
	private int mImageScope;
	private Rect mRect;
	private Paint mPaint;
	private Rect mTextBound;
	private int mHeight;
	private int mWidth;
	public static final int IMAGEFILLXY = 0;
	public static final int IMAGECENTER = 1;

	private ImageDetailsViewListener mListener = null;

	private long firstClick;
	private long lastClick;
	private int BORDER_COLOR = 0x00ffffff;

	public ImageDetailsView(Context context) {
		this(context, null);
	}

	public ImageDetailsView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ImageDetailsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// 初始化时监听触发事件
		setOnClickListener(this);
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ImageDetailsView, defStyle, 0);
		int n = a.getIndexCount();
		Log.i("test", "自定义的属性个数为：" + n);
		for (int i = 0; i < n; i++) {
			int attr = a.getIndex(i);
			Log.i("test", "自定义的属性为：" + attr);
			switch (attr) {
			case R.styleable.ImageDetailsView_titleText:
				if(mTitleText.length()<=0){
				mTitleText = a.getString(attr);
				}
				Log.i("test", "自定义属性text为:" + a.getString(attr));
				break;
			case R.styleable.ImageDetailsView_titleSize:
				mTitleSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics()));
				break;
			case R.styleable.ImageDetailsView_titleColor:
				mTitleColor = a.getColor(attr, Color.GREEN);
				break;
			case R.styleable.ImageDetailsView_titleImage:
				// 获取图片文件
				mImage = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
				break;
			case R.styleable.ImageDetailsView_titleImageScope:
				mImageScope = a.getInt(attr, 0);
				break;
			}
		}
		a.recycle();
		mRect = new Rect();
		mPaint = new Paint();
		mTextBound = new Rect();
		// 绘制字体需要的范围
		mPaint.setTextSize(mTitleSize);
		mPaint.setAntiAlias(true);
		Log.i("test", "文字是:" + mTitleText);
		mPaint.getTextBounds(mTitleText, 0, mTitleText.length(), mTextBound);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		// 获取宽度
		if (widthMode == MeasureSpec.EXACTLY) {
			mWidth = widthSize;
		} else {
			// 由图片决定的宽
			int imageWidth = getPaddingLeft() + getPaddingRight() + mImage.getWidth();
			// 由字体决定的宽
			int textWidth = getPaddingLeft() + getPaddingRight() + mTextBound.width();
			if (widthMode == MeasureSpec.AT_MOST) { // wrap_content
				// 取出最大宽度
				int desire = Math.max(imageWidth, textWidth);
				// 取出最小宽度
				mWidth = Math.min(desire, widthSize);
			}
		}

		// 获取高度
		if (heightMode == MeasureSpec.EXACTLY) {
			mHeight = heightSize;
		} else {
			int desire = getPaddingTop() + getPaddingBottom() + mImage.getHeight() + mTextBound.height();
			if (heightMode == MeasureSpec.AT_MOST) {// wrap_content
				mHeight = Math.min(desire, heightSize);
			}
		}
		// 设置为获取到的宽和高
		setMeasuredDimension(mWidth, mHeight);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 绘制边框
		// 设置画笔粗细
		mPaint.setStrokeWidth(4);
		// 设置画笔风格
		mPaint.setStyle(Paint.Style.STROKE);
		// 设置画笔颜色
		mPaint.setColor(BORDER_COLOR);
		mPaint.setAntiAlias(true);
		canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

		// 绘制文字
		mPaint.setColor(mTitleColor);
		mPaint.setStyle(Style.FILL);
		// 当字体的宽度大于设置的宽度的情况
		if (mTextBound.width() > mWidth) {
			TextPaint textPaint = new TextPaint(mPaint);
			// 缩略字体填充
			String msg = TextUtils.ellipsize(mTitleText, textPaint, (float) (mWidth - getPaddingLeft() - getPaddingRight()), TextUtils.TruncateAt.END).toString();
			canvas.drawText(msg, getPaddingLeft(), mHeight - getPaddingBottom(), textPaint);
		} else {
			// 正常情况,将字体居中
			canvas.drawText(mTitleText, mWidth / 2 - mTextBound.width() * 1.0f / 2, mHeight - getPaddingBottom(), mPaint);
		}

		// 绘制图片
		mRect.left = getPaddingLeft();
		mRect.top = getPaddingTop();
		mRect.right = mWidth - getPaddingRight();
		mRect.bottom = mHeight - getPaddingBottom() - mTextBound.height();

		// 判断图片的模式
		if (mImageScope == IMAGEFILLXY) {// 图片为填充模式
			canvas.drawBitmap(mImage, null, mRect, mPaint);
		} else {
			// 图片为居中模式
			mRect.left = mWidth / 2 - mImage.getWidth() / 2;
			mRect.right = mWidth / 2 + mImage.getWidth() / 2;
			mRect.top = (mHeight - mTextBound.height()) / 2 - mImage.getHeight() / 2;
			mRect.bottom = (mHeight - mTextBound.height()) / 2 + mImage.getHeight() / 2;
			canvas.drawBitmap(mImage, null, mRect, mPaint);
		}
	}

	public interface ImageDetailsViewListener {
		public void onclick();
	}

	// 设置单击事件监听器
	public void setImageDetailsViewOnclickListener(ImageDetailsViewListener listener) {
		this.mListener = listener;
	}

	@Override
	public void onClick(View v) {
		if (mListener == null) {
			mListener = new ImageDetailsViewListener() {
				@Override
				public void onclick() {
				}
			};
		}
		mListener.onclick();
	}
	
	public void setTitleText(String text){
		mTitleText=text;
	}
}
