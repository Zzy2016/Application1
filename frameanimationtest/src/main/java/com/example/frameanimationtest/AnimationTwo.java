package com.example.frameanimationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class AnimationTwo extends AppCompatActivity {

    private static final String TAG = "ansen";

    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_two);
        final ImageView image = (ImageView) findViewById(R.id.image);

        // 每50ms一帧 循环播放动画
//        final FrameAnimation frameAnimation = new FrameAnimation(image, getRes(), 50, true);
//        frameAnimation.setAnimationListener(new FrameAnimation.AnimationListener() {
//            @Override
//            public void onAnimationStart() {
//                Log.d(TAG, "start");
//            }
//
//            @Override
//            public void onAnimationEnd() {
//                Log.d(TAG, "end");
//            }
//
//            @Override
//            public void onAnimationRepeat() {
//                Log.d(TAG, "repeat");
//            }
//        });
//
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // 实现点击 暂停和继续播放动画
//                if (frameAnimation.isPause()) {
//                    Log.d(TAG, "restart");
//                    frameAnimation.restartAnimation();
//                } else {
//                    Log.d(TAG, "pause");
//                    frameAnimation.pauseAnimation();
//                }
//            }
//        });

//        循环播放动画,循环间隔为4000ms
//        FrameAnimation frameAnimation = new FrameAnimation(image, getRes(), 50, 4000);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable=(AnimationDrawable) image.getBackground();
                animationDrawable.start();
            }
        });
    }

    /**
     * 获取需要播放的动画资源
     */
    private int[] getRes() {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.c);
        int len = typedArray.length();
        int[] resId = new int[len];
        for (int i = 0; i < len; i++) {
            resId[i] = typedArray.getResourceId(i, -1);
        }
        typedArray.recycle();
        return resId;
    }

}
