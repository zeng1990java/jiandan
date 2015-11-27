package com.github.zeng1990java.jiandan.model;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/27 下午9:33
 */
public class NewsDetailModel {
    private String status;
    private String previous_url;
    private NewsDetailContentModel post;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrevious_url() {
        return previous_url;
    }

    public void setPrevious_url(String previous_url) {
        this.previous_url = previous_url;
    }

    public NewsDetailContentModel getPost() {
        return post;
    }

    public void setPost(NewsDetailContentModel post) {
        this.post = post;
    }
}
