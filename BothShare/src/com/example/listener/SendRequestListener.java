package com.example.listener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;

import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

public class SendRequestListener implements  RequestListener{

	private Handler handler;
	public SendRequestListener(Handler handler)
	{
		this.handler = handler;
	}
	
	@Override
	public void onComplete(String response) {
		Message msg = handler.obtainMessage();
		// TODO Auto-generated method stub
		Bundle b = new Bundle(); 
		b.putBoolean("gone", true);
		msg.setData(b);
		this.handler.sendMessage(msg);
	}

	@Override
	public void onComplete4binary(ByteArrayOutputStream responseOS) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onIOException(IOException e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(WeiboException e) {
		// TODO Auto-generated method stub
		
	}
	
}
