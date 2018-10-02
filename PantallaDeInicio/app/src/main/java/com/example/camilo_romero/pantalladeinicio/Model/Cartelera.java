package com.example.camilo_romero.pantalladeinicio.Model;

import java.util.List;

public class Cartelera {
    private String Cine;
    private String listaDeHorarios;

    public Cartelera(String cine, String listaDeHorarios) {
        Cine = cine;
        this.listaDeHorarios = listaDeHorarios;
    }

    public String getCine() {
        return Cine;
    }

    public String getListaDeHorarios() {
        return listaDeHorarios;
    }
}
