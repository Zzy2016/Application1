package com.example.viewpropertyanimationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    Button button;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button = (Button) findViewById(R.id.button);
//        button.setVisibility(View.GONE);
//        button.setAlpha(0f);
//        button.animate().setDuration(10000).alpha(1f);
//        button.setVisibility(View.VISIBLE);

        tv = (TextView) findViewById(R.id.tv);


        tv.setAlpha(0f);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnimation();
            }
        });

    }

    public void showAnimation() {
        tv.animate().alpha(1).setDuration(1000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                button.setText("ViewPropertyAnimation");
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
