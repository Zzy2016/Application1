package com.example.sound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class Main4Activity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }


    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            Looper.prepare();//为当前线程创建 looper
            handler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                }
            };
            Looper.getMainLooper();//不断循环读取 Message
        }
    }

}
