package com.carrillo.jesus.eventoline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VerAcontecimientoActivity extends AppCompatActivity {
    private static final String ACTIVITY=  "VerAcontecimiento";
    private TextView textView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_acontecimiento);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //hacemos visible el boton up
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //seleecionamos que hace el up
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //recogemos share preferer
        SharedPreferences prefs =
                getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        //recogemos
        String id = prefs.getString("id", "Error Con el SharePreferences");
        //leer de la base de datos.
        BaseDeDatosSQLiteHelper usdbh =
                new  BaseDeDatosSQLiteHelper(this, Environment.getExternalStorageDirectory()+"/Eventonline.db", null, 1);
        SQLiteDatabase db = usdbh.getReadableDatabase();


        String[] argsID = new String[] {id};
        Cursor cursor = db.rawQuery(" SELECT * FROM acontecimiento WHERE id=? ", argsID);

        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            LinearLayout layoutPrincipal = (LinearLayout) findViewById(R.id.linearVerAcontecimientos);
            layoutPrincipal.setOrientation(LinearLayout.VERTICAL);
            do{
//recogemos los datos
                String nombreAcontecimiento = cursor.getString(cursor.getColumnIndex("nombre"));
                String organizador = cursor.getString(cursor.getColumnIndex("organizador"));
                String descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));
                String tipo = cursor.getString(cursor.getColumnIndex("tipo"));
                String portada = cursor.getString(cursor.getColumnIndex("portada"));
                String inicio = cursor.getString(cursor.getColumnIndex("inicio"));
                String fin = cursor.getString(cursor.getColumnIndex("fin"));
                String direccion = cursor.getString(cursor.getColumnIndex("direccion"));
                String localidad = cursor.getString(cursor.getColumnIndex("localidad"));
                String codPostal = cursor.getString(cursor.getColumnIndex("cod_postal"));
                String provincia = cursor.getString(cursor.getColumnIndex("provincia"));
                String longitud = cursor.getString(cursor.getColumnIndex("longitud"));;
                String latitud = cursor.getString(cursor.getColumnIndex("latitud"));
                String telefono = cursor.getString(cursor.getColumnIndex("telefono"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                String web = cursor.getString(cursor.getColumnIndex("web"));
                String facebook = cursor.getString(cursor.getColumnIndex("facebook"));
                String twitter = cursor.getString(cursor.getColumnIndex("twitter"));
                String instagram = cursor.getString(cursor.getColumnIndex("instagram"));
//hacemos que rellene los que tengan datos
                if (!nombreAcontecimiento.isEmpty()) crearElementos(nombreAcontecimiento, R.drawable.ic_event_seat_black_24dp, layoutPrincipal);
                if(!organizador.isEmpty()) crearElementos(organizador, R.drawable.ic_record_voice_over_black_24dp, layoutPrincipal);
                if(!descripcion.isEmpty()) crearElementos(descripcion, R.drawable.ic_format_color_text_black_24dp, layoutPrincipal);
                if(!tipo.isEmpty()) crearElementos(tipo, R.drawable.ic_format_list_numbered_black_24dp, layoutPrincipal);
                if(!portada.isEmpty()) crearElementos(portada, R.drawable.ic_picture_in_picture_black_24dp, layoutPrincipal);
                if(!inicio.isEmpty()) crearElementos(inicio, R.drawable.ic_date_range_black_24dp, layoutPrincipal);
                if(!fin.isEmpty()) crearElementos(fin, R.drawable.ic_date_range_black_24dp, layoutPrincipal);
                if(!direccion.isEmpty()) crearElementos(direccion, R.drawable.ic_home_black_24dp, layoutPrincipal);
                if(!localidad.isEmpty()) crearElementos(localidad, R.drawable.ic_domain_black_24dp, layoutPrincipal);
                if(!codPostal.isEmpty()) crearElementos(codPostal, R.drawable.ic_date_range_black_24dp, layoutPrincipal);
                if(!provincia.isEmpty()) crearElementos(provincia, R.drawable.ic_location_city_black_24dp, layoutPrincipal);
                if(!longitud.isEmpty()) crearElementos(longitud, R.drawable.ic_explore_black_24dp, layoutPrincipal);
                if(!latitud.isEmpty()) crearElementos(latitud, R.drawable.ic_explore_black_24dp, layoutPrincipal);
                if(!telefono.isEmpty()) crearElementos(telefono, R.drawable.ic_contact_phone_black_24dp, layoutPrincipal);
                if(!email.isEmpty()) crearElementos(email, R.drawable.ic_email_black_24dp, layoutPrincipal);
                if(!web.isEmpty()) crearElementos(web , R.drawable.ic_public_black_24dp, layoutPrincipal);
                if(!facebook.isEmpty()) crearElementos(facebook, R.drawable.ic_email_black_24dp, layoutPrincipal);
                if(!twitter.isEmpty()) crearElementos(twitter, R.drawable.ic_email_black_24dp, layoutPrincipal);
                if(!instagram.isEmpty()) crearElementos(instagram, R.drawable.ic_add_a_photo_black_24dp,layoutPrincipal);
            }while(cursor.moveToNext());
        }

    }
    //creamos los elementos del acontecimiento
    public void crearElementos(String nombre, int rutaImage, LinearLayout layout){
        //creamos el segundo Layout.
        LinearLayout milayout = new LinearLayout(new ContextThemeWrapper(this, R.style.AppTheme));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //le pasamos los parametros y la orientación.
        milayout.setLayoutParams(params);
        milayout.setOrientation(LinearLayout.HORIZONTAL);
        //add textView
        textView = new TextView(new ContextThemeWrapper(this, R.style.AppTheme));
        textView.setText(nombre);
        imageView = new ImageView(new ContextThemeWrapper(this, R.style.AppTheme));
        imageView.setImageResource(rutaImage);
        //Le añadimos los parametros a los view.
        imageView.setLayoutParams(params);
        textView.setLayoutParams(params);
        //añadimos al layout que hemos creado tanto el texto como la imagen.
        milayout.addView(imageView);
        milayout.addView(textView);
        //le añadimos el layout que hemos creado al principal.
        layout.addView(milayout);
    }
    //En onBackPressed le decimos que  hacer al hacer la funcion de ir acia atras
    @Override
    public void onBackPressed() {
        this.startActivity(new Intent(this, ListadoAcontecimientosActivity.class));
        this.finish();
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
