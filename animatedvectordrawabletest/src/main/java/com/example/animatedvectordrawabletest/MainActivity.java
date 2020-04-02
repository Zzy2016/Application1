package com.example.animatedvectordrawabletest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.nio.InvalidMarkException;

public class MainActivity extends AppCompatActivity {

    AnimatedVectorDrawable animatedVectorDrawable;
    ImageView imageView;
    boolean isPlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatedVectorDrawable = (AnimatedVectorDrawable) imageView.getBackground();
                animatedVectorDrawable.start();
                isPlay = true;

                if (isPlay) {

                } else {

                }

            }
        });

    }
}
