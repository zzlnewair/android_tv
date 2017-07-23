package com.zzl.recyclerviewtv.demo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.RecyclerListener;

import com.zzl.recyclerviewtv.demo.adapter.TextAdapter;
import com.zzl.view.mainupview.MainUpView;
import com.zzl.view.mainupview.RecyclerViewBridge;
import com.zzl.view.recyclerview.RecyclerViewTV;

public class MainActivity extends Activity {

	private static final String[] mDatas = { 
		"LinearLayout水平","LinearLayout垂直",  "GridLayout水平","GridLayout垂直",
		"StaggeredGridLayout水平","StaggeredGridLayout垂直"};

	List<String> mlist = new ArrayList<String>();
	private RecyclerViewTV mRecyclerView;
	private LinearLayoutManager mManager;
	private TextAdapter mLayoutAdapter;
	private final String TAG = "MainActivity";
	
	private MainUpView mainUpView1;
    private RecyclerViewBridge mRecyclerViewBridge;
    private View oldView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    	initData();
		initView();
    }

    private void initData() {

		for (int i = 0; i < mDatas.length; i++) {

			mlist.add(mDatas[i]);
		}
	}
    

	private void initView() {

		mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
        mainUpView1.setEffectBridge(new RecyclerViewBridge());
        // 注意这里，需要使用 RecyclerViewBridge 的移动边框 Bridge.
        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.drawable.test_rectangle);
        
        
		mRecyclerView = ((RecyclerViewTV) findViewById(R.id.trv));
		mRecyclerView.setHasFixedSize(true);
		

		mManager = new LinearLayoutManager(this,
				LinearLayoutManager.HORIZONTAL, false);
		mRecyclerView.setLayoutManager(mManager);

		// mAdapter = new MenuAdapter();
		// mRecyclerView.setAdapter(mAdapter);
		mLayoutAdapter = new TextAdapter(getApplicationContext(), mlist,
				R.layout.item_main_example);
		mRecyclerView.setAdapter(mLayoutAdapter);
		
		mRecyclerView.setOnItemListener(new RecyclerViewTV.OnItemListener(){

			@Override
			public void onItemPreSelected(RecyclerViewTV parent, View itemView,
					int position) {
				// TODO Auto-generated method stub
				  mRecyclerViewBridge.setUnFocusView(oldView);
			}

			@Override
			public void onItemSelected(RecyclerViewTV parent, View itemView,
					int position) {
				// TODO Auto-generated method stub
				 mRecyclerViewBridge.setFocusView(itemView, 1.8f);
		         oldView = itemView;
			}

			@Override
			public void onReviseFocusFollow(RecyclerViewTV parent,
					View itemView, int position) {
				// TODO Auto-generated method stub
				  mRecyclerViewBridge.setFocusView(itemView, 1.2f);
		          oldView = itemView;
			}		
		});
		mRecyclerView.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				// TODO Auto-generated method stub
				
				switch (position) {
				case 0:
					Intent intent = new Intent(MainActivity.this,LinearLayoutActivity.class);
					intent.putExtra("isVertical", "0");
					startActivity(intent);
					break;
				case 1:
					Intent intent1 = new Intent(MainActivity.this,LinearLayoutActivity.class);
					intent1.putExtra("isVertical", "1");
					startActivity(intent1);
					break;
				case 2:
					Intent intent2 = new Intent(MainActivity.this,GridLayoutActivity.class);
					intent2.putExtra("isVertical", "0");
					startActivity(intent2);
					break;
				case 3:
					Intent intent3 = new Intent(MainActivity.this,GridLayoutActivity.class);
					intent3.putExtra("isVertical", "1");
					startActivity(intent3);
					break;
				case 4:
					Intent intent4 = new Intent(MainActivity.this,StaggeredGridActivity.class);
					intent4.putExtra("isVertical", "0");
					startActivity(intent4);
					break;
				case 5:
					Intent intent5 = new Intent(MainActivity.this,StaggeredGridActivity.class);
					intent5.putExtra("isVertical", "1");
					startActivity(intent5);
					break;
				}
				
				
			}
		});
		
	}
    
}
