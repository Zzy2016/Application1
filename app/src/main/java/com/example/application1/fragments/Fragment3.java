package com.example.application1.fragments;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.application1.R;
import com.example.application1.base.BaseFragment;
import com.example.application1.utils.PostAndGet;
import com.example.application1.utils.SharedPreferenceUtils;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Fragment3 extends Fragment {


    TextView tv;


    String token;
    String uid;
    OkHttpClient okHttpClient;
    Request request;
    Call call;
    String path;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_fragment3, container, false);
        View view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        token = (String) SharedPreferenceUtils.getData("assess_token", "token");
        uid = (String) SharedPreferenceUtils.getData("uid", "uid");
        path = "https://api.weibo.com/2/users/show.json?access_token=" + token + "&uid=" + uid;
        initView(view);
        return view;
    }

    public void initData() {
        okHttpClient = new OkHttpClient();
        request = new Request.Builder().url(path).get().build();
        call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("----1",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("----0",response.body().string());
            }
        });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String result= PostAndGet.doGetHttp(path);
//                Log.e("-----",result);
//            }
//        }).start();

    }

    public void initView(View view) {
        tv = (TextView) view.findViewById(R.id.tv);

        tv.setText(System.currentTimeMillis() + "");
        Log.e("---------", "initView");
        initData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            Log.e("---------", hidden + "");
            tv.setText(System.currentTimeMillis() + "");
            initData();
        }
    }
}
