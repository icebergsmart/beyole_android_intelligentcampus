package com.beyole.notifydialog.widget.effects;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 
 * @author Iceberg
 * 
 */
public class FadeIn extends BaseEffects {

	@Override
	protected void setupAnimation(View view) {
		getAnimatorSet().playTogether(ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration)

		);
	}
}
