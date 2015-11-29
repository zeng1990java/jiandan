package com.github.zeng1990java.jiandan.theme;

import android.content.Context;
import android.preference.PreferenceManager;
import android.view.ContextThemeWrapper;

import com.github.zeng1990java.jiandan.R;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/29 下午10:13
 */
public class Prefrences {

    public static void applyThme(ContextThemeWrapper contextThemeWrapper){
        if (darkThemeEnabled(contextThemeWrapper)){
            contextThemeWrapper.setTheme(R.style.AppTheme_Dark);
        }
    }

    public static void applyThmeMain(ContextThemeWrapper contextThemeWrapper){
        if (darkThemeEnabled(contextThemeWrapper)){
            contextThemeWrapper.setTheme(R.style.AppThemeMain_Dark);
        }
    }

    public static boolean darkThemeEnabled(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getString(
                context.getString(R.string.pref_theme), context.getString(R.string.pref_theme_value_light))
                .endsWith(context.getString(R.string.pref_theme_value_dark));
    }

    public static void changeTheme(Context context){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(context.getString(R.string.pref_theme),
                           darkThemeEnabled(context)? context.getString(R.string.pref_theme_value_light):context.getString(R.string.pref_theme_value_dark))
                .apply();
    }
}
