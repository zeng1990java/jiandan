package com.github.zeng1990java.jiandan.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;

import java.util.HashMap;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/25 下午5:49
 */
public class TimelineImageView extends ImageView{

    private static final HashMap<String, Point> sUrlSizeMap = new HashMap<>();

    private String mUrl;
    private Drawable mPlaceHolder;
    private GifDrawable mGifDrawable;
    private boolean isPlaying;

    public TimelineImageView(Context context) {
        this(context, null);
    }

    public TimelineImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimelineImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);

        Drawable drawable = getDrawable();
        Point point = sUrlSizeMap.get(mUrl);
        if (drawable == null){
            if (point == null) {
                setMeasuredDimension(width, width);
            }else {
                setMeasuredDimension(point.x, point.y);
            }
        }else {
            int dw = drawable.getIntrinsicWidth();
            int dh = drawable.getIntrinsicHeight();

            int height = 0;
            if (point != null){
                height = point.y;
            }else if (TextUtils.isEmpty(mUrl) || drawable == mPlaceHolder){
                height = width;
            }else {
                point = new Point(width, (int) (dh * 1.0f * width / dw));
                sUrlSizeMap.put(mUrl, point);

                height = point.y;
            }

            setMeasuredDimension(width, height);
        }

    }

    public void loadImage(RequestManager rm, String url){
        mUrl = url;
        isPlaying = false;
        if (mGifDrawable != null){
            mGifDrawable.setCallback(null);
            mGifDrawable = null;
        }
        if (mPlaceHolder == null){
            mPlaceHolder = ContextCompat.getDrawable(getContext(), android.R.drawable.progress_indeterminate_horizontal);
        }
        if (isGif(mUrl)){
            rm.load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .dontAnimate()
                    .placeholder(mPlaceHolder)
                    .into(this);
        }else {
            rm.load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .placeholder(mPlaceHolder)
                    .into(this);
        }

    }

    private boolean isGif(String url){
        return url.toLowerCase().endsWith(".gif");
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if(drawable instanceof GifDrawable){
            mGifDrawable= (GifDrawable) drawable;
            mGifDrawable.setCallback(new Drawable.Callback() {
                @Override
                public void invalidateDrawable(Drawable who) {
                    mGifDrawable.stop();
                    TimelineImageView.this.invalidateDrawable(who);
                    mGifDrawable.setCallback(TimelineImageView.this);
                }

                @Override
                public void scheduleDrawable(Drawable who, Runnable what, long when) {

                }

                @Override
                public void unscheduleDrawable(Drawable who, Runnable what) {

                }
            });

        }
    }

    @Override
    public void invalidateDrawable(Drawable dr) {
        if (isPlaying){
            super.invalidateDrawable(dr);
        }else if (mGifDrawable != null){
            mGifDrawable.stop();
            Bitmap firstFrame = mGifDrawable.getFirstFrame();
            super.invalidateDrawable(new BitmapDrawable(firstFrame));
        }else {
            super.invalidateDrawable(dr);
        }
    }

    public void starPlayGif(){
        if(mGifDrawable!=null){
            isPlaying = true;
            mGifDrawable.start();
        }
    }

    public void stopPlayGif(){
        if(mGifDrawable!=null){
            isPlaying = false;
            mGifDrawable.stop();
        }
    }

    public boolean isGifRunning(){
        if(mGifDrawable!=null){
            return mGifDrawable.isRunning();
        }
        return false;
    }

    public boolean isGifDrawable(){
        return mGifDrawable != null;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        if(mGifDrawable!=null){
            mGifDrawable.setCallback(null);
            mGifDrawable=null;
        }
        super.onDetachedFromWindow();
    }
}
