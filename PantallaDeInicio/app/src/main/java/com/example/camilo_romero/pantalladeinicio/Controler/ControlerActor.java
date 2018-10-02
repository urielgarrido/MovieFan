package com.example.camilo_romero.pantalladeinicio.Controler;

import com.example.camilo_romero.pantalladeinicio.DAO.DAOActoresRetrofit;
import com.example.camilo_romero.pantalladeinicio.DAO.DAOSeriesRetrofit;
import com.example.camilo_romero.pantalladeinicio.Model.Actor;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.List;

public class ControlerActor {

    public void obtenerListaDeActoresDePeliculas(Integer idDeLaPelicula, final ResultListener<List<Actor>> escuchadorDeLavista) {
        DAOActoresRetrofit daoActoresRetrofit = new DAOActoresRetrofit();

        daoActoresRetrofit.obtenerListaDeActoresDePeliculas(idDeLaPelicula, escuchadorDeLavista);
    }

    public void obtenerListaDeActoresDeSeries(Integer idDeLaPelicula, final ResultListener<List<Actor>> escuchadorDeLaVista) {
        DAOActoresRetrofit daoActoresRetrofit = new DAOActoresRetrofit();

        daoActoresRetrofit.obtenerListaDeActoresDeSeries(idDeLaPelicula, escuchadorDeLaVista);
    }
    public void obtenerActorPorID(Integer IDDelActor,final  ResultListener<Actor> escuchadorDeLaVista){
        DAOActoresRetrofit daoActoresRetrofit = new DAOActoresRetrofit();

        daoActoresRetrofit.obtenerActorPorID(IDDelActor, new ResultListener<Actor>() {
            @Override
            public void finish(Actor resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }
}



