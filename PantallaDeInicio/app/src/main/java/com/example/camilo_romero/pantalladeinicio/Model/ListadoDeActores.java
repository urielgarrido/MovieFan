package com.example.camilo_romero.pantalladeinicio.Model;

import java.io.Serializable;
import java.util.List;

public class ListadoDeActores implements Serializable {
    List<Actor> cast;

    public ListadoDeActores(List<Actor> cast) {
        this.cast = cast;
    }

    public List<Actor> getCast() {
        return cast;
    }
}
