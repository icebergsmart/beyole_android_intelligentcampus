package com.beyole.view.commondialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.beyole.intelligentcampus.R;



public class RoundProgressbarWithNumbers extends HorizontalProgressbarWithNumbers {

	private int mRadius = dp2px(30);

	public RoundProgressbarWithNumbers(Context context) {
		this(context, null);
	}

	public RoundProgressbarWithNumbers(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public RoundProgressbarWithNumbers(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mProcessReachedBarHeight = (int) (mProcessUnreachedBarHeight * 2.5f);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressbarWithNumbers);
		mRadius = (int) typedArray.getDimension(R.styleable.RoundProgressbarWithNumbers_radius, mRadius);
		typedArray.recycle();
		mTextSize = sp2px(14);
		mPaint.setStyle(Style.STROKE);// 画笔类型 STROKE空心 FILL 实心 FILL_AND_STROKE
												// 用契形填充
		mPaint.setAntiAlias(true); // 防锯齿
		mPaint.setDither(true);// 防抖动
		mPaint.setStrokeCap(Cap.ROUND);// //画笔笔刷类型 如影响画笔但始末端
	}

	@Override
	protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);

		int paintWidth = Math.max(mProcessReachedBarHeight, mProcessUnreachedBarHeight);
		if (heightMode != MeasureSpec.EXACTLY) {
			int exceptHeight = getPaddingTop() + getPaddingBottom() + paintWidth + mRadius * 2;
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight, MeasureSpec.EXACTLY);
		}

		if (widthMode != MeasureSpec.EXACTLY) {
			int exceptWidth = getPaddingLeft() + getPaddingRight() + mRadius * 2 + paintWidth;
			widthMeasureSpec = MeasureSpec.makeMeasureSpec(exceptWidth, MeasureSpec.EXACTLY);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		String progress = getProgress() + "%";
		float textWidth = mPaint.measureText(progress);
		float textHeight = (mPaint.ascent() + mPaint.descent()) / 2;
		canvas.save();
		canvas.translate(getPaddingLeft(), getPaddingTop());
		mPaint.setStyle(Style.STROKE);
		mPaint.setColor(mUnreachedBarColor);
		mPaint.setStrokeWidth(mProcessUnreachedBarHeight);
		canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
		mPaint.setColor(mReachedBarColor);
		mPaint.setStrokeWidth(mProcessReachedBarHeight);
		float sweepAngle = getProgress() * 1.0f / getMax() * 360;
		canvas.drawArc(new RectF(0, 0, mRadius * 2, mRadius * 2), 0, sweepAngle, false, mPaint);
		mPaint.setStyle(Style.FILL);
		canvas.drawText(progress, mRadius - textWidth / 2, mRadius - textHeight, mPaint);
		canvas.restore();
	}
}
