package com.example.camilo_romero.pantalladeinicio.Controler;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.camilo_romero.pantalladeinicio.DAO.DAOPeliculasRetrofit;
import com.example.camilo_romero.pantalladeinicio.DAO.PeliculaDaoUtil;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.List;

public class ControlerPelicula {

    Context context;

    public ControlerPelicula(Context context) {
        this.context = context;
    }

    public void deletePelicula(Pelicula pelicula, final ResultListener<Boolean> resultListener) {
        PeliculaDaoUtil peliculaDaoUtil = new PeliculaDaoUtil(context);
        peliculaDaoUtil.deletePelicula(pelicula, new ResultListener<Boolean>() {
            @Override
            public void finish(Boolean resultado) {
                resultListener.finish(resultado);
            }
        });
    }

    public void insertPelicula(Pelicula pelicula, final ResultListener<Long> resultListener) {
        PeliculaDaoUtil peliculaDaoUtil = new PeliculaDaoUtil(context);
        peliculaDaoUtil.insertPelicula(pelicula, new ResultListener<Long>() {
            @Override
            public void finish(Long resultado) {
                resultListener.finish(resultado);
            }
        });
    }

    public void obtenerPeliculasPorPedido(String pedido, final ResultListener<List<Pelicula>> resultListener) {
        final PeliculaDaoUtil peliculaDaoUtil = new PeliculaDaoUtil(context);
        peliculaDaoUtil.getTodasLasPeliculas(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                if(resultado != null && !resultado.isEmpty()){
                    resultListener.finish(resultado);
                }
            }
        });

        if (hayInternet()) {
            DAOPeliculasRetrofit daoPeliculasRetrofit = new DAOPeliculasRetrofit();
            daoPeliculasRetrofit.obtenerPeliculasAPedido(pedido, new ResultListener<List<Pelicula>>() {
                @Override
                public void finish(List<Pelicula> resultado) {
                    resultListener.finish(resultado);
                    PeliculaDaoUtil peliculaDaoUtil11 = new PeliculaDaoUtil(context);
                    peliculaDaoUtil11.insertPelicula(resultado, new ResultListener<Long[]>() {
                        @Override
                        public void finish(Long[] resultado) {
                            int a = resultado.length;
                        }
                    });
                }
            });
        }
    }

    public void updatePerson(Pelicula pelicula, final ResultListener<Boolean> resultListener) {
        PeliculaDaoUtil peliculaDaoUtil = new PeliculaDaoUtil(context);
        peliculaDaoUtil.updatePelicula(pelicula, new ResultListener<Boolean>() {
            @Override
            public void finish(Boolean resultado) {
                resultListener.finish(resultado);
            }
        });
    }

    public void obtenerPeliculasPopulares(final ResultListener<List<Pelicula>> escuchadorDeLaVista) {

        /*
        https://api.themoviedb.org/3/movie/top_rated?api_key=8f5f296308f1eb322bf913a9a5b059c2&language=en-US&page=1
        ejemplo de pedido de peliculas populares
         */

        DAOPeliculasRetrofit daoPeliculasRetrofit = new DAOPeliculasRetrofit();

        daoPeliculasRetrofit.obtenerPeliculasPopulares(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });

    }


    public void obtenerPeliculasaEstrenarse(final ResultListener<List<Pelicula>> escuchadorDeLaVista) {

        DAOPeliculasRetrofit daoPeliculasRetrofit = new DAOPeliculasRetrofit();

        daoPeliculasRetrofit.obtenerPeliculasAEstrenarse(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }


    public void obtenerPeliculasEstreno(final ResultListener<List<Pelicula>> esuchadorDeLaVista) {
        DAOPeliculasRetrofit daoPeliculasRetrofit = new DAOPeliculasRetrofit();

        daoPeliculasRetrofit.obtenerPeliculasEstrenadas(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                esuchadorDeLaVista.finish(resultado);
            }
        });
    }


    private boolean hayInternet() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


}
