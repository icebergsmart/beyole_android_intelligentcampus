package com.beyole.intelligentcampus.functions.convenient;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beyole.bean.UserLoss;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.convenient.ui.SpaceImageDetailActivity;
import com.beyole.ninegridviewexpand.CustomImageView;
import com.beyole.ninegridviewexpand.Image;
import com.beyole.ninegridviewexpand.NineGridlayout;
import com.beyole.ninegridviewexpand.NineGridlayout.OnItemClickListerner;

/**
 * 失物招领详情页
 * 
 * @date 2015/12/09
 * @author Iceberg
 * 
 */
public class LossDetailsActivity extends Activity {

	private TextView mUserName;
	private TextView mDeliveryDate;
	private TextView statusYes;
	private TextView statusNo;
	private TextView content;
	private TextView mContact;
	private TextView mContactInfo;
	private NineGridlayout mNineGridLayout;
	private List<Image> imagesList;
	private List<String> imagesUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_loss_details);
		UserLoss loss = (UserLoss) getIntent().getSerializableExtra("lossdetails");
		initViews();
		setValues(loss);
		//根据图片显示是否要显示九宫格图片
		if ("".equals(loss.getGoodsImageUrl()) || loss.getGoodsImageUrl() == null) {
			mNineGridLayout.setVisibility(View.GONE);
		} else {
			mNineGridLayout.setVisibility(View.VISIBLE);
			initImages(loss.getGoodsImageUrl().split(","));
		}
	}

	private void setValues(UserLoss loss) {
		mUserName.setText(loss.getUserName());
		mDeliveryDate.setText(loss.getDeliveryDate());
		if (loss.getFinished() == 0) {
			statusYes.setVisibility(View.VISIBLE);
			statusNo.setVisibility(View.GONE);
		} else {
			statusYes.setVisibility(View.GONE);
			statusNo.setVisibility(View.VISIBLE);
		}
		content.setText(loss.getContent());
		mContact.setText(loss.getContact());
		mContactInfo.setText(loss.getContactInfo());
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_loss_details_top).findViewById(R.id.id_top_banner_title);
		tv.setText("详细");
		mUserName = (TextView) findViewById(R.id.id_function_loss_details_username);
		mDeliveryDate = (TextView) findViewById(R.id.id_function_loss_details_deliverydate);
		statusNo = (TextView) findViewById(R.id.id_function_loss_details_over);
		statusYes = (TextView) findViewById(R.id.id_function_loss_details_ing);
		content = (TextView) findViewById(R.id.id_function_loss_details_content);
		mContact = (TextView) findViewById(R.id.id_function_loss_details_contactinfo);
		mContactInfo = (TextView) findViewById(R.id.id_function_loss_details_contact);
		mNineGridLayout = (NineGridlayout) findViewById(R.id.iv_ngrid_layout);

	}

	private void initEvents() {
		mNineGridLayout.setImagesData(imagesList);
		mNineGridLayout.setOnItemClickListerner(new OnItemClickListerner() {

			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(LossDetailsActivity.this, SpaceImageDetailActivity.class);
				intent.putExtra("images", imagesUrl.get(position));
				intent.putExtra("position", position);
				CustomImageView view2 = (CustomImageView) view;
				int[] location = new int[2];
				view.getLocationOnScreen(location);
				view2.setDrawingCacheEnabled(true);
				intent.putExtra("bitmap", view2.getDrawingCache());
				intent.putExtra("locationX", location[0]);
				intent.putExtra("locationY", location[1]);
				intent.putExtra("width", view.getWidth());
				intent.putExtra("height", view.getHeight());
				startActivity(intent);
			}
		});
	}

	private void initImages(String[] images) {
		imagesList = new ArrayList<Image>();
		imagesUrl = new ArrayList<String>();
		// 从一到9生成9条朋友圈内容，分别是1~9张图片
		for (int j = 0; j < images.length; j++) {
			imagesList.add(new Image(images[j], 250, 250));
			imagesUrl.add(images[j]);
		}
		initEvents();
	}
}
