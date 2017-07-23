package com.zzl.recyclerviewtv.demo;

import com.zzl.recyclerviewtv.demo.adapter.RecyclerViewPresenter;
import com.zzl.view.adapter.GeneralAdapter;
import com.zzl.view.recyclerview.LinearLayoutManagerTV;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
public class LinearLayoutActivity extends BaseActivity {

	 RecyclerViewPresenter mRecyclerViewPresenter;
	 GeneralAdapter mGeneralAdapter;
	    
	    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        if ("0".equals(mIsVertical)) {
            return new LinearLayoutManagerTV(this, LinearLayoutManager.HORIZONTAL, false);
        } else {
            return new LinearLayoutManagerTV(this, LinearLayoutManager.VERTICAL, false);
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
