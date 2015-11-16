package com.beyole.intelligentcampus;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.Toast;

import com.beyole.constant.Constant;
import com.beyole.constant.UpdateConstant;
import com.beyole.intelligentcampus.settings.AboutUsActivity;
import com.beyole.intelligentcampus.settings.FeedBackActivity;
import com.beyole.intelligentcampus.settings.FunctionIntroduceActivity;
import com.beyole.intelligentcampus.settings.HelpActivity;
import com.beyole.intelligentcampus.settings.QRActivity;
import com.beyole.intelligentcampus.settings.update.DownloadManager;
import com.beyole.intelligentcampus.settings.update.UpdateInfo;
import com.beyole.intelligentcampus.settings.update.UpdateInfoParser;
import com.beyole.notifydialog.widget.effectdialog.Effectstype;
import com.beyole.notifydialog.widget.effectdialog.NiftyDialogBuilder;
import com.beyole.view.commondialog.CommonDialog;
import com.beyole.view.commondialog.CommonDialog.DialogPositiveListener;
import com.beyole.view.commondialog.CommonProgressDialogWithoutDetails;

/**
 * 设置 fragment
 * 
 * @author Iceberg
 * 
 */
public class SettingFragment extends Fragment {
	private Effectstype effect;
	// 获取本地版本信息
	private String localVersion;
	private UpdateInfo info;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tab04, container, false);
		MyRowOnclickListener listener = new MyRowOnclickListener();
		TableRow row8 = (TableRow) view.findViewById(R.id.more_page_row8);
		TableRow row7 = (TableRow) view.findViewById(R.id.more_page_row7);
		TableRow row6 = (TableRow) view.findViewById(R.id.more_page_row6);
		TableRow row5 = (TableRow) view.findViewById(R.id.more_page_row5);
		TableRow row4 = (TableRow) view.findViewById(R.id.more_page_row4);
		row8.setOnClickListener(listener);
		row7.setOnClickListener(listener);
		row6.setOnClickListener(listener);
		row5.setOnClickListener(listener);
		row4.setOnClickListener(listener);
		return view;
	}

	class MyRowOnclickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.more_page_row8:
				intent = new Intent(getActivity(), AboutUsActivity.class);
				startActivity(intent);
				activitySwitchAnimation();
				break;
			case R.id.more_page_row7:
				checkUpdateVersion();
				break;
			case R.id.more_page_row6:
				intent = new Intent(getActivity(), FunctionIntroduceActivity.class);
				startActivity(intent);
				activitySwitchAnimation();
				break;
			case R.id.more_page_row5:
				intent = new Intent(getActivity(), FeedBackActivity.class);
				startActivity(intent);
				activitySwitchAnimation();
				break;
			case R.id.more_page_row4:
				intent = new Intent(getActivity(), HelpActivity.class);
				startActivity(intent);
				activitySwitchAnimation();
				break;
			}
		}

		/**
		 * activity切换动画
		 */
		private void activitySwitchAnimation() {
			getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		}

	}

	public void dialogShow() {
		final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(getActivity());
		effect = Effectstype.Slit;
		dialogBuilder.withTitle("提示框").withTitleColor("#FFFFFF").withDividerColor("#11000000").withMessage("点击了我的设置").withMessageColor("#FFFFFF").withIcon(getResources().getDrawable(R.drawable.ic_launcher)).isCancelableOnTouchOutside(true).withDuration(700).withEffect(effect).withButton1Text("确定")
				.withButton2Text("取消").setButton1Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(v.getContext(), "i'm btn1", Toast.LENGTH_SHORT).show();
						dialogBuilder.dismiss();
					}
				}).setButton2Click(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(v.getContext(), "i'm btn2", Toast.LENGTH_SHORT).show();
					}
				}).show();

	}

	/**
	 * 检查更新
	 */
	private void checkUpdateVersion() {
		// 获取本地app的版本信息
		localVersion = getVersionName();
		CheckVersionTask cv = new CheckVersionTask();
		// 启动更新线程
		new Thread(cv).start();
	}

	/**
	 * 获取本地app版本信息
	 * 
	 * @return
	 */
	private String getVersionName() {
		PackageManager packageManager = getActivity().getPackageManager();
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return packInfo.versionName;
	}

	class CheckVersionTask implements Runnable {
		InputStream is = null;
		Message message = new Message();

		@Override
		public void run() {
			try {
				URL url = new URL(Constant.REMOTE_UPDATE_APK_URL);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				// 设置超时时间为5s
				connection.setConnectTimeout(5000);
				// 设置请求方式为GET
				connection.setRequestMethod("GET");
				// 获取返回码
				int responseCode = connection.getResponseCode();
				if (responseCode == 200) {
					// 从服务器获得一个输入流
					is = connection.getInputStream();
				}
				info = UpdateInfoParser.getUpdataInfo(is);

				if (info.getVersion().equals(localVersion)) {
					Log.e("test", "您当前为最新版本！");
					message.what = UpdateConstant.NO_NEED_UPDATE;
				} else {
					Log.e("test", "确定需要更新吗？！");
					message.what = UpdateConstant.UPDATE_CLIENT;
				}
			} catch (Exception e) {
				e.printStackTrace();
				message.what = UpdateConstant.GET_UPDATEINFO_ERROR;
			}
			handler.sendMessage(message);
		}

	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			CommonDialog commonDialog = new CommonDialog(getActivity());
			switch (msg.what) {
			// 不需要更新
			case UpdateConstant.NO_NEED_UPDATE:
				commonDialog.initDialog("您当前是最新版本！", "取消", "确定").show();
				break;
			// 需要更新
			case UpdateConstant.UPDATE_CLIENT:
				commonDialog.setPositiveListener(new DialogPositiveListener() {
					@Override
					public void onClick() {
						downloadAPK();
					}
				});
				commonDialog.initDialog("您确定要更新到最新版本吗?", "取消", "更新").show();
				break;
			// 获取更新信息失败
			case UpdateConstant.GET_UPDATEINFO_ERROR:
				Toast.makeText(getActivity(), "获取更新信息失败！", Toast.LENGTH_SHORT).show();
				break;
			// 下载更新失败
			case UpdateConstant.DOWNLOAD_UPDATE_ERROR:
				Toast.makeText(getActivity(), "下载更新失败！", Toast.LENGTH_SHORT).show();
				break;
			// 存储更新失败，SD卡未挂载
			case UpdateConstant.SDCARD_UNMOUNTED:
				Toast.makeText(getActivity(), "SD卡未挂载！", Toast.LENGTH_SHORT).show();
				break;
			}
		};
	};

	private void downloadAPK() {
		// 进度条对话框
		final CommonProgressDialogWithoutDetails pd;
		pd = new CommonProgressDialogWithoutDetails(getActivity());
		pd.setMessage("正在下载更新");
		pd.show();
		new Thread() {
			@Override
			public void run() {
				try {
					File file = DownloadManager.getFileFromServer(info.getUrl(), pd);
					// 线程休眠3秒
					sleep(3000);
					// 安装apk
					installAPK(file);
					// 结束掉进度条
					pd.dismiss();
				} catch (Exception e) {
					e.printStackTrace();
					Message message = new Message();
					message.what = UpdateConstant.DOWNLOAD_UPDATE_ERROR;
					handler.sendMessage(message);
				}
			};
		}.start();
	}

	protected void installAPK(File file) {
		Intent intent = new Intent();
		// 执行动作
		intent.setAction(Intent.ACTION_VIEW);
		// 执行的数据类型
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
		startActivity(intent);
	}
}
