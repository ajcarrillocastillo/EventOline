package com.carrillo.jesus.eventoline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class LogoActivity extends AppCompatActivity {
    private static final String ACTIVITY = "LogoActivity";
    //actividad inicial
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MyLog.d(ACTIVITY, "Empieza onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), ListadoAcontecimientosActivity.class));
                finish();
            }
        }, 2000);
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(
                       this);

        String languageToLoad=pref.getString("ListaDeLenguajes", "default");
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
               getBaseContext().getResources().getDisplayMetrics());

    }

    //Estos son las acciones que hace en cada ciclo de vida de una actividad
    @Override
    protected void onStart() {
        MyLog.d(ACTIVITY, "Empieza onStar");
        super.onStart();

    }

    @Override
    protected void onResume() {
        MyLog.d(ACTIVITY, "Empieza onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        MyLog.d(ACTIVITY, "Empieza onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        MyLog.d(ACTIVITY, "Empieza onStop");
        super.onStop();

    }

    @Override
    protected void onRestart() {
        MyLog.d(ACTIVITY, "Empieza onRestar");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        MyLog.d(ACTIVITY, "Empieza onDestroy");
        // TODO Auto-generated method stub
        super.onDestroy();
    }
//fin del comentario de los on
}