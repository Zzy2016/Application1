package com.example.application1.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.application1.Constants;
import com.example.application1.R;
import com.example.application1.utils.PostAndGet;
import com.example.application1.utils.SharedPreferenceUtils;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import java.util.regex.Matcher;

public class LoginActivity extends AppCompatActivity {


    SsoHandler ssoHandler;

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        token = (String) SharedPreferenceUtils.getData("assess_token", "token");



        if (token.equals("token")) {
            ssoHandler = new SsoHandler(LoginActivity.this);
            ssoHandler.authorizeClientSso(new WbAuthListener() {
                @Override
                public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
                    Log.e("login-----1", oauth2AccessToken.getToken());
                }

                @Override
                public void cancel() {
                    Log.e("login-----2", "cancel");
                }

                @Override
                public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
                    Log.e("login-----3", wbConnectErrorMessage.getErrorMessage());
                }
            });
        } else {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
////                https://api.weibo.com/2/statuses/home_timeline.json?access_token=
//                    String url = "https://api.weibo.com/2/statuses/home_timeline.json?access_token=" + token;
//                    String result = PostAndGet.doGetHttp(url);
//                    Log.e("result--", result);
//                }
//            }).start();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
            Log.e("login---4", requestCode + "  " + resultCode + " " + data);
            Intent intent = data;
            Bundle bundle = data.getExtras();
//            Log.e("login----4", bundle.getString("_weibo_transaction"));
//            Log.e("login----4", bundle.getString("access_token"));
//            Log.e("login----4", bundle.getString("refresh_token"));
//            Log.e("login----4", bundle.getString("expires_in"));
//            Log.e("login----4", bundle.getString("_weibo_appPackage"));
//            Log.e("login----4", bundle.getString("com.sina.weibo.intent.extra.NICK_NAME"));
//            Log.e("login----4", bundle.getString("userName"));
//            Log.e("login----4", bundle.getString("uid"));
//            Log.e("login----4", bundle.getString("com.sina.weibo.intent.extra.USER_ICON"));
            SharedPreferenceUtils.putData("assess_token", bundle.getString("access_token"));
            SharedPreferenceUtils.putData("uid",bundle.getString("uid"));
            Intent intent1 = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent1);
            finish();
        }
    }
}
