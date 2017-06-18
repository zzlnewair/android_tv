package com.droid.activitys.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.droid.R;
import com.droid.bean.AppBean;

import java.util.List;

public class AllAppLayout extends LinearLayout implements View.OnClickListener {

    int iconIds[] = {R.id.app_icon0, R.id.app_icon1, R.id.app_icon2,
            R.id.app_icon3, R.id.app_icon4, R.id.app_icon5,
            R.id.app_icon6, R.id.app_icon7, R.id.app_icon8,
            R.id.app_icon9, R.id.app_icon10, R.id.app_icon11,
            R.id.app_icon12, R.id.app_icon13, R.id.app_icon14};
    static int nameIds[] = {R.id.app_name0, R.id.app_name1, R.id.app_name2,
            R.id.app_name3, R.id.app_name4, R.id.app_name5,
            R.id.app_name6, R.id.app_name7, R.id.app_name8,
            R.id.app_name9, R.id.app_name10, R.id.app_name11,
            R.id.app_name12, R.id.app_name13, R.id.app_name14};
    static int itemIds[] = {
            R.id.app_item0, R.id.app_item1, R.id.app_item2,
            R.id.app_item3, R.id.app_item4, R.id.app_item5,
            R.id.app_item6, R.id.app_item7, R.id.app_item8,
            R.id.app_item9, R.id.app_item10, R.id.app_item11,
            R.id.app_item12, R.id.app_item13, R.id.app_item14
    };
    private Context mContext;

    private ImageView appIcons[] = new ImageView[15];
    private LinearLayout appItems[] = new LinearLayout[15];
    private TextView appNames[] = new TextView[15];
    private List<AppBean> mAppList = null;
    private int mPagerIndex = -1;
    private int mPagerCount = -1;

    public AllAppLayout(Context context) {
        super(context);
        mContext = context;
    }

    public void setAppList(List<AppBean> list, int pagerIndex, int pagerCount) {
        mAppList = list;
        mPagerIndex = pagerIndex;
        mPagerCount = pagerCount;
    }

    public void managerAppInit() {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_pager_layout, this);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        int itemCount;
        if (mPagerIndex < mPagerCount - 1) {
            itemCount = 15;
        } else {
            itemCount = (mAppList.size() - (mPagerCount - 1) * 15);
        }
        for (int i = 0; i < itemCount; i++) {
            appItems[i] = (LinearLayout) v.findViewById(itemIds[i]);
            appIcons[i] = (ImageView) v.findViewById(iconIds[i]);
            appNames[i] = (TextView) v.findViewById(nameIds[i]);

            appItems[i].setVisibility(View.VISIBLE);
            appItems[i].setOnClickListener(this);
            AppBean bean = mAppList.get(mPagerIndex * 15 + i);
            appIcons[i].setImageDrawable(bean.getIcon());
            appNames[i].setText(bean.getName());


            System.out.println("bean = " + bean);
           //appItems[i].setOnFocusChangeListener(focusChangeListener);
        }
    }

    public View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {

            int focus;
            if (hasFocus) {
                focus = R.anim.enlarge;
            } else {
                focus = R.anim.decrease;
            }
//            如果有焦点就放大，没有焦点就缩小
            Animation mAnimation = AnimationUtils.loadAnimation(mContext, focus);
            mAnimation.setBackgroundColor(Color.TRANSPARENT);
            mAnimation.setFillAfter(hasFocus);
            v.startAnimation(mAnimation);
            mAnimation.start();
            v.bringToFront();
        }
    };

    @SuppressLint("NewApi")
    @Override
    public void onClick(View arg0) {
        int id = arg0.getId();
        int position = -1;
        switch (id) {
            case R.id.app_item0:
                position = mPagerIndex * 15;
                break;
            case R.id.app_item1:
                position = mPagerIndex * 15 + 1;
                break;
            case R.id.app_item2:
                position = mPagerIndex * 15 + 2;
                break;
            case R.id.app_item3:
                position = mPagerIndex * 15 + 3;
                break;
            case R.id.app_item4:
                position = mPagerIndex * 15 + 4;
                break;
            case R.id.app_item5:
                position = mPagerIndex * 15 + 5;
                break;
            case R.id.app_item6:
                position = mPagerIndex * 15 + 6;
                break;
            case R.id.app_item7:
                position = mPagerIndex * 15 + 7;
                break;
            case R.id.app_item8:
                position = mPagerIndex * 15 + 8;
                break;
            case R.id.app_item9:
                position = mPagerIndex * 15 + 9;
                break;
            case R.id.app_item10:
                position = mPagerIndex * 15 + 10;
                break;
            case R.id.app_item11:
                position = mPagerIndex * 15 + 11;
                break;
            case R.id.app_item12:
                position = mPagerIndex * 15 + 12;
                break;
            case R.id.app_item13:
                position = mPagerIndex * 15 + 13;
                break;
            case R.id.app_item14:
                position = mPagerIndex * 15 + 14;
                break;
            default:
                break;
        }
        if (position != -1) {
            PackageManager manager = mContext.getPackageManager();
            String packageName = mAppList.get(position).getPackageName();
            Intent intent = manager.getLaunchIntentForPackage(packageName);
            mContext.startActivity(intent);
        }
    }
}