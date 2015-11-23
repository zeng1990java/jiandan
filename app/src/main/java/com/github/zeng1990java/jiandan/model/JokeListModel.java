package com.github.zeng1990java.jiandan.model;

import java.util.List;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/23 下午10:53
 */
public class JokeListModel {
//    status: "ok",
//    current_page: 1,
//    total_comments: 18842,
//    page_count: 754,
//    count: 25,
//    comments
    private String status;
    private int current_page;
    private int total_comments;
    private int page_count;
    private int count;
    private List<JokeModel> comments;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getTotal_comments() {
        return total_comments;
    }

    public void setTotal_comments(int total_comments) {
        this.total_comments = total_comments;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<JokeModel> getComments() {
        return comments;
    }

    public void setComments(List<JokeModel> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "JokeListModel{" +
                "status='" + status + '\'' +
                ", current_page=" + current_page +
                ", total_comments=" + total_comments +
                ", page_count=" + page_count +
                ", count=" + count +
                ", comments=" + comments +
                '}';
    }
}
