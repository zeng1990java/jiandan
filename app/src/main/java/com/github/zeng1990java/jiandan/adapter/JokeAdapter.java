package com.github.zeng1990java.jiandan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.zeng1990java.jiandan.R;
import com.github.zeng1990java.jiandan.model.JokeModel;
import com.github.zeng1990java.jiandan.utils.CopyUtil;
import com.github.zeng1990java.jiandan.utils.TimeUtil;
import com.github.zeng1990java.jiandan.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
        return new ViewHolder(inflater.inflate(R.layout.item_joke, parent, false));
    }

    @Override
    public void onBindNormalViewHolder(ViewHolder holder, int position) {
        holder.setJokeModel(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        @Bind(R.id.joke_content)
        TextView jokeContent;
        @Bind(R.id.nickname)
        TextView nickname;
        @Bind(R.id.time)
        TextView time;

        private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private JokeModel mJokeModel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setJokeModel(JokeModel jokeModel){
            mJokeModel = jokeModel;

            jokeContent.setText(jokeModel.getText_content());
            nickname.setText(jokeModel.getComment_author());

            try {
                long commentDate = mDateFormat.parse(jokeModel.getComment_date()).getTime();
                time.setText(TimeUtil.getTimelineTime(commentDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onClick(View v) {
            ToastUtil.showShort(v.getContext(), "TODO Item Click");
        }

        @Override
        public boolean onLongClick(View v) {
            CopyUtil.copyToClipboard(v.getContext(), mJokeModel.getText_content());
            return true;
        }
    }
}
