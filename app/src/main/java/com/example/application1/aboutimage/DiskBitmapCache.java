package com.example.application1.aboutimage;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.example.application1.aboutimage.disk.DiskLruCache;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * @author: Administrator
 * @date: 2020-03-20
 */
public class DiskBitmapCache implements BitmapCache {

    private DiskLruCache diskLruCache;
    private static volatile DiskBitmapCache instance;
    private String imageCachePath = "image";
    private static final byte[] lock = new byte[0];
    private int MB = 1024 * 1024;
    private int maxDiskSize = 50 * MB;

    public static DiskBitmapCache getInstance(Context context) {
        if (instance == null) {
            synchronized (DiskBitmapCache.class) {
                if (instance == null) {
                    instance = new DiskBitmapCache(context);
                }
            }
        }
        return instance;
    }

    public DiskBitmapCache(Context context) {
        File cacheFile = getImageCacheFile(context, imageCachePath);
        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }
        try {
            diskLruCache = DiskLruCache.open(cacheFile, getAppVersion(context), 1, maxDiskSize);
        } catch (Exception e) {

        }
    }


    public File getImageCacheFile(Context context, String imageCachePath) {
        String path;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            path = context.getExternalCacheDir().getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        return new File(path + File.separator + imageCachePath);

    }

    public int getAppVersion(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {

        }
        return 0;
    }


    public boolean presetBitmap2Disk(OutputStream outputStream, Bitmap bitmap) {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


    @Override
    public void put(BitmapRequest bitmapRequest, Bitmap bitmap) {
        DiskLruCache.Editor editor;
        OutputStream outputStream = null;
        try {
            editor = diskLruCache.edit(bitmapRequest.getUrlMd5());
            outputStream = editor.newOutputStream(0);
            if (presetBitmap2Disk(outputStream, bitmap)) {
                editor.commit();
            } else {
                editor.abort();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Bitmap get(BitmapRequest bitmapRequest) {
        InputStream stream = null;
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(bitmapRequest.getUrlMd5());
            if (snapshot != null) {
                stream = snapshot.getInputStream(0);
                return BitmapFactory.decodeStream(stream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Exception e) {

                }
            }
        }
        return null;
    }

    @Override
    public void remove(BitmapRequest bitmapRequest) {
        try {
            diskLruCache.remove(bitmapRequest.getUrlMd5());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
