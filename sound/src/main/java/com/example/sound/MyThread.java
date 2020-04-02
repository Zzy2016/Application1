package com.example.sound;

import android.os.Looper;

/**
 * @author: Administrator
 * @date: 2020-03-31
 */
public class MyThread extends Thread {

    @Override
    public void run() {
        Looper.prepare();

    }
}
