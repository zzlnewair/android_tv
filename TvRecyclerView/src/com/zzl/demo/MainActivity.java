package com.zzl.demo;

import java.util.ArrayList;
import java.util.List;

import com.zzl.demo.adapter.TextAdapter;
import com.zzl.recyclerview.R;
import com.zzl.recyclerview.TvRecyclerView;
import com.zzl.recyclerview.TvRecyclerView.OnItemListener;
import com.zzl.recyclerview.boardview.BorderView;

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

public class MainActivity extends Activity {

	private static final String[] mDatas = { "LinearLayout水平",
			"LinearLayout垂直", "GridLayout水平", "GridLayout垂直",
			"StaggeredGridLayout水平", "StaggeredGridLayout垂直", };

	List<String> mlist = new ArrayList<String>();

	protected static final String TAG = "MainActivity";
	private TvRecyclerView mTvList;
	
	private TextAdapter mLayoutAdapter;

	private LinearLayoutManager mManager;

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

		mTvList = ((TvRecyclerView) findViewById(R.id.trv));
		mTvList.setHasFixedSize(true);
		mTvList.setInterceptKeyEvent(true);

		mManager = new LinearLayoutManager(this,
				LinearLayoutManager.HORIZONTAL, false);
		mTvList.setLayoutManager(mManager);

		// mAdapter = new MenuAdapter();
		// mTvList.setAdapter(mAdapter);
		mLayoutAdapter = new TextAdapter(getApplicationContext(), mlist,
				R.layout.item_main_example);
		mTvList.setAdapter(mLayoutAdapter);

		BorderView border = new BorderView(this);
		border.setBackgroundResource(R.drawable.test_rectangle);

		border.attachTo(mTvList);

	
		mTvList.setOnItemListener(new TvRecyclerView.OnItemListener() {

			@Override
			public void onReviseFocusFollow(TvRecyclerView parent,
					View itemView, int position) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onItemPreSelected(TvRecyclerView parent, View itemView,
					int position) {

			}

			@Override
			public void onItemSelected(TvRecyclerView parent, View itemView,
					int position) {
				// TODO Auto-generated method stub

				// itemView.animate().scaleX(1.2f).scaleY(1.2f).setDuration(500).start();

				Log.d(TAG, "onItemSelected--" + position);
			}

			@Override
			public void onItemClick(TvRecyclerView parent, View itemView,
					int position) {
				// TODO Auto-generated method stub

				switch (position) {
				case 0:
					Intent intent = new Intent(MainActivity.this,
							LinearLayoutActivity.class);
					intent.putExtra("isVertical", "0");
					startActivity(intent);
					break;
				case 1:
					Intent intent1 = new Intent(MainActivity.this,
							LinearLayoutActivity.class);
					intent1.putExtra("isVertical", "1");
					startActivity(intent1);
					break;
				case 2:
					Intent intent2 = new Intent(MainActivity.this,
							GridLayoutActivity.class);
					intent2.putExtra("isVertical", "0");
					startActivity(intent2);
					break;
				case 3:
					Intent intent3 = new Intent(MainActivity.this,
							GridLayoutActivity.class);
					intent3.putExtra("isVertical", "1");
					startActivity(intent3);
					break;
				case 4:
					Intent intent4 = new Intent(MainActivity.this,
							StaggeredGridActivity.class);
					intent4.putExtra("isVertical", "0");
					startActivity(intent4);
					break;
				case 5:
					Intent intent5 = new Intent(MainActivity.this,
							StaggeredGridActivity.class);
					intent5.putExtra("isVertical", "1");
					startActivity(intent5);
					break;
				}
			}
		});
	}

}
