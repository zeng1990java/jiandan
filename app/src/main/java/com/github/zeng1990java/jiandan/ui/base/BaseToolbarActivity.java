package com.github.zeng1990java.jiandan.ui.base;

import android.support.v7.widget.Toolbar;

import com.github.zeng1990java.jiandan.R;

import butterknife.Bind;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/23 下午10:38
 */
public class BaseToolbarActivity extends BaseActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    protected Toolbar getToolbar(){
        return mToolbar;
    }

    protected void setupToolbar(){
        if (mToolbar == null){
            throw new IllegalStateException("Toolbar with id toolbar is null.");
        }
        setSupportActionBar(mToolbar);
    }

}
