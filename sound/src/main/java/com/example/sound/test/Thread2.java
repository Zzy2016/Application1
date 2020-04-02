package com.example.sound.test;

import androidx.annotation.NonNull;

/**
 * @author: Administrator
 * @date: 2020-03-31
 */
public class Thread2 extends Thread{

    Object object;

    public Thread2(Object object) {
        this.object = object;
    }

    public  void run() {
       synchronized (object){
           for (int i = 0; i < 26; i++) {
               System.out.println("*");
               object.notifyAll();
               try {
                   object.wait();
               } catch (Exception e) {

               }
           }
       }
    }
}
