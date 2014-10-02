package com.example.listener;

import com.example.bothshare.MainActivity;
import com.example.util.LocationManagerUtil;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuth;
import com.sina.weibo.sdk.openapi.legacy.StatusesAPI;

import android.app.Activity;
import android.location.Location;
import android.view.View;
import android.view.View.OnClickListener;

public class PublishClickListener implements OnClickListener{

	private Activity act;
	private Oauth2AccessToken auth;
	
	public PublishClickListener(MainActivity activity,
			Oauth2AccessToken accessWeibo) {
		// TODO Auto-generated constructor stub
		this.act = activity;
		this.auth = accessWeibo;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Location locUtil = (new LocationManagerUtil()).getLocation(this.act);
        String latitude = "0";
        String Longitude = "0";
        if(locUtil != null)
        {
        	latitude = String.valueOf(locUtil.getLatitude());
        	Longitude = String.valueOf(locUtil.getLongitude());
        }
        StatusesAPI api = new StatusesAPI(auth);
        
        MainActivity main = ((MainActivity)this.act);
        if(main.GetImagePath() == ""){
        	// no need for listener temporarily.......
        	api.update(main.GetEditTextValue(), latitude, Longitude, null);
        }
        else
        {
        	api.upload(main.GetEditTextValue(), main.GetImagePath(), latitude, Longitude, null);
        }
	}

}
