package com.open.demo.adapter;

import java.util.ArrayList;
import java.util.List;

import com.open.demo.R;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
 /*
     * 
     * 左侧标题栏
     */
    public class ListViewAdapter extends BaseAdapter {
        private LayoutInflater mInflater = null;
        
        private List<String> mSubjectTitles = new ArrayList<String>();

        public ListViewAdapter(Context context,List<String> mSubjectTitles) {
            this.mInflater = LayoutInflater.from(context);
            this.mSubjectTitles=mSubjectTitles;
        }

        @Override
        public int getCount() {
            return mSubjectTitles.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            // 如果缓存convertView为空，则需要创建View
            if (convertView == null) {
                holder = new ViewHolder();
                // 根据自定义的Item布局加载布局
                convertView = mInflater.inflate(R.layout.item3, null);
                holder.subjectTitleTextView = (TextView) convertView.findViewById(R.id.subjectTitleTextView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
          
          
            holder.subjectTitleTextView.setText(mSubjectTitles.get(position));

            return convertView;
        }
        
        
        // ViewHolder静态类
        static class ViewHolder {
            public TextView subjectTitleTextView;
        }
        

    }