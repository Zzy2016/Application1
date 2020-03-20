package com.example.application1.home_fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.application1.R;
import com.example.application1.adapter.HomeLineAdapter;
import com.example.application1.base.BaseFragment;
import com.example.application1.model.HomeTimeLineModel;
import com.example.application1.utils.PostAndGet;
import com.example.application1.utils.SharedPreferenceUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class HomeFragment2 extends BaseFragment {

    String path;
    String token = "";
    HomeTimeLineModel homeTimeLineModel;
    Gson gson;
    RecyclerView rvList;
    HomeLineAdapter adapter;
    List<HomeTimeLineModel.StatusesBean> homeList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_fragment2;
    }

    @Override
    public void initView(View view) {
        gson = new Gson();
        homeTimeLineModel = new HomeTimeLineModel();
        token = (String) SharedPreferenceUtils.getData("assess_token", "token");
        path = "https://api.weibo.com/2/statuses/home_timeline.json?access_token=" + token;
        rvList = view.findViewById(R.id.rv_list);
        homeList = new ArrayList<>();
        adapter = new HomeLineAdapter(homeList, getContext());
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void initData() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = PostAndGet.doGetHttp(path);
                Log.e("---------------", result);
                homeTimeLineModel = gson.fromJson(result, HomeTimeLineModel.class);
                homeList = homeTimeLineModel.getStatuses();
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    adapter.addList(homeList);
                    break;
            }
        }
    };


}
