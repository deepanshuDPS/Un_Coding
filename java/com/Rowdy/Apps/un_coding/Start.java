package com.Rowdy.Apps.un_coding;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Start extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView start=(ImageView) findViewById(R.id.start);
        setContentView(R.layout.activity_start);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run()
            {
                startActivity(new Intent(Start.this, Home_Page.class));
                finish();
            }
        },1000);
    }
}
