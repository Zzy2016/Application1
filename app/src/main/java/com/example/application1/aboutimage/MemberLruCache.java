package com.example.application1.aboutimage;


import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * @author: Administrator
 * @date: 2020-03-20
 * <p>
 * 内存缓存通过LruCache实现
 * 使用强引用容易造成内存溢出
 * Android2.3之后，系统会优先考虑回收弱引用，官方建议使用lruCache
 */
public class MemberLruCache implements BitmapCache {
    private LruCache<String, Bitmap> lruCache;
    private static volatile MemberLruCache instance;
    private static final byte[] lock = new byte[0];


//    HashMap<String, Bitmap> mMemberCache = new HashMap<>();  //强引用
//    HashMap<String, SoftReference<Bitmap>> mMemberCache = new HashMap<>();  //弱引用

    public static MemberLruCache getInstance() {
        if (instance == null) {
            synchronized (MemberLruCache.class) {
                if (instance == null) {
                    instance = new MemberLruCache();
                }
            }
        }
        return instance;
    }


    /*
     *
     * 最大内存
     *
     * bitmap占用空间 Bitmap.getByteCount()=Bitmap.getRowBytes()*Bitmap.getHeight()
     *
     * getRowBytes() 每一行所占的空间数
     *
     *
     * */
    public MemberLruCache() {
        int maxMemorySize = (int) (Runtime.getRuntime().maxMemory() / 16);
        if (maxMemorySize <= 0) {
            maxMemorySize = 10 * 1024 * 1024;
        }
        lruCache = new LruCache<String, Bitmap>(maxMemorySize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }


    @Override
    public void put(BitmapRequest bitmapRequest, Bitmap bitmap) {
        if (bitmap != null) {
            lruCache.put(bitmapRequest.getUrlMd5(), bitmap);
        }
    }

    /*
     * 从内存中读取图片
     * */
    @Override
    public Bitmap get(BitmapRequest bitmapRequest) {
        return lruCache.get(bitmapRequest.getUrlMd5());
    }

    @Override
    public void remove(BitmapRequest bitmapRequest) {
        lruCache.remove(bitmapRequest.getUrlMd5());
    }
}
