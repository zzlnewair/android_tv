/**   
 * @Title: BaseActivity.java 
 * @Package com.zzl.demo 
 * @Description: TODO(用一句话描述该文件做什么) 
 * 
 * @version V1.0   
 */
package com.zzl.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Request;

import com.bumptech.glide.Glide;

import com.zzl.demo.bean.MovieBean;
import com.zzl.demo.http.OkHttpManager;
import com.zzl.demo.http.OkHttpManager.DataCallBack;
import com.zzl.recyclerview.R;
import com.zzl.recyclerview.TvRecyclerView;
import com.zzl.recyclerview.TvRecyclerView.OnItemListener;
import com.zzl.recyclerview.boardview.BorderView;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @ClassName: BaseActivity
 * @Description: TODO()
 * 
 */
public abstract class BaseActivity extends Activity {
	protected String mIsVertical;
	private final List<String> mDatas = new ArrayList<String>();
	protected TvRecyclerView mTvRecyclerView;
	private LayoutAdapter mAdapter;
	BorderView border;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentResource());
		mIsVertical = getIntent().getStringExtra("isVertical");
		initData();
		initView();
	}

	protected void initView() {

		mTvRecyclerView = ((TvRecyclerView) findViewById(R.id.trv));

		mTvRecyclerView.setOnItemListener(getItemListener());
		mTvRecyclerView.setLayoutManager(getLayoutManager());

		mAdapter = new LayoutAdapter(getApplicationContext(), mDatas);
		mTvRecyclerView.setAdapter(mAdapter);

		if (border == null) {
			border = new BorderView(this);
			border.setBackgroundResource(R.drawable.test_rectangle);
			border.attachTo(mTvRecyclerView);
		}

	}

	protected abstract RecyclerView.LayoutManager getLayoutManager();

	protected TvRecyclerView.OnItemListener getItemListener() {
		return new TvRecyclerView.OnItemListener() {

			@Override
			public void onItemPreSelected(TvRecyclerView parent, View itemView,
					int position) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onItemSelected(TvRecyclerView parent, View itemView,
					int position) {

			}

			@Override
			public void onReviseFocusFollow(TvRecyclerView parent,
					View itemView, int position) {
				// TODO Auto-generated method stub
				// 此处为了特殊情况时校正移动框

			}

			@Override
			public void onItemClick(TvRecyclerView parent, View itemView,
					int position) {
				// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), ""+position, Toast.LENGTH_SHORT).show();
				
			}

		};
	}

	protected void initData() {

		for (int i = 0; i < 30; i++) {
			mDatas.add(i + "");
		}

	}

	protected int getContentResource() {
		return R.layout.activity_main;
	}

	/**
	 * 根据位置动态设置条目的大小，为了实现瀑布流效果
	 * 
	 * @param itemView
	 *            当前的itemView
	 * @param position
	 *            当前位置
	 */
	protected void changeSize(View itemView, int position) {

	}

	class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.SimpleViewHolder> {

		private Context mContext;

		private List<String> mDatas = new ArrayList<String>();

		public class SimpleViewHolder extends RecyclerView.ViewHolder {
			public final TextView title;
			public final ImageView image;

			public SimpleViewHolder(View view) {
				super(view);
				title = (TextView) view.findViewById(R.id.title);
				image = (ImageView) view.findViewById(R.id.image);
			}
		}

		public LayoutAdapter(Context context, List<String> mDatas) {
			this.mContext = context;

			this.mDatas = mDatas;

		}

		@Override
		public SimpleViewHolder onCreateViewHolder(ViewGroup parent,
				int viewType) {
			final View view = LayoutInflater.from(mContext).inflate(
					R.layout.item, parent, false);
			return new SimpleViewHolder(view);
		}

		@Override
		public void onBindViewHolder(SimpleViewHolder holder, final int position) {
			changeSize(holder.itemView, position);
			holder.title.setText("" + position);
			Glide.with(mContext).load(R.drawable.poster)
					.error(R.drawable.ic_launcher).into(holder.image);

		}

		@Override
		public int getItemCount() {
			return mDatas.size();
		}

	}

}
