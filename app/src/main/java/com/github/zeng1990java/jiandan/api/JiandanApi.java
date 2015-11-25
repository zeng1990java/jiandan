package com.github.zeng1990java.jiandan.api;

import com.github.zeng1990java.jiandan.model.JokeListModel;
import com.github.zeng1990java.jiandan.model.NewsListModel;
import com.github.zeng1990java.jiandan.model.PictureListModel;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/23 下午10:42
 */
public interface JiandanApi {
    @GET("?oxwlxojflwblxbsapi=jandan.get_duan_comments&")
    Observable<JokeListModel> loadJokeList(@Query("page") int page);

    @GET("?oxwlxojflwblxbsapi=jandan.get_ooxx_comments&")
    Observable<PictureListModel> loadMeiziPictureList(@Query("page") int page);

    @GET("?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,comment_count,custom_fields&custom_fields=thumb_c,views&dev=1&")
    Observable<NewsListModel> loadNewsList(@Query("page") int page);
}
