package com.github.zeng1990java.jiandan.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.zeng1990java.jiandan.App;
import com.github.zeng1990java.jiandan.R;
import com.github.zeng1990java.jiandan.adapter.MeiziAdapter;
import com.github.zeng1990java.jiandan.api.JiandanApi;
import com.github.zeng1990java.jiandan.model.JokeListModel;
import com.github.zeng1990java.jiandan.model.PictureListModel;
import com.github.zeng1990java.jiandan.model.PictureModel;
import com.github.zeng1990java.jiandan.ui.base.BaseFragment;
import com.github.zeng1990java.jiandan.ui.listener.EndlessRecyclerOnScrollListener;
import com.trello.rxlifecycle.FragmentEvent;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * $desc
 *
 * @author zxb
 * @date 15/11/24 下午11:13
 */
public class MeiziPictureFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private MeiziAdapter mMeiziAdapter;
    private EndlessRecyclerOnScrollListener mLoadmoreListener;
    private int mCurrentPage = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_meizi_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        ButterKnife.bind(this, view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mLoadmoreListener = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                loadMore(mCurrentPage+1);
            }
        };

        mMeiziAdapter = new MeiziAdapter();
        mRecyclerView.setAdapter(mMeiziAdapter);

        mRecyclerView.addOnScrollListener(mLoadmoreListener);

        mRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorAccent));
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

    @Override
    public void onRefresh() {
        JiandanApi jiandanApi = App.getApp().getRetrofit().create(JiandanApi.class);
        jiandanApi.loadMeiziPictureList(1)
                .compose(this.<PictureListModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PictureListModel>() {
                    @Override
                    public void onCompleted() {
                        mRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(PictureListModel pictureListModel) {
                        mMeiziAdapter.replaceAll(pictureListModel.getComments());
                        setHasMore(pictureListModel);
                    }
                });

    }

    private void loadMore(int page){
        JiandanApi jiandanApi = App.getApp().getRetrofit().create(JiandanApi.class);
        jiandanApi.loadMeiziPictureList(page)
                .compose(this.<PictureListModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PictureListModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mLoadmoreListener.reset();
                        mLoadmoreListener.setHasMore(true);
                    }

                    @Override
                    public void onNext(PictureListModel pictureListModel) {
                        mMeiziAdapter.replaceAll(pictureListModel.getComments());
                        setHasMore(pictureListModel);
                    }
                });
    }

    private void setHasMore(PictureListModel jokeListModel){
        mCurrentPage = jokeListModel.getCurrent_page();
        mLoadmoreListener.setHasMore(jokeListModel.getCurrent_page() < jokeListModel.getPage_count());
        mMeiziAdapter.setHasMore(jokeListModel.getCurrent_page() < jokeListModel.getPage_count());
    }
}
