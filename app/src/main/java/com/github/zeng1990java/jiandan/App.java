package com.github.zeng1990java.jiandan;

import android.app.Application;
import android.content.Context;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/23 下午10:19
 */
public class App extends Application {

    private static App sApp;
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();

        sApp = this;
        sContext = this;
    }

    public static Context getContext(){
        return sContext;
    }

    public static App getApp(){
        return sApp;
    }
}
