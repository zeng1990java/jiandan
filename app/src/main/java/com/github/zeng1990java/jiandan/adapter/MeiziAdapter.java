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
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/24 上午7:47
 */
public class MeiziAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int ITEM_NORMAL = 0;
    private static final int ITEM_LOAD_MORE = 1;

    private List<PictureModel> mDatas;
    private boolean hasMore;

    public MeiziAdapter(List<PictureModel> list){
        mDatas = list;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean isHasMore) {
        this.hasMore = isHasMore;
    }

    public void replaceAll(List<PictureModel> list){
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(List<PictureModel> list){
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public PictureModel getItem(int position){
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meizi, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ITEM_LOAD_MORE){
            return;
        }

        PictureModel jokeModel = getItem(position);
        ViewHolder vh = (ViewHolder) holder;
        vh.setJokeModel(jokeModel);
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

        @Bind(R.id.meizi_content)
        TextView jokeContent;
        @Bind(R.id.nickname)
        TextView nickname;
        @Bind(R.id.time)
        TextView time;
        @Bind(R.id.meizi_picture)
        ImageView picture;


        private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private PictureModel mJokeModel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setJokeModel(PictureModel jokeModel){
            mJokeModel = jokeModel;

            jokeContent.setText(jokeModel.getText_content());
            nickname.setText(jokeModel.getComment_author());
            if (TextUtils.isEmpty(jokeModel.getText_content())){
                jokeContent.setVisibility(View.GONE);
            }else {
                jokeContent.setVisibility(View.VISIBLE);
            }

            try {
                long commentDate = mDateFormat.parse(jokeModel.getComment_date()).getTime();
                time.setText(TimeUtil.getTimelineTime(commentDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Glide.with(itemView.getContext()).load(mJokeModel.getPics().get(0)).into(picture);
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
