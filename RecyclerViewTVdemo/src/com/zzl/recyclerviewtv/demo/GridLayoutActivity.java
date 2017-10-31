package com.zzl.recyclerviewtv.demo;

import com.zzl.recyclerviewtv.demo.adapter.RecyclerViewPresenter;
import com.zzl.view.adapter.GeneralAdapter;
import com.zzl.view.mode.ListRowPresenter;
import com.zzl.view.mode.OpenPresenter;
import com.zzl.view.recyclerview.GridLayoutManagerTV;
import com.zzl.view.recyclerview.LinearLayoutManagerTV;
import com.zzl.view.recyclerview.RecyclerViewTV;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.View;

public class GridLayoutActivity extends BaseActivity {

	 protected static final String TAG = null;
	RecyclerViewPresenter mRecyclerViewPresenter;
	 GeneralAdapter mGeneralAdapter;
	    
	 private int mSavePos = 2;
	    
	 Handler moreHandler = new Handler() {
	        @Override
	        public void handleMessage(Message msg) {
	            mRecyclerViewPresenter.addDatas(msg.arg1);
	            mSavePos = mTvRecyclerView.getSelectPostion();
	            mTvRecyclerView.setOnLoadMoreComplete(); // 加载更多完毕.
	            mFocusHandler.sendEmptyMessageDelayed(10, 10); // 延时请求焦点.
	            Log.d(TAG,"加载更多....");
	            load_more_pb.setVisibility(View.GONE);
	        }
	    };
	    
	    

	    Handler mFocusHandler = new Handler() {
	        @Override
	        public void handleMessage(Message msg) {
	            if (!isListRowPresenter())
	            	mTvRecyclerView.setDefaultSelect(mSavePos);
	        }
	    };
	    
	    
	    
	
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	    	mTvRecyclerView.setPagingableListener(new RecyclerViewTV.PagingableListener() {
	            @Override
	            public void onLoadMoreItems() {
	                // 加载更多测试.
//	                moreHandler.removeCallbacksAndMessages(null);
	                Message msg = moreHandler.obtainMessage();
	                msg.arg1 = 10;
	                moreHandler.sendMessageDelayed(msg, 3000);
	                load_more_pb.setVisibility(View.VISIBLE);
	            }
	        });
	        mFocusHandler.sendEmptyMessageDelayed(10, 1000);
	        
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
	
	
	  /**
     * 排除 Leanback demo的RecyclerView.
     */
    private boolean isListRowPresenter() {
        GeneralAdapter generalAdapter = (GeneralAdapter) mTvRecyclerView.getAdapter();
        OpenPresenter openPresenter = generalAdapter.getPresenter();
        return (openPresenter instanceof ListRowPresenter);
    }

}
