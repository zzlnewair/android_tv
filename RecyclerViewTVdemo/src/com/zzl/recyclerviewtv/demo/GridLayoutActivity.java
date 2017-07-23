package com.zzl.recyclerviewtv.demo;

import com.zzl.recyclerviewtv.demo.adapter.RecyclerViewPresenter;
import com.zzl.view.adapter.GeneralAdapter;
import com.zzl.view.recyclerview.GridLayoutManagerTV;
import com.zzl.view.recyclerview.LinearLayoutManagerTV;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;

public class GridLayoutActivity extends BaseActivity {

	 RecyclerViewPresenter mRecyclerViewPresenter;
	 GeneralAdapter mGeneralAdapter;
	    
	 
	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	 }
	 
	 
	@Override
	protected LayoutManager getLayoutManager() {
		// TODO Auto-generated method stub
		  if ("0".equals(mIsVertical)) {
	            return new GridLayoutManagerTV(this, 2,LinearLayoutManager.HORIZONTAL, false);
	        } else {
	            return new GridLayoutManagerTV(this, 5,LinearLayoutManager.VERTICAL, false);
	        }
	}

	@Override
	protected Adapter getAdapter() {
		// TODO Auto-generated method stub
		 mRecyclerViewPresenter = new RecyclerViewPresenter(20);
		 mGeneralAdapter = new GeneralAdapter(mRecyclerViewPresenter);
		 
		return mGeneralAdapter;
	}

}
