package com.github.zeng1990java.jiandan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.zeng1990java.jiandan.R;
import com.github.zeng1990java.jiandan.model.JokeModel;
import com.github.zeng1990java.jiandan.utils.CopyUtil;
import com.github.zeng1990java.jiandan.view.TimelineTimeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/24 上午7:47
 */
public class JokeAdapter extends RvLoadmoreAdapter<JokeModel, JokeAdapter.ViewHolder>{

    @Override
    public ViewHolder onCreateNormalViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return ViewHolder.newInstance(inflater, parent);
    }

    @Override
    public void onBindNormalViewHolder(ViewHolder holder, int position) {
        holder.onBindViewHolder(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        @Bind(R.id.joke_content)
        TextView content;
        @Bind(R.id.nickname)
        TextView nickname;
        @Bind(R.id.time)
        TimelineTimeView time;

        private JokeModel mJokeModel;

        public static ViewHolder newInstance(LayoutInflater inflater, ViewGroup parent) {
            View view = inflater.inflate(R.layout.item_joke, parent, false);
            return new ViewHolder(view);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void onBindViewHolder(JokeModel jokeModel){
            mJokeModel = jokeModel;

            content.setText(jokeModel.getText_content());
            nickname.setText(jokeModel.getComment_author());
            time.setFormatTime(jokeModel.getComment_date());
        }

        @Override
        public void onClick(View v) {
//            ToastUtil.showShort(v.getContext(), "TODO Item Click");
        }

        @Override
        public boolean onLongClick(View v) {
            CopyUtil.copyToClipboard(v.getContext(), mJokeModel.getText_content());
            return true;
        }
    }
}
