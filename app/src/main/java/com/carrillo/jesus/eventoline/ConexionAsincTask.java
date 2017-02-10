package com.carrillo.jesus.eventoline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jesus on 25/10/2016.
 */
// gestionamos la desgarga de datos de la base de datos de forma asincrona y si sale bien abriremos ver acontecimiento
public class ConexionAsincTask extends AsyncTask<String, String, String> {
        String id;
        Context myContext;
        HttpURLConnection urlConnection;
        ProgressBar barraCarga;
        View boton;
        boolean existeAcontecimiento=true;


    public ConexionAsincTask(Context myContext, String id,ProgressBar barraCarga,View Boton) {
        this.id = id;
        this.myContext=myContext;
        this.barraCarga=barraCarga;
        this.boton=Boton;

    }
//accion antes de cargar
    @Override
    protected void onPreExecute() {

        barraCarga.setVisibility(View.VISIBLE);
    }

    @Override
        protected String doInBackground(String... args) {

            StringBuilder result = new StringBuilder();
            try {
                //url de la bdd
                //y ewcogida de datos
                URL url = new URL("http://eventonline.hol.es/api/v1/acontecimiento/"+id);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                    // leemos json

                JSONObject jsonCompleto = new JSONObject(result.toString());
                //si existe acontecimientos abriremos la bse de datos sqlLite
                if (jsonCompleto.has("acontecimiento")) {
                    //Abrimos la base de datos 'Eventonline' en modo escritura
                    BaseDeDatosSQLiteHelper usdbh =
                            new  BaseDeDatosSQLiteHelper(myContext, Environment.getExternalStorageDirectory()+"/Eventonline.db", null, 1);
                    SQLiteDatabase db = usdbh.getWritableDatabase();

                    //Si hemos abierto correctamente la base de datos
                    if(db != null){
                   //recogemos los datos
                        JSONObject jsonAcontecimiento = new JSONObject(jsonCompleto.getString("acontecimiento"));
                        String nombreAcontecimiento = (jsonAcontecimiento.has("nombre") ? jsonAcontecimiento.getString("nombre") : "");
                        String organizador = (jsonAcontecimiento.has("organizador") ? jsonAcontecimiento.getString("nombre") : "");
                        String descripcion = (jsonAcontecimiento.has("descripcion") ? jsonAcontecimiento.getString("descripcion") : "");
                        String tipo = (jsonAcontecimiento.has("tipo") ? jsonAcontecimiento.getString("tipo") : "");
                        String portada = (jsonAcontecimiento.has("portada") ? jsonAcontecimiento.getString("portada") : "");
                        String inicio = (jsonAcontecimiento.has("inicio") ? jsonAcontecimiento.getString("inicio") : "");
                        String fin = (jsonAcontecimiento.has("fin") ? jsonAcontecimiento.getString("fin") : "");
                        String direccion = (jsonAcontecimiento.has("direccion") ? jsonAcontecimiento.getString("direccion") : "");
                        String localidad = (jsonAcontecimiento.has("localidad") ? jsonAcontecimiento.getString("localidad") : "");
                        String codPostal = (jsonAcontecimiento.has("codPostal") ? jsonAcontecimiento.getString("codPostal") : "");
                        String provincia = (jsonAcontecimiento.has("provincia") ? jsonAcontecimiento.getString("provincia") : "");
                        String longitud = (jsonAcontecimiento.has("longitud") ? jsonAcontecimiento.getString("longitud") : "");
                        String latitud = (jsonAcontecimiento.has("latitud") ? jsonAcontecimiento.getString("latitud") : "");
                        String telefono = (jsonAcontecimiento.has("telefono") ? jsonAcontecimiento.getString("telefono") : "");
                        String email = (jsonAcontecimiento.has("email") ? jsonAcontecimiento.getString("email") : "");
                        String web = (jsonAcontecimiento.has("web") ? jsonAcontecimiento.getString("web") : "");
                        String facebook = (jsonAcontecimiento.has("facebook") ? jsonAcontecimiento.getString("facebook") : "");
                        String twitter = (jsonAcontecimiento.has("twitter") ? jsonAcontecimiento.getString("twitter") : "");
                        String instagram = (jsonAcontecimiento.has("instagram") ? jsonAcontecimiento.getString("instagram") : "");
                        MyLog.i("NuevoAcontecimiento-Acon", (jsonAcontecimiento.has("nombre")) ? jsonAcontecimiento.getString("nombre") : "No nombre");
                        //borro los datos para que no de errores
                        db.execSQL("DELETE FROM `acontecimiento` WHERE id='"+id+"';");
                        //Insertamos los datos en la tabla Acontecimiento
                        db.execSQL("INSERT INTO `acontecimiento` (`id`, `nombre`, `organizador`, `descripcion`, `tipo`, `portada`, `inicio`, `fin`, `direccion`, `localidad`, `cod_postal`, `provincia`, `longitud`, `latitud`, `telefono`, `email`, `web`, `facebook`, `twitter`, `instagram`) " +
                                "VALUES ('"+id+"', '"+nombreAcontecimiento+"','"+organizador+"', '"+descripcion+"', '"+tipo+"', '"+portada+"', '"+inicio+"', '"+fin+"', '"+direccion+"', '"+localidad+"', '"+codPostal+"', '"+provincia+"', '"+longitud+"', '"+latitud+"', '"+telefono+"', '"+email+"', '"+web+"', '"+facebook+"', '"+twitter+"','"+instagram+"');");

                        if (jsonCompleto.has("eventos")) {
                        //Recuperar array
                        JSONArray jsonEventoArray = new JSONArray(jsonCompleto.getString("eventos"));
                        for (int i = 0; i < jsonEventoArray.length(); i++) {
                            JSONObject jsoneventoObjeto = jsonEventoArray.getJSONObject(i);
                            String idEvento= (jsoneventoObjeto.has("id") ? jsoneventoObjeto.getString("id") : "");
                            String idAcontecimientoEvento=(jsoneventoObjeto.has("id_acontecimiento") ? jsoneventoObjeto.getString("id_acontecimiento") : "");
                            String nombreEvento = (jsoneventoObjeto.has("nombre") ? jsoneventoObjeto.getString("nombre") : "");
                            String descripcionEvento = (jsoneventoObjeto.has("descripcion") ? jsoneventoObjeto.getString("descripcion") : "");
                            String inicioEvento = (jsoneventoObjeto.has("inicio") ? jsoneventoObjeto.getString("inicio") : "");
                            String finEvento = (jsoneventoObjeto.has("fin") ? jsoneventoObjeto.getString("fin") : "");
                            String direccionEvento = (jsoneventoObjeto.has("direccion") ? jsoneventoObjeto.getString("direccion") : "");
                            String localidadEvento = (jsoneventoObjeto.has("localidad") ? jsoneventoObjeto.getString("localidad") : "");
                            String codPostalEvento = (jsoneventoObjeto.has("codPostal") ? jsoneventoObjeto.getString("codPostal") : "");
                            String provinciaEvento = (jsonAcontecimiento.has("provincia") ? jsonAcontecimiento.getString("provincia") : "");
        /*nota rafa aqui era*/                    String longitudEvento = (jsoneventoObjeto.has("longitud") ? jsoneventoObjeto.getString("longitud") : "");
                            String latitudEvento = (jsonAcontecimiento.has("latitud") ? jsonAcontecimiento.getString("latitud") : "");
                            MyLog.i("NuevoAcontecimiento-Event", jsoneventoObjeto.getString("nombre"));
                            db.execSQL("DELETE FROM `evento` WHERE id='"+idEvento+"';");
                            //Insertamos los datos en la tabla Acontecimiento
                            db.execSQL("INSERT INTO `evento` (`id`, `id_acontecimiento`, `nombre`, `descripcion`, `inicio`, `fin`," +
                                    " `direccion`, `localidad`, `cod_postal`, `provincia`, `longitud`, `latitud`) " +
                                    "VALUES('"+idEvento+"', '"+idAcontecimientoEvento+"', '"+nombreEvento+"', '"+descripcionEvento+"', '"+inicioEvento+"', '" +
                                    finEvento+"', '"+ direccionEvento+"', '"+localidadEvento+"', '"+ codPostalEvento+"', '"+ provinciaEvento+"', '"+
                                    longitudEvento+"', '"+ latitudEvento+"')");

                        }
                    }



                        //Cerramos la base de datos
                        db.close();
                    }
                    } else {
                        MyLog.i("NuevoAcontecimiento-Acon", "error" );
                    this.existeAcontecimiento=false;

                    }

//Fin


            }catch( Exception e) {
                e.printStackTrace();
            }
            finally {
                urlConnection.disconnect();
            }


            return result.toString();
        }
    //acciones despues de ejecutar
        @Override
        protected void onPostExecute(String result) {

            MyLog.d("onPostExecute",result);
            if(this.existeAcontecimiento) {
                //Para pasar los datos al fichero de Preferencias
                SharedPreferences prefs =
                        myContext.getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("id", id);
                editor.commit();
                //escondemos la barra de carga
                barraCarga.setVisibility(View.INVISIBLE);
                //abrimos la nueva actividad
                myContext.startActivity(new Intent(myContext, VerAcontecimientoActivity.class));
                ((Activity) myContext).finish();
            }else{
                //le pasamos la vista del boton y mandamos el mensaje
                Snackbar.make(boton, "No Existe Acontecimiento", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                barraCarga.setVisibility(View.INVISIBLE);
            }


        }



}
