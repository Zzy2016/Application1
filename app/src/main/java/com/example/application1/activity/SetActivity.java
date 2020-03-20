package com.example.application1.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.application1.R;
import com.example.application1.aboutimage.Glide;

import androidx.appcompat.app.AppCompatActivity;

public class SetActivity extends AppCompatActivity {

    ImageView imageView;

    String path = "https://tvax3.sinaimg.cn/crop.0.0.996.996.50/007bwwI2ly8g6ailu299uj30ro0rognc.jpg?KID=imgbed,tva&Expires=1584702121&ssig=oIa3EoVlna";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        imageView = (ImageView) findViewById(R.id.image);
        Glide.with(this).load(path).into(imageView);
    }
}
