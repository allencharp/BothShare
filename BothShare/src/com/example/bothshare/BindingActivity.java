package com.example.bothshare;

import com.example.listener.PublishClickListener;
import com.example.listener.WeiboBindButtonListener;
import com.example.persistent.AccessTokenKeeper;
import com.example.persistent.Constant;
import com.sina.weibo.sdk.auth.WeiboAuth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BindingActivity extends Activity {

	private Button bWeiboBindButton;
	private WeiboAuth mWeiboAuth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		
		bWeiboBindButton = (Button) findViewById(R.id.bindWeibo);

		if(AccessTokenKeeper.readAccessToken(this) != null)
		{
			Intent redirect = new Intent(BindingActivity.this, MainActivity.class);
			redirect.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			this.startActivity(redirect);
			bWeiboBindButton.setVisibility(View.INVISIBLE);
		}
		
		Init_View();
	}

	private void Init_View() {
		
		mWeiboAuth = new WeiboAuth(this, Constant.AppKep, Constant.RedirectUri, Constant.Scope);
		
		bWeiboBindButton.setOnClickListener(new WeiboBindButtonListener(BindingActivity.this, mWeiboAuth));
	}

}
