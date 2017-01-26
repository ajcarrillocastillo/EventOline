package com.carrillo.jesus.eventoline;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MostrarEventoFragment extends Fragment{
    private static final String ACTIVITY=  "VerFragmento";
    private TextView textView;
    private ImageView imageView;

    public MostrarEventoFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mostrar_evento, container, false);
    }
    @Override
    public void onStart(){
        super.onStart();
        Bundle args=getArguments();
        if(args!=null){
            updateView(args.getString("id"));
        }
    }

    public void updateView(String id){

        BaseDeDatosSQLiteHelper usdbh =
                new BaseDeDatosSQLiteHelper(getActivity(), Environment.getExternalStorageDirectory()+"/Eventonline.db", null, 1);
        //instancia la db.
        SQLiteDatabase db = usdbh.getReadableDatabase();

        String[] argsID = new String[] {id};
        Cursor cursor = db.rawQuery(" SELECT * FROM evento WHERE id=? ", argsID);
        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            LinearLayout layoutPrincipal = (LinearLayout) getActivity().findViewById(R.id.mostrarEventosLayout);
            layoutPrincipal.setOrientation(LinearLayout.VERTICAL);
            do{
//recogemos los datos
                String nombreEvento = cursor.getString(cursor.getColumnIndex("nombre"));
                String descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));
                String inicio = cursor.getString(cursor.getColumnIndex("inicio"));
                String fin = cursor.getString(cursor.getColumnIndex("fin"));
                String direccion = cursor.getString(cursor.getColumnIndex("direccion"));
                String localidad = cursor.getString(cursor.getColumnIndex("localidad"));
                String codPostal = cursor.getString(cursor.getColumnIndex("cod_postal"));
                String provincia = cursor.getString(cursor.getColumnIndex("provincia"));
                String longitud = cursor.getString(cursor.getColumnIndex("longitud"));;
                String latitud = cursor.getString(cursor.getColumnIndex("latitud"));


//hacemos que rellene los que tengan datos
                layoutPrincipal.removeAllViews();
                if (!nombreEvento.isEmpty()) crearElementos(nombreEvento, R.drawable.ic_event_seat_black_24dp, layoutPrincipal);
                if(!descripcion.isEmpty()) crearElementos(descripcion, R.drawable.ic_format_color_text_black_24dp, layoutPrincipal);
                if(!inicio.isEmpty()) crearElementos(inicio, R.drawable.ic_date_range_black_24dp, layoutPrincipal);
                if(!fin.isEmpty()) crearElementos(fin, R.drawable.ic_date_range_black_24dp, layoutPrincipal);
                if(!direccion.isEmpty()) crearElementos(direccion, R.drawable.ic_home_black_24dp, layoutPrincipal);
                if(!localidad.isEmpty()) crearElementos(localidad, R.drawable.ic_domain_black_24dp, layoutPrincipal);
                if(!codPostal.isEmpty()) crearElementos(codPostal, R.drawable.ic_date_range_black_24dp, layoutPrincipal);
                if(!provincia.isEmpty()) crearElementos(provincia, R.drawable.ic_location_city_black_24dp, layoutPrincipal);
                if(!longitud.isEmpty()) crearElementos(longitud, R.drawable.ic_explore_black_24dp, layoutPrincipal);
                if(!latitud.isEmpty()) crearElementos(latitud, R.drawable.ic_explore_black_24dp, layoutPrincipal);
            }while(cursor.moveToNext());
        }

    }
    public void crearElementos(String nombre, int rutaImage, LinearLayout layout){
        //creamos el segundo Layout.
        LinearLayout milayout = new LinearLayout(new ContextThemeWrapper(getActivity(), R.style.AppTheme));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //le pasamos los parametros y la orientación.
        milayout.setLayoutParams(params);
        milayout.setOrientation(LinearLayout.HORIZONTAL);
        //add textView
        textView = new TextView(new ContextThemeWrapper(getActivity(), R.style.AppTheme));
        textView.setText(nombre);
        imageView = new ImageView(new ContextThemeWrapper(getActivity(), R.style.AppTheme));
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


}
