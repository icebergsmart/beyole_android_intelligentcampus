package com.beyole.intelligentcampus.settings.update;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.beyole.view.commondialog.CommonProgressDialogWithoutDetails;

import android.app.ProgressDialog;
import android.os.Environment;

/**
 * 下载更新管理器
 * 
 * @author Iceberg
 * 
 */
public class DownloadManager {
	public static File getFileFromServer(String path, CommonProgressDialogWithoutDetails pd) throws Exception {
		// 如果相等的话表示当前的sdcard挂载在手机上并且是可用的
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置连接超时时间
			conn.setConnectTimeout(5000);
			int max=conn.getContentLength();
			// 获取到文件的大小
			pd.setMax(100);
			InputStream is = conn.getInputStream();
			File file = new File(Environment.getExternalStorageDirectory(), "intelligentcampus_update.apk");
			FileOutputStream fos = new FileOutputStream(file);
			BufferedInputStream bis = new BufferedInputStream(is);
			byte[] buffer = new byte[1024];
			int len;
			int total = 0;
			while ((len = bis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
				total += len;
				// 获取当前下载量
				pd.setProgress((int)(((total*1.0f)/max)*100));
			}
			fos.close();
			bis.close();
			is.close();
			return file;
		} else {
			return null;
		}
	}
}
