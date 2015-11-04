package com.beyole.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * 带有图标的edittext封装
 * 
 * @date 2015-11-04
 * @author Iceberg
 * 
 */
public class EditTextWithRightButton extends EditText {

	private static final String TAG = "EDITTEXTWITHRIGHTBUTTON";
	// 右边小图标
	private Drawable drawableRight;
	// 左边小图标
	private Drawable drawableLeft;
	// 上面小图标
	private Drawable drawableTop;
	// 下面小图标
	private Drawable drawableBottom;

	private DrawableLeftOnClickListener mLeftListener;
	private DrawableRightOnClickListener mRightListener;
	private DrawableTopOnClickListener mTopListener;
	private DrawableBottomOnClickListener mBottomListener;

	private static final int DRAWABLE_LEFT = 0;
	private static final int DRAWABLE_TOP = 1;
	private static final int DRAWABLE_RIGHT = 2;
	private static final int DRAWABLE_BOTTOM = 3;

	public EditTextWithRightButton(Context context) {
		super(context);
		initEditText();
	}

	public EditTextWithRightButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initEditText();
	}

	public EditTextWithRightButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initEditText();
	}

	/**
	 * 初始化EditText控件
	 */
	private void initEditText() {
		setEditTextDrawable();
		// 对文本内容改变进行监听
		addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				setEditTextDrawable();
			}
		});
	}

	/**
	 * 控制图片的显示
	 */
	public void setEditTextDrawable() {
		if (getText().toString().length() == 0) {
			setCompoundDrawables(drawableLeft, null, null, null);
		} else {
			setCompoundDrawables(drawableLeft, drawableTop, drawableRight, drawableBottom);
		}
	}

	/**
	 * 解除关联时调用 GC
	 */
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		this.drawableRight = null;
		this.drawableLeft = null;
		this.drawableTop = null;
		this.drawableBottom = null;
	}

	/**
	 * 显示右侧的图片
	 */
	@Override
	public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
		if (right != null) {
			this.drawableRight = right;
		}
		if (left != null) {
			this.drawableLeft = left;
		}
		if (top != null) {
			this.drawableTop = top;
		}
		if (bottom != null) {
			this.drawableBottom = bottom;
		}
		super.setCompoundDrawables(left, top, right, bottom);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			if (mRightListener != null) {
				Drawable rightDrawable = getCompoundDrawables()[DRAWABLE_RIGHT];
				if (rightDrawable != null && event.getRawX() >= (getRight() - rightDrawable.getBounds().width())) {
					mRightListener.onDrawableRightClick(this);
					event.setAction(MotionEvent.ACTION_CANCEL);
					// 在super之前分发事件的话，清空的时候就会产生paste的提示
					// return true;
				}
			}
			if (mLeftListener != null) {
				Drawable leftDrawable = getCompoundDrawables()[DRAWABLE_LEFT];
				if (leftDrawable != null && (event.getRawX() <= getLeft() + leftDrawable.getBounds().width())) {
					mLeftListener.onDrawableLeftClick(this);
					event.setAction(MotionEvent.ACTION_CANCEL);
					// 在super之前分发事件的话，清空的时候就会产生paste的提示
					// return true;
				}
			}
			if (mTopListener != null) {
				Drawable topDrawable = getCompoundDrawables()[DRAWABLE_TOP];
				if (topDrawable != null && (event.getRawY() <= getTop() + topDrawable.getBounds().height())) {
					mTopListener.onDrawableTopClick(this);
					event.setAction(MotionEvent.ACTION_CANCEL);
					// 在super之前分发事件的话，清空的时候就会产生paste的提示
					// return true;
				}
			}
			if (mBottomListener != null) {
				Drawable bottomDrawable = getCompoundDrawables()[DRAWABLE_BOTTOM];
				if (bottomDrawable != null && (event.getRawY() >= getBottom() - bottomDrawable.getBounds().height())) {
					mBottomListener.onDrawableBottomClick(this);
					event.setAction(MotionEvent.ACTION_CANCEL);
					// 在super之前分发事件的话，清空的时候就会产生paste的提示
					// return true;
				}
			}
			break;
		}
		return super.onTouchEvent(event);
	}

	public interface DrawableLeftOnClickListener {
		public void onDrawableLeftClick(View view);
	}

	public interface DrawableRightOnClickListener {
		public void onDrawableRightClick(View view);
	}

	public interface DrawableTopOnClickListener {
		public void onDrawableTopClick(View view);
	}

	public interface DrawableBottomOnClickListener {
		public void onDrawableBottomClick(View view);
	}

	public void setDrawableLeftListener(DrawableLeftOnClickListener listener) {
		mLeftListener = listener;
	}

	public void setDrawableRightListener(DrawableRightOnClickListener listener) {
		mRightListener = listener;
	}

	public void setDrawableTopListener(DrawableTopOnClickListener listener) {
		mTopListener = listener;
	}

	public void setDrawableBottomListener(DrawableBottomOnClickListener listener) {
		mBottomListener = listener;
	}
}
