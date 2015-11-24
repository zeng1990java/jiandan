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
import com.github.zeng1990java.jiandan.adapter.JokeAdapter;
import com.github.zeng1990java.jiandan.api.JiandanApi;
import com.github.zeng1990java.jiandan.model.JokeListModel;
import com.github.zeng1990java.jiandan.model.JokeModel;
import com.github.zeng1990java.jiandan.ui.base.BaseFragment;
import com.github.zeng1990java.jiandan.ui.listener.EndlessRecyclerOnScrollListener;
import com.socks.library.KLog;
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
 * @date 15/11/24 上午7:26
 */
public class JokeListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;


    private JokeAdapter mJokeAdapter;
    private EndlessRecyclerOnScrollListener mLoadmoreListener;
    private int mCurrentPage = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_joke_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        KLog.d();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mLoadmoreListener = new EndlessRecyclerOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore() {
                loadMoreJokeList(mCurrentPage+1);
            }
        };

        mRecyclerView.addOnScrollListener(mLoadmoreListener);

        mJokeAdapter = new JokeAdapter(new ArrayList<JokeModel>());

        mRecyclerView.setAdapter(mJokeAdapter);

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

    private void refreshJokeList(){
        KLog.d();
        JiandanApi jiandanApi = App.getApp().getRetrofit().create(JiandanApi.class);
        jiandanApi.loadJokeList(1)
                .compose(this.<JokeListModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<JokeListModel>() {
                            @Override
                            public void onCompleted() {
                                KLog.d();
                                mRefreshLayout.setRefreshing(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                mRefreshLayout.setRefreshing(false);
                            }

                            @Override
                            public void onNext(JokeListModel jokeListModel) {
                                KLog.d(jokeListModel.getStatus() + "; " + jokeListModel.getComments().size());
                                mJokeAdapter.replaceAll(jokeListModel.getComments());
                                setHasMore(jokeListModel);
                            }
                        }
                );
    }

    private void loadMoreJokeList(int page){
        KLog.d();
        JiandanApi jiandanApi = App.getApp().getRetrofit().create(JiandanApi.class);
        jiandanApi.loadJokeList(page)
                .compose(this.<JokeListModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<JokeListModel>() {
                            @Override
                            public void onCompleted() {
                                KLog.d();
                            }

                            @Override
                            public void onError(Throwable e) {
                            }

                            @Override
                            public void onNext(JokeListModel jokeListModel) {
                                KLog.d(jokeListModel.getStatus()+"; "+jokeListModel.getComments().size());
                                mJokeAdapter.addAll(jokeListModel.getComments());
                                setHasMore(jokeListModel);
                            }
                        }
                );
    }

    private void setHasMore(JokeListModel jokeListModel){
        mCurrentPage = jokeListModel.getCurrent_page();
        mLoadmoreListener.setHasMore(jokeListModel.getCurrent_page() < jokeListModel.getPage_count());
        mJokeAdapter.setHasMore(jokeListModel.getCurrent_page() < jokeListModel.getPage_count());
    }

    @Override
    public void onRefresh() {
        KLog.d();
        refreshJokeList();
    }
}
