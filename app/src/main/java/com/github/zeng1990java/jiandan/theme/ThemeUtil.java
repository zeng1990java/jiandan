package com.github.zeng1990java.jiandan.theme;

import android.content.Context;
import android.util.TypedValue;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/30 下午6:48
 */
public class ThemeUtil {
    public static int getThemeColor(Context context, String attrName){
        int attrId = context.getResources().getIdentifier(attrName, "attr", context.getPackageName());
        if (attrId > 0){
            TypedValue colorAccent = new TypedValue();
            context.getTheme().resolveAttribute(attrId, colorAccent, true);

            return colorAccent.data;
        }
        return 0;
    }
}
