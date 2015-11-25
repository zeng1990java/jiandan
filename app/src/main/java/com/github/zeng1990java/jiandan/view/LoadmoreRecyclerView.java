package com.github.zeng1990java.jiandan.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.github.zeng1990java.jiandan.adapter.RvLoadmoreAdapter;
import com.github.zeng1990java.jiandan.ui.listener.EndlessRecyclerOnScrollListener;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/25 下午2:30
 */
public class LoadmoreRecyclerView extends RecyclerView {

    private RvLoadmoreAdapter mLoadmoreAdapter;
    private EndlessRecyclerOnScrollListener mLoadmoreListener;

    public LoadmoreRecyclerView(Context context) {
        this(context, null);
    }

    public LoadmoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadmoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (!(adapter instanceof RvLoadmoreAdapter)){
            throw new IllegalStateException("adapter must extends from RvLoadmoreAdapter.");
        }
        mLoadmoreAdapter = (RvLoadmoreAdapter) adapter;
        super.setAdapter(adapter);
    }

    public EndlessRecyclerOnScrollListener getLoadmoreListener() {
        return mLoadmoreListener;
    }

    public void setLoadmoreListener(EndlessRecyclerOnScrollListener loadmoreListener) {
        if (mLoadmoreListener != null){
            removeOnScrollListener(mLoadmoreListener);
        }
        mLoadmoreListener = loadmoreListener;
        addOnScrollListener(mLoadmoreListener);
    }

    public void setLoadmoreError(){
        mLoadmoreListener.reset();
        mLoadmoreListener.setHasMore(true);
    }

    public void setHasMore(boolean hasMore){
        mLoadmoreListener.setHasMore(hasMore);
        mLoadmoreAdapter.setHasMore(hasMore);
    }
}
