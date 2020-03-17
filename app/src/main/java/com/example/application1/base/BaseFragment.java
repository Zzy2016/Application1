package com.example.application1.base;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author: Administrator
 * @date: 2020-03-16
 */
public abstract class BaseFragment extends Fragment {


    private boolean isViewPre = false;
    private boolean isVisible = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view);
        isViewPre = true;
//        loadData();
        return view;
    }

    public abstract int getLayoutId();

    public abstract void initView(View view);

    public abstract void initData();

//    public void loadData(){
//        if(isVisible&&isViewPre){
//            initData();
//        }
//    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewPre) {
            initData();
            isVisible = true;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isViewPre = false;
    }
}
