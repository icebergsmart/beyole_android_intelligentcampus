package com.beyole.view.locker;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.beyole.intelligentcampus.R;
import com.beyole.view.locker.GestureLockView.Mode;

/**
 * 整体包含n*n个GestureLockView,每个GestureLockView之间都有一定的margin
 * 最外层的GestureLockView与容器之间也存在着相应的外边距
 * 
 * @author Iceberg
 * 
 */
public class GestureLockViewGroup extends RelativeLayout {
	private static final String TAG = "GestureLockViewGroup";
	/**
	 * 保存所有的GestureLockView
	 */
	private GestureLockView[] mGestureLockViews;
	/**
	 * 每个边的GestureLockView数目
	 */
	private int mCount = 4;
	/**
	 * 存储答案
	 */
	private int[] mAnswer = { 0, 1, 2, 5, 8 };
	/**
	 * 保存用户选中的GestureLockView的id
	 */
	private List<Integer> mChoose = new ArrayList<Integer>();
	private Paint mPaint;
	/**
	 * 每个GestureLockView中间的间距，设置为：mGestureLockViewWidth*25%
	 */
	private int mMarginBetweenLockView = 30;
	/**
	 * GestureLockView的边长 4 * mWidth / ( 5 * mCount + 1 )
	 */
	private int mGestureLockViewWidth;
	/**
	 * GestureLockView无手指触摸的状态下内圆的颜色
	 */
	private int mNoFingerInnerCircleColor = 0xFF939090;
	/**
	 * GestureLockView无手指触摸的状态下外圆的颜色
	 */
	private int mNoFingerOuterCircleColor = 0xFFE0DBDB;
	/**
	 * GestureLockView手指触摸的状态下内圆和外圆的颜色
	 */
	private int mFingerOnColor = 0xFF378FC9;
	/**
	 * GestureLockView手指抬起的状态下内圆和外圆的颜色
	 */
	private int mFingerUpColor = 0xFFFF0000;
	/**
	 * 宽度
	 */
	private int mWidth;
	/**
	 * 高度
	 */
	private int mHeight;
	private Path mPath;
	/**
	 * 指引线的开始位置X
	 */
	private int mLastPathX;
	/**
	 * 指引线的开始位置Y
	 */
	private int mLastPathY;
	/**
	 * 指引下的结束位置
	 */
	private Point mTmpTarget = new Point();
	/**
	 * 最大尝试次数为4次
	 */
	private int mTryTimes = 4;
	/**
	 * 是否是设置手势密码
	 */
	private boolean isSetValue = false;
	private OnGestureLockViewListener mOnGestureLockViewListener;

	public GestureLockViewGroup(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GestureLockViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		/**
		 * 获取自定义的参数的值
		 */
		TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.GestureLockViewGroup, defStyle, 0);
		int n = ta.getIndexCount();
		for (int i = 0; i < n; i++) {
			int attr = ta.getIndex(i);
			switch (attr) {
			case R.styleable.GestureLockViewGroup_color_no_finger_inner_circle:
				mNoFingerInnerCircleColor = ta.getColor(attr, mNoFingerInnerCircleColor);
				break;
			case R.styleable.GestureLockViewGroup_color_no_finger_outer_circle:
				mNoFingerOuterCircleColor = ta.getColor(attr, mNoFingerOuterCircleColor);
				break;
			case R.styleable.GestureLockViewGroup_color_finger_on:
				mFingerOnColor = ta.getColor(attr, mFingerOnColor);
				break;
			case R.styleable.GestureLockViewGroup_color_finger_up:
				mFingerUpColor = ta.getColor(attr, mFingerUpColor);
				break;
			case R.styleable.GestureLockViewGroup_count:
				mCount = ta.getInt(attr, 3);
				break;
			case R.styleable.GestureLockViewGroup_tryTimes:
				mTryTimes = ta.getInt(attr, 5);
			default:
				break;
			}
		}
		ta.recycle();
		// 初始化画笔
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPath = new Path();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);
		mWidth = mHeight = mWidth < mHeight ? mWidth : mHeight;
		// 初始化GestureLockViews
		if (mGestureLockViews == null) {
			mGestureLockViews = new GestureLockView[mCount * mCount];
			// 计算每个GestureLockView的宽度
			mGestureLockViewWidth = (int) (4 * mWidth * 1.0f / (5 * mCount + 1));
			// 计算每个GestureLockView的间距
			mMarginBetweenLockView = (int) (mGestureLockViewWidth * 0.25);
			// 设置画笔的宽度为GestureLockView的内圆直径稍微小点（不喜欢的话，随便设）
			mPaint.setStrokeWidth(mGestureLockViewWidth * 0.29f);
			for (int i = 0; i < mGestureLockViews.length; i++) {
				// 初始化每个GestureLockView
				mGestureLockViews[i] = new GestureLockView(getContext(), mNoFingerInnerCircleColor, mNoFingerOuterCircleColor, mFingerOnColor, mFingerUpColor);
				mGestureLockViews[i].setId(i + 1);
				// 设置参数，主要是定位GestureLockView间的位置
				LayoutParams lockerParams = new LayoutParams(mGestureLockViewWidth, mGestureLockViewWidth);
				// 不是每行的第一个，则设置位置为前一个的右边
				if (i % mCount != 0) {
					lockerParams.addRule(RelativeLayout.RIGHT_OF, mGestureLockViews[i - 1].getId());
				}
				// 从第二行开始，设置为上一行同一位置view的下面
				if (i > mCount - 1) {
					lockerParams.addRule(RelativeLayout.BELOW, mGestureLockViews[i - mCount].getId());
				}
				// 设置右下左上的边距
				int rightMargin = mMarginBetweenLockView;
				int bottomMargin = mMarginBetweenLockView;
				int leftMargin = 0;
				int topMargin = 0;
				// 每个view都有右外边距和底外边距，第一行的有上外边距，第一列的有左外边距
				if (i >= 0 && i < mCount) { // 第一行
					topMargin = mMarginBetweenLockView;
				}
				if (i % mCount == 0) {// 第一列
					leftMargin = mMarginBetweenLockView;
				}
				lockerParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
				mGestureLockViews[i].setMode(Mode.STATUS_NO_FINGER);
				addView(mGestureLockViews[i], lockerParams);
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			reset();
			break;
		case MotionEvent.ACTION_MOVE:
			mPaint.setColor(mFingerOnColor);
			mPaint.setAlpha(50);
			GestureLockView child = getChildByPosition(x, y);
			if (child != null) {
				int cId = child.getId();
				if (!mChoose.contains(cId)) {
					mChoose.add(cId);
					child.setMode(Mode.STATUS_FINGER_ON);
					if (mOnGestureLockViewListener != null) {
						mOnGestureLockViewListener.onBlockSelected(cId);
					}
					// 设置指引线的起点
					mLastPathX = child.getLeft() / 2 + child.getRight() / 2;
					mLastPathY = child.getTop() / 2 + child.getBottom() / 2;
					// 当前添加为第一个
					if (mChoose.size() == 1) {
						mPath.moveTo(mLastPathX, mLastPathY);
					} else {
						// 如果不是第一个，将两者用线连上
						mPath.lineTo(mLastPathX, mLastPathY);
					}
				}
			}
			mTmpTarget.x = x;
			mTmpTarget.y = y;
			break;
		case MotionEvent.ACTION_UP:
			mPaint.setColor(mFingerUpColor);
			mPaint.setAlpha(50);
			this.mTryTimes--;
			// 回调是否成功
			if (mOnGestureLockViewListener != null && mChoose.size() > 0) {
				if(isSetValue==true){
					mOnGestureLockViewListener.setGestureValue(mChoose);
				}else{
					mOnGestureLockViewListener.onGestureEvent(checkAnswer());
					if (this.mTryTimes == 0) {
						mOnGestureLockViewListener.onUnMatchedExceedBoundary();
					}
				}
			}
			// 将终点位置设置为起点位置，即取消指引线
			mTmpTarget.x = mLastPathX;
			mTmpTarget.y = mLastPathY;
			// 改变子元素的状态为UP
			changeItemMode();
			// 计算每个箭头需要旋转的角度
			for (int i = 0; i + 1 < mChoose.size(); i++) {
				int childId = mChoose.get(i);
				int nextChildId = mChoose.get(i + 1);
				GestureLockView startGestureView = (GestureLockView) findViewById(childId);
				GestureLockView nextGestureView = (GestureLockView) findViewById(nextChildId);
				int dx = nextGestureView.getLeft() - startGestureView.getLeft();
				int dy = nextGestureView.getTop() - startGestureView.getTop();
				// 计算角度
				int angle = (int) (Math.toDegrees(Math.atan2(dy, dx)) + 90);
				startGestureView.setArrowDegree(angle);
			}
			break;
		}
		invalidate();
		return true;
	}

	private void changeItemMode() {
		for (GestureLockView gestureLockView : mGestureLockViews) {
			if (mChoose.contains(gestureLockView.getId())) {
				gestureLockView.setMode(Mode.STATUS_FINGER_UP);
			}
		}
	}

	/**
	 * 做一些必要的重置
	 */
	private void reset() {
		mChoose.clear();
		mPath.reset();
		for (GestureLockView gestureLockView : mGestureLockViews) {
			gestureLockView.setMode(Mode.STATUS_NO_FINGER);
			gestureLockView.setArrowDegree(-1);
		}
	}

	/**
	 * 检查用户手势是否正确
	 */
	private boolean checkAnswer() {
		if (mAnswer.length != mChoose.size()) {
			return false;
		}
		for (int i = 0; i < mAnswer.length; i++) {
			if (mAnswer[i] != mChoose.get(i)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 检查是否当前处在GestureLockView当中
	 * 
	 * @param child
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean checkPositionInChild(View child, int x, int y) {
		// 设置了内边距，即x,y必须落入下GestureLockView的内部中间的小区域中，可以通过调整padding使得x,y落入范围不变大，或者不设置padding
		int padding = (int) (mGestureLockViewWidth * 0.15);
		if (x >= child.getLeft() + padding && x <= child.getRight() - padding && y >= child.getTop() + padding && y <= child.getBottom() - padding) {
			return true;
		}
		return false;
	}

	/**
	 * 通过落入的x，y获取相应的GestureLockView
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private GestureLockView getChildByPosition(int x, int y) {
		for (GestureLockView gestureLockView : mGestureLockViews) {
			if (checkPositionInChild(gestureLockView, x, y)) {
				return gestureLockView;
			}
		}
		return null;
	}

	/**
	 * 设置回调接口
	 * 
	 */

	public void setOnGestureLockViewListener(OnGestureLockViewListener listener) {
		this.mOnGestureLockViewListener = listener;
	}

	/**
	 * 
	 * 对外公布设置答案的方法
	 */
	public void setAnswer(int[] answer) {
		this.mAnswer = answer;
	}

	/**
	 * 设置最大实验次数
	 * 
	 */
	public void setOnUnMatchedExceedBoundary(int boundary) {
		this.mTryTimes = boundary;
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		// 绘制GestureLockView之间的连线
		if (mPath != null) {
			canvas.drawPath(mPath, mPaint);
		}
		// 绘制指引线
		if (mChoose.size() > 0) {
			if (mLastPathX != 0 && mLastPathY != 0) {
				canvas.drawLine(mLastPathX, mLastPathY, mTmpTarget.x, mTmpTarget.y, mPaint);
			}
		}
	}

	public interface OnGestureLockViewListener {
		/**
		 * 单独选中元素的Id
		 * 
		 * @param cId
		 */
		public void onBlockSelected(int cId);

		/**
		 * 是否匹配
		 * 
		 * @param matched
		 */
		public void onGestureEvent(boolean matched);

		/**
		 * 超过尝试次数
		 */
		public void onUnMatchedExceedBoundary();
		
		/**
		 * 是否是设置手势密码
		 */
		public void setGestureValue(List<Integer> result);
	}
	/**
	 * 设置手势密码开启
	 */
	public void setGestureValueOn() {
		isSetValue = true;
	}

}
