package com.example.camilo_romero.pantalladeinicio.Model;

import java.io.Serializable;
import java.util.List;

public class ListadoDeSeries implements Serializable {
    List<Serie> results;

    public ListadoDeSeries(List<Serie> results) {
        this.results = results;
    }

    public List<Serie> getResults() {
        return results;
    }
}
