package com.zzl.recyclerviewtv.demo.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zzl.recyclerviewtv.demo.R;
import com.zzl.view.adapter.GeneralAdapter;
import com.zzl.view.mode.OpenPresenter;
import com.zzl.view.mode.OpenPresenter.ViewHolder;

public class StaggeredGridPresenter extends OpenPresenter {

	    private final List<String> labels;
	    private GeneralAdapter mAdapter;
        private  int orientation =0;
	    public StaggeredGridPresenter(int count,int orientation) {
	        this.labels = new ArrayList<String>(count);
	        this.orientation = orientation ;
	        for (int i = 0; i < count; i++) {
	            labels.add(String.valueOf(i));
	        }
	    }

	    @Override
	    public void setAdapter(GeneralAdapter adapter) {
	        this.mAdapter = adapter;
	    }

	    /**
	     * 用于数据加载更多测试.
	     */
	    public void addDatas(int count) {
	        int sum = labels.size();
	        for (int i = sum; i < sum + count; i++) {
	            labels.add(String.valueOf(i));
	        }
	        this.mAdapter.notifyDataSetChanged();
	    }

	    @Override
	    public int getItemCount() {
	        return labels.size();
	    }

	    @Override
	    public int getItemViewType(int position) {
	        return 0;
	    }

	    @Override
	    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
	        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staggered, parent, false);
	        return new GridViewHolder(view);
	    }

	    @Override
	    public void onBindViewHolder(ViewHolder viewHolder, int position) {
	       
	    	changeSize(viewHolder.view, position);
	    	
	    	
	    	GridViewHolder gridViewHolder = (GridViewHolder) viewHolder;
	        TextView textView = (TextView) gridViewHolder.tv;
	        textView.setText("item " + labels.get(position));
	    }

	    
	    public void changeSize(View itemView, int position) {
	        StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
	        if (orientation == StaggeredGridLayoutManager.HORIZONTAL) {
	            if (position == 0 || position == 4) {
	                lp.height =  320;
	                lp.width =  220;
	                lp.setFullSpan(true);
	            } else if (position == 1) {
	                lp.height = 200;
	                lp.width =  320;
	                lp.setFullSpan(false);
	            } else {
	                lp.height =  200;
	                lp.width = 200;
	                lp.setFullSpan(false);
	            }
	        } else {
	            if (position == 0) {
	                lp.width = StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT;
	                lp.height =  266;
	                lp.setFullSpan(true);
	            } else if (position ==1|| position == 4 || position ==9) {
	                lp.width =  200;
	                lp.height = 420;
	                lp.setFullSpan(false);
	            } else {
	                lp.width = 200;
	                lp.height = 200;
	                lp.setFullSpan(false);
	            }
	        }
	        itemView.setLayoutParams(lp);
	    }
	    
	   
}
