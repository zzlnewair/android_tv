package com.zzl.recyclerviewtv.demo;

import java.util.ArrayList;
import java.util.List;

import com.zzl.view.mainupview.MainUpView;
import com.zzl.view.mainupview.RecyclerViewBridge;
import com.zzl.view.recyclerview.RecyclerViewTV;
import com.zzl.view.recyclerview.RecyclerViewTV.OnItemClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {
	protected String mIsVertical;
	private final List<String> mDatas = new ArrayList<String>();
	protected RecyclerViewTV mTvRecyclerView;
	
	private MainUpView mainUpView1;
    private RecyclerViewBridge mRecyclerViewBridge;
    private View oldView;
    protected View load_more_pb;
    

	@Override
	protected void onCreate( Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(getContentResource());
	mIsVertical = getIntent().getStringExtra("isVertical");
	initData();
	initMainUpView();
	initView();
}

	

	protected int getContentResource() {
		return R.layout.activity_example;
	}
	
	protected void initData() {

		for (int i = 0; i < 30; i++) {
			mDatas.add(i + "");
		}

	}
	
	void initMainUpView(){
		
		
		mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
        mainUpView1.setEffectBridge(new RecyclerViewBridge());
        // 注意这里，需要使用 RecyclerViewBridge 的移动边框 Bridge.
        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
        mRecyclerViewBridge.setUpRectResource(R.drawable.test_rectangle);
        
        
	}
	protected void initView() {

		mTvRecyclerView = ((RecyclerViewTV) findViewById(R.id.trv));
		load_more_pb = findViewById(R.id.load_more_pb);

		mTvRecyclerView.setOnItemListener(getItemListener());
		mTvRecyclerView.setOnItemClickListener(getItemClickListener());
		mTvRecyclerView.setLayoutManager(getLayoutManager());

		mTvRecyclerView.setAdapter(getAdapter());
	

	}
	
	
	private RecyclerViewTV.OnItemClickListener getItemClickListener() {
		// TODO Auto-generated method stub
		return new RecyclerViewTV.OnItemClickListener() {
			
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), ""+position,Toast.LENGTH_SHORT).show();
				
			}
		};
	}



	protected RecyclerViewTV.OnItemListener getItemListener() {
		return new RecyclerViewTV.OnItemListener() {

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
				 mRecyclerViewBridge.setFocusView(itemView, 1.2f);
		         oldView = itemView;
			}

			@Override
			public void onReviseFocusFollow(RecyclerViewTV parent,
					View itemView, int position) {
				// TODO Auto-generated method stub
				  mRecyclerViewBridge.setFocusView(itemView, 1.2f);
		          oldView = itemView;
			}

			
			
		};
	}
	
	protected abstract RecyclerView.LayoutManager getLayoutManager();

	protected abstract RecyclerView.Adapter     getAdapter ();
	
}