package com.beyole.view.commondialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.beyole.intelligentcampus.R;



public class HorizontalProgressbarWithNumbers extends ProgressBar {

	// 设置默认字体大小
	private static final int DEFAULT_TEXT_SIZE = 10;
	// 设置默认字体的颜色
	private static final int DEFAULT_TEXT_COLOR = 0XFFFC00D1;
	// 设置默认未处理完的进度条的颜色
	private static final int DEFAULT_PROCESS_UNREACHED_COLOR = 0XFFD3D6DA;
	// 设置默认正在运行的进度条的高度
	private static final int DEFAULT_PROCESS_UNREACHED_BAR_HEIGHT = 2;
	// 设置默认还未进行的进度条的高度
	private static final int DEFAULT_PROCESS_REACHED_BAR_HEIGHT = 2;
	// 设置默认的字体与正在运行的进度条的偏移
	private static final int DEFAULT_PROCESS_TEXT_OFFSET = 10;
	// 画笔
	protected Paint mPaint = new Paint();
	// 字体颜色
	protected int mTextColor = DEFAULT_TEXT_COLOR;
	// 字体大小 sp转换为px
	protected int mTextSize = sp2px(DEFAULT_TEXT_SIZE);
	// 字体偏移 dp转换为px
	protected int mTextOffset = dp2px(DEFAULT_PROCESS_TEXT_OFFSET);
	// 未在运行的进度条的高度
	protected int mProcessUnreachedBarHeight = dp2px(DEFAULT_PROCESS_UNREACHED_BAR_HEIGHT);
	// 正在运行的进度条的高度
	protected int mProcessReachedBarHeight = dp2px(DEFAULT_PROCESS_REACHED_BAR_HEIGHT);
	// 设置正在运行的进度条的颜色
	protected int mReachedBarColor = DEFAULT_TEXT_COLOR;
	// 设置未运行的进度条的颜色
	protected int mUnreachedBarColor = DEFAULT_PROCESS_UNREACHED_COLOR;
	// 进度条宽度
	protected int mRealWidth;
	// 是否显示文字进度
	protected boolean mIfDrawtext = true;
	// 是否可见
	protected static final int VISIBLE = 0;

	public HorizontalProgressbarWithNumbers(Context context) {
		this(context, null);
	}

	public HorizontalProgressbarWithNumbers(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public HorizontalProgressbarWithNumbers(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setHorizontalScrollBarEnabled(true);
		obtainStyledAttributeSet(attrs);
		mPaint.setColor(mTextColor);
		mPaint.setTextSize(mTextSize);
	}

	/**
	 * 获取xml中设置好的属性
	 * 
	 * @param attrs
	 */
	private void obtainStyledAttributeSet(AttributeSet attrs) {
		TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.HorizontalProgressbarWithNumbers);
		mTextColor = typedArray.getColor(R.styleable.HorizontalProgressbarWithNumbers_process_text_color, DEFAULT_TEXT_COLOR);
		mTextSize = (int) typedArray.getDimension(R.styleable.HorizontalProgressbarWithNumbers_process_text_size, mTextSize);
		mReachedBarColor = typedArray.getColor(R.styleable.HorizontalProgressbarWithNumbers_process_reached_color, mTextColor);
		mUnreachedBarColor = typedArray.getColor(R.styleable.HorizontalProgressbarWithNumbers_process_unreached_color, DEFAULT_PROCESS_UNREACHED_COLOR);
		mProcessReachedBarHeight = (int) typedArray.getDimension(R.styleable.HorizontalProgressbarWithNumbers_process_reached_bar_height, DEFAULT_PROCESS_REACHED_BAR_HEIGHT);
		mProcessUnreachedBarHeight = (int) typedArray.getDimension(R.styleable.HorizontalProgressbarWithNumbers_process_unreached_bar_height, DEFAULT_PROCESS_UNREACHED_BAR_HEIGHT);
		mTextOffset = typedArray.getInt(R.styleable.HorizontalProgressbarWithNumbers_process_text_offset, DEFAULT_PROCESS_TEXT_OFFSET);
		int textVisible = typedArray.getInt(R.styleable.HorizontalProgressbarWithNumbers_process_text_visibility, VISIBLE);
		if (textVisible != VISIBLE) {
			mIfDrawtext = false;
		}
		typedArray.recycle();
	}

	/**
	 * 所有的属性，包括进度条的宽度都是由用户自定义的，所以需要重新测量一下view的大小
	 */
	@Override
	protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		// 如果宽度模式为wrap_content,则进行测量
		if (heightMode != MeasureSpec.EXACTLY) {
			float textHeight = mPaint.ascent() + mPaint.descent();
			int exceptHeight = (int) (getPaddingTop() + getPaddingBottom() + Math.max(Math.max(mProcessReachedBarHeight, mProcessUnreachedBarHeight), Math.abs(textHeight)));
			// 重新设置宽度
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(exceptHeight, MeasureSpec.EXACTLY);
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected synchronized void onDraw(Canvas canvas) {
		// 保存当前画布
		canvas.save();
		// 画笔平移到getPaddingLeft(),getHeight/2。以此为坐标原点(0,0)
		canvas.translate(getPaddingLeft(), getHeight() / 2);
		boolean noNeedBg = false;
		// 当前进度与最大值的比率
		float ratio = getProgress() * 1.0f / getMax();
		// 已到达的宽度
		float processPosX = ratio * mRealWidth;
		// 绘制的文本
		String text = getProgress() + "%";
		// 拿到字体的宽度和高度
		float textWidth = mPaint.measureText(text);
		float textHeight = (mPaint.ascent() + mPaint.descent()) / 2;

		// 如果到达最后，则未进行的部分不用绘制
		if (processPosX + textWidth > mRealWidth) {
			processPosX = mRealWidth - textWidth;
			noNeedBg = true;
		}

		// 绘制到达的进度
		float endX = processPosX - mTextOffset / 2;
		if (endX > 0) {
			mPaint.setColor(mReachedBarColor);
			mPaint.setStrokeWidth(mProcessReachedBarHeight);
			canvas.drawLine(0, 0, endX, 0, mPaint);
		}
		// 绘制文本
		if (mIfDrawtext) {
			mPaint.setColor(mTextColor);
			mPaint.setAntiAlias(true);
			canvas.drawText(text, processPosX, -textHeight, mPaint);
		}
		// 绘制未到达的进度
		if (!noNeedBg) {
			float start = processPosX + textWidth + mTextOffset / 2;
			mPaint.setColor(mUnreachedBarColor);
			mPaint.setStrokeWidth(mProcessUnreachedBarHeight);
			canvas.drawLine(start, 0, mRealWidth, 0, mPaint);
		}
		canvas.restore();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mRealWidth = w - getPaddingLeft() - getPaddingRight();
	}

	/**
	 * dp转换为px
	 * 
	 * @param dpVal
	 * @return
	 */
	public int dp2px(int dpVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
	}

	/**
	 * sp转换为px
	 * 
	 * @param spVal
	 * @return
	 */
	public int sp2px(int spVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, getResources().getDisplayMetrics());
	}
}
