package com.example.sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.EventListener;

public class Main2Activity extends AppCompatActivity {


    int audioSource = MediaRecorder.AudioSource.MIC;
    int sampleRateInHz = 16000;
    int channelConfig = AudioFormat.CHANNEL_IN_MONO;
    int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
    int bufferSizeInBytes;

    AudioRecord audioRecord;


    Button btnSound;
    File sourceFile;
    String rootPath;

    boolean isRecord = false;//
    String filename;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        PermissionUtil.requestPermission(Main2Activity.this);
//        AudioRecord audioRecord = new AudioRecord();
        bufferSizeInBytes = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);
        audioRecord = new AudioRecord(audioSource, sampleRateInHz, channelConfig, audioFormat, bufferSizeInBytes);


        rootPath = getExternalFilesDir("").getAbsolutePath();
        Log.e("123", rootPath);

        btnSound = (Button) findViewById(R.id.btn_sound);
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnSound.getText().toString().equals("停止")) {
                    isRecord = false;
                    btnSound.setText("开始");
                    audioRecord.stop();
                    Log.e("录音", "tingzhi");
//                    convertWaveFile(System.currentTimeMillis() + ".wav");
//                    pcm2amr(sourceFile.getAbsolutePath());
                    pcm2amr1(sourceFile.getAbsolutePath());
                } else {
                    Log.e("录音", "开始");
                    btnSound.setText("停止");
                    isRecord = true;
                    audioRecord.startRecording();
                    readFile();

                }
            }
        });

    }

    public void readFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                name = rootPath + "/" + System.currentTimeMillis();
                sourceFile = new File(name + ".pcm");
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(sourceFile);
                    byte[] buffer = new byte[bufferSizeInBytes];
                    int length = audioRecord.read(buffer, 0, buffer.length);
                    while (isRecord) {
                        if (length > 0) {
                            Log.e("123", "123" + System.currentTimeMillis());
                            fileOutputStream.write(buffer);
                        }
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void pcm2amr1(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            File file = new File(name + ".amr");
//            FileOutputStream fileOutputStream = new FileOutputStream(file);

            AmrInputStream ais = new AmrInputStream(fileInputStream);
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[4096];
            int len = -1;
            /*
             * 下面的amr的文件头
             * 缺少这几个字节是不行的
             */
            out.write(0x23);
            out.write(0x21);
            out.write(0x41);
            out.write(0x4D);
            out.write(0x52);
            out.write(0x0A);
            while ((len = ais.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            ais.close();
        } catch (Exception e) {

        }
    }


//    public void pcm2amr(String path) {
//        try {
//            File file = new File(name + ".amr");
//            FileInputStream fileInputStream = new FileInputStream(path);
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//// 获得Class
//            Class<?> cls = Class.forName("android.media.AmrInputStream");
//            // 通过Class获得所对应对象的方法
//            Method[] methods = cls.getMethods();
//            // 输出每个方法名
//            fileOutputStream.write(new byte[]{0x23, 0x21, 0x41, 0x4D, 0x52, 0x0A});//amr头文件
//            Constructor<?> con = cls.getConstructor(InputStream.class);
//            Object obj = con.newInstance(fileInputStream);
//            for (Method method : methods) {
//                Class<?>[] parameterTypes = method.getParameterTypes();
//                if ("read".equals(method.getName()) && parameterTypes.length == 3) {
//                    byte[] buf = new byte[1024];
//                    int len = 0;
//                    while ((len = (int) method.invoke(obj, buf, 0, 1024)) > 0) {
//                        fileOutputStream.write(buf, 0, len);
//                    }
//                    break;
//                }
//            }
//            for (Method method : methods) {
//                if ("close".equals(method.getName())) {
//                    method.invoke(obj);
//                    break;
//                }
//            }
//            fileOutputStream.close();
//
//
//        } catch (Exception e) {
//
//        }
//    }

}
