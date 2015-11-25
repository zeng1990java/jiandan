package com.github.zeng1990java.jiandan.model;

import java.util.List;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/25 下午10:14
 */
public class NewsListModel {
//    status: "ok",
//    count: 24,
//    count_total: 52147,
//    pages: 2173,
    private String status;
    private int count;
    private int count_total;
    private int pages;
    private List<NewsModel> posts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount_total() {
        return count_total;
    }

    public void setCount_total(int count_total) {
        this.count_total = count_total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<NewsModel> getPosts() {
        return posts;
    }

    public void setPosts(List<NewsModel> posts) {
        this.posts = posts;
    }
}
