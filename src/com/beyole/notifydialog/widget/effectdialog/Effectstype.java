package com.beyole.notifydialog.widget.effectdialog;

import com.beyole.notifydialog.widget.effects.BaseEffects;
import com.beyole.notifydialog.widget.effects.FadeIn;
import com.beyole.notifydialog.widget.effects.Fall;
import com.beyole.notifydialog.widget.effects.FlipH;
import com.beyole.notifydialog.widget.effects.FlipV;
import com.beyole.notifydialog.widget.effects.NewsPaper;
import com.beyole.notifydialog.widget.effects.RotateBottom;
import com.beyole.notifydialog.widget.effects.RotateLeft;
import com.beyole.notifydialog.widget.effects.Shake;
import com.beyole.notifydialog.widget.effects.SideFall;
import com.beyole.notifydialog.widget.effects.SlideBottom;
import com.beyole.notifydialog.widget.effects.SlideLeft;
import com.beyole.notifydialog.widget.effects.SlideRight;
import com.beyole.notifydialog.widget.effects.SlideTop;
import com.beyole.notifydialog.widget.effects.Slit;

/**
 * notify弹出框效果类
 * 
 * @author Iceberg
 * 
 */
public enum Effectstype {

	Fadein(FadeIn.class), Slideleft(SlideLeft.class), Slidetop(SlideTop.class), SlideBottom(SlideBottom.class), Slideright(SlideRight.class), Fall(Fall.class), Newspager(NewsPaper.class), Fliph(FlipH.class), Flipv(FlipV.class), RotateBottom(RotateBottom.class), RotateLeft(RotateLeft.class), Slit(
			Slit.class), Shake(Shake.class), Sidefill(SideFall.class);
	private Class effectsClazz;

	private Effectstype(Class mclass) {
		effectsClazz = mclass;
	}

	public BaseEffects getAnimator() {
		try {
			return (BaseEffects) effectsClazz.newInstance();
		} catch (Exception e) {
			throw new Error("Can not init animatorClazz instance");
		}
	}
}
