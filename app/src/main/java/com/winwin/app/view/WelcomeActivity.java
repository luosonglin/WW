package com.winwin.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.winwin.app.MainActivity;
import com.winwin.app.R;
import com.winwin.app.UI.ListvView.ListvViewActivity;


public class WelcomeActivity extends ListvViewActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);
    }
}
