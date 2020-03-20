package com.example.application1.aboutimage;

import android.graphics.Bitmap;

/**
 * @author: Administrator
 * @date: 2020-03-20
 */
public interface RequestListener {

    boolean onSuccess(Bitmap bitmap);

    boolean onFaile();

}
