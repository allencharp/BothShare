package com.example.listener;

import com.sina.weibo.sdk.auth.WeiboAuth;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class WeiboBindButtonListener implements OnClickListener{

	private Activity act;
	private WeiboAuth auth;
	public WeiboBindButtonListener(Activity activity, WeiboAuth auth)
	{
		this.act = activity;
		this.auth = auth;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		auth.anthorize(new SinaWeiboAuthListener(act));
	}

	
}
