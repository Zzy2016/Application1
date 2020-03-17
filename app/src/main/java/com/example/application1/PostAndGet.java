package com.example.application1;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

/**
 * @author: Administrator
 * @date: 2020-03-17
 */
public class PostAndGet {

    public static String doGetHttp(String path) {
        String result = "";

        try {
            URL url = new URL(Constants.path1);
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
                Log.e("getHttp", "123");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuffer stringBuffer = new StringBuffer();
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    stringBuffer.append(str);
                    Log.e("读取",str);
                }

            } else {
                Log.e("getHttp", "2354234" + httpURLConnection.getResponseCode());
            }

        } catch (Exception e) {
            Log.e("getHttp", e.toString());
        }

        return result;
    }
}
