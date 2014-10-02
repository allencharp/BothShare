package com.example.listener;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.persistent.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;

public class SinaWeiboAuthListener implements WeiboAuthListener
{
	private Activity act;
	
	private Oauth2AccessToken mAccessToken;
	
	public SinaWeiboAuthListener(Activity activity)
	{
		this.act = activity;
	}
	
	@Override
	public void onCancel() {
		// TODO Auto-generated method stub
		Toast.makeText(this.act, "user canceled" , Toast.LENGTH_LONG).show();
	}

	@Override
	public void onComplete(Bundle values) {		
		// 浠� Bundle 涓В鏋� Token
        mAccessToken = Oauth2AccessToken.parseAccessToken(values);
        if (mAccessToken.isSessionValid()) {
            AccessTokenKeeper.writeAccessToken(this.act, mAccessToken);
            Toast.makeText(this.act, 
                    "success", Toast.LENGTH_SHORT).show();
            
            AccessTokenKeeper.writeAccessToken(this.act, mAccessToken);
        } else {
            String code = values.getString("code");
            String message = "failed";
            if (!TextUtils.isEmpty(code)) {
                message = message + "\nObtained the code: " + code;
            }
            Toast.makeText(this.act, message, Toast.LENGTH_LONG).show();
        }
	}

	@Override
	public void onWeiboException(WeiboException arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(this.act, "Exception happened" , Toast.LENGTH_LONG).show();
	}
	
	
}
