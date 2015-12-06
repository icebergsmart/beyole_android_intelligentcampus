package com.beyole.intelligentcampus.settings;

import java.util.Hashtable;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beyole.bean.GlobalParameterApplication;
import com.beyole.intelligentcampus.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * 我的名片页面（二维码的生成）
 * 
 * @author Iceberg
 * 
 */
public class QRActivity extends Activity {

	// 存放二维码的ImageView
	private ImageView mImageView;
	private ProgressBar mProgressBar;
	// 二维码的宽度
	private static final int QR_WIDTH = 300;
	// 二维码的高度
	private static final int QR_HEIGHT = 300;

	// 存放整个处理过二维码的bitmap
	private Bitmap mImgBitmap;
	private Canvas mCanvas;

	// 存放过渡的bitmap
	private Bitmap durationBitmap;
	// 存放TextView的bitmap
	private Bitmap mTextViewBitmap;
	private TextView mTextViewHint;
	// 存储底部提示信息
	private TextView mBottomTextViewHint;
	private Bitmap mBottomTextViewBitmap;

	private int logoHeight;
	private int logoWidth;
	private TextView id_top_banner_title;

	private GlobalParameterApplication application;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrscan_layout);
		application = (GlobalParameterApplication) getApplicationContext();
		initViews();
		id_top_banner_title.setText("我的二维码");
		new MyQRAsyncTask().execute(application.getUser().getUserName());
	}

	private void initViews() {
		mImageView = (ImageView) findViewById(R.id.id_qrimageview);
		mProgressBar = (ProgressBar) findViewById(R.id.id_qrprogressBar);
		mTextViewHint = (TextView) findViewById(R.id.id_username);
		mBottomTextViewHint = (TextView) findViewById(R.id.id_bottominformation);
		id_top_banner_title = (TextView) findViewById(R.id.id_top_banner_title);
	}

	class MyQRAsyncTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			mImgBitmap = buildHeadPhotoBitmp();
			logoHeight = mImgBitmap.getHeight();
			durationBitmap = Bitmap.createBitmap(QR_WIDTH, mImgBitmap.getHeight() + QR_HEIGHT, Bitmap.Config.ARGB_8888);
			mCanvas = new Canvas(durationBitmap);
			mCanvas.drawBitmap(mImgBitmap, QR_WIDTH / 8, logoHeight / 5, null);
			mTextViewBitmap = buildInformationBitmap("Iceberg");
			mCanvas.drawBitmap(mTextViewBitmap, mImgBitmap.getWidth() + 40, mImgBitmap.getHeight() / 5, null);
			// 获取传入的URL
			String url = params[0];
			Bitmap mQRImageBitmap = createQRImage(url);
			mCanvas.drawBitmap(mQRImageBitmap, 0, mImgBitmap.getHeight(), null);
			mBottomTextViewBitmap = buildBottomInformationBitmap("扫一扫上面的二维码图案");
			mCanvas.drawBitmap(mBottomTextViewBitmap, QR_WIDTH / 6, QR_HEIGHT + logoHeight - 30, null);
			return durationBitmap;
		}

		@Override
		protected void onPreExecute() {
			mProgressBar.setVisibility(View.VISIBLE);
			mImageView.setVisibility(View.GONE);

		}

		@Override
		protected void onPostExecute(Bitmap result) {
			mImageView.setBackgroundColor(Color.WHITE);
			mImageView.setImageBitmap(result);
			mProgressBar.setVisibility(View.GONE);
			mImageView.setVisibility(View.VISIBLE);
		}

	}

	public Bitmap createQRImage(String url) {
		Bitmap finallyBitmap = null;
		try {
			// 判断URL合法性
			if (url == null || "".equals(url) || url.length() < 1) {
				return null;
			}
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			// 图像数据转换，使用了矩阵转换
			BitMatrix bitMatrix = new QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
			int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
			// 下面这里按照二维码的算法，逐个生成二维码的图片，
			// 两个for循环是图片横列扫描的结果
			for (int y = 0; y < QR_HEIGHT; y++) {
				for (int x = 0; x < QR_WIDTH; x++) {
					if (bitMatrix.get(x, y)) {
						pixels[y * QR_WIDTH + x] = 0xff000000;
					} else {
						pixels[y * QR_WIDTH + x] = 0xffffffff;
					}
				}
			}
			// 生成二维码图片的格式，使用ARGB_8888
			Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT, Bitmap.Config.ARGB_8888);
			bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
			// 显示到一个ImageView上面
			finallyBitmap = bitmap;
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return finallyBitmap;
	}

	// 创建扫一扫二维码建立关系的用户名信息 将textview转化为bitmap
	private Bitmap buildInformationBitmap(String text) {
		TextView tv = mTextViewHint;
		tv.setText(text);
		tv.setDrawingCacheEnabled(true);
		tv.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		tv.layout(0, 0, tv.getMeasuredWidth(), tv.getMeasuredHeight());
		return tv.getDrawingCache();
	}

	// 创建二维码底部提示信息 将textview转化为bitmap
	private Bitmap buildBottomInformationBitmap(String text) {
		TextView tv = mBottomTextViewHint;
		tv.setText(text);
		tv.setDrawingCacheEnabled(true);
		tv.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		tv.layout(0, 0, tv.getMeasuredWidth(), tv.getMeasuredHeight());
		return tv.getDrawingCache();
	}

	// 创建头像bitmap，以后改写成通过传递过来的user的头像url设置头像
	private Bitmap buildHeadPhotoBitmp() {
		return resizeHeadPhotoBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.user_information_img));
	}

	// 调整头像大小的缩放比例 这边的scale需要到后期的时候进行调整，通过参数传进去
	private Bitmap resizeHeadPhotoBitmap(Bitmap bitmap) {
		Matrix matrix = new Matrix();
		matrix.postScale(0.3f, 0.3f);
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	}
}
