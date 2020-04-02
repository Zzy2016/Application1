package com.example.sound;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * @author: Administrator
 * @date: 2020-03-31
 */
public class PermissionUtil {
    public static String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};

    public static void requestPermission(Context context) {
        ActivityCompat.requestPermissions((Activity) context,permissions,1);
    }
}
