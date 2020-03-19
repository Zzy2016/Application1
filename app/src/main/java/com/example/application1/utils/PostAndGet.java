package com.example.application1.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author: Administrator
 * @date: 2020-03-17
 */
public class PostAndGet {

    public static String doGetHttp(String path) {
        String result = "";

        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("accept", "application/json");
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                Log.e("请求数据1---->", "Code"+httpURLConnection.getResponseCode());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    stringBuffer.append(str);

                    Log.e("请求数据2---->", str);

                }
                result=stringBuffer.toString();

            } else {

                Log.e("请求数据3---->", "Code"+httpURLConnection.getResponseCode());

            }

        } catch (Exception e) {
//            Log.e("getHttp", e.toString());
            Log.e("请求数据4---->",  e.toString());

        }

        return result;
    }
}
