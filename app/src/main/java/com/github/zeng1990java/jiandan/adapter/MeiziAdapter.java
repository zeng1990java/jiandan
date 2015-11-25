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
import com.github.zeng1990java.jiandan.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/24 上午7:47
 */
public class MeiziAdapter extends RvLoadmoreAdapter<PictureModel, MeiziAdapter.ViewHolder>{

    @Override
    public ViewHolder onCreateNormalViewHolder(LayoutInflater inflater, ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_meizi, parent, false));
    }

    @Override
    public void onBindNormalViewHolder(ViewHolder holder, int position) {
        holder.setPictureModel(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

        @Bind(R.id.meizi_content)
        TextView jokeContent;
        @Bind(R.id.nickname)
        TextView nickname;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.meizi_picture)
        ImageView picture;


        private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private PictureModel mPictureModel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setPictureModel(PictureModel pictureModel){
            mPictureModel = pictureModel;

            jokeContent.setText(pictureModel.getText_content());
            nickname.setText(pictureModel.getComment_author());
            if (TextUtils.isEmpty(pictureModel.getText_content())){
                jokeContent.setVisibility(View.GONE);
            }else {
                jokeContent.setVisibility(View.VISIBLE);
            }

            try {
                long commentDate = mDateFormat.parse(pictureModel.getComment_date()).getTime();
                time.setText(TimeUtil.getTimelineTime(commentDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Glide.with(itemView.getContext()).load(mPictureModel.getPics().get(0)).into(picture);
        }

        @Override
        public void onClick(View v) {
            ToastUtil.showShort(v.getContext(), "TODO Item Click");
        }

        @Override
        public boolean onLongClick(View v) {
            CopyUtil.copyToClipboard(v.getContext(), mPictureModel.getText_content());
            return true;
        }
    }
}
