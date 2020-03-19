package com.example.application1;

import android.app.Application;

import com.example.application1.utils.SharedPreferenceUtils;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

/**
 * @author: Administrator
 * @date: 2020-03-19
 */
public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        WbSdk.install(this, new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE));
        SharedPreferenceUtils.getInstance(this, "app");


    }


}
