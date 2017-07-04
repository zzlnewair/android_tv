package com.open.androidtvwidget.leanback.adapter;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.State;
import android.util.Log;
import android.view.View;

/**
 * 此ItemDecoration可以很好的适配LinearLayoutManaager
 * GridLayoutManager、StaggeredGridLayoutManager
 * 可以设置颜色和Drawable
 *
 *
 */
public class MyItemDecoration extends ItemDecoration {
	/**
	 * RecyclerView 显示方向
	 *
	 *
	 */
	public enum OrientationType{
		VERTICAL, HORIZONTAL
	}
	/**
	 * divider Drawable
	 */
	private Drawable dividerDrawable;
	/**
	 * divider宽度
	 */
	private int dividerHeight = 10;
	
	/**
	 * divider宽度
	 */
	private int dividerWidth = 10;
	
	/**
	 * divider颜色
	 */
	private int dividerColor = Color.GRAY;
	/**
	 * 方向
	 */
	private OrientationType orientationType = OrientationType.VERTICAL;
	/**
	 * LayoutManager 类型
	 */
	private LayoutManager layoutType;
	/**
	 * 绘制颜色画笔
	 */
	private Paint mPaint;
	
	public MyItemDecoration() {
		mPaint = new Paint();
	}
	
	public MyItemDecoration(Drawable dividerDrawable, int dividerHeight,int dividerWidth,
			OrientationType orientationType) {
		this();
		setDividerDrawable(dividerDrawable);
		setDividerHeight(dividerHeight);
		setDividerWidth(dividerWidth);
		setOrientationType(orientationType);
	}

	public MyItemDecoration(int dividerColor, int dividerHeight, int dividerWidth,
			OrientationType orientationType) {
		this();
		setDividerHeight(dividerHeight);
		setDividerWidth(dividerWidth);
		setOrientationType(orientationType);
		setDividerColor(dividerColor);
	}

	public void setDividerDrawable(Drawable dividerDrawable) {
		this.dividerDrawable = dividerDrawable;
	}

	public void setDividerHeight(int dividerHeight) {
		this.dividerHeight = dividerHeight;
	}
	
	public void setDividerWidth(int dividerWidth) {
		this.dividerWidth = dividerWidth;
	}

	public void setDividerColor(int dividerColor) {
		this.dividerColor = dividerColor;
		mPaint.setColor(dividerColor);
	}

	public void setOrientationType(OrientationType orientationType) {
		this.orientationType = orientationType;
	}

	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
			State state) {
		super.getItemOffsets(outRect, view, parent, state);
		layoutType = parent.getLayoutManager();
		int itemPosition = parent.getChildPosition(view);
		
		if(layoutType instanceof GridLayoutManager || layoutType instanceof StaggeredGridLayoutManager){
			//通过判断Item是否是最右/最小侧的方式，来实现不绘制右侧/下侧边界的实现方式
			//存在一个很致命的bug，虽然最右侧的Item不会绘制右边界，但是相应的item会自动
			//向右侧扩展，将原先边界的区域填满，这样就会导致最右侧一列的Item比其他的要宽（边界的宽度）
			//最下侧的一行同理也是一样的，暂时没找到好的解决方式。。。。
			
//			if(isLastColum(parent, itemPosition)){
//				//最下面一列不绘制  下边界
//				outRect.set(0, 0, dividerHeight, 0);
//			} else if(isLastRaw(parent, itemPosition)){
//				//最右侧一行不绘制  右边界
//				outRect.set(0, 0, 0, dividerHeight);
//			} else{
//				//中间部分的Item 绘制下边界和右边界
//				outRect.set(0, 0, dividerHeight, dividerHeight);
//			}
			
				outRect.set(0, 0, dividerWidth, dividerHeight);
			
			return;
		}
		
		//LayoutManager 为LinearLayoutManager时
		if(orientationType == OrientationType.VERTICAL){
			outRect.set(0, 0, 0, dividerHeight);
		} else{
			outRect.set(0, 0, dividerHeight, 0);
		}
	}

	/**
	 * 判断View是否是最右侧一行
	 * @param parent
	 * @param position
	 * @return
	 */
	private boolean isLastRaw(RecyclerView parent, int position){
		int spanCount = getSpan(parent);
		int itemCount = parent.getAdapter().getItemCount();

		if(orientationType == OrientationType.VERTICAL){
			//RecyclerView竖直排列时，（position + 1）为列数的整数倍时，此Item在最右侧
			if((position + 1) % spanCount == 0){
				return true;
			} 
		} else{
			//RecyclerView水平排列，最后一列的几个Item为最右侧的Item
			//    也就是当(Item总数 % 行数)时，余数为最后一列Item的个数
			if(position >= itemCount - (itemCount % spanCount)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 是否是最底部一列
	 * @param parent
	 * @param position
	 * @return
	 */
	private boolean isLastColum(RecyclerView parent, int position){
		int spanCount = getSpan(parent);
		int itemCount = parent.getAdapter().getItemCount();
		
		if(orientationType == OrientationType.VERTICAL){
			//RecyclerView垂直排列，最后一行的几个Item为最底部的Item
			//    也就是当(Item总数 % 行数)时，余数为最后一行Item的个数
			if(position >= itemCount - (itemCount % spanCount)){
				return true;
			}
		} else{
			//RecyclerView水平排列时，（position + 1）为列数的整数倍时，此Item在最底部
			if((position + 1) % spanCount == 0){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 获取RecyclerView SpanCount
	 * @param parent
	 * @return
	 */
	private int getSpan(RecyclerView parent){
		int spanCount = -1;
		LayoutManager manager = parent.getLayoutManager();
		if(manager instanceof GridLayoutManager){
			spanCount = ((GridLayoutManager)manager).getSpanCount();
		} else if(manager instanceof StaggeredGridLayoutManager){
			spanCount = ((StaggeredGridLayoutManager)manager).getSpanCount();
		}
		return spanCount;
	}
	
	@Override
	public void onDraw(Canvas c, RecyclerView parent, State state) {
		
		super.onDraw(c, parent, state);
		layoutType = parent.getLayoutManager();
		
		if(layoutType instanceof GridLayoutManager || layoutType instanceof StaggeredGridLayoutManager){
			drawGridLayoutDivider(c, parent, state);
			return;
		}
		
		if(orientationType == OrientationType.VERTICAL){
			drawVerticalLinearLayoutDivider(c, parent, state);
		} else{
			drawHorizontalLinearLayoutDivider(c, parent, state);
		}
	}
	
	/**
	 * 为GridLayout及StaggeredGridLayout绘制边界
	 * @param c
	 * @param parent
	 * @param state
	 */
	private void drawGridLayoutDivider(Canvas c, RecyclerView parent, State state){
		for(int i = 0; i < parent.getChildCount(); i ++){
			View childView = parent.getChildAt(i);
			LayoutParams params = (LayoutParams) childView.getLayoutParams();
			int leftH = childView.getLeft();
			int rightH = childView.getRight() + dividerHeight;
			int topH = childView.getBottom();
			int bottomH = topH + dividerHeight;
			
			if(dividerDrawable != null){
				dividerDrawable.setBounds(leftH, topH, rightH, bottomH);
				dividerDrawable.draw(c);
			} else{
				c.drawRect(leftH, topH, rightH, bottomH, mPaint);
			}
			
			int leftV = childView.getRight();
			int rightV = leftV + dividerHeight;
			int topV = childView.getTop();
			int bottomV = childView.getBottom();
			
			if(dividerDrawable != null){
				dividerDrawable.setBounds(leftV, topV, rightV, bottomV);
				dividerDrawable.draw(c);
			} else{
				c.drawRect(leftV, topV, rightV, bottomV, mPaint);
			}
		}
	}
	
	
	/**
	 * 这个是LayoutManger 为Vertical时  画横线 横线 横线的
	 * @param c
	 * @param parent
	 * @param state
	 */
	private void drawVerticalLinearLayoutDivider(Canvas c, RecyclerView parent, State state){
		//left、right、top、bottom都是相对与父控件的 
		int left = parent.getPaddingLeft();
		int right = parent.getWidth() - parent.getPaddingRight();
		for(int i = 0; i < parent.getChildCount(); i ++){
			View childView = parent.getChildAt(i);
			LayoutParams params = (LayoutParams) childView.getLayoutParams();
			int top = childView.getBottom() + params.bottomMargin;
			int bottom = top + dividerHeight;
			if(dividerDrawable != null){
				dividerDrawable.setBounds(left, top, right, bottom);
				dividerDrawable.draw(c);
			} else{
				c.drawRect(left, top, right, bottom, mPaint);
			}
		}
	}

	/**
	 * 这个是LayoutManger 为Horizontal时画竖线 竖线 竖线的
	 * @param c
	 * @param parent
	 * @param state
	 */
	private void drawHorizontalLinearLayoutDivider(Canvas c, RecyclerView parent, State state){
		int top = parent.getPaddingTop();
		int bottom = parent.getHeight() - parent.getPaddingBottom();
		for(int i = 0; i < parent.getChildCount(); i ++){
			View childView = parent.getChildAt(i);
			LayoutParams params = (LayoutParams) childView.getLayoutParams();
			int left = childView.getRight() + params.rightMargin;
			int right = left + dividerHeight;
			if(dividerDrawable != null){
				dividerDrawable.setBounds(left, top, right, bottom);
				dividerDrawable.draw(c);
			} else{
				c.drawRect(left, top, right, bottom, mPaint);
			}
		}
	}
	
	@Override
	public void onDrawOver(Canvas c, RecyclerView parent, State state) {
		
		super.onDrawOver(c, parent, state);
	}
}
