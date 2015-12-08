package com.beyole.intelligentcampus.functions.convenient.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.convenient.tools.BitmapCache;
import com.beyole.util.VolleySingleton;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

public class SpaceImageDetailActivity extends Activity {

	private String mDatas;
	private int mPosition;
	private int mLocationX;
	private int mLocationY;
	private int mWidth;
	private int mHeight;
	private Bitmap mBitmap;
	SmoothImageView imageView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mDatas = getIntent().getStringExtra("images");
		mPosition = getIntent().getIntExtra("position", 0);
		mLocationX = getIntent().getIntExtra("locationX", 0);
		mLocationY = getIntent().getIntExtra("locationY", 0);
		mWidth = getIntent().getIntExtra("width", 0);
		mHeight = getIntent().getIntExtra("height", 0);

		imageView = new SmoothImageView(this);
		imageView.setOriginalInfo(mWidth, mHeight, mLocationX, mLocationY);
		imageView.transformIn();
		imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
		imageView.setScaleType(ScaleType.FIT_CENTER);
		setContentView(imageView);
		
		mBitmap=getIntent().getParcelableExtra("bitmap");
		imageView.setImageBitmap(mBitmap);
		Picasso.with(this)
		.load(mDatas)
		.memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).config(Bitmap.Config.RGB_565)
		.into(imageView);
		// 减少OOM发生频率，还是会发生OOM，解决方案初步定为开启新进程，通过AIDL来解决
		//RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
		//ImageLoader imageLoader = new ImageLoader(mQueue, new BitmapCache());
		//ImageListener listener = ImageLoader.getImageListener(imageView, R.drawable.ic_launcher, R.drawable.ic_launcher);
		//imageLoader.get(mDatas, listener);

		/*
		 * ImageRequest imageRequest = new ImageRequest(mDatas, new
		 * Response.Listener<Bitmap>() {
		 * 
		 * @Override public void onResponse(Bitmap response) {
		 * imageView.setImageBitmap(response); } }, 0, 0, Config.RGB_565, new
		 * Response.ErrorListener() {
		 * 
		 * @Override public void onErrorResponse(VolleyError error) {
		 * imageView.setImageResource(R.drawable.ic_launcher); } });
		 * VolleySingleton
		 * .getVolleySingleton(getApplicationContext()).addToRequestQueue
		 * (imageRequest);
		 */
	}

	@Override
	public void onBackPressed() {
		imageView.setOnTransformListener(new SmoothImageView.TransformListener() {
			@Override
			public void onTransformComplete(int mode) {
				if (mode == 2) {
					finish();
				}
			}
		});
		imageView.transformOut();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (isFinishing()) {
			overridePendingTransition(0, 0);
		}
	}

}
