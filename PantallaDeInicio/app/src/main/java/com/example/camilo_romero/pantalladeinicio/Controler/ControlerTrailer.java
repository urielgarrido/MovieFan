package com.example.camilo_romero.pantalladeinicio.Controler;

import com.example.camilo_romero.pantalladeinicio.DAO.DAOPeliculasRetrofit;
import com.example.camilo_romero.pantalladeinicio.DAO.DAOTrailerRetrofit;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.Model.Trailer;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.List;

public class ControlerTrailer {

    public void obtenerTrailersDePeliculaPorID(Integer id, final ResultListener<List<Trailer>> escuchadorDeLaVista){
        DAOTrailerRetrofit daoTrailerRetrofit = new DAOTrailerRetrofit();

        daoTrailerRetrofit.obtenerTrailersDePeliculasPorID(id, new ResultListener<List<Trailer>>() {

            @Override
            public void finish(List<Trailer> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }

}
