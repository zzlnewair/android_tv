package com.zzl.recyclerview;

import java.lang.reflect.Constructor;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

public class TvRecyclerView extends RecyclerView {

	private static final String LOGTAG = TvRecyclerView.class.getSimpleName()+ ":::";
	
	private static final int DEFAULT_SELECTED_ITEM_OFFSET = 40;
	private static final int DEFAULT_LOAD_MORE_BEFOREHAND_COUNT = 4;

	private int mVerticalSpacingWithMargins = 0;
	private int mHorizontalSpacingWithMargins = 0;

	private int mSelectedItemOffsetStart;
	private int mSelectedItemOffsetEnd;

	private boolean mSelectedItemCentered;
	private boolean mIsBaseLayoutManager;
	private boolean mIsInterceptKeyEvent;
	private boolean mIsSelectFirstVisiblePosition;

	private OnItemListener mOnItemListener;

	private ItemListener mItemListener;
	private boolean mHasFocus = false;
	private int mLoadMoreBeforehandCount;

	private int mPreSelectedPosition = 0;
	private int mSelectedPosition = 0;
	private int mOverscrollValue;
	private int mOffset = -1;

	private static final Class<?>[] sConstructorSignature = new Class[] {
        Context.class, AttributeSet.class};

    private final Object[] sConstructorArgs = new Object[2];


	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 110:
				mHasFocus = true;
				onFocusChanged(mHasFocus, View.FOCUS_DOWN, null);
				break;

			case 111:
				if (getFocusedChild() == null) {
					mHasFocus = false;
					onFocusChanged(mHasFocus, View.FOCUS_DOWN, null);
				}
				break;
			}
		}
	};

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

		final TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.TvRecyclerView, defStyle, 0);

		final String name = a
				.getString(R.styleable.TvRecyclerView_tv_layoutManager);
		if (!TextUtils.isEmpty(name)) {
			loadLayoutManagerFromName(context, attrs, name);
		}
		mSelectedItemCentered = a.getBoolean(
				R.styleable.TvRecyclerView_tv_selectedItemIsCentered, false);
		mIsInterceptKeyEvent = a.getBoolean(
				R.styleable.TvRecyclerView_tv_isInterceptKeyEvent, false);
		// mIsMenu = a.getBoolean(R.styleable.TvRecyclerView_tv_isMenu, false);
		mIsSelectFirstVisiblePosition = a.getBoolean(
				R.styleable.TvRecyclerView_tv_isSelectFirstVisiblePosition,
				false);
		mLoadMoreBeforehandCount = a.getInt(
				R.styleable.TvRecyclerView_tv_loadMoreBeforehandCount,
				DEFAULT_LOAD_MORE_BEFOREHAND_COUNT);
		mSelectedItemOffsetStart = a.getDimensionPixelOffset(
				R.styleable.TvRecyclerView_tv_selectedItemOffsetStart,
				DEFAULT_SELECTED_ITEM_OFFSET);
		mSelectedItemOffsetEnd = a.getDimensionPixelOffset(
				R.styleable.TvRecyclerView_tv_selectedItemOffsetEnd,
				DEFAULT_SELECTED_ITEM_OFFSET);

		a.recycle();
	}

	private void init(Context context) {
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

		mItemListener = new ItemListener() {
			/**
			 * 子控件的焦点变动事件
			 * 
			 * @param itemView
			 * @param hasFocus
			 */
			@Override
			public void onFocusChange(View itemView, boolean hasFocus) {
				// TODO Auto-generated method stub

				mHandler.removeMessages(110);
				mHandler.removeMessages(111);
				if (hasFocus && !mHasFocus) {
					mHandler.sendEmptyMessage(110);
				} else if (!hasFocus && mHasFocus) {
					mHandler.sendEmptyMessageDelayed(111, 10);
				}

				if (null != itemView) {
					final int position = getChildLayoutPosition(itemView);
					itemView.setSelected(hasFocus);
					if (hasFocus) {
						mSelectedPosition = position;
						if (itemView.isActivated()) {
							itemView.setActivated(false);
						}
						if (null != mOnItemListener)
							mOnItemListener.onItemSelected(TvRecyclerView.this,
									itemView, position);
					} else {
						mPreSelectedPosition = position;

						if (null != mOnItemListener)
							mOnItemListener.onItemPreSelected(
									TvRecyclerView.this, itemView, position);
					}
				}

			}

			/**
			 * 子控件的点击事件
			 * 
			 * @param itemView
			 */
			@Override
			public void onClick(View itemView) {
				// TODO Auto-generated method stub
				if (null != mOnItemListener) {
					mOnItemListener.onItemClick(TvRecyclerView.this, itemView,
							getChildLayoutPosition(itemView));
				}
			}
		};

	}

	public interface OnItemListener {
		void onItemPreSelected(TvRecyclerView parent, View itemView,
				int position);

		void onItemSelected(TvRecyclerView parent, View itemView, int position);

		void onReviseFocusFollow(TvRecyclerView parent, View itemView,
				int position);

		void onItemClick(TvRecyclerView parent, View itemView, int position);
	}

	private interface ItemListener extends View.OnClickListener,
			View.OnFocusChangeListener {

	}
	
	
	
	  
    private void loadLayoutManagerFromName(Context context, AttributeSet attrs, String name) {
        try {
            final int dotIndex = name.indexOf('.');
            if (dotIndex == -1) {
                name = "com.owen.tvrecyclerview.widget." + name;
            } else if (dotIndex == 0) {
                final String packageName = context.getPackageName();
                name = packageName + "." + name;
            }

            Class<? extends TvLayoutManager> clazz =
                    context.getClassLoader().loadClass(name).asSubclass(TvLayoutManager.class);

            Constructor<? extends TvLayoutManager> constructor =
                    clazz.getConstructor(sConstructorSignature);

            sConstructorArgs[0] = context;
            sConstructorArgs[1] = attrs;

            setLayoutManager(constructor.newInstance(sConstructorArgs));
        } catch (Exception e) {
            throw new IllegalStateException("Could not load TwoWayLayoutManager from " +
                                             "class: " + name, e);
        }
    }


}

