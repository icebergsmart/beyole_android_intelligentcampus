package com.beyole.intelligentcampus.functions.convenient;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beyole.bean.UserSecondHand;
import com.beyole.constant.ImageUrlConstant;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.convenient.ui.SpaceImageDetailActivity;
import com.beyole.ninegridviewexpand.CustomImageView;
import com.beyole.ninegridviewexpand.Image;
import com.beyole.ninegridviewexpand.NineGridlayout;
import com.beyole.ninegridviewexpand.NineGridlayout.OnItemClickListerner;

public class SecondHandDetailsActivity extends Activity {

	private TextView mUserName;
	private TextView mDeliveryDate;
	private TextView mStatusYes;
	private TextView mStatusNo;
	private TextView mContent;
	private TextView mContact;
	private TextView mContactInfo;
	private NineGridlayout mNineGridLayout;
	private List<Image> imagesList;
	private List<String> imagesUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.convenient_second_details);
		initViews();
		UserSecondHand secondHand = (UserSecondHand) getIntent().getSerializableExtra("goodsList");
		setValues(secondHand);
		// 根据图片显示是否要显示九宫格图片
		if ("".equals(secondHand.getGoodsImage()) || secondHand.getGoodsImage() == null) {
			mNineGridLayout.setVisibility(View.GONE);
		} else {
			mNineGridLayout.setVisibility(View.VISIBLE);
			initImages(secondHand.getGoodsImage().split(","));
		}
	}

	private void setValues(UserSecondHand secondHand) {
		mUserName.setText(secondHand.getUserName());
		mDeliveryDate.setText(secondHand.getDeliveryDate());
		if (secondHand.getFinished() == 0) {
			mStatusYes.setVisibility(View.VISIBLE);
			mStatusNo.setVisibility(View.GONE);
		} else {
			mStatusYes.setVisibility(View.GONE);
			mStatusNo.setVisibility(View.VISIBLE);
		}
		mContent.setText(secondHand.getContent());
		mContact.setText(secondHand.getContact());
		mContactInfo.setText(secondHand.getContactInfo());
	}

	private void initViews() {
		TextView tv = (TextView) findViewById(R.id.id_convenient_second_details_top).findViewById(R.id.id_top_banner_title);
		tv.setText("详细");
		mUserName = (TextView) findViewById(R.id.id_convenient_secondhand_details_person_details_username);
		mDeliveryDate = (TextView) findViewById(R.id.id_convenient_secondhand_details_deliverydate);
		mStatusYes = (TextView) findViewById(R.id.id_convenient_secondhand_details_person_details_ing);
		mStatusNo = (TextView) findViewById(R.id.id_convenient_secondhand_details_person_details_over);
		mContent = (TextView) findViewById(R.id.id_convenient_secondhand_details_person_details_content);
		mContact = (TextView) findViewById(R.id.id_convenient_secondhand_details_contact);
		mContactInfo = (TextView) findViewById(R.id.id_convenient_secondhand_details_contactinfo);
		mNineGridLayout = (NineGridlayout) findViewById(R.id.iv_ngrid_layout);
	}

	private void initEvents() {
		if(imagesUrl.size()>0){
		mNineGridLayout.setImagesData(imagesList);
		mNineGridLayout.setOnItemClickListerner(new OnItemClickListerner() {

			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(SecondHandDetailsActivity.this, SpaceImageDetailActivity.class);
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
		}else{
			mNineGridLayout.setVisibility(View.GONE);
		}
	}

	private void initImages(String[] images) {
		imagesList = new ArrayList<Image>();
		imagesUrl = new ArrayList<String>();
		// 从一到9生成9条朋友圈内容，分别是1~9张图片
		for (int j = 0; j < images.length; j++) {
			imagesList.add(new Image(ImageUrlConstant.REMOTE_SECOND_HAND_DESCRIPTION_SNAIL_IMAGE_URL+images[j], 250, 250));
			imagesUrl.add(ImageUrlConstant.REMOTE_SECOND_HAND_DESCRIPTION_SNAIL_IMAGE_URL+images[j]);
		}
		initEvents();
	}
}
