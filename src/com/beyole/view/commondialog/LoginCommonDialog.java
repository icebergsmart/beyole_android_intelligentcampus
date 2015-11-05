package com.beyole.view.commondialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.beyole.intelligentcampus.R;

/**
 * 通用弹出框
 * 
 */

public class LoginCommonDialog {
	private Dialog dialog;
	Context context;

	public LoginCommonDialog(Context context) {
		super();
		this.context = context;
	}

	/**
	 * context promptMsg 提示信息 cancleBtnMsg 取消按钮信息 sureBtnMsg 确认按钮信息
	 * */
	public Dialog initDialog(String promptMsg, String cancleBtnMsg, String sureBtnMsg, Activity activity) {
		View view = LayoutInflater.from(context).inflate(R.layout.layout_common_dialog_login, null);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		dialog = ResultDialog.creatAlertDialog(context, view);
		TextView tvPromptMsg = (TextView) view.findViewById(R.id.tvPromptMsg);
		tvPromptMsg.setText(promptMsg);
		AssetManager assets = activity.getAssets();
		Typeface tf = Typeface.createFromAsset(assets, "fonts/default.otf");
		tvPromptMsg.setTypeface(tf);
		return dialog;
	}

	public void dismissLoginDialog() {
		if (dialog != null) {
			dialog.dismiss();
		}
	}
}
