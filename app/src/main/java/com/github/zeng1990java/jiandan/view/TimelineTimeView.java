package com.github.zeng1990java.jiandan.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.github.zeng1990java.jiandan.utils.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/27 下午2:33
 */
public class TimelineTimeView extends TextView {

    private SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public TimelineTimeView(Context context) {
        super(context);
    }

    public TimelineTimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TimelineTimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setFormatTime(String formatTime){
        try {
            long commentDate = mDateFormat.parse(formatTime).getTime();
            setText(TimeUtil.getTimelineTime(commentDate));
        } catch (ParseException e) {
            e.printStackTrace();
            setText(formatTime);
        }
    }
}
