package com.example.sound.test;

/**
 * @author: Administrator
 * @date: 2020-03-31
 */
class Shuzi implements Runnable{
    private Object obj;//声明一个类的引用
    public Shuzi(Object obj){
        this.obj = obj;	//通过构造器将共享的资源-->对象传进来
    }

    @Override
    public void run() {
        synchronized(obj){//给共享资源上锁
            for(int i = 1;i < 53;i++ ){
                System.out.println(i);
                if(i % 2 == 0){
                    obj.notifyAll();//唤醒其他线程

                    try {
                        obj.wait();//等待并释放锁
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}

