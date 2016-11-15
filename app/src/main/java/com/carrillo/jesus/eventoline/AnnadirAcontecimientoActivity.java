package com.carrillo.jesus.eventoline;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AnnadirAcontecimientoActivity extends AppCompatActivity {
    private final int REQUEST_CODE_INTERNET=10;
    public static Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myContext=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annadir_acontecimiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button but = (Button) findViewById(R.id.buttonBuscarID);
        but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText editTextoId = (EditText) findViewById(R.id.editTextBuscardorID);
                String editTextBuscadorText= editTextoId.getText().toString();
                int longitudTexto=editTextBuscadorText.length();
                if( longitudTexto==0){
                Snackbar.make(view, "tiene que meter un valor", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
                int permissionCheck = ContextCompat.checkSelfPermission(myContext, Manifest.permission.INTERNET);

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    new ConexionAsincTask(myContext,editTextBuscadorText).execute();
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
    });

}
}