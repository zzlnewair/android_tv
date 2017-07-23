package com.zzl.recyclerviewtv.demo.adapter;

import com.zzl.recyclerviewtv.demo.R;
import com.zzl.view.mode.OpenPresenter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewHolder extends OpenPresenter.ViewHolder {
	
	public ImageView iv; 
	public TextView tv;
	public TextView head_tv;
	
	public GridViewHolder(View itemView) {
		super(itemView);
		iv = (ImageView)itemView.findViewById(R.id.image);
		tv = (TextView)itemView.findViewById(R.id.textView);
	}

}
