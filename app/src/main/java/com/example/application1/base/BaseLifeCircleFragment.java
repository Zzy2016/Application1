package com.example.application1.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author: Administrator
 * @date: 2020-03-17
 */
public abstract class BaseLifeCircleFragment extends Fragment {
    public static final String TAG =  "BaseLifeCircleFragment";
    protected View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);        
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.e(TAG, getClass().getSimpleName() + "  onCreate getParentFragment  " + (getParentFragment() == null));        
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {        
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutRes(), container, false);
        }
        initView(rootView);
        return rootView;
    }


    /**
     * 返回布局 resId
     *
     * @return layoutId
     */
    protected abstract int getLayoutRes();


    /**
     * 初始化view
     *
     * @param rootView
     */
    protected abstract void initView(View rootView);

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
//        Log.e(TAG,getClass().getSimpleName() + "  onAttachFragment");
//        Log.e(TAG, getClass().getSimpleName() + "  onAttachFragment getParentFragment  " + (getParentFragment() == null));
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e(TAG,getClass().getSimpleName() + "  setUserVisibleHint " + isVisibleToUser);
//        Log.e(TAG,getClass().getSimpleName() + "  isResumed() " + isResumed());
//        Log.e(TAG,getClass().getSimpleName() + "  isAdded() " + isAdded());
//        Log.e(TAG, getClass().getSimpleName() + "  setUserVisibleHint getParentFragment != null  " + (getParentFragment() != null));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e(TAG,getClass().getSimpleName() + "  onHiddenChanged " + hidden);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TAG,getClass().getSimpleName() + "  onActivityCreated ");
//        Log.e(TAG, getClass().getSimpleName() + "  onActivityCreated getParentFragment != null  " + (getParentFragment() != null));
    }

    @Override
    public void onStart() {
        super.onStart();
//        Log.e(TAG,getClass().getSimpleName() + "  onStart ");
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.e(TAG,getClass().getSimpleName() + " onResume  ");
//        Log.e(TAG,getClass().getSimpleName() + "   fragment.getUserVisibleHint() = "  + getUserVisibleHint());
//        Log.e(TAG, getClass().getSimpleName() + "  onResume getParentFragment != null  " + (getParentFragment() != null));

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(TAG,getClass().getSimpleName() + "  onPause ");
//        Log.e(TAG,getClass().getSimpleName() + "   fragment.getUserVisibleHint() = "  + getUserVisibleHint());
//        Log.e(TAG, getClass().getSimpleName() + "  onPause getParentFragment != null  " + (getParentFragment() != null));

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(TAG,getClass().getSimpleName() + "  onStop ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG,getClass().getSimpleName() + "  onDestroyView ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG,getClass().getSimpleName() + "  onDestroy ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TAG,getClass().getSimpleName() + "  onDetach ");
    }
}
