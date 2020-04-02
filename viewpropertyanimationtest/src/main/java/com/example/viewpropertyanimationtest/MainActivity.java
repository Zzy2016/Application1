package com.example.viewpropertyanimationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private View contentView, loadingView;
    private int shortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentView = findViewById(R.id.content);
        loadingView = findViewById(R.id.loading_spinner);
        contentView.setVisibility(View.GONE);

        shortAnimationDuration = 1000;
//        crossfade();
        contentView.setAlpha(0f);
        contentView.setVisibility(View.VISIBLE);
        contentView.animate().alpha(1f).setDuration(5000);

    }

    private void crossfade() {
        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        contentView.setAlpha(0f);
        contentView.setVisibility(View.VISIBLE);

        // Animate the content view to 100% opacity, and clear any animation
        // listener set on the view.
        contentView.animate().alpha(1f).setDuration(shortAnimationDuration).setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        loadingView.animate().alpha(0f).setDuration(shortAnimationDuration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                loadingView.setVisibility(View.GONE);
            }
        });
    }
}

