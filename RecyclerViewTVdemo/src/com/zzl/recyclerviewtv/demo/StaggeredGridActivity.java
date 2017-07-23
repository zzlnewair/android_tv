package com.zzl.recyclerviewtv.demo;

import com.zzl.recyclerviewtv.demo.adapter.RecyclerViewPresenter;
import com.zzl.recyclerviewtv.demo.adapter.StaggeredGridPresenter;
import com.zzl.view.adapter.GeneralAdapter;
import com.zzl.view.recyclerview.GridLayoutManagerTV;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

public class StaggeredGridActivity extends BaseActivity {
	StaggeredGridPresenter mRecyclerViewPresenter;
	GeneralAdapter mGeneralAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected LayoutManager getLayoutManager() {
		// TODO Auto-generated method stub
		if ("0".equals(mIsVertical)) {
			return new StaggeredGridLayoutManager(2,
					StaggeredGridLayoutManager.HORIZONTAL);
		} else {
			
			 ViewGroup.LayoutParams layoutParams =
	                    mTvRecyclerView.getLayoutParams();
	            layoutParams.width =  800;
	            mTvRecyclerView.setLayoutParams(layoutParams);
			return new StaggeredGridLayoutManager(4,
					StaggeredGridLayoutManager.VERTICAL);
		}
	}

	@Override
	protected Adapter getAdapter() {
		// TODO Auto-generated method stub
		if ("0".equals(mIsVertical)) {
			mRecyclerViewPresenter = new StaggeredGridPresenter(20,
					StaggeredGridLayoutManager.HORIZONTAL);
		} else {
			mRecyclerViewPresenter = new StaggeredGridPresenter(20,
					StaggeredGridLayoutManager.VERTICAL);

		}

		mGeneralAdapter = new GeneralAdapter(mRecyclerViewPresenter);

		return mGeneralAdapter;

	}

}
