package com.carrillo.jesus.eventoline;

/**
 * Created by jesus on 18/10/2016.
 */
//recoge las partes de los acontecimientos a mostrar /equivalente a un objeto en java
public class EventoItem {
    private String id;
    private String nombre;

    public EventoItem(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
