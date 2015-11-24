package com.github.zeng1990java.jiandan.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/24 下午2:58
 */
public class ToastUtil {
    public static void showShort(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
