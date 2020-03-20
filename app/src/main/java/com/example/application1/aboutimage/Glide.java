package com.example.application1.aboutimage;

import android.content.Context;

/**
 * @author: Administrator
 * @date: 2020-03-20
 */
public class Glide {
    public static BitmapRequest with(Context context) {
        return new BitmapRequest(context);
    }
}
