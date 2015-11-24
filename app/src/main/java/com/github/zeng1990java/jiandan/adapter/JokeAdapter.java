package com.github.zeng1990java.jiandan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.zeng1990java.jiandan.R;
import com.github.zeng1990java.jiandan.model.JokeModel;
import com.github.zeng1990java.jiandan.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/24 上午7:47
 */
public class JokeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int ITEM_NORMAL = 0;
    private static final int ITEM_LOAD_MORE = 1;

    private List<JokeModel> mDatas;
    private boolean hasMore;

    public JokeAdapter(List<JokeModel> list){
        mDatas = list;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean isHasMore) {
        this.hasMore = isHasMore;
    }

    public void replaceAll(List<JokeModel> list){
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(List<JokeModel> list){
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public JokeModel getItem(int position){
        return mDatas.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (!isHasMore()){
            return ITEM_NORMAL;
        }
        return position==mDatas.size()?ITEM_LOAD_MORE:ITEM_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_LOAD_MORE){
            return new LoadMoreViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more_item, parent, false));
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_joke, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_LOAD_MORE){
            return;
        }
        ViewHolder vh = (ViewHolder) holder;
        vh.jokeContent.setText(getItem(position).getText_content());
    }

    @Override
    public int getItemCount() {
        return isHasMore()?mDatas.size()+1:mDatas.size();
    }

    static class LoadMoreViewHolder extends RecyclerView.ViewHolder{

        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        @Bind(R.id.joke_content)
        TextView jokeContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ToastUtil.showShort(v.getContext(), "Item Click");
        }

        @Override
        public boolean onLongClick(View v) {
            ToastUtil.showShort(v.getContext(), "Item Long Click");
            return true;
        }
    }
}
