package com.github.zeng1990java.jiandan.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.zeng1990java.jiandan.R;
import com.github.zeng1990java.jiandan.adapter.RvLoadmoreAdapter;
import com.github.zeng1990java.jiandan.ui.listener.EndlessRecyclerOnScrollListener;
import com.github.zeng1990java.jiandan.view.ColorSwipeRefreshLayout;
import com.github.zeng1990java.jiandan.view.LoadmoreRecyclerView;

import butterknife.Bind;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/25 下午4:48
 */
public abstract class BaseTimelineFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.swipe_refresh_layout)
    ColorSwipeRefreshLayout mRefreshLayout;
    @Bind(R.id.recycler_view)
    LoadmoreRecyclerView mRecyclerView;

    private RvLoadmoreAdapter mLoadmoreAdapter;
    private int mCurrentPage = 1;

    @Override
    protected int provideLayoutId() {
        return R.layout.content_timeline;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLoadmoreListener(new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                onLoadmore(mCurrentPage + 1);
            }
        });

        mLoadmoreAdapter = provideAdapter();

        mRecyclerView.setAdapter(mLoadmoreAdapter);

        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(true);
                        onRefresh();
                    }
                }
                , 345);
    }

    public void setRefreshing(boolean refreshing){
        mRefreshLayout.setRefreshing(refreshing);
    }

    public void setHasMore(int currentPage, int pageCount){
        mCurrentPage = currentPage;
        mRecyclerView.setHasMore(mCurrentPage < pageCount);
    }

    public void setLoadmoreError(){
        mRecyclerView.setLoadmoreError();
    }

    protected abstract void onLoadmore(int page);
    protected abstract RvLoadmoreAdapter provideAdapter();
}
