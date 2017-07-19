package com.zzl.demo;

import com.zzl.recyclerview.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;



public class StaggeredGridActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        if ("0".equals(mIsVertical)) {
            return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        } else {
            ViewGroup.LayoutParams layoutParams =
                    mTvRecyclerView.getLayoutParams();
            layoutParams.width = dpToPx(this, 886f);
            mTvRecyclerView.setLayoutParams(layoutParams);
            return new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        }
    }

    @Override
    protected int getContentResource() {
        return R.layout.activity_staggered_layout;
    }

   
    @Override
	public void changeSize(View itemView, int position) {
        StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) itemView.getLayoutParams();
        if ("0".equals(mIsVertical)) {
            if (position == 0 || position == 4) {
                lp.height = dpToPx(this, 420);
                lp.width = dpToPx(this, 220);
                lp.setFullSpan(true);
            } else if (position == 1) {
                lp.height = dpToPx(this, 200);
                lp.width = dpToPx(this, 420);
                lp.setFullSpan(false);
            } else {
                lp.height = dpToPx(this, 200);
                lp.width = dpToPx(this, 200);
                lp.setFullSpan(false);
            }
        } else {
            if (position == 0) {
                lp.width = StaggeredGridLayoutManager.LayoutParams.MATCH_PARENT;
                lp.height = dpToPx(this, 266);
                lp.setFullSpan(true);
            } else if (position ==1|| position == 4 || position ==9) {
                lp.width = dpToPx(this, 200);
                lp.height = dpToPx(this, 420);
                lp.setFullSpan(false);
            } else {
                lp.width = dpToPx(this, 200);
                lp.height = dpToPx(this, 200);
                lp.setFullSpan(false);
            }
        }
        itemView.setLayoutParams(lp);
    }
    
    public static int dpToPx(Context ctx, float dp) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int pxToDp(Context ctx, float px) {
        final float scale = ctx.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

}
