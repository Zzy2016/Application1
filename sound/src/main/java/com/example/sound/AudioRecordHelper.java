package com.example.sound;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author: Administrator
 * @date: 2020-03-31
 */
public class AudioRecordHelper {

    private AudioRecord audioRecord = null;

    private int recordBufSize = 0;//缓存大小
    private int mAudioSampleRate = 16000;//采样率
    private int mAudioSource = MediaRecorder.AudioSource.MIC;//音频源
    private int mAudioFormat = AudioFormat.ENCODING_PCM_16BIT;//编码大小
    private int mAudioChannel = AudioFormat.CHANNEL_IN_MONO;//声道


    private boolean mWhetherRecord;

    public AudioRecordHelper() {
        recordBufSize = AudioRecord.getMinBufferSize(mAudioSampleRate, mAudioChannel, mAudioFormat);
        audioRecord = new AudioRecord(mAudioSource, mAudioSampleRate, mAudioChannel, mAudioFormat, recordBufSize);
        Log.e("123", recordBufSize + "");
    }

    private void startRecord() {
        final File file = new File(Environment.getDataDirectory().getPath(), "file");
        mWhetherRecord = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                audioRecord.startRecording();
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    byte[] bytes = new byte[120000];
                    while (mWhetherRecord) {
                        audioRecord.read(bytes, 0, bytes.length);
                        fileOutputStream.write(bytes);
                        fileOutputStream.flush();
                    }
                    audioRecord.stop();
                    fileOutputStream.flush();
                    fileOutputStream.close();

                } catch (Exception e) {

                }

            }
        }).start();
    }


}
