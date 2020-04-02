package com.example.sound.test;

import android.renderscript.Allocation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author: Administrator
 * @date: 2020-03-31
 */
public class Thread1 extends Thread {


    Object object;

    public Thread1(Object object) {
        this.object = object;
    }

    public void run() {
        synchronized (object){
            for (int i = 1; i < 53; i++) {
                System.out.println(i);
                if (i % 2 == 0) {
                    object.notifyAll();
                    try {

                        object.wait();
                    } catch (Exception e) {

                    }
                }
            }
        }
    }

//    public synchronized void run() {
//        for (int i = 1; i < 53; i++) {
//            System.out.println(i);
//            if (i % 2 == 0) {
//                object.notifyAll();
//                try {
//                    object.wait();
//                } catch (Exception e) {
//
//                }
//            }
//        }
//
//    }
}
