package com.carrillo.jesus.eventoline;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class AboutUS extends AppCompatActivity {
    private static final String ACTIVITY=  "AboutUs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //hacemos visible el upbuton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
    @Override
    protected void onStart() {
        MyLog.d(ACTIVITY,"Empieza onStar");
        super.onStart();

    }

    @Override
    protected void onResume() {
        MyLog.d(ACTIVITY,"Empieza onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        MyLog.d(ACTIVITY,"Empieza onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        MyLog.d(ACTIVITY,"Empieza onStop");
        super.onStop();

    }

    @Override
    protected void onRestart() {
        MyLog.d(ACTIVITY,"Empieza onRestar");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        MyLog.d(ACTIVITY,"Empieza onDestroy");
        // TODO Auto-generated method stub
        super.onDestroy();
    }

}
