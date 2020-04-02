package com.example.sound;

import android.annotation.SuppressLint;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.File;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {


    MediaRecorder mediaRecorder;
    Button button;
    File file;
    boolean isRecord = false;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mediaRecorder = new MediaRecorder();
        button = (Button) findViewById(R.id.btn_media);


        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (button.getText().toString().equals("开始")) {//开始录音
                    button.setText("停止");
                    startRecord();
                    isRecord = true;
                    timeCount();
                } else {//结束录音
                    button.setText("开始");
                    stopRecord();
                    isRecord = false;
                }
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void startRecord() {
        if (mediaRecorder == null) {
            mediaRecorder = new MediaRecorder();
        } else {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置麦克风
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            String fileName = getExternalFilesDir("").getAbsolutePath() + "/" + System.currentTimeMillis() + ".m4a";
            try {
                file = new File(fileName);
                mediaRecorder.setOutputFile(file);
                mediaRecorder.prepare();
                mediaRecorder.start();
            } catch (Exception e) {

            }
        }


    }


    public void timeCount() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRecord) {
                    try {
                        Thread.sleep(1000);
                        Log.e("time---", ++count + " ");
                    } catch (Exception e) {

                    }
                }
            }
        }).start();
    }


    public void stopRecord() {
        try {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        } catch (RuntimeException e) {
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
