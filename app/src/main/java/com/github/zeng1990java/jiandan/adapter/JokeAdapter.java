package com.github.zeng1990java.jiandan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.zeng1990java.jiandan.R;
import com.github.zeng1990java.jiandan.model.JokeModel;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/24 上午7:47
 */
public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder>{

    private List<JokeModel> mDatas;

    public JokeAdapter(List<JokeModel> list){
        mDatas = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_joke, parent, false));
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
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.jokeContent.setText(getItem(position).getText_content());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.joke_content)
        TextView jokeContent;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
