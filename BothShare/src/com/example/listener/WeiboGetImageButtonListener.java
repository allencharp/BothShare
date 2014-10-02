package com.example.listener;

import com.example.persistent.Constant;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class WeiboGetImageButtonListener implements OnClickListener{

	private Activity act;
	public WeiboGetImageButtonListener(Activity activity)
	{
		this.act = activity;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Intent intent = new Intent(
				Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				
		this.act.startActivityForResult(intent, Constant.RESULT_LOAD_IMAGE);
	}

}
