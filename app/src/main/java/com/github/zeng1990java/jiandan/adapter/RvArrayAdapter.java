package com.github.zeng1990java.jiandan.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/25 上午9:55
 */
public abstract class RvArrayAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH>{

    private Object mLock = new Object();
    private List<T> mDatas;

    public RvArrayAdapter(){
        this(new ArrayList<T>());
    }

    public RvArrayAdapter(@NonNull List<T> datas){
        mDatas = datas;
    }

    public T getItem(int position){
        return mDatas.get(position);
    }

    public void add(T data){
        add(0, data);
    }

    public void add(int position, T data){
        synchronized (mLock){
            mDatas.add(position, data);
            notifyItemInserted(position);
        }
    }

    public void remove(int position, T data){
        synchronized (mLock){
            mDatas.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addAll(@NonNull List<T> datas){
        synchronized (mLock){
            int positionStart = mDatas.size();
            mDatas.addAll(datas);
            notifyItemRangeInserted(positionStart, datas.size());
        }
    }

    public void replaceAll(@NonNull List<T> datas){
        synchronized (mLock) {

            if (mDatas.equals(datas)){
                return;
            }

            if (mDatas.isEmpty() && datas.isEmpty()) {
                return;
            }

            if (mDatas.isEmpty()){
                addAll(datas);
                return;
            }

            if (datas.isEmpty()){
                clear();
                return;
            }
            mDatas.clear();
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void set(int postion, T data){
        synchronized (mLock){
            mDatas.set(postion, data);
            notifyItemChanged(postion);
        }
    }

    public void retainAll(@NonNull List<T> datas){
        synchronized (mLock){
            if (datas.isEmpty()){
                clear();
                return;
            }
            Iterator<T> iterator = mDatas.iterator();
            while (iterator.hasNext()){
                T next = iterator.next();
                if (datas.contains(next)){
                    continue;
                }
                int index = mDatas.indexOf(next);
                iterator.remove();
                notifyItemRemoved(index);
            }
        }
    }

    public void clear(){
        synchronized (mLock){
            if (mDatas.isEmpty()){
                return;
            }
            int size = mDatas.size();
            mDatas.clear();
            notifyItemRangeRemoved(0, size);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
