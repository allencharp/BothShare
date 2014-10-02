package com.example.listener;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.openapi.legacy.StatusesAPI;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class WeiboPublishButtonListener implements OnClickListener{

	private Activity act;
	private Oauth2AccessToken accessWeibo;
	private String text;
	private String imagePath;
	
	public WeiboPublishButtonListener(Activity act, Oauth2AccessToken auth, String text, String imagePath)
	{
		this.act = act;
		this.accessWeibo = auth;
		this.text = text;
		this.imagePath = imagePath;
	}
	@Override
	public void onClick(View v) {
		
		if(accessWeibo!= null)
		{
			/*IWeiboShareAPI  mWeiboShareAPI =  WeiboShareSDK.createWeiboAPI(MainActivity.this, Constant.AppKep);
			mWeiboShareAPI.registerApp();
			
			WeiboMessage weiboMessage = new WeiboMessage();
			
			TextObject textObject = new TextObject();
	        textObject.text = "aaabbbccc";
			
			weiboMessage.mediaObject = textObject;
			SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();

	        request.transaction = String.valueOf(System.currentTimeMillis());
	        request.message = weiboMessage;*/
	        
	        StatusesAPI api = new StatusesAPI(accessWeibo);
	        api.upload(text, imagePath  , "30", "30", null);
		}
	}
	
}
