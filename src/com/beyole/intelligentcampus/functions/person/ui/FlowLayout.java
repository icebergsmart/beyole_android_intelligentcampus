package com.beyole.intelligentcampus.functions.person.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 查询帐号流式标签布局viewgroup容器
 * 
 * @date 2015/12/2
 * @author Iceberg
 * 
 */
public class FlowLayout extends ViewGroup {
	// 存储所有的view，按行存储
	private List<List<View>> mAllViews = new ArrayList<List<View>>();
	// 存储每行的最大高度
	private List<Integer> mLineHeight = new ArrayList<Integer>();

	public FlowLayout(Context context) {
		super(context);
	}

	public FlowLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 因为我们所写的viewgroup要支持margin，所以就直接使用了系统自带的marginLayoutParams
	 */
	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new MarginLayoutParams(getContext(), attrs);
	}

	/**
	 * 测量子view的宽度和高度，来觉得viewgroup的宽度和高度
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 获得父容器的测量模式和大小
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		// 如果是wrap_content的情况下记录下宽度和高度
		int width = 0;
		int height = 0;
		// 记录下每一行的宽度，取最大宽度
		int lineWidth = 0;
		// 记录下每一行的高度，并累加
		int lineHeight = 0;
		// 获得子控件的个数
		int cCount = getChildCount();
		for (int i = 0; i < cCount; i++) {
			// 获得子view
			View child = getChildAt(i);
			// 测量子view
			measureChild(child, widthMeasureSpec, heightMeasureSpec);
			// 获得子view的layoutParams
			MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
			// 当前子view占据的宽度
			int childWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
			// 当前子view占据的高度
			int childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
			// 如果加入此view之后大于设定的宽度，则另起一行
			if (lineWidth + childWidth > widthSize) {
				// 取最大的
				width = Math.max(lineWidth, childWidth);
				lineWidth = childWidth;// 开启新行
				// 叠加高度
				height += lineHeight;
				lineHeight = childHeight;
			} else {
				lineWidth += childWidth;
				lineHeight = Math.max(lineHeight, childHeight);
			}
			// 如果是最后一个,则将最大宽度与lineWidth进行比较
			if (i == cCount - 1) {
				width = Math.max(width, lineWidth);
				height += lineHeight;
			}
		}
		setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width, heightMode == MeasureSpec.EXACTLY ? heightSize : height);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		mAllViews.clear();
		mLineHeight.clear();
		int width = getWidth();
		int lineWidth = 0;
		int lineHeight = 0;
		// 存储每行的childview
		List<View> childViewList = new ArrayList<View>();
		int cCount = getChildCount();
		// 遍历所有的孩子节点
		for (int i = 0; i < cCount; i++) {
			View child = getChildAt(i);
			MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
			int childWidth = child.getMeasuredWidth();
			int childHeight = child.getMeasuredHeight();
			// 如果需要换行
			if (childWidth + layoutParams.leftMargin + layoutParams.rightMargin + lineWidth > width) {
				// 记录这一行的最大高度以及所有的view
				mLineHeight.add(lineHeight);
				mAllViews.add(childViewList);
				// 重置行宽
				lineWidth = 0;
				childViewList = new ArrayList<View>();
			}
			// 如果不需要换行，则累加
			lineWidth += childWidth + layoutParams.leftMargin + layoutParams.rightMargin;
			lineHeight = Math.max(lineHeight, childHeight + layoutParams.topMargin + layoutParams.bottomMargin);
			childViewList.add(child);
		}
		// 记录最后一行
		mLineHeight.add(lineHeight);
		mAllViews.add(childViewList);
		int left = 0;
		int top = 0;
		// 得到总行数
		int lineNums = mAllViews.size();
		for (int i = 0; i < lineNums; i++) {
			// 每一行所有的view
			childViewList = mAllViews.get(i);
			// 每一行的最大高度
			lineHeight = mLineHeight.get(i);
			for (int j = 0; j < childViewList.size(); j++) {
				View child = childViewList.get(j);
				if (child.getVisibility() == View.GONE) {
					continue;
				}
				MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
				// 计算childview的left,right,top,bottom
				int lc = left + layoutParams.leftMargin;
				int tc = top + layoutParams.topMargin;
				int rc = lc + child.getMeasuredWidth();
				int bc = tc + child.getMeasuredHeight();
				child.layout(lc, tc, rc, bc);
				left += child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
			}
			left = 0;
			top += lineHeight;
		}
	}

}
