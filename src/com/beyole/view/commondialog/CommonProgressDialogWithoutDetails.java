package com.beyole.view.commondialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.beyole.intelligentcampus.R;


public class CommonProgressDialogWithoutDetails extends AlertDialog {

	public static final String TAG = "COMMONPROGRESSDIALOGWITHOUTDETAILS";
	private HorizontalProgressbarWithNumbers mProgressBar;
	private TextView mProgressBarMessage;


	private boolean mHasStarted;
	private int mMax;
	private CharSequence mMessage;
	private int mProgressVal;

	public CommonProgressDialogWithoutDetails(Context context) {
		super(context);
	}

	public CommonProgressDialogWithoutDetails(Context context, int theme) {
		super(context, theme);
	}

	public CommonProgressDialogWithoutDetails(Context context, boolean cancelable, OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.common_progress_dialog_without_details);
		mProgressBar = (HorizontalProgressbarWithNumbers) findViewById(R.id.id_common_progress_dialog_progress);
		mProgressBarMessage = (TextView) findViewById(R.id.id_common_progress_dialog_message);
		if (mMessage != null) {
			setMessage(mMessage);
		}
		if (mMax > 0) {
			setMax(mMax);
		}
		if (mProgressVal > 0) {
			setProgress(mProgressVal);
		}
	}

	public int getMax() {
		if (mProgressBar != null) {
			return mProgressBar.getMax();
		}
		return mMax;
	}

	public void setMax(int max) {
		if (mProgressBar != null) {
			mProgressBar.setMax(max);
		} else {
			mMax = max;
		}
	}

	@Override
	public void setMessage(CharSequence message) {
		if (mProgressBarMessage != null) {
			mProgressBarMessage.setText(message);
		} else {
			mMessage = message;
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		mHasStarted = true;
	}

	public void setProgress(int value) {
		if (mHasStarted) {
			mProgressBar.setProgress(value);
		} else {
			mProgressVal = value;
		}
	}

	public void setIndeterminate(boolean indeterminate) {
		if (mProgressBar != null) {
			mProgressBar.setIndeterminate(indeterminate);
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		mHasStarted = false;
	}

}
