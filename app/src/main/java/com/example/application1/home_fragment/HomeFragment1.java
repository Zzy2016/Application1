package com.example.application1.home_fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;

import com.example.application1.PostAndGet;
import com.example.application1.R;
import com.example.application1.adapter.RecommendAdapter;
import com.example.application1.base.BaseFragment;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import model.RecommendModel;

//getLayoutId
//initView
//initData
public class HomeFragment1 extends BaseFragment {

    String url = "http://api.bilibili.cn/recommend";
    RecyclerView rvList;
    RecommendAdapter adapter;
    RecommendModel recommendModel;
    List<RecommendModel.ListBean> recommendList;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_fragment1;
    }

    @Override
    public void initView(View view) {
        rvList = view.findViewById(R.id.rv_list);
        recommendList = new ArrayList<>();
        adapter = new RecommendAdapter(recommendList, getContext());
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        FlexboxLayoutManager flexboxLayoutManager=new FlexboxLayoutManager();
//        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
//        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
//        flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);
//        rvList.setLayoutManager(flexboxLayoutManager);
    }


    @Override
    public void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = PostAndGet.doGetHttp(url);
                Gson gson = new Gson();

                recommendModel = gson.fromJson(result, RecommendModel.class);
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
//                    recommendList.addAll(recommendModel.getList());
//                    adapter.notifyDataSetChanged();
                    adapter.addList(recommendModel.getList());
                    break;
            }
        }
    };


}
