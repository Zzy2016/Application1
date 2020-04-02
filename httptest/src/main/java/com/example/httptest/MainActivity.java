package com.example.httptest;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.annotation.Target;

public class MainActivity extends AppCompatActivity {

    String path = "https://www.cnblogs.com/hankzhouAndroid/p/8710284.html";
    String tag = "main2tag";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testAsyncGetRequest();
    }

    private void testAsyncGetRequest() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new LoggingInterceptor()).build();
        final Request request = new Request.Builder().url(path).build();
        Callback callback = new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(tag, "request Failed ; " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Log.e(tag, "onResponse:" + response.body().string());
                } else {
                    Log.e(tag, "onResponse failed");
                }
            }
        };

        okHttpClient.newCall(request).enqueue(callback);
    }


    class LoggingInterceptor implements Interceptor {

        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            //第一步，获得chain内的request
            Request request = chain.request();
            Log.e("intercept", "intercept-request:" + request.url());
            //第二步，用chain执行request
            Response response = chain.proceed(request);
            Log.e("intercept", "intercept-response" + "-" + response.request().url());
            //第三步，返回response
            return response;
        }
    }

    
}
