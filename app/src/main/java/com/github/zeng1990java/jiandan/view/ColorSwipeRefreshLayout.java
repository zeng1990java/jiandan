package com.github.zeng1990java.jiandan.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.github.zeng1990java.jiandan.R;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/25 下午4:22
 */
public class ColorSwipeRefreshLayout extends SwipeRefreshLayout {
    public ColorSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public ColorSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setColorSchemeColors(ContextCompat.getColor(getContext(), R.color.colorAccent));
    }
}
