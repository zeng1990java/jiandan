package com.github.zeng1990java.jiandan.model;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/23 下午10:44
 */
public class JokeModel {
//    http://jandan.net/?oxwlxojflwblxbsapi=jandan.get_duan_comments&page=1
//    comment_ID: "2991937",
//    comment_post_ID: "55592",
//    comment_author: "光消失的地方",
//    comment_author_email: "aqua5200@qq.com",
//    comment_author_url: "",
//    comment_author_IP: "1.57.117.107",
//    comment_date: "2015-11-23 21:49:51",
//    comment_date_gmt: "2015-11-23 13:49:51",
//    comment_content: "南方的朋友们！我有个提议！既然你们今年冬天那么热，不如就取消寒假吧",
//    comment_karma: "0",
//    comment_approved: "1",
//    comment_type: "",
//    comment_parent: "0",
//    user_id: "0",
//    comment_subscribe: "N",
//    comment_reply_ID: "0",
//    vote_positive: "15",
//    vote_negative: "12",
//    vote_ip_pool: "",
//    text_content: "南方的朋友们！我有个提议！既然你们今年冬天那么热，不如就取消寒假吧",
//    videos: [ ]
    private String comment_ID;
    private String comment_post_ID;
    private String comment_author;
    private String comment_author_email;
    private String comment_author_url;
    private String comment_author_IP;
    private String comment_date;
    private String comment_date_gmt;
    private String comment_content;
    private String text_content;
    private String comment_agent;
    private String vote_positive;
    private String vote_negative;

    public String getComment_ID() {
        return comment_ID;
    }

    public void setComment_ID(String comment_ID) {
        this.comment_ID = comment_ID;
    }

    public String getComment_post_ID() {
        return comment_post_ID;
    }

    public void setComment_post_ID(String comment_post_ID) {
        this.comment_post_ID = comment_post_ID;
    }

    public String getComment_author() {
        return comment_author;
    }

    public void setComment_author(String comment_author) {
        this.comment_author = comment_author;
    }

    public String getComment_author_email() {
        return comment_author_email;
    }

    public void setComment_author_email(String comment_author_email) {
        this.comment_author_email = comment_author_email;
    }

    public String getComment_author_url() {
        return comment_author_url;
    }

    public void setComment_author_url(String comment_author_url) {
        this.comment_author_url = comment_author_url;
    }

    public String getComment_author_IP() {
        return comment_author_IP;
    }

    public void setComment_author_IP(String comment_author_IP) {
        this.comment_author_IP = comment_author_IP;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getComment_date_gmt() {
        return comment_date_gmt;
    }

    public void setComment_date_gmt(String comment_date_gmt) {
        this.comment_date_gmt = comment_date_gmt;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public String getComment_agent() {
        return comment_agent;
    }

    public void setComment_agent(String comment_agent) {
        this.comment_agent = comment_agent;
    }

    public String getVote_positive() {
        return vote_positive;
    }

    public void setVote_positive(String vote_positive) {
        this.vote_positive = vote_positive;
    }

    public String getVote_negative() {
        return vote_negative;
    }

    public void setVote_negative(String vote_negative) {
        this.vote_negative = vote_negative;
    }

    @Override
    public String toString() {
        return "JokeModel{" +
                "comment_ID='" + comment_ID + '\'' +
                ", comment_post_ID='" + comment_post_ID + '\'' +
                ", comment_author='" + comment_author + '\'' +
                ", comment_author_email='" + comment_author_email + '\'' +
                ", comment_author_url='" + comment_author_url + '\'' +
                ", comment_author_IP='" + comment_author_IP + '\'' +
                ", comment_date='" + comment_date + '\'' +
                ", comment_date_gmt='" + comment_date_gmt + '\'' +
                ", comment_content='" + comment_content + '\'' +
                ", text_content='" + text_content + '\'' +
                ", comment_agent='" + comment_agent + '\'' +
                ", vote_positive='" + vote_positive + '\'' +
                ", vote_negative='" + vote_negative + '\'' +
                '}';
    }
}
