package com.zzl.view.recyclerview.impl;

import com.zzl.view.recyclerview.RecyclerViewTV;

/**
 *  按键加载更多接口.
 */
public interface PrvInterface {

    void setOnLoadMoreComplete(); // 按键加载更多-->完成.
    void setPagingableListener(RecyclerViewTV.PagingableListener pagingableListener);

}
