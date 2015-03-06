package com.metova.selectableflipimageview;


import android.animation.Animator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SelectableFlipImageView extends RelativeLayout {

    // 0 = front, 1 = back
    private int current = 0;
    private ImageView mFront;
    private ImageView mBack;

    private boolean mCheckSelected = false;
    private Drawable mSelectedDrawable = null;
    private Drawable mUnselectedDrawable = null;

    public SelectableFlipImageView(Context context) {
        super(context);
    }

    public SelectableFlipImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelectableFlipImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setFront(ImageView front) {
        mFront = front;
    }

    public void setBack(ImageView back) {
        mBack = back;
    }

    public ImageView getCurrent() {

        switch (current) {

            case 0:

                return mFront;

            case 1:

                return mBack;
        }

        return mFront;
    }

    public void setCurrent(int current) {

        this.current = current;
        performFlip();
    }

    public void flip() {

        this.current = (this.current == 0 ? 1 : 0);
        performFlip();
    }

    public void setCheckSelected(boolean checkSelected) {

        mCheckSelected = checkSelected;

        if(mCheckSelected) {

            if(mSelectedDrawable != null) {

                mBack.setImageDrawable(mSelectedDrawable);
            }
        }
        else {

            if(mUnselectedDrawable != null) {

                mBack.setImageDrawable(mUnselectedDrawable);
            }
        }
    }

    public boolean isCheckSelected() {
        return mCheckSelected;
    }

    public void setSelectedDrawable(Drawable selectedDrawable) {
        mSelectedDrawable = selectedDrawable;
    }

    public void setUnselectedDrawable(Drawable unselectedDrawable) {
        mUnselectedDrawable = unselectedDrawable;
    }

    private void performFlip() {

        if(current == 0) {

            // Flipping to front
            mBack.animate().rotationY(-90f).setDuration(200).setStartDelay(0).setListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                    mBack.setAlpha(0f);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();

            mFront.animate().rotationY(0f).setDuration(200).setStartDelay(200).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                    mFront.setAlpha(1f);
                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
        else if(current == 1) {

            // Flipping to back
            mFront.animate().rotationY(90f).setDuration(200).setStartDelay(0).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                    mFront.setAlpha(0f);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();

            mBack.animate().rotationY(0f).setDuration(200).setStartDelay(200).setListener(new Animator.AnimatorListener() {

                @Override
                public void onAnimationStart(Animator animation) {

                    mBack.setAlpha(1f);
                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
        }
    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();

        mBack = (ImageView) getChildAt(0);
        mFront = (ImageView) getChildAt(1);

        mUnselectedDrawable = mBack.getDrawable();

        if(current == 0) {

            mFront.setAlpha(1f);
            mFront.setRotationY(0f);

            mBack.setAlpha(0f);
            mBack.setRotationY(-90f);
        }
        else {

            mFront.setAlpha(0f);
            mFront.setRotationY(90f);

            mBack.setAlpha(1f);
            mBack.setRotationY(0f);
        }

        setSelected(mCheckSelected);
    }

    // If using EventBus, you can attach it like below
//
//    @Override
//    protected void onAttachedToWindow() {
//
//        EventBus.getDefault().registerSticky(this);
//        super.onAttachedToWindow();
//        // View is now attached
//    }
//
//    @Override
//    protected void onDetachedFromWindow() {
//
//        EventBus.getDefault().unregister(this);
//        super.onDetachedFromWindow();
//        // View is now detached, and about to be destroyed
//    }

    // If you are using an event bus and you want these views to react to
    // an action in a recycling view (like a RecyclerView), you can
    // do something like below
//
//    public void onEvent(NotificationsBeginSelectEvent event) {
//
//        this.current = (event.isSelecting() ? 1 : 0);
//        performFlip();
//    }
//
//    public void onEvent(NotificationClearSelectedEvent event) {
//
//        setCheckSelected(false);
//    }
}
