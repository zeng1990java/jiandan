package com.github.zeng1990java.jiandan.model;

import java.util.ArrayList;
import java.util.List;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/24 下午11:08
 */
public class PictureModel {
    private String comment_ID;
    private String comment_author;
    private String comment_date;
    private String text_content;
    private String vote_positive;
    private String vote_negative;
    private List<String> pics;
    private String pic;

    public PictureModel() {
    }

    public PictureModel(String comment_ID, String comment_author, String comment_date, String text_content, String vote_positive, String vote_negative, String pic) {
        this.comment_ID = comment_ID;
        this.comment_author = comment_author;
        this.comment_date = comment_date;
        this.text_content = text_content;
        this.vote_positive = vote_positive;
        this.vote_negative = vote_negative;
        this.pic = pic;
    }

    public String getComment_ID() {
        return comment_ID;
    }

    public void setComment_ID(String comment_ID) {
        this.comment_ID = comment_ID;
    }

    public String getComment_author() {
        return comment_author;
    }

    public void setComment_author(String comment_author) {
        this.comment_author = comment_author;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
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

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public List<PictureModel> toPictureModel(){
        List<PictureModel> list = new ArrayList<>(pics.size());
        for (String pic : pics) {
            PictureModel pm = new PictureModel(comment_ID, comment_author, comment_date, text_content, vote_positive, vote_negative, pic);
            list.add(pm);
        }
        return list;
    }
}
