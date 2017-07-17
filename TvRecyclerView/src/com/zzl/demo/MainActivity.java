package com.zzl.demo;


import com.zzl.demo.adapter.LayoutAdapter;
import com.zzl.demo.adapter.TextAdapter;
import com.zzl.recyclerview.R;
import com.zzl.recyclerview.TvRecyclerView;
import com.zzl.recyclerview.TvRecyclerView.OnItemListener;
import com.zzl.recyclerview.animeffect.MainUpView;
import com.zzl.recyclerview.animeffect.RecyclerViewBridge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity  {

	
	private static final String[] mDatas = {
        "LinearLayout--->HORIZONTAL",
        "LinearLayout--->VERTICAL",
        "GridLayout--->HORIZONTAL",
        "GridLayout--->VERTICAL",
        "StaggeredGridLayout--->HORIZONTAL",
        "StaggeredGridLayout--->VERTICAL",
	};
	 protected static final String TAG = "MainActivity";
	 private TvRecyclerView mTvList;
	 //private MenuAdapter mAdapter;
	 private TextAdapter mLayoutAdapter;
	    
	 private LinearLayoutManager mManager;
	 private MainUpView mainUpView1;
	 private RecyclerViewBridge mRecyclerViewBridge;
	 private View oldView;
	 private View newView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

  

	 private void initView() {
		 
		    mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
	        mainUpView1.setEffectBridge(new RecyclerViewBridge());
	        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
	        mRecyclerViewBridge.setUpRectResource(R.drawable.test_rectangle);
	        mainUpView1.setDrawUpRectPadding(6);
	        
	        
	        mTvList = ((TvRecyclerView) findViewById(R.id.trv));
	       // mFocusFrame = ((FocusFrameView) findViewById(R.id.frame));
	       // mTvList.initFrame(mFocusFrame, R.drawable.select_border, -2);
	        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
	        mTvList.setLayoutManager(mManager);
	       
	       // mAdapter = new MenuAdapter();
	      //  mTvList.setAdapter(mAdapter);
	        mLayoutAdapter = new TextAdapter(getApplicationContext(), mTvList, 0);
	        mTvList.setAdapter(mLayoutAdapter);
	        
	        mTvList.setOnItemListener(new TvRecyclerView.OnItemListener() {
				
				@Override
				public void onReviseFocusFollow(TvRecyclerView parent, View itemView,
						int position) {
					// TODO Auto-generated method stub
					
					// 此处为了特殊情况时校正移动框
	                mRecyclerViewBridge.setFocusView(itemView, 1.1f);
	                
				}
				
			
				
				@Override
				public void onItemPreSelected(TvRecyclerView parent, View itemView,
						int position) {
					// TODO Auto-generated method stub
					   mRecyclerViewBridge.setUnFocusView(itemView);
				}
				@Override
				public void onItemSelected(TvRecyclerView parent, View itemView,
						int position) {
					// TODO Auto-generated method stub
				    newView = itemView;
	                mRecyclerViewBridge.setFocusView(itemView, 1.1f);
//	                itemView.animate().scaleX(1.2f).scaleY(1.2f).setDuration(500).start();
	             
					Log.d(TAG,"onItemSelected--"+position);
				}
				@Override
				public void onItemClick(TvRecyclerView parent, View itemView, int position) {
					// TODO Auto-generated method stub
					
					 switch (position) {
	                    case 0:
	                        Intent intent = new Intent(MainActivity.this, LinearLayoutActivity.class);
	                        intent.putExtra("isVertical", "0");
	                        startActivity(intent);
	                        break;
	                    case 1:
	                        Intent intent1 = new Intent(MainActivity.this, LinearLayoutActivity.class);
	                        intent1.putExtra("isVertical", "1");
	                        startActivity(intent1);
	                        break;
	                    case 2:
//	                        Intent intent2 = new Intent(MainActivity.this, GridLayoutActivity.class);
//	                        intent2.putExtra("isVertical", "0");
//	                        startActivity(intent2);
	                        break;
	                    case 3:
//	                        Intent intent3 = new Intent(MainActivity.this, GridLayoutActivity.class);
//	                        intent3.putExtra("isVertical", "1");
//	                        startActivity(intent3);
	                        break;
	                    case 4:
//	                        Intent intent4 = new Intent(MainActivity.this, StaggeredGridActivity.class);
//	                        intent4.putExtra("isVertical", "0");
//	                        startActivity(intent4);
	                        break;
	                    case 5:
//	                        Intent intent5 = new Intent(MainActivity.this, StaggeredGridActivity.class);
//	                        intent5.putExtra("isVertical", "1");
//	                        startActivity(intent5);
//	                        break;
	                }
				}
			});
	    }

	  
}
