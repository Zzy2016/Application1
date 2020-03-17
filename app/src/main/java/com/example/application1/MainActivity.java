package com.example.application1;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.application1.fragments.Fragment2;
import com.example.application1.fragments.Fragment3;
import com.example.application1.fragments.Home1Fragment;
import com.example.application1.fragments.HomeFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {


    FrameLayout flContent;
    RadioGroup rgGroup;
    RadioButton rbHome1, rbHome2, rbHome3, rbHome4;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    HomeFragment homeFragment;
    Home1Fragment home1Fragment;
    Fragment2 fragment2;
    Fragment3 fragment3;
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        flContent = (FrameLayout) findViewById(R.id.fl_content);
        rgGroup = (RadioGroup) findViewById(R.id.rg_group);
        rbHome1 = (RadioButton) findViewById(R.id.rb_home1);
        rbHome2 = (RadioButton) findViewById(R.id.rb_home2);
        rbHome3 = (RadioButton) findViewById(R.id.rb_home3);
        rbHome4 = (RadioButton) findViewById(R.id.rb_home4);

        rbHome1.setOnClickListener(onClickListener);
        rbHome2.setOnClickListener(onClickListener);
        rbHome3.setOnClickListener(onClickListener);
        rbHome4.setOnClickListener(onClickListener);


        changePage(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                PostAndGet.doGetHttp(Constants.path);
            }
        }).start();

    }

    public void changePage(int index) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        hideAll(fragmentTransaction);
        if (index == 0) {
            if (homeFragment == null) {
                homeFragment = new HomeFragment();
                fragmentTransaction.add(R.id.fl_content, homeFragment).show(homeFragment).commit();
            } else {
                fragmentTransaction.show(homeFragment).commit();
            }
        } else if (index == 1) {
            if (home1Fragment == null) {
                home1Fragment = new Home1Fragment();
                fragmentTransaction.add(R.id.fl_content, home1Fragment).show(home1Fragment).commit();
            } else {
                fragmentTransaction.show(home1Fragment).commit();
            }
        } else if (index == 2) {
            if (fragment2 == null) {
                fragment2 = new Fragment2();
                fragmentTransaction.add(R.id.fl_content, fragment2).show(fragment2).commit();
            } else {
                fragmentTransaction.show(fragment2).commit();
            }
        } else if (index == 3) {
            if (fragment3 == null) {
                fragment3 = new Fragment3();
                fragmentTransaction.add(R.id.fl_content, fragment3).show(fragment3).commit();
            } else {
                fragmentTransaction.show(fragment3).commit();
            }
        }
    }


    public void hideAll(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) fragmentTransaction.hide(homeFragment);
        if (home1Fragment != null) fragmentTransaction.hide(home1Fragment);
        if (fragment2 != null) fragmentTransaction.hide(fragment2);
        if (fragment3 != null) fragmentTransaction.hide(fragment3);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rb_home1:
                    if (currentIndex == 0) {
                        return;
                    }
                    changePage(0);
                    currentIndex = 0;
                    break;
                case R.id.rb_home2:
                    if (currentIndex == 1) {
                        return;
                    }
                    changePage(1);
                    currentIndex = 1;
                    break;
                case R.id.rb_home3:
                    if (currentIndex == 2) {
                        return;
                    }
                    changePage(2);
                    currentIndex = 2;
                    break;
                case R.id.rb_home4:
                    if (currentIndex == 3) {
                        return;
                    }
                    changePage(3);
                    currentIndex = 3;
                    break;
            }
        }
    };
}
