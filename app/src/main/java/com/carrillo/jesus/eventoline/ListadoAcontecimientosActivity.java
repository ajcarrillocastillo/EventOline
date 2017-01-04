package com.carrillo.jesus.eventoline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListadoAcontecimientosActivity extends AppCompatActivity {
    private static final String ACTIVITY=  "listadoAcontecimiento";


    private ArrayList<AcontecimientoItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_acontecimientos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //acion del boton flotante
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(), AnnadirAcontecimientoActivity.class));
                    }
                });
        // Crear elementos
        rellenarReciclerview();




    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_listado_acontecimietos, menu);
        return true;
    }
    @Override
    public void onBackPressed() {
        System.exit(0);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id=item.getItemId();
        if(id==R.id.aboutUsItem){

            startActivity( new Intent(this,AboutUS.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        //rellenarReciclerview();
        MyLog.d(ACTIVITY,"Empieza onStar");
        super.onStart();

    }

    @Override
    protected void onResume() {
        rellenarReciclerview();
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
//fin del comentario de los on

    private void rellenarReciclerview(){
        items = new ArrayList<AcontecimientoItem>();
        BaseDeDatosSQLiteHelper usdbh =
                new  BaseDeDatosSQLiteHelper(this, Environment.getExternalStorageDirectory()+"/Eventonline.db", null, 1);
        SQLiteDatabase db = usdbh.getReadableDatabase();


        String[] argsSentecia = new String[] {};
        Cursor cursor = db.rawQuery(" SELECT id,nombre,inicio,fin FROM acontecimiento ORDER BY inicio DESC ", argsSentecia);
        // Se inicializa el RecyclerView
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            items.removeAll(items);

            //Recorremos el cursor hasta que no haya más registros
           /* LinearLayout layoutPrincipal = (LinearLayout) findViewById(R.id.linearVerAcontecimientos);
            layoutPrincipal.setOrientation(LinearLayout.VERTICAL);*/
            do{
//recogemos los datos
                String id =cursor.getString(cursor.getColumnIndex("id"));
                String nombreAcontecimiento = cursor.getString(cursor.getColumnIndex("nombre"));
                String inicioSinFormato = cursor.getString(cursor.getColumnIndex("inicio"));
                // el que parsea
                SimpleDateFormat parseador = new SimpleDateFormat("yyyymmddhhmm");
// el que formatea
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

                Date date = null;
                try {
                    date = parseador.parse(inicioSinFormato);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String inicio=formateador.format(date);
                String finSinFormato = cursor.getString(cursor.getColumnIndex("fin"));

                try {
                    date = parseador.parse(inicioSinFormato);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String fin=formateador.format(date);
                items.add(new AcontecimientoItem(id,nombreAcontecimiento,inicio,fin));

            }while(cursor.moveToNext());

            // Se crea el Adaptador con los datos
            AcontecimientoAdapter adaptador = new AcontecimientoAdapter(items);

            // Se asocia el elemento con una acción al pulsar el elemento
            adaptador.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = recyclerView.getChildAdapterPosition(v);
                    // Acción al pulsar el elemento
                    //Para pasar los datos al fichero de Preferencias
                    SharedPreferences prefs =
                            getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("id", items.get(position).getId());
                    editor.commit();
                    //abrimos la nueva actividad
                    startActivity(new Intent(ListadoAcontecimientosActivity.this, VerAcontecimientoActivity.class));
                    MyLog.d(ACTIVITY, "Click en RecyclerView");

                    /*Toast toast = Toast.makeText(ListadoAcontecimientosActivity.this, " ID:" + String.valueOf(position) + " ID:" + items.get(position).getId() + " Nombre: " + items.get(position).getNombre(), Toast.LENGTH_SHORT);
                    toast.show();*/
                }
            });

            // Se asocia el Adaptador al RecyclerView
            recyclerView.setAdapter(adaptador);

            // Se muestra el RecyclerView en vertical
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }else{
            Snackbar.make(recyclerView, "No hay acontecimientos", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }
}
