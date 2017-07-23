package com.open.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import com.open.androidtvwidget.leanback.adapter.MyItemDecoration;
import com.open.androidtvwidget.leanback.adapter.MyItemDecoration.OrientationType;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.utils.DisplayUtil;
import com.open.androidtvwidget.utils.LogUtils;
import com.open.androidtvwidget.utils.Utils;
import com.open.androidtvwidget.view.TvGridLayoutManagerScrolling;
import com.open.androidtvwidget.view.BorderView.BorderView;


import okhttp3.Request;


import com.open.demo.adapter.ListViewAdapter;
import com.open.demo.adapter.MyAdapter;
import com.open.demo.http.OkHttpManager;
import com.open.demo.http.OkHttpManager.DataCallBack;
import com.open.demo.mode.MovieBean;
import com.umeng.analytics.MobclickAgent;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class DemoTwoRecyclerViewActivity extends Activity {

	private BorderView border;

	private static final String URL = "http://gvod.video.51togic.com/api/v1/programs?mobile=false&version_code=102&device_id=419000000000000000005cc6d0b7e7e80000000000000000&city=%E5%8C%97%E4%BA%AC&vip=0&show_top_recommend=0";

	private static final String TAG = null;

    private final List<MovieBean> mDetailInfoListAll = new CopyOnWriteArrayList<MovieBean>();
	  
    private final List<MovieBean> mDetailInfoList = new CopyOnWriteArrayList<MovieBean>();
    
    
	//RecyclerView recyclerViewTitle; 
	
	private ListView subjectTitlesListView;
    private BaseAdapter subjectTitlesAdapter;
    private List<String> mSubjectTitles = new ArrayList<String>();
    
    int listViewSelectItem = 0;
    private boolean mIsGridViewInMaxLeft = false;
	
    RecyclerViewTV recyclerViewMovie;
	MyAdapter recyclerViewMovieAdapter ;
	private final int MOVIE_COLUMNS = 6;
	
	final int FRFRESHSUCESS_WHAT = 1001;
	
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			switch (msg.what) {
			case FRFRESHSUCESS_WHAT:
				
				/*
				 * 测试使用，其实应该每个标题都有数据刷新
				 */
				mDetailInfoList.clear();
				for(int i= msg.arg1+6;i<mDetailInfoListAll.size();i++){
				   mDetailInfoList.add(mDetailInfoListAll.get(i));
				}
				
				listViewSelectItem =msg.arg1;
				recyclerViewMovieAdapter.notifyDataSetChanged();
				recyclerViewMovie.scrollToPosition(0);
			
				LogUtils.d("","listViewSelectItem=="+listViewSelectItem);
				break;

			default:
				break;
			}
		}
		
		
	};
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.demo_two_recycler_view);

		border = new BorderView(this);
		border.setBackgroundResource(R.drawable.border_red);
		

		
		
	
		testRecyclerViewLinerLayout();
		
		testRecyclerViewGridLayout();

		refreshMovies(0);
	}

	private void testRecyclerViewLinerLayout() {
		// test linearlayout
	
		// 创建一个线性布局管理器

		createData1(9);
		
	    subjectTitlesListView = (ListView) findViewById(R.id.subjectTitlesListView);
	    subjectTitlesAdapter = new ListViewAdapter(this,mSubjectTitles);
	    subjectTitlesListView.setAdapter(subjectTitlesAdapter);
	    subjectTitlesListView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				refreshMovies(position);
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		
	    
	   
	    }) ;

		

	}

	
	  private void refreshMovies(int titleIndex) {
		
	      

		  
		     createData2(titleIndex);
				
				
			

			
		  
	    }
	  
	  
	private void testRecyclerViewGridLayout() {
		// test grid
		
		
		recyclerViewMovie= (RecyclerViewTV) findViewById(R.id.secondRecyclerView);
		GridLayoutManager gridlayoutManager = new TvGridLayoutManagerScrolling(
				this, MOVIE_COLUMNS);
		gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL);
		recyclerViewMovie.setLayoutManager(gridlayoutManager);
		recyclerViewMovie.setFocusable(false);

		border.attachTo(recyclerViewMovie);

		MyItemDecoration	itemDecoration = new MyItemDecoration(0x00000000, DisplayUtil.dip2px(getApplicationContext(), 40), 0, OrientationType.VERTICAL);
		// 创建Adapter，并指定数据集
		recyclerViewMovieAdapter= new MyAdapter(getApplicationContext(), mDetailInfoList, R.layout.item);
		// 设置Adapter
		recyclerViewMovie.setAdapter(recyclerViewMovieAdapter);
		recyclerViewMovie.scrollToPosition(0);
	
		recyclerViewMovie.addItemDecoration(itemDecoration);
		
		recyclerViewMovie.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
			
			@Override
			public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
				// TODO Auto-generated method stub
				
			}
		});
		
		recyclerViewMovie.setOnItemListener(new RecyclerViewTV.OnItemListener() {

			@Override
			public void onItemPreSelected(RecyclerViewTV parent, View itemView,
					int position) {
				// TODO Auto-generated method stub
				
				
			}

			@Override
			public void onItemSelected(RecyclerViewTV parent, View itemView,
					int position) {
				// TODO Auto-generated method stub
				
			    LogUtils.d("", "position=="+position);
				if (position % MOVIE_COLUMNS == 0) {
                    mIsGridViewInMaxLeft = true;
                    LogUtils.d("", "mIsGridViewInMaxLeft=="+mIsGridViewInMaxLeft);
                } else {
                    mIsGridViewInMaxLeft = false;
                }
			}

			@Override
			public void onReviseFocusFollow(RecyclerViewTV parent,
					View itemView, int position) {
				// TODO Auto-generated method stub
				
			}
		});
		
	
		
	}

	
	
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		 if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
             if (mIsGridViewInMaxLeft) {
             	
             	LogUtils.d(TAG,"listViewSelectItem=setSelection="+listViewSelectItem);
               
             	subjectTitlesListView.requestFocusFromTouch();

                 subjectTitlesListView.setSelection(listViewSelectItem);

                 
                 return true;
             }
         }
		 
		return super.onKeyDown(keyCode, event);
	}

	private void createData1(int num) {
		// 创建数据集
		String[] dataset = new String[num];
		for (int i = 0; i < num; i++) {
			dataset[i] = "标题" + i;
			
			mSubjectTitles.add(dataset[i]);
		}
		
	}

	private void createData2(final int titleIndex) {
		// 创建数据集
		
		OkHttpManager.getAsync(URL, new DataCallBack() {
			
			@Override
			public void requestSuccess(String result) throws Exception {
				// TODO Auto-generated method stub
				 parseJson(result,titleIndex);
				 
			}
			
			@Override
			public void requestFailure(Request request, IOException e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
//		
	
	}
	
	 private void parseJson(String content,int titleIndex) {
	        JSONObject jason = JSONObject.parseObject(content);
	        JSONArray data = jason.getJSONArray("items");
	        int totalCount = Integer.parseInt(jason.getString("count"));
	        mDetailInfoListAll.clear();
	        for (int i = 0; i < data.size(); i++) {
	            MovieBean info = new MovieBean();
	            JSONObject jsonObj = (JSONObject) data.get(i);
	            String infotext = jsonObj.getString("infotext");
	            String url = jsonObj.getString("poster");
	            String title = jsonObj.getString("title");
	            info.setPoster(url);
	            info.setInfotext(infotext);
	            info.setTitle(title);
	            mDetailInfoListAll.add(info);
	            //Log.d(TAG, "parseJson mDetailInfoList " + mDetailInfoListAll.size());
	        }
//	        for (int j = 0; j < mDetailInfoListAll.size(); j++) {
//	            Log.d(TAG, "parseJson mDetailInfoListAll : " + j + "content : " + mDetailInfoListAll.get(j).toString());
//	        }
			if(mDetailInfoListAll!=null &&mDetailInfoListAll.size()>0){
			
				Message msg=mHandler.obtainMessage(FRFRESHSUCESS_WHAT);
				
				msg.what =FRFRESHSUCESS_WHAT;
				msg.arg1 = titleIndex;
				mHandler.sendMessageDelayed(msg, 300);
				
			}
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
