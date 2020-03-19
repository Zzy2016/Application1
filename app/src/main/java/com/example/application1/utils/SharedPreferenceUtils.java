package com.example.application1.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * @author: Administrator
 * @date: 2020-03-19
 */
public class SharedPreferenceUtils {


    private static SharedPreferenceUtils sharedPreferenceUtils;
    private static SharedPreferences sharedPreferences;

    public SharedPreferenceUtils(Context context, String name) {
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static void getInstance(Context context, String name) {
        if (sharedPreferenceUtils == null) {
            sharedPreferenceUtils = new SharedPreferenceUtils(context, name);
        }
    }


    public static void putData(String key, Object value) {
        boolean result;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String type = value.getClass().getSimpleName();
        switch (type) {
            case "Boolean":
                editor.putBoolean(key, (Boolean) value);
                break;
            case "Long":
                editor.putLong(key, (Long) value);
                break;
            case "Float":
                editor.putFloat(key, (Float) value);
                break;
            case "String":
                editor.putString(key, (String) value);
                break;
            case "Integer":
                editor.putInt(key, (Integer) value);
                break;
        }
        editor.commit();
    }

    public static Object getData(String key, Object defaultValue) {
        Object result;
        String type = defaultValue.getClass().getSimpleName();
        try {
            switch (type) {
                case "Boolean":
                    result = sharedPreferences.getBoolean(key, (Boolean) defaultValue);
                    break;
                case "Long":
                    result = sharedPreferences.getLong(key, (Long) defaultValue);
                    break;
                case "Float":
                    result = sharedPreferences.getFloat(key, (Float) defaultValue);
                    break;
                case "String":
                    result = sharedPreferences.getString(key, (String) defaultValue);
                    break;
                case "Integer":
                    result = sharedPreferences.getInt(key, (Integer) defaultValue);
                    break;
                default:
                    Gson gson = new Gson();
                    String json = sharedPreferences.getString(key, "");
                    if (!json.equals("") && json.length() > 0) {
                        result = gson.fromJson(json, defaultValue.getClass());
                    } else {
                        result = defaultValue;
                    }
                    break;
            }
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result;

    }


}
