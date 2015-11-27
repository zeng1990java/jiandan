package com.github.zeng1990java.jiandan.config;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * $desc
 *
 * @author zxb
 * @date 15/11/27 下午3:21
 */
public class CacheGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // 50 MB
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, 50 * 1024 * 1024));
        // 50MB
        builder.setMemoryCache(new LruResourceCache(50 * 1024 * 1024));
        // 50MB
        builder.setBitmapPool(new LruBitmapPool(50 * 1024 * 1024));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
