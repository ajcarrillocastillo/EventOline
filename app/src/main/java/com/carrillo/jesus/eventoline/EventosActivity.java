package com.carrillo.jesus.eventoline;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EventosActivity extends AppCompatActivity  implements ListadoEventosFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
        if (findViewById(R.id.unique_fragment)!=null){
            listadoFragment listadoFrag=new listadoFragment();
        }
    }
    public void onFramgmentInteraction (int position){
        MostrarEventoFragment mostrarEventoFragment=(MostrarEventoFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMostrarEvento);
        if(mostrarEventoFragment!= null){
            mostrarEventoFragment.updateView(position)
        }else{
            MostrarEventoFragment newMostrarEventoFragment=new MostrarEventoFragment();
            Bundle args =new Bundle();
            args.putInt("position",position);
            newMostrarEventoFragment.setArguments(args);
            FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.unique_fragment, newMostrarEventoFragment);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}
