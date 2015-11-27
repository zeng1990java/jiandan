package com.github.zeng1990java.jiandan.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.zeng1990java.jiandan.R;
import com.github.zeng1990java.jiandan.model.NewsModel;
import com.github.zeng1990java.jiandan.ui.NewsDetailActivity;
import com.github.zeng1990java.jiandan.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author zxb
 * @date 2015/11/15/11/27 下午7:55
 */
public class NewsAdapter extends RvLoadmoreAdapter<NewsModel, NewsAdapter.ViewHolder> {


    private RequestManager mRequestManager;

    public NewsAdapter(RequestManager requestManager) {
        mRequestManager = requestManager;
    }

    @Override
    public ViewHolder onCreateNormalViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return ViewHolder.newInstance(inflater, parent).setRequestManager(mRequestManager);
    }

    @Override
    public void onBindNormalViewHolder(ViewHolder holder, int position) {
        holder.onBindViewHolder(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.nickname_group)
        TextView nicknameGroup;
        @Bind(R.id.preview)
        ImageView preview;

        private NewsModel mNewsModel;
        private RequestManager mRequestManager;

        public ViewHolder setRequestManager(RequestManager requestManager) {
            mRequestManager = requestManager;
            return this;
        }

        public static ViewHolder newInstance(LayoutInflater inflater, ViewGroup parent) {
            View view = inflater.inflate(R.layout.item_news, parent, false);
            return new ViewHolder(view);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void onBindViewHolder(NewsModel itemData) {

            mNewsModel = itemData;

            title.setText(itemData.getTitle());
            nicknameGroup.setText(String.format("%s @ %s", itemData.getAuthor().getName(), itemData.getTags().get(0).getTitle()));

            mRequestManager.load(itemData.getCustom_fields().getThumb_c().get(0))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .into(preview);
        }

        @Override
        public void onClick(View v) {
            NewsDetailActivity.start((Activity) v.getContext(), mNewsModel);
        }
    }
}
