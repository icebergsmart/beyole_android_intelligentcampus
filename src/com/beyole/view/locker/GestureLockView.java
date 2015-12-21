package com.beyole.view.locker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.view.View;

public class GestureLockView extends View {

	private static final String TAG = "GestureLockView";

	/**
	 * GestureLockView的三种状态
	 * STATUS_NO_FINGER:没有手指触碰
	 * STATUS_FINGER_ON：手指触碰
	 * STATUS_FINGER_UP：手指抬起
	 */
	enum Mode {
		STATUS_NO_FINGER, STATUS_FINGER_ON, STATUS_FINGER_UP;
	}
	
	/**
	 * GestureLockView当前的状态为默认的没有手指触碰
	 */
	private Mode mCurretnStatus=Mode.STATUS_NO_FINGER;
	
	/**
	 * 宽度
	 */
	private int mWidth;
	/**
	 * 高度
	 */
	private int mHeight;
	/**
	 * 外圆半径
	 */
	private int mRadius;
	/**
	 * 画笔的宽度
	 */
	private int mStrokeWidth=2;
	/**
	 * 圆心坐标
	 */
	private int mCenterX;
	private int mCenterY;
	/**
	 * 画笔
	 */
	private Paint mPaint;
	/**
	 * 箭头 1/3的半径长度（小三角最长边的一半长度 = mArrawRate * mWidth / 2 ） 
	 */
	private float mArrowRate=0.333f;
	private int mArrowDegree=-1;
	private Path mArrowPath;
	/**
	 * 内圆的半径=mInnerCircleRadiusRate * mRadus
	 */
	private float mInnerCircleRadiusRate=0.3f;
	/**
	 * 四个颜色，可由用户自定义，初始化时传入
	 */
	private int mColorNoFingerInner;
	private int mColorNoFingerOuter;
	private int mColorFingerOn;
	private int mColorFingerUp;
	public GestureLockView(Context context,int colorNoFingerInner,int colorNoFingerOuter,int colorFingerOn,int colorFingerUp){
		super(context);
		this.mColorNoFingerInner=colorNoFingerInner;
		this.mColorNoFingerOuter=colorNoFingerOuter;
		this.mColorFingerOn=colorFingerOn;
		this.mColorFingerUp=colorFingerUp;
		mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
		mArrowPath=new Path();
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mWidth=MeasureSpec.getSize(widthMeasureSpec);
		mHeight=MeasureSpec.getSize(heightMeasureSpec);
		//取长和宽中的小值
		mWidth=mWidth<mHeight?mWidth:mHeight;
		mRadius=mCenterX=mCenterY=mWidth/2;
		mRadius-=mStrokeWidth/2;
		//绘制三角形，初始时是默认朝上的等腰三角形，用户绘制结束之后，根据由两个GestureLockView来决定旋转的度数
		float mArrowLength=mWidth/2*mArrowRate;
		mArrowPath.moveTo(mWidth/2, mStrokeWidth+2);
		mArrowPath.lineTo(mWidth/2-mArrowLength, mStrokeWidth+2+mArrowLength);
		mArrowPath.lineTo(mWidth/2+mArrowLength, mStrokeWidth+2+mArrowLength);
		mArrowPath.close();
		mArrowPath.setFillType(Path.FillType.WINDING);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		switch (mCurretnStatus) {
		case STATUS_FINGER_ON:
			//绘制外圆
			mPaint.setStyle(Style.STROKE);
			mPaint.setColor(mColorFingerOn);
			mPaint.setStrokeWidth(2);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
			//绘制内圆
			mPaint.setStyle(Style.FILL);
			canvas.drawCircle(mCenterX, mCenterY, mRadius*mInnerCircleRadiusRate, mPaint);
			break;

		case STATUS_FINGER_UP:
			mPaint.setColor(mColorFingerUp);
			mPaint.setStyle(Style.STROKE);
			mPaint.setStrokeWidth(2);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
			// 绘制内圆
			mPaint.setStyle(Style.FILL);
			canvas.drawCircle(mCenterX, mCenterY, mRadius* mInnerCircleRadiusRate, mPaint);
			drawArrow(canvas);
			break;
		case STATUS_NO_FINGER:
			//绘制外圆
			mPaint.setStyle(Style.FILL);
			mPaint.setColor(mColorNoFingerOuter);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
			//绘制内圆
			mPaint.setColor(mColorNoFingerInner);
			canvas.drawCircle(mCenterX, mCenterY, mRadius*mInnerCircleRadiusRate, mPaint);
			break;
		}
	}
	/**
	 * 绘制箭头
	 * @param canvas
	 */
	private void drawArrow(Canvas canvas){
		if(mArrowDegree!=-1){
			mPaint.setStyle(Style.FILL);
			canvas.save();
			canvas.rotate(mArrowDegree,mCenterX,mCenterY);
			canvas.drawPath(mArrowPath, mPaint);
			canvas.restore();
		}
	}
	/**
	 * 设置当前模式，并重绘
	 * @param mode
	 */
	public void setMode(Mode mode){
		this.mCurretnStatus=mode;
		invalidate();
	}
	/**
	 * 设置当前三角形的旋转角度
	 * @param degree
	 */
	public void setArrowDegree(int degree){
		this.mArrowDegree=degree;
	}
	/**
	 * 得到当前三角形的旋转角度
	 * @return
	 */
	public int getArrowDegree(){
		return this.mArrowDegree;
	}
	

}
