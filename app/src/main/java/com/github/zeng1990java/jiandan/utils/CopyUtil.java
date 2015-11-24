package com.github.zeng1990java.jiandan.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/24 下午6:50
 */
public class CopyUtil {
    public static void copyToClipboard(Context context, String text){
        if (TextUtils.isEmpty(text)){
            return;
        }
        ClipData data = ClipData.newPlainText("jiandan", text);
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        manager.setPrimaryClip(data);
        ToastUtil.showShort(context, "已经复制好");
    }
}
