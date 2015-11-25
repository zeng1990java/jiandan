package com.github.zeng1990java.jiandan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.zeng1990java.jiandan.R;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/25 上午10:42
 */
public abstract class RvLoadmoreAdapter<T, VH extends RecyclerView.ViewHolder> extends RvArrayAdapter<T, RecyclerView.ViewHolder> {

    private static final int ITEM_NORMAL = 0;
    private static final int ITEM_LOAD_MORE = Integer.MAX_VALUE / 3;

    private LayoutInflater mInflater;
    private boolean hasMore;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @Override
    public int getItemCount() {
        if (hasMore){
            return super.getItemCount() + 1;
        }
        return super.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (hasMore){
            if (position == super.getItemCount()){
                return ITEM_LOAD_MORE;
            }
        }
        return ITEM_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null){
            mInflater = LayoutInflater.from(parent.getContext());
        }
        if (viewType == ITEM_LOAD_MORE){
            return new LoadMoreViewHolder(mInflater.inflate(R.layout.load_more_item, parent, false));
        }
        return onCreateNormalViewHolder(mInflater, parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position) == ITEM_LOAD_MORE){
            return;
        }
        onBindNormalViewHolder((VH) holder, position);
    }

    public abstract VH onCreateNormalViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType);
    public abstract void onBindNormalViewHolder(VH holder, int position);

    static class LoadMoreViewHolder extends RecyclerView.ViewHolder{
        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }
}
