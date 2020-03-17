package com.example.application1;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

        homeFragment = new HomeFragment();
        home1Fragment = new Home1Fragment();


        changePage(0);

    }

    public void changePage(int index) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_content, homeFragment);
        fragmentTransaction.add(R.id.fl_content, home1Fragment);

        fragmentTransaction.show(homeFragment).hide(home1Fragment).commit();
        switch (index){
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
        }

    }
}
