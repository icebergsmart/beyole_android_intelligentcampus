package com.beyole.intelligentcampus.functions.convenient;

import java.util.ArrayList;
import java.util.List;

import com.beyole.bean.UserRecruit;
import com.beyole.intelligentcampus.R;
import com.beyole.intelligentcampus.functions.convenient.ui.SpaceImageDetailActivity;
import com.beyole.ninegridviewexpand.CustomImageView;
import com.beyole.ninegridviewexpand.Image;
import com.beyole.ninegridviewexpand.NineGridlayout;
import com.beyole.ninegridviewexpand.NineGridlayout.OnItemClickListerner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RecruitDetailsActivity extends Activity {

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
		setContentView(R.layout.convenient_recruit_details);
		initViews();
		UserRecruit recruit = (UserRecruit) getIntent().getSerializableExtra("recruitList");
		setValues(recruit);
		// 根据图片显示是否要显示九宫格图片
		if ("".equals(recruit.getGoodsImage()) || recruit.getGoodsImage() == null) {
			mNineGridLayout.setVisibility(View.GONE);
		} else {
			mNineGridLayout.setVisibility(View.VISIBLE);
			initImages(recruit.getGoodsImage().split(","));
		}
	}

	private void setValues(UserRecruit recruit) {
		mUserName.setText(recruit.getUserName());
		mDeliveryDate.setText(recruit.getDeliveryDate());
		if (recruit.getFinished() == 0) {
			mStatusYes.setVisibility(View.VISIBLE);
			mStatusNo.setVisibility(View.GONE);
		} else {
			mStatusNo.setVisibility(View.VISIBLE);
			mStatusYes.setVisibility(View.GONE);
		}
		mContact.setText(recruit.getContact());
		mContent.setText(recruit.getContent());
		mContactInfo.setText(recruit.getContactInfo());
	}

	private void initViews() {
		mUserName = (TextView) findViewById(R.id.id_convenient_recruit_details_person_details_username);
		mDeliveryDate = (TextView) findViewById(R.id.id_convenient_recruit_details_deliverydate);
		mStatusNo = (TextView) findViewById(R.id.id_convenient_recruit_details_person_details_over);
		mStatusYes = (TextView) findViewById(R.id.id_convenient_recruit_details_person_details_ing);
		mContent = (TextView) findViewById(R.id.id_convenient_recruit_details_person_details_content);
		mContact = (TextView) findViewById(R.id.id_convenient_recruit_details_contact);
		mContactInfo = (TextView) findViewById(R.id.id_convenient_recruit_details_contactinfo);
		mNineGridLayout = (NineGridlayout) findViewById(R.id.iv_ngrid_layout);
	}

	private void initEvents() {
		mNineGridLayout.setImagesData(imagesList);
		mNineGridLayout.setOnItemClickListerner(new OnItemClickListerner() {

			@Override
			public void onItemClick(View view, int position) {
				Intent intent = new Intent(RecruitDetailsActivity.this, SpaceImageDetailActivity.class);
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
