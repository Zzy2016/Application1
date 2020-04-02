package com.example.sound.test;

/**
 * @author: Administrator
 * @date: 2020-03-31
 */
class Zimu implements Runnable{
    private Object obj;
    public Zimu(Object obj){
        this.obj = obj;
    }

    @Override
    public void run() {
        synchronized(obj){
            for(int i = 0;i < 26;i++ ){
                System.out.println((char)(i+'A'));
                obj.notifyAll();//唤醒其他线程
                try {
                    obj.wait();//释放锁等待
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }

}

