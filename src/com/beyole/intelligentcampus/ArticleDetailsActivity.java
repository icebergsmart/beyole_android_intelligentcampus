package com.beyole.intelligentcampus;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.beyole.bean.Article;
import com.beyole.constant.APIConstant;
import com.beyole.constant.ArticleConstant;
import com.beyole.util.JsonUtils;
import com.beyole.util.NormalPostRequest;
import com.beyole.util.VolleySingleton;

public class ArticleDetailsActivity extends Activity {
	private TextView mContentTv, mContentTitleTv;
	private LinearLayout mWaittingBtn, mWrapperLL;
	private int currentArticleId;
	public static final int INIT_COMPLETE = 0X001;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_article_details_layout);
		currentArticleId = getIntent().getIntExtra("articleId", 0);
		Log.i("articleDetails", "此时的文章id为：" + currentArticleId);
		initViews();
		getCampusInformations();
	}

	private void initViews() {
		TextView tv=(TextView) findViewById(R.id.id_article_details_top).findViewById(R.id.id_top_banner_title);
		tv.setText("新闻详情");
		mContentTv = (TextView) this.findViewById(R.id.id_home_article_details_content_tv);
		mWaittingBtn = (LinearLayout) findViewById(R.id.id_home_article_details_waitting_annimation_ll);
		mContentTitleTv = (TextView) findViewById(R.id.id_home_article_details_title_tv);
		mWrapperLL = (LinearLayout) findViewById(R.id.id_home_article_details_wrapper_ll);
		mContentTv.setMovementMethod(ScrollingMovementMethod.getInstance());// 滚动
		mWrapperLL.setVisibility(View.GONE);
		mWaittingBtn.setVisibility(View.VISIBLE);
		Log.i("articleDetails", "执行完initViews");
	}

	public void initEvents(final Article article) {
		mWrapperLL.setVisibility(View.VISIBLE);
		mWaittingBtn.setVisibility(View.GONE);
		mContentTitleTv.setText(article.getArticleName());
		Thread t = new Thread(new Runnable() {
			Message msg = Message.obtain();

			@Override
			public void run() {
				ImageGetter imageGetter = new ImageGetter() {

					@Override
					public Drawable getDrawable(String source) {
						URL url;
						Drawable drawable = null;
						try {
							url = new URL(source);
							drawable = Drawable.createFromStream(url.openStream(), null);
							drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						return drawable;
					}
				};
				CharSequence content = Html.fromHtml(article.getArticleContent(), imageGetter, null);
				msg.what = INIT_COMPLETE;
				msg.obj = content;
				myHandler.sendMessage(msg);
			}
		});
		t.start();

	}

	private Handler myHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case INIT_COMPLETE:
				mContentTv.setText((CharSequence) msg.obj);
				break;
			}
		}
	};

	public void getCampusInformations() {
		Log.i("articleDetails", "开始远程获取数据");
		Map<String, String> map = new HashMap<String, String>();
		map.put("articleId", currentArticleId + "");
		Request<JSONObject> request = new NormalPostRequest(APIConstant.FINDARTICLEBYARTICLEIDINTERFACE, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				Article article = null;
				try {
					if (response.getInt("code") == ArticleConstant.QUERY_FOR_ARTICLE_BY_ID_SUCCESS) {
						JSONObject object = response.getJSONObject("article");
						article = JsonUtils.readJsonToObject(Article.class, object.toString());
						initEvents(article);
					}
				} catch (JSONException e) {
					Toast.makeText(ArticleDetailsActivity.this, "获取数据异常", Toast.LENGTH_LONG).show();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Toast.makeText(ArticleDetailsActivity.this, "服务器交互错误", Toast.LENGTH_LONG).show();
			}
		}, map);
		VolleySingleton.getVolleySingleton(ArticleDetailsActivity.this.getApplicationContext()).addToRequestQueue(request);
		Log.i("articleDetails", "数据解析完成");
	}

}
