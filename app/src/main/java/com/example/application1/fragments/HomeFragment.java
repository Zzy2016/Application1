package com.example.application1.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.application1.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class HomeFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("HomeFragment------","onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.e("HomeFragment------","onCreateView");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("HomeFragment------","onResume");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("HomeFragment------","onStart");

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("HomeFragment------","onAttach");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("HomeFragment------","onDetach");

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("HomeFragment------","onHiddenChanged");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("HomeFragment------","onStop");
    }

    @Override
    public void onPause() {
        Log.e("HomeFragment------","onPause");
        super.onPause();
    }
}
