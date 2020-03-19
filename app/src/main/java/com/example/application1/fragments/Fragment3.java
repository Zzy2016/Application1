package com.example.application1.fragments;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.application1.R;
import com.example.application1.base.BaseFragment;
import com.example.application1.model.HomeTimeLineModel;
import com.example.application1.utils.PostAndGet;
import com.example.application1.utils.SharedPreferenceUtils;
import com.google.gson.Gson;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Fragment3 extends Fragment {



    private String token;
    private String uid;
    private OkHttpClient okHttpClient;
    private Request request;
    private Call call;
    private String path;
    private Gson gson;
    private HomeTimeLineModel homeTimeLineModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gson = new Gson();
        homeTimeLineModel = new HomeTimeLineModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("----1", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    homeTimeLineModel = gson.fromJson(result, HomeTimeLineModel.class);


                } else {
                    Toast.makeText(getContext(), "获取信息失败", Toast.LENGTH_SHORT).show();
                }
                Log.e("----0", response.body().string());
            }
        });
    }




    public void initView(View view) {

        Log.e("---------", "initView");
        initData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            Log.e("---------", hidden + "");

            initData();
        }
    }
}
