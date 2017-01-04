package com.carrillo.jesus.eventoline;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AnnadirAcontecimientoActivity extends AppCompatActivity {
    private static final String ACTIVITY=  "AnnadirAcontecimiento";
    private final int REQUEST_CODE_INTERNET=10;
    public static Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myContext=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annadir_acontecimiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //hacemos visible el boton up
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //declaramos barra de Profreso
        final ProgressBar barraCarga=(ProgressBar) findViewById(R.id.progressBar);
        Button but = (Button) findViewById(R.id.buttonBuscarID);
        //seleccionamos que hace el boton up
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //seleccionamos lo que jace el boton
        but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText editTextoId = (EditText) findViewById(R.id.editTextBuscardorID);
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                //escondemos el teclado
                inputMethodManager.hideSoftInputFromWindow(editTextoId.getWindowToken(), 0);

                if (isOnline()){

                String editTextBuscadorText= editTextoId.getText().toString();
                int longitudTexto=editTextBuscadorText.length();
                if (longitudTexto==0){
                Snackbar.make(view, "tiene que meter un valor", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }else{
                int permissionCheck = ContextCompat.checkSelfPermission(myContext, Manifest.permission.INTERNET);

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    //llamar a la tarea asincrona
                    permissionCheck=ContextCompat.checkSelfPermission(myContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    if(permissionCheck==PackageManager.PERMISSION_GRANTED) {
                        new ConexionAsincTask(myContext, editTextBuscadorText, barraCarga, findViewById(R.id.buttonBuscarID)).execute();
                    }else{
                        if(ActivityCompat.shouldShowRequestPermissionRationale(AnnadirAcontecimientoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                            Toast.makeText(myContext, "Necesita permisos de Almacenamiento.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Explicar permiso
                    if(ActivityCompat.shouldShowRequestPermissionRationale(AnnadirAcontecimientoActivity.this, Manifest.permission.INTERNET)) {
                        Toast.makeText(myContext, "El permiso es necesario para utilizar internet.",
                                Toast.LENGTH_SHORT).show();
                    }

// Solicitar el permiso

                    ActivityCompat.requestPermissions(AnnadirAcontecimientoActivity.this, new String[]{Manifest.permission.INTERNET}, REQUEST_CODE_INTERNET);
            }
                }
                }else{
                    Toast.makeText(myContext, "Active alguna conexion a internet para continuar.",
                            Toast.LENGTH_SHORT).show();
                }
        }
    });
}
//comprobamos la conexion
    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(myContext, "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
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