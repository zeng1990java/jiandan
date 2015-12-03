package com.github.zeng1990java.jiandan.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import com.github.zeng1990java.jiandan.R;
import com.socks.library.KLog;

/**
 * $desc
 *
 * @author zxb
 * @date 15/12/2 下午9:50
 */
public class SwipeBackLayout extends FrameLayout {

    public interface Callback {
        void onSwipeStart();
        void onSwipeProgress(float progress);
        void onSwipeCancel();
        void onSwipeComplete();
    }


    private ViewDragHelper mDragHelper;
    private int mMinimumFlingVelocity;
    private SwipeBackLayout.Callback mCallback;
    private boolean back = false;
    private Drawable mShadowLeft;
    private Rect mTempRect = new Rect();

    public SwipeBackLayout(Context context) {
        this(context, null);
    }

    public SwipeBackLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeBackLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mDragHelper = ViewDragHelper.create(this, 1.0f / 8.0f, new ViewDragCallback());
        mMinimumFlingVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
        mShadowLeft = ContextCompat.getDrawable(context, R.drawable.ic_shadow_left);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }else {
            if (back){
                if (mCallback != null){
                    mCallback.onSwipeComplete();
                }
            }
        }
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {

        boolean ret = super.drawChild(canvas, child, drawingTime);

        if (mDragHelper.getViewDragState() != ViewDragHelper.STATE_IDLE){
            final Rect childRect = mTempRect;
            child.getHitRect(childRect);
            KLog.d(child.getClass().getSimpleName());
            KLog.d(childRect.toShortString());
            mShadowLeft.setBounds(childRect.left - mShadowLeft.getIntrinsicWidth(),
                                  childRect.top,
                                  childRect.left,
                                  childRect.bottom
                                  );
            mShadowLeft.draw(canvas);
        }

        return ret;
    }

    class ViewDragCallback extends ViewDragHelper.Callback{

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return Math.max(0, left);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            return getWidth();
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
            // start dragging
            if (mCallback != null){
                mCallback.onSwipeStart();
            }
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
            float progress = Math.max(0, left) / getWidth();
            // dragging progress
            if (mCallback != null){
                mCallback.onSwipeProgress(progress);
            }
            // for draw shadow left: drawChild method will be call
            invalidate();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            int minDragRange = xvel > mMinimumFlingVelocity ? getWidth() / 6 : getWidth() / 3;
            if (releasedChild.getLeft() > minDragRange){
                // back
                mDragHelper.settleCapturedViewAt(getWidth(), releasedChild.getTop());
                back = true;
            }else {
                // cancel
                if (mCallback != null) {
                    mCallback.onSwipeCancel();
                }

                    // reset
                mDragHelper.settleCapturedViewAt(0, releasedChild.getTop());
            }
            ViewCompat.postInvalidateOnAnimation(SwipeBackLayout.this);
        }
    }
}
