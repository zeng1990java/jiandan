package com.github.zeng1990java.jiandan.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.bumptech.glide.Glide;
import com.github.zeng1990java.jiandan.R;
import com.github.zeng1990java.jiandan.api.JiandanApi;
import com.github.zeng1990java.jiandan.model.JokeListModel;
import com.github.zeng1990java.jiandan.theme.Prefrences;
import com.github.zeng1990java.jiandan.ui.base.BaseToolbarActivity;
import com.github.zeng1990java.jiandan.ui.fragments.JokeListFragment;
import com.github.zeng1990java.jiandan.ui.fragments.MeiziPictureFragment;
import com.github.zeng1990java.jiandan.ui.fragments.NewsFragment;
import com.github.zeng1990java.jiandan.utils.ToastUtil;
import com.socks.library.KLog;
import com.trello.rxlifecycle.ActivityEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseToolbarActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final long BACK_PRESSED_WAIT_TIME = 2000;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.fab)
    FloatingActionButton mFab;
    private long mBackPressedClickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolbar();

        mFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
        );

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_container, new JokeListFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_camera);
            setTitle(R.string.nav_joke);
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            long curTime = System.currentTimeMillis();
            if(curTime - mBackPressedClickTime >= BACK_PRESSED_WAIT_TIME){
                ToastUtil.showShort(this, "再按一次退出程序");
                mBackPressedClickTime = curTime;
                return;
            }
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Glide.get(this).clearMemory();
        if (id == R.id.nav_camera) {
            // Handle the camera action
            getSupportFragmentManager().beginTransaction().replace(R.id.content_container, new JokeListFragment()).commit();
            setTitle(R.string.nav_joke);
        } else if (id == R.id.nav_gallery) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_container, new MeiziPictureFragment()).commit();
            setTitle(R.string.nav_meizi_picture);
        } else if (id == R.id.nav_slideshow) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_container, new NewsFragment()).commit();
            setTitle(R.string.nav_news);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            Prefrences.changeTheme(this);
            recreate();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void applyTheme() {
        Prefrences.applyThmeMain(this);
    }
}
