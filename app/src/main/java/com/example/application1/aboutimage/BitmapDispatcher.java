package com.example.application1.aboutimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: Administrator
 * @date: 2020-03-20
 */
public class BitmapDispatcher extends Thread {
    Handler handler = new Handler(Looper.getMainLooper());
    //阻塞进程
    private LinkedBlockingQueue<BitmapRequest> requestQueue;
    private DoubleLruCache doubleLruCache;


    public BitmapDispatcher(LinkedBlockingQueue<BitmapRequest> requestQueue, Context context) {
        this.requestQueue = requestQueue;
        doubleLruCache = new DoubleLruCache(context);
    }

    @Override
    public void run() {
        super.run();
//        该线程没有被中断
        while (!isInterrupted()) {
            if (requestQueue == null) {
                continue;
            }
            try {
                BitmapRequest bitmapRequest = requestQueue.take();
                if (bitmapRequest == null) {
                    continue;
                }
                showLoaddingImg(bitmapRequest);
                Bitmap bitmap = findBitmap(bitmapRequest);
                showImageView(bitmapRequest, bitmap);
            } catch (Exception e) {

            }
        }
    }


    /*
     * 设置图片
     * 比较图片是否为空
     * 空间是否为空
     * 图片名和空间tag是否匹配
     *
     *
     * */
    public void showImageView(final BitmapRequest bitmapRequest, final Bitmap bitmap) {
        final ImageView imageView = bitmapRequest.getImageView().get();
        if (bitmap != null && imageView != null && bitmapRequest.getUrlMd5().equals(imageView.getTag())) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageBitmap(bitmap);
                    RequestListener requestListener = bitmapRequest.getRequestListener();
                    if (requestListener != null) {
                        requestListener.onSuccess(bitmap);
                    }
                }
            });
        } else {
            RequestListener requestListener = bitmapRequest.getRequestListener();
            if (requestListener != null) {
                requestListener.onFaile();
            }
        }
    }

    /*
     * 在内存缓存和硬盘缓存中查找图片
     *  如果都没有，从网络下载图片，并且加入内存缓存LruCache
     *
     *
     * */
    private Bitmap findBitmap(BitmapRequest bitmapRequest) {
        Bitmap bitmap = null;
        bitmap = doubleLruCache.get(bitmapRequest);
        if (bitmap == null) {

            bitmap = downloadBitmap(bitmapRequest.getUrl());
            if (bitmap != null) {
                doubleLruCache.put(bitmapRequest, bitmap);
            }
        }
        return bitmap;
    }

    private void showLoaddingImg(BitmapRequest bitmapRequest) {
        final ImageView imageView = bitmapRequest.getImageView().get();
        if (bitmapRequest.getResId() > 0 && imageView != null) {
            final int resId = bitmapRequest.getResId();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(resId);
                }
            });
        }
    }

    private Bitmap downloadBitmap(String uri) {
        InputStream is = null;
        Bitmap bitmap = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            is = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }
}
