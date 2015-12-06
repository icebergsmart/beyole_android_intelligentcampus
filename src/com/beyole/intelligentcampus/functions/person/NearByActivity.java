package com.beyole.intelligentcampus.functions.person;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.person.ui.CardFragment;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

/**
 * 周边
 * 
 * @date 2015/11/26
 * @author Iceberg
 * 
 */
public class NearByActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_nearby_talk);
		initImageLoader();
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().add(R.id.container, new CardFragment()).commitAllowingStateLoss();
		}
	}

	@SuppressWarnings("deprecation")
	private void initImageLoader() {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).memoryCacheExtraOptions(480, 800)
		// default = device screen dimensions
				.threadPoolSize(3)
				// default
				.threadPriority(Thread.NORM_PRIORITY - 1)
				// default
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				// default
				.denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(2 * 1024 * 1024)).memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13) // default
				.discCacheSize(50 * 1024 * 1024) // 缓冲大小
				.discCacheFileCount(100) // 缓冲文件数目
				.discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
				.imageDownloader(new BaseImageDownloader(this)) // default
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
				.writeDebugLogs().build();

		// 2.单例ImageLoader类的初始化
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.init(config);
	}
}
