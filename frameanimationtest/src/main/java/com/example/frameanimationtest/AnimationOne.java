package com.example.frameanimationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class AnimationOne extends AppCompatActivity {


    private static final String TAG = "ansen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_one);
        ImageView image = (ImageView) findViewById(R.id.image);


        AnimationDrawable animationDrawable = (AnimationDrawable) image.getDrawable();
        animationDrawable.start();
    }

}
