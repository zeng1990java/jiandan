package com.github.zeng1990java.jiandan.model;

import java.util.List;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/27 下午8:16
 */
public class CustomFields {
//    thumb_c: [
//            "http://tankr.net/s/custom/3BX1.jpg"
//            ],
//    views: [
//            "9698"
//            ]
    private List<String> thumb_c;
    private List<String> views;

    public List<String> getThumb_c() {
        return thumb_c;
    }

    public void setThumb_c(List<String> thumb_c) {
        this.thumb_c = thumb_c;
    }

    public List<String> getViews() {
        return views;
    }

    public void setViews(List<String> views) {
        this.views = views;
    }
}
