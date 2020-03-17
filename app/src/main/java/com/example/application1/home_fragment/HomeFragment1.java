package com.example.application1.home_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.application1.PostAndGet;
import com.example.application1.R;
import com.example.application1.base.BaseFragment;


public class HomeFragment1 extends BaseFragment {

    String url="http://api.bilibili.cn/recommend";

    boolean isVisible=false;
    boolean isViewpre=false;
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
////        return super.onCreateView(inflater, container, savedInstanceState);
//        View view=inflater.inflate(R.layout.fragment_home_fragment1,container,false);
//        initView(view);
//        return view;
//    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_fragment1;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        new Thread(new Runnable() {
                @Override
                public void run() {
                    PostAndGet.doGetHttp(url);
                }
            }).start();
    }


//    public void initView(View view){
//        isViewpre=true;
//        initData();
//    }
//
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser){
//            isVisible=true;
//            initData();
//        }else{
//            isVisible=false;
//        }
//    }
//
//    public void initData(){
//        if(isVisible&&isViewpre){
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    PostAndGet.doGetHttp(url);
//                }
//            }).start();
//        }
//    }


}
