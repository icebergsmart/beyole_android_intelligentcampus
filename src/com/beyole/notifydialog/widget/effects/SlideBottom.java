package com.beyole.notifydialog.widget.effects;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 
 * @author Iceberg
 * 
 */
public class SlideBottom extends BaseEffects {

	@Override
	protected void setupAnimation(View view) {
		getAnimatorSet().playTogether(ObjectAnimator.ofFloat(view, "translationY", 300, 0).setDuration(mDuration), ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration * 3 / 2)

		);
	}
}
