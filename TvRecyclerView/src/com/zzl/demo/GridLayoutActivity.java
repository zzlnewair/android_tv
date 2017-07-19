/**   
* @Title: GridLayoutActivity.java 
* @Package com.zzl.demo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhangzhl  
* @date 2017-7-19 下午12:36:47 
* @version V1.0   
*/ 
package com.zzl.demo;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.GridLayoutManager;
/**
 
 * @ClassName: GridLayoutActivity 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author
 * 
 * 
 */
public class GridLayoutActivity extends BaseActivity{

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	 
	 
	@Override
	protected LayoutManager getLayoutManager() {
		// TODO Auto-generated method stub
		 if ("0".equals(mIsVertical)) {
	            return new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
	        } else {
	            return new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
	        }
		 
		 
	}

}
