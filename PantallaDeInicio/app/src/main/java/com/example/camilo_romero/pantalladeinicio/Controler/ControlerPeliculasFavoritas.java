package com.example.camilo_romero.pantalladeinicio.Controler;

import android.content.Context;

import com.example.camilo_romero.pantalladeinicio.DAO.DAOPeliculasFavoritas;
import com.example.camilo_romero.pantalladeinicio.DAO.PeliculaDaoUtil;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.List;

public class ControlerPeliculasFavoritas{
    public void obtenerPeliculasFavoritas(Context context,final ResultListener<List<Pelicula>> escuchadorDeLaVista){
        final PeliculaDaoUtil peliculaDaoUtil = new PeliculaDaoUtil(context);
        peliculaDaoUtil.getTodasLasPeliculas(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                if (!resultado.isEmpty()&&resultado!=null){
                    escuchadorDeLaVista.finish(resultado);
                }
            }
        });
        new DAOPeliculasFavoritas().obtenerListaDeFavoritos(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                escuchadorDeLaVista.finish(resultado);
                peliculaDaoUtil.insertPelicula(resultado, new ResultListener<Long[]>() {
                    @Override
                    public void finish(Long[] resultado) {
                        int a = resultado.length;
                    }
                });

            }
        });
    }
}
