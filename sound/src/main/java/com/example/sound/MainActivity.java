package com.example.sound;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {


    boolean type = false;//true 语音 false键盘
    ImageView imgSound;
    EditText etInput;
    Button btnSound;
    Button btnComplete;

    Button button1, button2;

    AudioUtil util;
    boolean isStart = false;//false  未录取 true 录取
    String filename;
    String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();
        imgSound = (ImageView) findViewById(R.id.img_sound);
        etInput = (EditText) findViewById(R.id.et_input);
        btnSound = (Button) findViewById(R.id.btn_sound);
        btnComplete = (Button) findViewById(R.id.btn_complete);

        imgSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = !type;
                switchInput();
            }
        });


        btnSound.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("MotionEvent", "ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e("MotionEvent", "ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("MotionEvent", "ACTION_UP");
                        break;
                }
                return false;
            }
        });

        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        util = new AudioUtil("audio");

        if(isStart){
            button1.setText("停止");
        }else{
            button1.setText("开始");
        }

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = !isStart;
                if (isStart) {
                    filename = System.currentTimeMillis() + ".wav";
                    util.startRecord();
                    util.recordData();
                    button1.setText("停止");
                } else {
                    util.stopRecord();
                    util.convertWaveFile(filename);
                    button1.setText("开始");
                }
            }
        });
    }


    public void switchInput() {
        if (type) {
            imgSound.setBackgroundResource(R.drawable.keymap);//显示键盘
            btnSound.setVisibility(View.VISIBLE);
            etInput.setVisibility(View.GONE);
        } else {
            imgSound.setBackgroundResource(R.drawable.sound);//显示键盘
            btnSound.setVisibility(View.GONE);
            etInput.setVisibility(View.VISIBLE);
        }
    }

    public void checkPermission() {
//       for(int i=0;i<permissions.length;i++){
//           if(ContextCompat.checkSelfPermission(this,permissions[0])!=PackageManager.PERMISSION_GRANTED){
//
//           }
//       }
        ActivityCompat.requestPermissions(MainActivity.this, permissions, 0);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
