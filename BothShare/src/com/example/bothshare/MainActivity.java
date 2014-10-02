package com.example.bothshare;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.listener.PublishClickListener;
import com.example.listener.SelectedImageLongClickListener;
import com.example.listener.WeiboBindButtonListener;
import com.example.listener.WeiboGetImageButtonListener;
import com.example.persistent.AccessTokenKeeper;
import com.example.persistent.Constant;
import com.example.util.LocationManagerUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.openapi.legacy.StatusesAPI;


public class MainActivity extends Activity {
	
	private Button bWeiboBindButton;
	private Button bWeiboPublishButton;
	private Button bWeiboGetImage;
	private EditText editText;
	private ImageView currentImageView;
	
	private String ImagePath = "";
	
	private WeiboAuth mWeiboAuth;
	private Oauth2AccessToken accessWeibo;
	
	// Use Auth2 authentication now....
	private SsoHandler mSsoHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		accessWeibo = AccessTokenKeeper.readAccessToken(MainActivity.this);
		currentImageView = (ImageView) findViewById(R.id.image);
		
		InitView();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// Get the image result from image pickeer
		if (requestCode == Constant.RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };
	 
			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();
	
			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			ImagePath = cursor.getString(columnIndex);
			cursor.close();
	
			Init_selectedImage();
		}
	}

	public String GetEditTextValue()
	{
		return this.editText.getText().toString();
	}
	
	public String GetImagePath()
	{
		return this.ImagePath;
	}

	private void InitView()
	{
		this.editText = (EditText) findViewById(R.id.uploadText);
		
		Init_bWeiboBindButton();
		Init_bWeiboPublishButton();
		Init_bWeiboGetImage();
		//Init_selectedImage();
		
		if(accessWeibo != null && accessWeibo.isSessionValid())
		{
			bWeiboBindButton.setVisibility(View.INVISIBLE);
		}
		
		
	}

	private void Init_selectedImage() {
		// Initlize the image view
		
		currentImageView.setImageBitmap(BitmapFactory.decodeFile(ImagePath));
		currentImageView.setLongClickable(true);
		currentImageView.setOnLongClickListener(new SelectedImageLongClickListener(MainActivity.this, currentImageView));
		
		// create another row for putting image
		TableLayout layout = (TableLayout) findViewById(R.id.mainTable);
		TableRow row = new TableRow(this);
		android.widget.TableRow.LayoutParams lp = 
				new android.widget.TableRow.LayoutParams(
						android.widget.TableRow.LayoutParams.WRAP_CONTENT, 
						android.widget.TableRow.LayoutParams.WRAP_CONTENT);
		row.setLayoutParams(lp);
		
		ImageView newImage = new ImageView(this);
		TableRow.LayoutParams lparams = new TableRow.LayoutParams(150,150);
		newImage.setLayoutParams(lparams);

		row.addView(newImage);
		layout.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
		
		currentImageView = newImage;
	}

	private void Init_bWeiboGetImage() {
		// TODO Auto-generated method stub
		bWeiboGetImage = (Button) findViewById(R.id.select);
		
		bWeiboGetImage.setOnClickListener(new WeiboGetImageButtonListener(MainActivity.this));
	}

	private void Init_bWeiboPublishButton() {
		bWeiboPublishButton = (Button) findViewById(R.id.publishWeibo);
		
		bWeiboPublishButton.setOnClickListener(new PublishClickListener(MainActivity.this, accessWeibo));
	}

	private void Init_bWeiboBindButton() {
		bWeiboBindButton = (Button) findViewById(R.id.bindWeibo);
			
		mWeiboAuth = new WeiboAuth(this, Constant.AppKep, Constant.RedirectUri, Constant.Scope);
		
		bWeiboBindButton.setOnClickListener(new WeiboBindButtonListener(MainActivity.this, mWeiboAuth));
	}
}
