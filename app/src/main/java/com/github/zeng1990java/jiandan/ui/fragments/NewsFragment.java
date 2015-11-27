package com.github.zeng1990java.jiandan.ui.fragments;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.github.zeng1990java.jiandan.adapter.NewsAdapter;
import com.github.zeng1990java.jiandan.adapter.RvLoadmoreAdapter;
import com.github.zeng1990java.jiandan.model.NewsListModel;
import com.github.zeng1990java.jiandan.ui.base.BaseTimelineFragment;
import com.trello.rxlifecycle.FragmentEvent;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/26 上午11:35
 */
public class NewsFragment extends BaseTimelineFragment {

    private NewsAdapter mNewsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNewsAdapter = new NewsAdapter(Glide.with(this));
    }

    @Override
    protected void onLoadmore(final int page) {
        getJiandanApi().loadNewsList(page)
                .compose(this.<NewsListModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsListModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        setLoadmoreError();
                    }

                    @Override
                    public void onNext(NewsListModel newsListModel) {
                        mNewsAdapter.addAll(newsListModel.getPosts());
                        setHasMore(page, newsListModel.getPages());
                    }
                });
    }

    @Override
    protected RvLoadmoreAdapter provideAdapter() {
        return mNewsAdapter;
    }

    @Override
    public void onRefresh() {
        getJiandanApi().loadNewsList(1)
                .compose(this.<NewsListModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsListModel>() {
                    @Override
                    public void onCompleted() {
                        setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        setRefreshing(false);
                    }

                    @Override
                    public void onNext(NewsListModel newsListModel) {
                        mNewsAdapter.replaceAll(newsListModel.getPosts());
                        setHasMore(1, newsListModel.getPages());
                    }
                });
    }
}
