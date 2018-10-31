package com.open.androidtvwidget.view;

import com.open.androidtvwidget.R;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;


public class FocusMenuRelativeLayout extends RelativeLayout {
	private AnimatorSet startAnimSet;
	private AnimatorSet endAnimSet;

	private Drawable focusedBackground;
	private Drawable defaultBackGround;

	private Context context;
	public FocusMenuRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		this.context = context;
		this.setFocusable(true);
		this.setFocusableInTouchMode(true);
		this.setPadding(getResources().getDimensionPixelSize(R.dimen.focus_rel_padding),
				getResources().getDimensionPixelSize(R.dimen.focus_rel_padding),
				getResources().getDimensionPixelSize(R.dimen.focus_rel_padding),
				getResources().getDimensionPixelSize(R.dimen.focus_rel_padding));

		final TypedArray a = context.obtainStyledAttributes(
				attrs, R.styleable.FocusMenuRelativeLayout);
		try {
			defaultBackGround = a.getDrawable(R.styleable.FocusMenuRelativeLayout_defaultBackground);
			focusedBackground = a.getDrawable(R.styleable.FocusMenuRelativeLayout_focusedBackground);

//			int startAnimSet = a.getResourceId(R.styleable.FocusMenuRelativeLayout_startAnimatorSet,0);
//			setStartAnimatorSet(startAnimSet);
//			int endAnimSet = a.getResourceId(R.styleable.FocusMenuRelativeLayout_endAnimatorSet,0);
//			setEndAnimatorSet(endAnimSet);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			a.recycle();
		}

		if(defaultBackGround != null)
			this.setBackgroundDrawable(defaultBackGround);
	}

	public FocusMenuRelativeLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FocusMenuRelativeLayout(Context context) {
		this(context, null);
	}

	public void setStartAnimatorSet(int set){
		startAnimSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, set);
	}

	public void setEndAnimatorSet(int set){
		endAnimSet = (AnimatorSet) AnimatorInflater.loadAnimator(context, set);
	}

	public void setAnimator(int startSet, int endSet){
		setStartAnimatorSet(startSet);
		setEndAnimatorSet(endSet);
	}

	public void setFocusedBackground(int resource){
		focusedBackground = context.getResources().getDrawable(resource);
	}

	public void setDefaultBackGround(int resource){
		defaultBackGround = context.getResources().getDrawable(resource);
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		if (gainFocus) {
			startAnim();
			if(focusedBackground != null)
				this.setBackgroundDrawable(focusedBackground);
		} else {
			stopAnim();
			if(defaultBackGround != null)
				this.setBackgroundDrawable(defaultBackGround);
		}
	}
	
	@SuppressLint("NewApi")
	public void startAnim(){
		this.bringToFront();
		if(startAnimSet == null){
//		this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
			ObjectAnimator animX = ObjectAnimator.ofFloat(this, "scaleX", 1.05f);
			ObjectAnimator animY = ObjectAnimator.ofFloat(this, "scaleY", 1.05f);

			startAnimSet = new AnimatorSet();
			startAnimSet.setDuration(200);//.before(animTran)
//			DecelerateInterpolator   OvershootInterpolator
			startAnimSet.setInterpolator(new DecelerateInterpolator());
			startAnimSet.play(animX).with(animY);
		}


//		setLarger.playTogether(animX,animY);
		startAnimSet.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				
			}
		});
//		setLarger.setInterpolator(new BounceInterpolator());
		startAnimSet.start();
	}
	
	
	@SuppressLint("NewApi")
	public void stopAnim(){
		if(endAnimSet == null){
//		this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
			ObjectAnimator animX = ObjectAnimator.ofFloat(this, "scaleX", 1.0f);
			ObjectAnimator animY = ObjectAnimator.ofFloat(this, "scaleY", 1.0f);
			endAnimSet = new AnimatorSet();
			endAnimSet.playTogether(animX,animY);
			endAnimSet.setDuration(200);
		}
//		set.setInterpolator(new BounceInterpolator());
		endAnimSet.start();
	}
}
