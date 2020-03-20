package com.example.application1.aboutimage;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

/**
 * @author: Administrator
 * @date: 2020-03-20
 */
public class BitmapRequest {
    private String url;  //图片路径
    private Context context;
    private SoftReference<ImageView> imageView;//需要加载图片的控件
    private int resId;  //占位图片
    private RequestListener requestListener;//回调对象
    private String urlMd5;  //图片标示

    public SoftReference<ImageView> getImageView() {
        return imageView;
    }


    public BitmapRequest(Context context) {
        this.context = context;
    }

    public BitmapRequest load(String url) {
        this.url = url;
        if (!TextUtils.isEmpty(url)) this.urlMd5 = MD5.MD516(url);
        return this;
    }


    public BitmapRequest loadding(int resId) {
        this.resId = resId;
        return this;
    }

    public void into(ImageView imageView) {
        imageView.setTag(urlMd5);
        this.imageView = new SoftReference<>(imageView);
        RequestManager.getInstance(context).addBitmapREquest(this);
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public RequestListener getRequestListener() {
        return requestListener;
    }

    public BitmapRequest setRequestListener(RequestListener requestListener) {
        this.requestListener = requestListener;
        return this;
    }

    public String getUrlMd5() {
        return urlMd5;
    }

    public void setUrlMd5(String urlMd5) {
        this.urlMd5 = urlMd5;
    }
}
