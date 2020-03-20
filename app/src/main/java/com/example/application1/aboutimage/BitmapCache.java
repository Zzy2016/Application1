package com.example.application1.aboutimage;

import android.graphics.Bitmap;

/**
 * @author: Administrator
 * @date: 2020-03-20
 */
public interface BitmapCache {
    void put(BitmapRequest bitmapRequest, Bitmap bitmap);//写入内存

    Bitmap get(BitmapRequest bitmapRequest);//读取缓存

    void remove(BitmapRequest bitmapRequest);//清楚缓存
}
