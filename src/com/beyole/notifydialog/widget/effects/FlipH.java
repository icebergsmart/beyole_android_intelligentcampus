package com.beyole.notifydialog.widget.effects;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 
 * @author Iceberg
 * 
 */
public class FlipH extends BaseEffects {

	@Override
	protected void setupAnimation(View view) {
		getAnimatorSet().playTogether(ObjectAnimator.ofFloat(view, "rotationY", -90, 0).setDuration(mDuration)

		);
	}
}
