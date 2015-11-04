package com.beyole.view.commondialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.beyole.intelligentcampus.R;

/**
 * view 视图作为 dialog 弹出提示框
 * */
public class ResultDialog {
	static Dialog loading = null;

	public static Dialog creatAlertDialog(Context context, View view) {
		Dialog loading = new Dialog(context, R.style.commonDialog);
		loading.setCancelable(true);
		loading.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (LinearLayout.LayoutParams.MATCH_PARENT)));
		return loading;
	}
}
