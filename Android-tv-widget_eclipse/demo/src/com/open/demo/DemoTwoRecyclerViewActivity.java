package com.open.demo;

import com.open.androidtvwidget.view.BorderView.*;
import com.open.androidtvwidget.view.TvGridLayoutManagerScrolling;
import com.open.demo.adapter.MyAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class DemoTwoRecyclerViewActivity extends Activity {

	private BorderView border;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.demo_two_recycler_view);

		border = new BorderView(this);
		border.setBackgroundResource(R.drawable.border_red);
		testRecyclerViewLinerLayout();
		testRecyclerViewGridLayout();

	}

	private void testRecyclerViewLinerLayout() {
		// test linearlayout
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.firstRecyclerView);
		// 创建一个线性布局管理器

		GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setFocusable(false);
		border.attachTo(recyclerView);

		createData1(recyclerView, R.layout.item3);

	}

	private void testRecyclerViewGridLayout() {
		// test grid
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.secondRecyclerView);
		GridLayoutManager gridlayoutManager = new TvGridLayoutManagerScrolling(
				this, 4);
		gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(gridlayoutManager);
		recyclerView.setFocusable(false);

		border.attachTo(recyclerView);

		createData2(recyclerView, R.layout.item);

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
}
