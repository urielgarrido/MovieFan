package com.example.camilo_romero.pantalladeinicio.Model;

import java.io.Serializable;
import java.util.List;

public class ListadoDePeliculas implements Serializable {

    //ATRIBUTOS
    private List<Pelicula> results;
    private Integer total_pages;

    //CONSTRUCTOR
    public ListadoDePeliculas(List<Pelicula> results) {
        this.results = results;
    }


    //GET RESULTS
    public List<Pelicula> getResults() {
        return results;
    }

    //GET TOTAL PAGES (INTEGER)
    public Integer getTotal_pages() {
        return total_pages;
    }


    //--SET TOTAL PAGES--Posiblemente NO sea utilizado porque viene en el results
    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }
}
