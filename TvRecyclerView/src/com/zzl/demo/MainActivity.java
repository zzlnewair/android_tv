package com.zzl.demo;

import com.zzl.recyclerview.R;
import com.zzl.recyclerview.TvRecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity implements OnClickListener {

	
	private static final String[] mDatas = {
        "LinearLayout--->HORIZONTAL",
        "LinearLayout--->VERTICAL",
        "GridLayout--->HORIZONTAL",
        "GridLayout--->VERTICAL",
        "StaggeredGridLayout--->HORIZONTAL",
        "StaggeredGridLayout--->VERTICAL",
	};
	 private TvRecyclerView mTvList;
	 private MenuAdapter mAdapter;
	 private LinearLayoutManager mManager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	 private void initView() {
	        mTvList = ((TvRecyclerView) findViewById(R.id.trv));
	       // mFocusFrame = ((FocusFrameView) findViewById(R.id.frame));
	       // mTvList.initFrame(mFocusFrame, R.drawable.select_border, -2);
	        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
	        mTvList.setLayoutManager(mManager);
	       
	        mAdapter = new MenuAdapter();
	        mTvList.setAdapter(mAdapter);
	    }

	    class MenuAdapter extends TvRecyclerView.TvAdapter {
	        @Override
	        public TvRecyclerView.TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	            return new MenuHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.item_main_example, parent, false));
	        }

	        @Override
	        public void onBindViewHolder(TvRecyclerView.TvViewHolder holder, int position) {
	          //  ((TextView) holder.itemView).setText(mDatas[position]);
	            //holder.itemView.title.setText(mDatas[position]);
	            
	            TextView   title= (TextView)holder.getView(R.id.tv_example);
	            title.setText(mDatas[position]);
	        }

	        @Override
	        public int getItemCount() {
	            return mDatas.length;
	        }

	        @Override
	        protected Object getData(int start) {
	            return null;
	        }
	    }

	    class MenuHolder extends TvRecyclerView.TvViewHolder {
	    	public  TextView title;
	          
	        public MenuHolder(View view) {
	            super(view);
	           // title = (TextView) view.findViewById(R.id.tv_example);
	           // image = (ImageView) view.findViewById(R.id.image);
	        }
	    }
}
