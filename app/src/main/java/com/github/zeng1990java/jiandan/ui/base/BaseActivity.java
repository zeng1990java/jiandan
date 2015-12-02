package com.github.zeng1990java.jiandan.ui.base;

import android.os.Bundle;

import com.github.zeng1990java.jiandan.theme.Prefrences;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;


/**
 * $desc
 *
 * @author zxb
 * @date 15/11/23 下午10:38
 */
public class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applyTheme();
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    protected void applyTheme(){
        Prefrences.applyThme(this);
    }
}
