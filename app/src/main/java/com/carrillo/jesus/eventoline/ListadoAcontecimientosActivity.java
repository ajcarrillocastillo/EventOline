package com.carrillo.jesus.eventoline;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoAcontecimientosActivity extends AppCompatActivity {
    private static final String ACTIVITY=  "LogoActivity";


    private ArrayList<AcontecimientoItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_acontecimientos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(), AnnadirAcontecimientoActivity.class));
                    }
                });
        // Crear elementos
        items = new ArrayList<AcontecimientoItem>();
        items.add(new AcontecimientoItem("1", "Primer acontecimiento"));
        items.add(new AcontecimientoItem("2", "Segundo acontecimiento"));
        items.add(new AcontecimientoItem("3", "Tercero acontecimiento"));
        items.add(new AcontecimientoItem("4", "Cuarto acontecimiento"));
        items.add(new AcontecimientoItem("5", "Quinto acontecimiento"));
        items.add(new AcontecimientoItem("6", "Sexto acontecimiento"));
        items.add(new AcontecimientoItem("7", "Septimo acontecimiento"));
        items.add(new AcontecimientoItem("8", "Octavo acontecimiento"));
        items.add(new AcontecimientoItem("9", "Noveno acontecimiento"));
        items.add(new AcontecimientoItem("10", "Decimo acontecimiento"));
        items.add(new AcontecimientoItem("1", "Primer acontecimiento"));
        items.add(new AcontecimientoItem("2", "Segundo acontecimiento"));
        items.add(new AcontecimientoItem("3", "Tercero acontecimiento"));
        items.add(new AcontecimientoItem("4", "Cuarto acontecimiento"));
        items.add(new AcontecimientoItem("5", "Quinto acontecimiento"));
        items.add(new AcontecimientoItem("6", "Sexto acontecimiento"));
        items.add(new AcontecimientoItem("7", "Septimo acontecimiento"));
        items.add(new AcontecimientoItem("8", "Octavo acontecimiento"));
        items.add(new AcontecimientoItem("9", "Noveno acontecimiento"));
        items.add(new AcontecimientoItem("10", "Decimo acontecimiento"));
        items.add(new AcontecimientoItem("1", "Primer acontecimiento"));
        items.add(new AcontecimientoItem("2", "Segundo acontecimiento"));
        items.add(new AcontecimientoItem("3", "Tercero acontecimiento"));
        items.add(new AcontecimientoItem("4", "Cuarto acontecimiento"));
        items.add(new AcontecimientoItem("5", "Quinto acontecimiento"));
        items.add(new AcontecimientoItem("6", "Sexto acontecimiento"));
        items.add(new AcontecimientoItem("7", "Septimo acontecimiento"));
        items.add(new AcontecimientoItem("8", "Octavo acontecimiento"));
        items.add(new AcontecimientoItem("9", "Noveno acontecimiento"));
        items.add(new AcontecimientoItem("10", "Decimo acontecimiento"));
        items.add(new AcontecimientoItem("1", "Primer acontecimiento"));
        items.add(new AcontecimientoItem("2", "Segundo acontecimiento"));
        items.add(new AcontecimientoItem("3", "Tercero acontecimiento"));
        items.add(new AcontecimientoItem("4", "Cuarto acontecimiento"));
        items.add(new AcontecimientoItem("5", "Quinto acontecimiento"));
        items.add(new AcontecimientoItem("6", "Sexto acontecimiento"));
        items.add(new AcontecimientoItem("7", "Septimo acontecimiento"));
        items.add(new AcontecimientoItem("8", "Octavo acontecimiento"));
        items.add(new AcontecimientoItem("9", "Noveno acontecimiento"));
        items.add(new AcontecimientoItem("10", "Decimo acontecimiento"));
        items.add(new AcontecimientoItem("1", "Primer acontecimiento"));
        items.add(new AcontecimientoItem("2", "Segundo acontecimiento"));
        items.add(new AcontecimientoItem("3", "Tercero acontecimiento"));
        items.add(new AcontecimientoItem("4", "Cuarto acontecimiento"));
        items.add(new AcontecimientoItem("5", "Quinto acontecimiento"));
        items.add(new AcontecimientoItem("6", "Sexto acontecimiento"));
        items.add(new AcontecimientoItem("7", "Septimo acontecimiento"));
        items.add(new AcontecimientoItem("8", "Octavo acontecimiento"));
        items.add(new AcontecimientoItem("9", "Noveno acontecimiento"));
        items.add(new AcontecimientoItem("10", "Decimo acontecimiento"));
        items.add(new AcontecimientoItem("1", "Primer acontecimiento"));
        items.add(new AcontecimientoItem("2", "Segundo acontecimiento"));
        items.add(new AcontecimientoItem("3", "Tercero acontecimiento"));
        items.add(new AcontecimientoItem("4", "Cuarto acontecimiento"));
        items.add(new AcontecimientoItem("5", "Quinto acontecimiento"));
        items.add(new AcontecimientoItem("6", "Sexto acontecimiento"));
        items.add(new AcontecimientoItem("7", "Septimo acontecimiento"));
        items.add(new AcontecimientoItem("8", "Octavo acontecimiento"));
        items.add(new AcontecimientoItem("9", "Noveno acontecimiento"));
        items.add(new AcontecimientoItem("10", "Decimo acontecimiento"));
        items.add(new AcontecimientoItem("1", "Primer acontecimiento"));
        items.add(new AcontecimientoItem("2", "Segundo acontecimiento"));
        items.add(new AcontecimientoItem("3", "Tercero acontecimiento"));
        items.add(new AcontecimientoItem("4", "Cuarto acontecimiento"));
        items.add(new AcontecimientoItem("5", "Quinto acontecimiento"));
        items.add(new AcontecimientoItem("6", "Sexto acontecimiento"));
        items.add(new AcontecimientoItem("7", "Septimo acontecimiento"));
        items.add(new AcontecimientoItem("8", "Octavo acontecimiento"));
        items.add(new AcontecimientoItem("9", "Noveno acontecimiento"));
        items.add(new AcontecimientoItem("10", "Decimo acontecimiento"));

        // Se inicializa el RecyclerView
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Se crea el Adaptador con los datos
        AcontecimientoAdapter adaptador = new AcontecimientoAdapter(items);

        // Se asocia el elemento con una acción al pulsar el elemento
        adaptador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Acción al pulsar el elemento

                MyLog.d(ACTIVITY, "Click en RecyclerView");
                int position = recyclerView.getChildAdapterPosition(v);
                Toast toast = Toast.makeText(ListadoAcontecimientosActivity.this, " ID:" + String.valueOf(position) + " ID:" + items.get(position).getId() + " Nombre: " + items.get(position).getNombre(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        // Se asocia el Adaptador al RecyclerView
        recyclerView.setAdapter(adaptador);

        // Se muestra el RecyclerView en vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
//fin del comentario de los on


}
