package com.github.zeng1990java.jiandan.model;

import java.util.List;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/25 下午10:06
 */
public class NewsModel {
//    {
//        id: 71968,
//                url: "http://jandan.net/2015/11/24/hobbits-separate-species.html",
//            title: "霍比特人是另一物种，而非畸形的现代人",
//            date: "2015-11-24 20:32:19",
//            tags: [
//        {
//            id: 687,
//                    slug: "%e6%97%a0%e5%8e%98%e5%a4%b4%e7%a0%94%e7%a9%b6",
//                title: "无厘头研究",
//                description: "",
//                post_count: 2010
//        }
//        ],
//        author: {
//            id: 563,
//                    slug: "siyi",
//                    name: "蛋花",
//                    first_name: "",
//                    last_name: "",
//                    nickname: "蛋花",
//                    url: "",
//                    description: ""
//        },
//        comment_count: 10,
//                custom_fields: {
//        thumb_c: [
//        "http://tankr.net/s/custom/XIU3.jpg"
//        ],
//        views: [
//        "10525"
//        ]
//    }
//    }

    private int id;
    private String url;
    private String title;
    private String date;
    private int comment_count;
    private List<String> thumb_c;
    private NewsAuthor author;
    private List<NewsTag> tags;
    private CustomFields custom_fields;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public List<String> getThumb_c() {
        return thumb_c;
    }

    public void setThumb_c(List<String> thumb_c) {
        this.thumb_c = thumb_c;
    }

    public NewsAuthor getAuthor() {
        return author;
    }

    public void setAuthor(NewsAuthor author) {
        this.author = author;
    }

    public List<NewsTag> getTags() {
        return tags;
    }

    public void setTags(List<NewsTag> tags) {
        this.tags = tags;
    }

    public CustomFields getCustom_fields() {
        return custom_fields;
    }

    public void setCustom_fields(CustomFields custom_fields) {
        this.custom_fields = custom_fields;
    }
}
