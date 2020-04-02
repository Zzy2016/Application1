package com.example.sendsound;

import android.Manifest;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayAdapter<Recorder> mAdapter;
    private List<Recorder> mDatas = new ArrayList<Recorder>();

    private AudioRecorderButton mAudioRecorderButton;

    private View mAnimView;
    public String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActivityCompat.requestPermissions(this, permissions, 1);



//        mListView = (ListView) findViewById(R.id.id_listview);
        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.id_recorder_button);
        mAudioRecorderButton.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(float seconds, String filePath) {
                Recorder recorder = new Recorder(seconds, filePath);
//                mDatas.add(recorder);
//                mAdapter.notifyDataSetChanged();
//                mListView.setSelection(mDatas.size() - 1);
            }
        });
//
//        mAdapter = new RecorderAdapter(this, mDatas);
//        mListView.setAdapter(mAdapter);
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
//
//                if (mAnimView != null) {
//                    mAnimView.setBackgroundResource(R.drawable.adj);
//                    mAnimView = null;
//                }
//                // 播放动画
//                mAnimView = view.findViewById(R.id.id_recorder_anim);
//                mAnimView.setBackgroundResource(R.drawable.play_anim);
//                AnimationDrawable anim = (AnimationDrawable) mAnimView.getBackground();
//                anim.start();
//                // 播放音频
//                MediaManager.playSound(mDatas.get(position).getFilePath(), new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        mAnimView.setBackgroundResource(R.drawable.adj);
//                    }
//                });
//            }
//        });
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        MediaManager.pause();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        MediaManager.resume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        MediaManager.release();
//    }
}
