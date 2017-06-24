package com.open.demo;

import com.open.androidtvwidget.view.BorderView.*;
import com.open.androidtvwidget.view.TvGridLayoutManagerScrolling;
import com.open.demo.adapter.MyAdapter;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class DemoTwoRecyclerViewActivity extends Activity {

	private BorderView border;

	private static final String URL = "http://gvod.video.51togic.com/api/v1/programs?mobile=false&version_code=102&device_id=419000000000000000005cc6d0b7e7e80000000000000000&city=%E5%8C%97%E4%BA%AC&vip=0&show_top_recommend=0";

	RecyclerView recyclerViewTitle; 
	
	RecyclerView recyclerViewMovie;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.demo_two_recycler_view);

		border = new BorderView(this);
		border.setBackgroundResource(R.drawable.border_red);
		
		
		
		recyclerViewTitle = (RecyclerView) findViewById(R.id.firstRecyclerView);
		
		recyclerViewMovie= (RecyclerView) findViewById(R.id.secondRecyclerView);
		testRecyclerViewLinerLayout();
		
		testRecyclerViewGridLayout();

	}

	private void testRecyclerViewLinerLayout() {
		// test linearlayout
	
		// 创建一个线性布局管理器

		GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerViewTitle.setLayoutManager(layoutManager);
		recyclerViewTitle.setFocusable(false);
		border.attachTo(recyclerViewTitle);

		createData1(recyclerViewTitle, R.layout.item3);

	}

	private void testRecyclerViewGridLayout() {
		// test grid
		
		
		GridLayoutManager gridlayoutManager = new TvGridLayoutManagerScrolling(
				this, 4);
		gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL);
		recyclerViewMovie.setLayoutManager(gridlayoutManager);
		recyclerViewMovie.setFocusable(false);

		border.attachTo(recyclerViewMovie);

		createData2(recyclerViewMovie, R.layout.item);

	}

	private void createData1(RecyclerView recyclerView, int id) {
		// 创建数据集
		String[] dataset = new String[10];
		for (int i = 0; i < dataset.length; i++) {
			dataset[i] = "标题" + i;
		}
		// 创建Adapter，并指定数据集
		MyAdapter adapter = new MyAdapter(this, dataset, id);
		// 设置Adapter
		recyclerView.setAdapter(adapter);
		recyclerView.scrollToPosition(0);
	}

	private void createData2(RecyclerView recyclerView, int id) {
		// 创建数据集
		String[] dataset = new String[100];
		for (int i = 0; i < dataset.length; i++) {
			dataset[i] = "item" + i;
		}
		// 创建Adapter，并指定数据集
		MyAdapter adapter = new MyAdapter(this, dataset, id);
		// 设置Adapter
		recyclerView.setAdapter(adapter);
		recyclerView.scrollToPosition(0);
	}
	
	
	  private Context mContext = DemoTwoRecyclerViewActivity.this;
	    private final String mPageName = "DemoTwoRecyclerViewActivity";
		@Override
	    public void onResume() {
	        super.onResume();
	        MobclickAgent.onPageStart(mPageName);
	        MobclickAgent.onResume(mContext);
	    }

	    @Override
	    public void onPause() {
	        super.onPause();
	        MobclickAgent.onPageEnd(mPageName);
	        MobclickAgent.onPause(mContext);
	    }
}
