package com.github.zeng1990java.jiandan.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import com.github.zeng1990java.jiandan.R;


/**
 * $desc
 *
 * @author zxb
 * @date 15/11/23 下午10:38
 */
public class BaseActivity extends RxAppCompatActivity {

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        setStatusBarColor();
    }

    /**
     * set status bar color
     */
    protected void setStatusBarColor() {
        if(Build.VERSION.SDK_INT == 19){
            // create our manager instance after the content view is set
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // enable status bar tint
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint
            tintManager.setNavigationBarTintEnabled(true);
            tintManager.setStatusBarTintColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
