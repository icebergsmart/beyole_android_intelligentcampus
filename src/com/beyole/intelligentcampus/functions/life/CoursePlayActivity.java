package com.beyole.intelligentcampus.functions.life;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.beyole.constant.APIConstant;
import com.beyole.intelligentcampus.R;

public class CoursePlayActivity extends Activity {
	/**
	 * TODO: Set the path variable to a streaming video URL or a local media file
	 * path.
	 */
	private String path;
	private VideoView mVideoView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
		setContentView(R.layout.function_life_course_details_video_play_layout);
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		path=getIntent().getStringExtra("videoUrl");
		if (path == "") {
			// Tell the user to provide a media file URL/path.
			Toast.makeText(CoursePlayActivity.this, "Please edit VideoViewDemo Activity, and set path" + " variable to your media file URL/path", Toast.LENGTH_LONG).show();
			return;
		} else {
			/*
			 * Alternatively,for streaming media you can use
			 * mVideoView.setVideoURI(Uri.parse(URLstring));
			 */
			mVideoView.setVideoPath(path);
			mVideoView.setMediaController(new MediaController(this));
			mVideoView.requestFocus();
			mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
				@Override
				public void onPrepared(MediaPlayer mediaPlayer) {
					// optional need Vitamio 4.0
					mediaPlayer.setPlaybackSpeed(1.0f);
				}
			});
			mVideoView.setVideoQuality(MediaPlayer.VIDEOQUALITY_LOW);
		}

	}
}
