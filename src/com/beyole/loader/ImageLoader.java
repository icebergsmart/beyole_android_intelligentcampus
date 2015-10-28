package com.beyole.loader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import com.beyole.adapter.NewsAdapter;
import com.beyole.intelligentcampus.R;

/**
 * 首页图片缓存处理类
 * 
 * @author Iceberg
 * 
 */
public class ImageLoader {

	private ImageView mImageView;
	private String mUrl;
	private ListView mListView;
	private Set<NewsAsyncTask> mTask;
	private LruCache<String, Bitmap> mCaches;

	public ImageLoader(ListView listView) {
		mListView = listView;
		mTask = new HashSet<ImageLoader.NewsAsyncTask>();
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheSize = maxMemory / 4;
		mCaches = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getByteCount();
			}
		};
	}

	public void addBitmapToCache(String url, Bitmap bitmap) {
		if (getBitmapFromCache(url) == null) {
			mCaches.put(url, bitmap);
		}
	}

	public Bitmap getBitmapFromCache(String url) {
		return mCaches.get(url);
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (mImageView.getTag().equals(mUrl)) {
				mImageView.setImageBitmap((Bitmap) msg.obj);
			}
		};
	};

	public void showImageByThread(ImageView imageView, final String url) {
		mImageView = imageView;
		mUrl = url;
		new Thread() {
			@Override
			public void run() {
				super.run();
				Bitmap bitmap = getBitmapFromUrl(url);
				Message message = Message.obtain();
				message.obj = bitmap;
				handler.sendMessage(message);
			}
		}.start();
	}

	public Bitmap getBitmapFromUrl(String url) {
		InputStream is = null;
		Bitmap bitmap;
		try {
			URL url1 = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
			is = new BufferedInputStream(connection.getInputStream());
			bitmap = BitmapFactory.decodeStream(is);
			connection.disconnect();
			return bitmap;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void showImageByAsyncTask(ImageView imageView, String url) {
		Bitmap bitmap = getBitmapFromCache(url);
		if (bitmap == null) {
			imageView.setImageResource(R.drawable.ic_launcher);
		} else {
			imageView.setImageBitmap(bitmap);
		}
	}

	public void loadImages(int start, int end) {
		for (int i = start; i < end; i++) {
			String url = NewsAdapter.URLS[i];
			Bitmap bitmap = getBitmapFromCache(url);
			if (bitmap == null) {
				NewsAsyncTask task = new NewsAsyncTask(url);
				task.execute(url);
				mTask.add(task);
			} else {
				ImageView imageView = (ImageView) mListView.findViewWithTag(url);
				imageView.setImageBitmap(bitmap);
			}
		}
	}

	public void cancelAllTasks() {
		if (mTask != null) {
			for (NewsAsyncTask task : mTask) {
				task.cancel(false);
			}
		}
	}

	private class NewsAsyncTask extends AsyncTask<String, Void, Bitmap> {

		// private ImageView mImageView;
		private String mUrl;

		public NewsAsyncTask(String url) {
			// mImageView = imageView;
			mUrl = url;
		}

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bitmap = getBitmapFromUrl(params[0]);
			if (bitmap != null) {
				addBitmapToCache(params[0], bitmap);
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			super.onPostExecute(result);
			ImageView imageView = (ImageView) mListView.findViewWithTag(mUrl);
			if (imageView != null && result != null) {
				imageView.setImageBitmap(result);
			}
			mTask.remove(this);
		}

	}
}
