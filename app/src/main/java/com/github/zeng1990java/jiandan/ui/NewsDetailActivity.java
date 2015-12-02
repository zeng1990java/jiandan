package com.github.zeng1990java.jiandan.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.zeng1990java.jiandan.App;
import com.github.zeng1990java.jiandan.R;
import com.github.zeng1990java.jiandan.model.NewsDetailModel;
import com.github.zeng1990java.jiandan.model.NewsModel;
import com.github.zeng1990java.jiandan.theme.Prefrences;
import com.github.zeng1990java.jiandan.ui.base.BaseToolbarActivity;
import com.github.zeng1990java.jiandan.view.SwipeBackLayout;
import com.pnikosis.materialishprogress.ProgressWheel;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/27 下午9:23
 */
public class NewsDetailActivity extends BaseToolbarActivity implements SwipeBackLayout.Callback{

    static final String MIME_TYPE = "text/html";
    static final String ENCODING = "utf-8";

    private static final String NEWS_ID = "news_id";
    private static final String NEWS_TITLE = "news_title";
    private static final String NEWS_AUTHOR = "news_author";
    private static final String NEWS_DATE = "news_date";


    public static void start(Activity activity, NewsModel newsModel){
        Intent i = new Intent(activity, NewsDetailActivity.class);
        i.putExtra(NEWS_ID, newsModel.getId());
        i.putExtra(NEWS_TITLE, newsModel.getTitle());
        i.putExtra(NEWS_AUTHOR, newsModel.getAuthor().getName());
        i.putExtra(NEWS_DATE, newsModel.getDate());
        activity.startActivity(i);
    }

    @Bind(R.id.swipe_back_layout)
    SwipeBackLayout mSwipeBackLayout;
    @Bind(R.id.webview)
    WebView mWebView;
    @Bind(R.id.progress_wheel)
    ProgressWheel mProgressWheel;

    private int mNewsId;
    private String mTitle;
    private String mAuthor;
    private String mDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        mSwipeBackLayout.setCallback(this);

        setupToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupWebView();

        mNewsId = getIntent().getIntExtra(NEWS_ID, -1);
        mAuthor = getIntent().getStringExtra(NEWS_AUTHOR);
        mTitle = getIntent().getStringExtra(NEWS_TITLE);
        mDate = getIntent().getStringExtra(NEWS_DATE);

        App.getApp().getJiandanApi().getNewsDetail(mNewsId)
                .retry()
                .compose(this.<NewsDetailModel>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsDetailModel>() {
                    @Override
                    public void onCompleted() {
                        mProgressWheel.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsDetailModel newsDetailModel) {
                        mWebView.setVisibility(View.VISIBLE);
                        mWebView.loadDataWithBaseURL("", getHtml(newsDetailModel.getPost().getContent()), MIME_TYPE, ENCODING, "");
                    }
                });
    }

    private void setupWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.setBackgroundColor(Color.TRANSPARENT);

    }

    private String getHtml(String content) {
        final StringBuilder sb = new StringBuilder(content.length() + 500);
        boolean isDarkTheme = Prefrences.darkThemeEnabled(this);
        final String cssStyle = isDarkTheme ? "style_night.css" : "style.css";
        sb.append("<!DOCTYPE html>");
        sb.append("<html dir=\"ltr\" lang=\"zh\">");
        sb.append("<head>");
        sb.append("<meta name=\"viewport\" content=\"width=100%; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\" />");
        sb.append("<link rel=\"stylesheet\" href='file:///android_asset/"+cssStyle+"' type=\"text/css\" media=\"screen\" />");
        sb.append("</head>");
        sb.append("<body style=\"padding:0px 8px 8px 8px;\">");
        sb.append("<div id=\"pagewrapper\">");
        sb.append("<div id=\"mainwrapper\" class=\"clearfix\">");
        sb.append("<div id=\"maincontent\">");
        sb.append("<div class=\"post\">");
        sb.append("<div class=\"posthit\">");
        sb.append("<div class=\"postinfo\">");
        sb.append("<h2 class=\"thetitle\">");
        sb.append("<a>");
        sb.append(mTitle);
        sb.append("</a>");
        sb.append("</h2>");
        sb.append(mAuthor + " @ " + mDate);
        sb.append("</div>");
        sb.append("<div class=\"entry\">");
        sb.append(content);
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    @Override
    public void onSwipeStart() {

    }

    @Override
    public void onSwipeProgress(float progress) {

    }

    @Override
    public void onSwipeCancel() {

    }

    @Override
    public void onSwipeComplete() {
        finish();
        overridePendingTransition(0 , 0);
    }
}
