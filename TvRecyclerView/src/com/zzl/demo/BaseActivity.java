/**   
* @Title: BaseActivity.java 
* @Package com.zzl.demo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhangzhl  
* @date 2017-7-17 下午12:12:53 
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
import com.zzl.demo.adapter.LayoutAdapter;
import com.zzl.demo.bean.MovieBean;
import com.zzl.demo.http.OkHttpManager;
import com.zzl.demo.http.OkHttpManager.DataCallBack;
import com.zzl.recyclerview.R;
import com.zzl.recyclerview.TvRecyclerView;
import com.zzl.recyclerview.TvRecyclerView.OnItemListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


/**
 
 * @ClassName: BaseActivity 
 * @Description: TODO() 
 * 
 
 */
public abstract class BaseActivity extends Activity {
	  protected String mIsVertical;
	//  private List<String> mDatas = new ArrayList<String>();
	  
	  private final List<MovieBean> mDatas = new CopyOnWriteArrayList<MovieBean>();

	  protected TvRecyclerView mTvRecyclerView;
	 // protected FocusFrameView mFocusFrame;
	  
	//  protected DefaultAdapter mAdapter;
	 
	  private LayoutAdapter mAdapter;
	  
	  private static final String URL = "http://gvod.video.51togic.com/api/v1/programs?mobile=false&version_code=102&device_id=419000000000000000005cc6d0b7e7e80000000000000000&city=%E5%8C%97%E4%BA%AC&vip=0&show_top_recommend=0";

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
	      //  mTvRecyclerView.setItemScale(1.1f);
	     
	       // mAdapter = new DefaultAdapter();
	        //mTvRecyclerView.setAdapter(mAdapter);
	        
	        mAdapter = new LayoutAdapter(getApplicationContext(), mTvRecyclerView, 0);
	        mTvRecyclerView.setAdapter(mAdapter);
	        
	        
	    }

	    protected abstract RecyclerView.LayoutManager getLayoutManager();

	    protected TvRecyclerView.OnItemListener getItemListener() {
	        return new TvRecyclerView.OnItemListener() {

				@Override
				public void onItemPreSelected(TvRecyclerView parent,
						View itemView, int position) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onItemSelected(TvRecyclerView parent,
						View itemView, int position) {
					
					//  mCrurrentItemView = itemView;
		            //   mCurrentFocus = position;
				}

				@Override
				public void onReviseFocusFollow(TvRecyclerView parent,
						View itemView, int position) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onItemClick(TvRecyclerView parent, View itemView,
						int position) {
					// TODO Auto-generated method stub
					
				}
	           
	         
	        };
	    }

	 protected void initData() {
	      
	       // mDatas.clear();
	      //  for (String data : Datas.getDatas()) {
	       //     mDatas.add(data);
	       // }
		 

			OkHttpManager.getAsync(URL, new DataCallBack() {

				@Override
				public void requestSuccess(String result) throws Exception {
					// TODO Auto-generated method stub
					parseJson(result);

				}

				@Override
				public void requestFailure(Request request, IOException e) {
					// TODO Auto-generated method stub

				}
			});
	    }
	 
	 private void parseJson(String jsonString) {
			
		 
		  try{
			  
			  JSONObject jsonObject = new JSONObject(jsonString);
			  JSONArray data = jsonObject.getJSONArray("items");
			  mDatas.clear();
			  for(int i=0;i<data.length();i++){
				  
				  MovieBean info = new MovieBean();
				  JSONObject jsonObj = data.getJSONObject(i);
					
				  String infotext = jsonObj.getString("infotext");
			      String url = jsonObj.getString("poster");
				  String title = jsonObj.getString("title");
				  info.setPoster(url);
				  info.setInfotext(infotext);
				  info.setTitle(title);
				  
				  mDatas.add(info);
					
					
				  
			  }
			  
		  }catch(Exception  e){
			  
			  
		  }
		 
	 }
	 
	 
	 protected int getContentResource() {
	        return R.layout.activity_main;
	    }
	 
	 
	 
	 /**
	     * 根据位置动态设置条目的大小，为了实现瀑布流效果
	     *
	     * @param itemView 当前的itemView
	     * @param position 当前位置
	     */
	    protected void changeSize(View itemView, int position) {

	    }

	    
	    
	    
}
