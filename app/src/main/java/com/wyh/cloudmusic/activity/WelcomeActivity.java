package com.wyh.cloudmusic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.wyh.cloudmusic.MainActivity;
import com.wyh.cloudmusic.R;

/**
 * Created by muyang on 2016/3/1.
 */
public class WelcomeActivity extends AppCompatActivity {
    private static final int DELAY_TIME = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, DELAY_TIME);

    }
}
