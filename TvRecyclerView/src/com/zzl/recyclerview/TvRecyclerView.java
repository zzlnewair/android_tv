package com.zzl.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class TvRecyclerView extends RecyclerView  {

	public TvRecyclerView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	  public TvRecyclerView(Context context, AttributeSet attrs) {
	        this(context, attrs, 0);
	    }
	
	  
	  public TvRecyclerView(Context context, AttributeSet attrs, int defStyle) {
	        super(context, attrs, defStyle);
	        
	        init(context);
	        
	     
	    }
	  
	  
	  private void init(Context context){
	        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
	        setChildrenDrawingOrderEnabled(true);
	        setWillNotDraw(true); // 自身不作onDraw处理
	        setHasFixedSize(true);
	        setOverScrollMode(View.OVER_SCROLL_NEVER);

	        setClipChildren(false);
	        setClipToPadding(false);

	        setClickable(false);
	        setFocusable(true);
	        setFocusableInTouchMode(true);
	        
	       
	    }
}

