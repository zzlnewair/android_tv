

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
import com.zzl.recyclerview.TvLayoutManager.Orientation;
import com.zzl.recyclerview.TvRecyclerView;

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.SimpleViewHolder> {
    private static final int COUNT = 30;

    private final Context mContext;
    private final TvRecyclerView mRecyclerView;
    private final List<Integer> mItems;
    private final int mLayoutId;
    private int mCurrentItemId = 0;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final ImageView image;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }

    public LayoutAdapter(Context context, TvRecyclerView recyclerView, int layoutId) {
        mContext = context;
        mItems = new ArrayList<Integer>(COUNT);
        for (int i = 0; i < COUNT; i++) {
            addItem(i);
        }

        mRecyclerView = recyclerView;
        mLayoutId = layoutId;
    }
    
    public void appendDatas() {
        Log.i("@@@@", "appendDatas: ");
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    final int id = mCurrentItemId++;
                    mItems.add(id);
                }
                notifyItemRangeInserted(mCurrentItemId, 20);
            }
        }, 1000);
    }

    public void addItem(int position) {
        final int id = mCurrentItemId++;
        mItems.add(position, id);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.title.setText(mItems.get(position).toString());
       Glide.with(mContext)
               .load("http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1503/17/c2/3974346_1426551981202_mthumb.jpg")
               .into(holder.image);
       
        boolean isVertical = (mRecyclerView.getOrientation() == Orientation.HORIZONTAL);
        final View itemView = holder.itemView;
        final int itemId = mItems.get(position);

       
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
    
    public interface OnItemSelectedListenner {
        void onSelected(View view, int positin);
    }
}
