package com.github.zeng1990java.jiandan.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.zeng1990java.jiandan.R;
import com.github.zeng1990java.jiandan.model.PictureModel;
import com.github.zeng1990java.jiandan.utils.CopyUtil;
import com.github.zeng1990java.jiandan.utils.TimeUtil;
import com.github.zeng1990java.jiandan.view.TimelineImageView;
import com.github.zeng1990java.jiandan.view.TimelineTimeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * $desc
 * gif的播放与暂停感谢https://github.com/MMCBen提供技术支持
 * @author zxb
 * @date 15/11/24 上午7:47
 */
public class MeiziAdapter extends RvLoadmoreAdapter<PictureModel, MeiziAdapter.ViewHolder>{

    @Override
    public ViewHolder onCreateNormalViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return ViewHolder.newInstance(inflater, parent);
    }

    @Override
    public void onBindNormalViewHolder(ViewHolder holder, int position) {
        holder.onBindViewHolder(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        @Bind(R.id.meizi_content)
        TextView jokeContent;
        @Bind(R.id.nickname)
        TextView nickname;
        @Bind(R.id.time)
        TimelineTimeView time;
        @Bind(R.id.meizi_picture)
        TimelineImageView picture;
        @Bind(R.id.gif_icon)
        ImageView gifIcon;


        private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private PictureModel mPictureModel;

        public static ViewHolder newInstance(LayoutInflater inflater, ViewGroup parent) {
            View view = inflater.inflate(R.layout.item_meizi, parent, false);
            return new ViewHolder(view);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void onBindViewHolder(PictureModel pictureModel){
            mPictureModel = pictureModel;

            String content = pictureModel.getText_content();

            jokeContent.setText(content);
            nickname.setText(pictureModel.getComment_author());
            if (TextUtils.isEmpty(content) || TextUtils.isEmpty(content.trim())){
                jokeContent.setVisibility(View.GONE);
            }else {
                jokeContent.setVisibility(View.VISIBLE);
            }

            time.setFormatTime(pictureModel.getComment_date());
            picture.loadImage(Glide.with(itemView.getContext()), mPictureModel.getPic());
            if (mPictureModel.getPic().endsWith(".gif")){
                gifIcon.setVisibility(View.VISIBLE);
            }else {
                gifIcon.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            if (picture.isGifDrawable()){
                if (picture.isGifRunning()){
                    picture.stopPlayGif();
                    gifIcon.setVisibility(View.VISIBLE);
                }else {
                    picture.starPlayGif();
                    gifIcon.setVisibility(View.GONE);
                }
            }
        }

        @Override
        public boolean onLongClick(View v) {
            CopyUtil.copyToClipboard(v.getContext(), mPictureModel.getText_content());
            return true;
        }
    }
}
