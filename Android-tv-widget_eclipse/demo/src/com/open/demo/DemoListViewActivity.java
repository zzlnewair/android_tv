package com.open.demo;

import java.util.ArrayList;
import java.util.List;

import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.utils.Utils;
import com.open.androidtvwidget.view.ListViewHorizontal;
import com.open.androidtvwidget.view.ListViewTV;
import com.open.androidtvwidget.view.MainUpView;
import com.umeng.analytics.MobclickAgent;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DemoListViewActivity extends Activity {

	private static final String TAG = "DemoListViewActivity";

	private List<String> data;
	private MainUpView mainUpView1;
	private LayoutInflater mInflater;
	private View mOldView;
	private ListViewTV listView;

	private ListViewHorizontal mListViewHorizontal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.demo_list_view);
		this.mInflater = LayoutInflater.from(getApplicationContext());
		listView = (ListViewTV) findViewById(R.id.listview2);

		mListViewHorizontal = (ListViewHorizontal) findViewById(R.id.listview1);

		initData();
		listViewHorizontalInit();
		listViewInit();

	}

	public void initData() {
		data = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			String text = "item" + i;
			data.add(text);
		}
	}

	private void listViewInit() {

		mainUpView1 = (MainUpView) findViewById(R.id.mainUpView1);
		// 默认是 OpenEff...，建议使用 NoDraw... ...
		mainUpView1.setEffectBridge(new EffectNoDrawBridge());
		EffectNoDrawBridge bridget = (EffectNoDrawBridge) mainUpView1
				.getEffectBridge();
		bridget.setTranDurAnimTime(200);
		//
		mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
		mainUpView1.setDrawUpRectPadding(new Rect(01, 01, 01, 01)); // 边框图片设置间距.
		//

		listView.setAdapter(new DemoAdapter());
		listView.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d(TAG, "zzl----position" + position);
				if (view != null) {
					view.bringToFront();
					mainUpView1.setFocusView(view, mOldView, 1.1f);
					mOldView = view;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.d(TAG, "zzl----onNothingSelected");

			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						"position : " + position, Toast.LENGTH_LONG).show();
			}
		});

	}

	private void listViewHorizontalInit() {

		mListViewHorizontal.setAdapter(new DemoAdapter());
		mListViewHorizontal
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
		mListViewHorizontal.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(),
						"position : " + position, Toast.LENGTH_LONG).show();
			}
		});
	}

	public class DemoAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.item_listview, null);
				holder.title = (TextView) convertView
						.findViewById(R.id.textView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.title.setText(data.get(position));
			return convertView;
		}

		public class ViewHolder {
			public TextView title;
		}
	}

	private Context mContext = DemoListViewActivity.this;
	private final String mPageName = "DemoListViewActivity";

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
