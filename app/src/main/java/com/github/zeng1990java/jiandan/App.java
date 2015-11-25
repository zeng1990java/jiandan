package com.github.zeng1990java.jiandan;

import android.app.Application;
import android.content.Context;

import com.github.zeng1990java.jiandan.api.JiandanApi;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/23 下午10:19
 */
public class App extends Application {

    private static App sApp;
    private static Context sContext;
    private Retrofit mRetrofit;
    private JiandanApi mJiandanApi;

    @Override
    public void onCreate() {
        super.onCreate();

        sApp = this;
        sContext = this;

        initRetrofit();
    }

    private void initRetrofit() {
        if (mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("http://jandan.net/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    public static Context getContext(){
        return sContext;
    }

    public static App getApp(){
        return sApp;
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }

    public JiandanApi getJiandanApi(){
        if (mJiandanApi == null) {
            synchronized (this) {
                if (mJiandanApi == null){
                    mJiandanApi = mRetrofit.create(JiandanApi.class);
                }
            }
        }
        return mJiandanApi;
    }
}
