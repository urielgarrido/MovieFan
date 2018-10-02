package com.example.camilo_romero.pantalladeinicio.Model;

import java.io.Serializable;
import java.util.List;

public class ListadoDeNoticias implements Serializable {
    private List<Noticia> articles;

    public ListadoDeNoticias(List<Noticia> listaDeNoticias) {
    }

    public List<Noticia> getArticles() {
        return articles;
    }
}
