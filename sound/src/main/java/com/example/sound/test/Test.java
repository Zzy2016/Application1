package com.example.sound.test;

/**
 * @author: Administrator
 * @date: 2020-03-31
 */
public class Test {
    public static void main(String[] args) {


//        Object object = new Object();
//        Shuzi s = new Shuzi(object);
//        Zimu z = new Zimu(object);
//        new Thread(s).start();
//        new Thread(z).start();

        Object object = new Object();
        new Thread1(object).start();
        new Thread2(object).start();
    }
}
