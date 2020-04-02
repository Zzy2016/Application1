package com.example.application1.utils;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author: Administrator
 * @date: 2020-03-25
 */
public class OkHttpHelper {
    /**
     * 采用单例模式使用OkHttpClient
     */
    private static OkHttpHelper okHttpHelper;
    private static OkHttpClient okHttpClient;
    private Handler mHandler;
    private Gson gson;

    /**
     * 单例模式，私有构造函数，构造函数里面进行一些初始化
     */
    private OkHttpHelper() {
        okHttpClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(20, TimeUnit.SECONDS).build();
        gson = new Gson();
        mHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * 获取实例
     *
     * @return
     */
    public static OkHttpHelper getinstance() {

        if (okHttpHelper == null) {
            synchronized (OkHttpHelper.class) {
                if (okHttpHelper == null) {
                    okHttpHelper = new OkHttpHelper();
                }
            }

        }
        return okHttpHelper;
    }

    private void request(final Request request, final BaseCallback callback) {
        callback.onRequestBefore();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure( Call call,  IOException e) {
                callbackFailure(request, callback, e);
            }

            @Override
            public void onResponse( Call call,  Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    if (callback.mType == String.class) {
                        callback.onSuccess(response, result);
                    } else {
                        try {
                            Object object = gson.fromJson(result, callback.mType);
                            callbackSuccess(response, object, callback);
                        } catch (Exception e) {

                            callback.onError(response, 1, e);
                        }
                    }
                } else {
                    callbackFailure(request, callback, null);
                }
            }
        });
    }


    private void callbackSuccess(final Response response, final Object o, final BaseCallback callback) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(response, o);
            }
        });
    }

    private void callbackFailure(final Request request, final BaseCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onFailure(request, e);
            }
        });
    }

    private void callbackError(final Response response, final BaseCallback callback, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(response, response.code(), e);
            }
        });
    }

    public void get(String url, BaseCallback callback) {
        Request request = buildRequest(url, null, HttpMethodType.GET);
        request(request, callback);
    }

    public void post(String url, RequestBody requestBody, BaseCallback callback) {
        Request request = buildRequest(url, requestBody, HttpMethodType.POST);
        request(request, callback);
    }

    private Request buildRequest(String url, RequestBody requestBody, HttpMethodType httpMethodType) {
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (httpMethodType == HttpMethodType.POST) {
            builder.post(requestBody);
        } else {
            builder.get();
        }
        return builder.build();
    }

    enum HttpMethodType {
        GET, POST
    }


}
