package com.example.application1.home_fragment;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.application1.R;
import com.example.application1.base.BaseFragment;

import okhttp3.OkHttpClient;


public class HomeFragment3 extends BaseFragment {

    TextView textView;
    String token;

    //    access_token uid
    String path = "https://api.weibo.com/2/users/show.json";


    OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_fragment3;
    }

    @Override
    public void initView(View view) {
        textView = view.findViewById(R.id.tv_time);
        textView.setText("fragment1");
        Log.e("HomeFragment3", "initView");
    }

    @Override
    public void initData() {
        Log.e("HomeFragment3", "initData");
        textView.setText("fragment1---initData");
    }
}
