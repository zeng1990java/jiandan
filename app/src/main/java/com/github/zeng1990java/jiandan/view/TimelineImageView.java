package com.github.zeng1990java.jiandan.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/25 下午5:49
 */
public class TimelineImageView extends ImageView {

    public TimelineImageView(Context context) {
        super(context);
    }

    public TimelineImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimelineImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        Drawable drawable = getDrawable();
        if (drawable == null){
            setMeasuredDimension(width, width);
        }else {
            int dw = drawable.getIntrinsicWidth();
            int dh = drawable.getIntrinsicHeight();

            setMeasuredDimension(width, (int) (dh * 1.0f * width / dw));
        }
    }
}
