package com.example.application1.aboutimage;

import android.content.Context;


import com.example.application1.aboutimage.disk.DiskLruCache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author: Administrator
 * @date: 2020-03-20
 */
public class RequestManager {


    private static RequestManager requestManager;
    private LinkedBlockingQueue<BitmapRequest> requestQueue = new LinkedBlockingQueue<>();
    private BitmapDispatcher[] bitmapDispatchers;


    public ExecutorService executorService;//线程池管理线程

    private Context context;


    public static RequestManager getInstance(Context context) {
        if (requestManager == null) {
            synchronized (DiskLruCache.class) {
                if (requestManager == null) {
                    requestManager = new RequestManager(context);
                }
            }
        }
        return requestManager;
    }


    private RequestManager(Context context) {
        this.context = context;
        initThreadExecutor();
        start();
    }

    public void initThreadExecutor() {
        int size = Runtime.getRuntime().availableProcessors();
        if (size <= 0) {
            size = 1;
        }
        size *= 2;
        executorService = Executors.newFixedThreadPool(size);
    }

    public void start() {
        stop();
        startAllDispatcher();
    }

    public void addBitmapREquest(BitmapRequest bitmapRequest) {
        if (bitmapRequest == null) {
            return;
        }
        if (!requestQueue.contains(bitmapRequest)) {
            requestQueue.add(bitmapRequest);
        }
    }

    //处理并开始所有的线程
    public void startAllDispatcher() {
//获取线程最大数量
        final int threadCount = Runtime.getRuntime().availableProcessors();
        bitmapDispatchers = new BitmapDispatcher[threadCount];
        if (bitmapDispatchers.length > 0) {
            for (int i = 0; i < threadCount; i++) {
//                线程数量开辟的请求分发去抢资源对象 抢到处理
                BitmapDispatcher bitmapDispatcher = new BitmapDispatcher(requestQueue, context);
                executorService.execute(bitmapDispatcher);
//                将每一个dispatcher放到数组中，方便统一处理
                bitmapDispatchers[i] = bitmapDispatcher;
            }
        }
    }

    //停止所有的线程
    public void stop() {
        if (bitmapDispatchers != null && bitmapDispatchers.length > 0) {
            for (BitmapDispatcher bitmapDispatcher : bitmapDispatchers) {
                if (!bitmapDispatcher.isInterrupted()) {
                    bitmapDispatcher.interrupt();//中断
                }
            }
        }
    }

}
