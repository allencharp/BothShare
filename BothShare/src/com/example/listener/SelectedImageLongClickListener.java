package com.example.listener;

import com.example.bothshare.MainActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;

public class SelectedImageLongClickListener implements OnLongClickListener{
	
	private Activity act;
	private ImageView holder;
	
	public SelectedImageLongClickListener(Activity activity, ImageView me)
	{
		this.act = activity;
		this.holder =  me;
	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(
                this.act);
		alert.setMessage("Are you sure to delete Image?");
		
		alert.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				holder.setImageBitmap(null);
			}
		
		});
		
		
		alert.show();
		return false;
	}
	
	
}
