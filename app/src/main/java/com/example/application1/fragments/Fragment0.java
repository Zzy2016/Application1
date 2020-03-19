package com.example.application1.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.application1.R;
import com.example.application1.base.BaseFragment;
import com.example.application1.home_fragment.HomeFragment1;
import com.example.application1.home_fragment.HomeFragment2;
import com.example.application1.home_fragment.HomeFragment3;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class Fragment0 extends BaseFragment {

    ImageView imageMenu;
    TabLayout tlTitle;
    ViewPager vpHome;
    List<androidx.fragment.app.Fragment> fragmentList;
    HomeAdapter homeAdapter;
    String[] titles = new String[]{"title1", "title2", "title3"};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeAdapter = new HomeAdapter(getChildFragmentManager());
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment1());
        fragmentList.add(new HomeFragment2());
        fragmentList.add(new HomeFragment3());
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    public void initView(View view) {
        imageMenu = view.findViewById(R.id.image_menu);

        tlTitle = view.findViewById(R.id.tl_title);

        vpHome = view.findViewById(R.id.vp_home);
        vpHome.setAdapter(homeAdapter);
        tlTitle.setupWithViewPager(vpHome);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("Fragment0------", "onHiddenChanged");

    }

    class HomeAdapter extends FragmentPagerAdapter {

        public HomeAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public androidx.fragment.app.Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
