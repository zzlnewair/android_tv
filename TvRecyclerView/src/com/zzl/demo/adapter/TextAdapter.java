

package com.zzl.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.zzl.recyclerview.R;


public class TextAdapter extends RecyclerView.Adapter<TextAdapter.SimpleViewHolder> {
    private static final int COUNT = 30;

    private final Context mContext;
    private List<String> mlist ;
    
    private  int mLayoutId;
    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
      
        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_example);
            
        }
    }

    public TextAdapter(Context context, List<String> mlist ,int layoutId) {
        this.mContext = context;
        this.mlist = mlist;
        this.mLayoutId = layoutId;
        
    }
    
    

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.title.setText(mlist.get(position).toString());


       
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    
  
}
