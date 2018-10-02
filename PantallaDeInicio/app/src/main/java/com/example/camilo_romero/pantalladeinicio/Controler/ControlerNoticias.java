package com.example.camilo_romero.pantalladeinicio.Controler;

import com.example.camilo_romero.pantalladeinicio.DAO.DAONoticiasRetrofit;
import com.example.camilo_romero.pantalladeinicio.Model.Noticia;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.List;

public class ControlerNoticias {

    public void obtenerListaDeNoticiasDePeliculas(String dominios, String busqueda,Integer cantidad, final ResultListener<List<Noticia>> escuchadorDeLaVista){

            DAONoticiasRetrofit daoNoticiasRetrofit = new DAONoticiasRetrofit();

            daoNoticiasRetrofit.obtenerListaDeNoticiasDePeliculas(dominios, busqueda,cantidad, new ResultListener<List<Noticia>>() {
                @Override
                public void finish(List<Noticia> resultado) {
                    escuchadorDeLaVista.finish(resultado);
                }
            });

    }

}
