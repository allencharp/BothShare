package com.example.listener;

import com.example.bothshare.MainActivity;
import com.example.bothshare.R;
import com.example.util.LocationManagerUtil;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.openapi.legacy.StatusesAPI;

import android.app.Activity;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;

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
		
		final ProgressBar bar = (ProgressBar) this.act.findViewById(R.id.sending);
		bar.setVisibility(View.VISIBLE);
		
		final Handler handler = new Handler() 
		{ 
			public void handleMessage(Message msg) 
			{ 
				Boolean gone = msg.getData().getBoolean("gone");
				if(gone != null && gone)
				{
					bar.setVisibility(View.GONE);
				}
			} 
		};
		
		Thread weiboThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Location locUtil = (new LocationManagerUtil()).getLocation(act);
		        String latitude = "0";
		        String Longitude = "0";
		        if(locUtil != null)
		        {
		        	latitude = String.valueOf(locUtil.getLatitude());
		        	Longitude = String.valueOf(locUtil.getLongitude());
		        }
		        StatusesAPI api = new StatusesAPI(auth);
		        
		        MainActivity main = ((MainActivity)act);
		        if(main.GetImagePath() == ""){
		        	// no need for listener temporarily.......
		        	api.update(main.GetEditTextValue(), latitude, Longitude, new SendRequestListener(handler));
		        }
		        else
		        {
		        	api.upload(main.GetEditTextValue(), main.GetImagePath(), latitude, Longitude, new SendRequestListener(handler));
		        }
			}
		});
		
		weiboThread.start();		
	}

}
