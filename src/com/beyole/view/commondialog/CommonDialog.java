package com.beyole.view.commondialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.beyole.intelligentcampus.R;

/**
 * 通用弹出框
 * 
 */

public class CommonDialog {
	private Dialog dialog;
	Context context;
	DialogPositiveListener positiveListener;
	DialogNegativeListener negativeListener;

	public CommonDialog(Context context) {
		super();
		this.context = context;
	}

	public void setPositiveListener(DialogPositiveListener positiveListener) {
		this.positiveListener = positiveListener;
	}

	public void setNegativeListener(DialogNegativeListener negativeListener) {
		this.negativeListener = negativeListener;
	}

	/**
	 * context promptMsg 提示信息
	 * */
	public Dialog initDialog(String promptMsg) {
		return initDialog(promptMsg, "取消", "确认");
	}

	/**
	 * context promptMsg 提示信息 cancleBtnMsg 取消按钮信息 sureBtnMsg 确认按钮信息
	 * */
	public Dialog initDialog(String promptMsg, String cancleBtnMsg, String sureBtnMsg) {
		View view = LayoutInflater.from(context).inflate(R.layout.layout_common_dialog, null);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		dialog = ResultDialog.creatAlertDialog(context, view);
		TextView tvPromptMsg = (TextView) view.findViewById(R.id.tvPromptMsg);
		Button btnCancle = (Button) view.findViewById(R.id.btnCancle);
		Button btnSure = (Button) view.findViewById(R.id.btnSure);

		tvPromptMsg.setText(promptMsg);
		btnCancle.setText(cancleBtnMsg);
		btnSure.setText(sureBtnMsg);

		btnCancle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (negativeListener != null) {
					negativeListener.onClick();
				}
				dialog.dismiss();
			}
		});
		btnSure.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (positiveListener != null) {
					positiveListener.onClick();
				}
				dialog.dismiss();
			}
		});
		return dialog;
	}

	public interface DialogPositiveListener {
		void onClick();
	}

	public interface DialogNegativeListener {
		void onClick();
	}
}
