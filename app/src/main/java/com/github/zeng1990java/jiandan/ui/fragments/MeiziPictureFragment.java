package com.github.zeng1990java.jiandan.ui.fragments;

import android.support.v4.widget.SwipeRefreshLayout;

import com.github.zeng1990java.jiandan.adapter.MeiziAdapter;
import com.github.zeng1990java.jiandan.adapter.RvLoadmoreAdapter;
import com.github.zeng1990java.jiandan.model.PictureListModel;
import com.github.zeng1990java.jiandan.ui.base.BaseTimelineFragment;
import com.trello.rxlifecycle.FragmentEvent;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * $desc
 *
 * @author zxb
 * @date 15/11/24 下午11:13
 */
public class MeiziPictureFragment extends BaseTimelineFragment implements SwipeRefreshLayout.OnRefreshListener {


    private MeiziAdapter mMeiziAdapter = new MeiziAdapter();

    @Override
    public void onRefresh() {
        getJiandanApi().loadMeiziPictureList(1)
                .compose(this.<PictureListModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PictureListModel>() {
                    @Override
                    public void onCompleted() {
                        setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        setRefreshing(false);
                    }

                    @Override
                    public void onNext(PictureListModel pictureListModel) {
                        mMeiziAdapter.replaceAll(pictureListModel.getComments());
                        setHasMore(pictureListModel.getCurrent_page(), pictureListModel.getPage_count());
                    }
                });

    }


    @Override
    protected void onLoadmore(int page) {
        getJiandanApi().loadMeiziPictureList(page)
                .compose(this.<PictureListModel>bindUntilEvent(FragmentEvent.DESTROY_VIEW))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PictureListModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        setLoadmoreError();
                    }

                    @Override
                    public void onNext(PictureListModel pictureListModel) {
                        mMeiziAdapter.addAll(pictureListModel.getComments());
                        setHasMore(pictureListModel.getCurrent_page(), pictureListModel.getPage_count());
                    }
                });
    }

    @Override
    protected RvLoadmoreAdapter provideAdapter() {
        return mMeiziAdapter;
    }
}
