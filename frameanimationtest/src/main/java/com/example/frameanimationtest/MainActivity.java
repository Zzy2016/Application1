package com.example.frameanimationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickOne(View v) {
        startActivity(new Intent(this, AnimationOne.class));
    }

    public void clickTwo(View v) {
        startActivity(new Intent(this, AnimationTwo.class));
    }
}
