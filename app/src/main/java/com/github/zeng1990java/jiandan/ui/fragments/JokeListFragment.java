package com.github.zeng1990java.jiandan.ui.fragments;

import com.github.zeng1990java.jiandan.App;
import com.github.zeng1990java.jiandan.adapter.JokeAdapter;
import com.github.zeng1990java.jiandan.adapter.RvLoadmoreAdapter;
import com.github.zeng1990java.jiandan.api.JiandanApi;
import com.github.zeng1990java.jiandan.model.JokeListModel;
import com.github.zeng1990java.jiandan.ui.base.BaseTimelineFragment;
import com.socks.library.KLog;
import com.trello.rxlifecycle.FragmentEvent;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/24 上午7:26
 */
public class JokeListFragment extends BaseTimelineFragment{

    private JokeAdapter mJokeAdapter = new JokeAdapter();

    @Override
    public void onRefresh() {
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
                                setRefreshing(false);
                            }

                            @Override
                            public void onError(Throwable e) {
                                setRefreshing(false);
                            }

                            @Override
                            public void onNext(JokeListModel jokeListModel) {
                                KLog.d(jokeListModel.getStatus() + "; " + jokeListModel.getComments().size());
                                mJokeAdapter.replaceAll(jokeListModel.getComments());
                                setHasMore(jokeListModel.getCurrent_page(), jokeListModel.getPage_count());
                            }
                        }
                );
    }

    @Override
    protected void onLoadmore(int page) {
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
                                setLoadmoreError();
                            }

                            @Override
                            public void onNext(JokeListModel jokeListModel) {
                                mJokeAdapter.addAll(jokeListModel.getComments());
                                setHasMore(jokeListModel.getCurrent_page(), jokeListModel.getPage_count());
                            }
                        }
                );
    }

    @Override
    protected RvLoadmoreAdapter provideAdapter() {
        return mJokeAdapter;
    }
}
