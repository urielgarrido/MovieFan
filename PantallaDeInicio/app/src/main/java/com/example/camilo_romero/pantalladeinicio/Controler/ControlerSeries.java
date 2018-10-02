package com.example.camilo_romero.pantalladeinicio.Controler;

import com.example.camilo_romero.pantalladeinicio.DAO.DAOPeliculasRetrofit;
import com.example.camilo_romero.pantalladeinicio.DAO.DAOSeriesRetrofit;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.Model.Serie;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.List;

public class ControlerSeries {

    DAOSeriesRetrofit daoSeriesRetrofit = new DAOSeriesRetrofit();

    /*
    https://api.themoviedb.org/3/tv/popular?api_key=8f5f296308f1eb322bf913a9a5b059c2&language=en-US&page=676
    ejemplo de pedido de series
     */

    //PEDIDO POPULARES
    public void obtenerSeriesPopulares(final ResultListener<List<Serie>> escuchadorDeLaVista) {


        daoSeriesRetrofit.obtenerSeriesPopulares(new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });


    }

    //PEDIDO EN TRANSMISION
    public void obtenerSeriesEnTransmision(final ResultListener<List<Serie>> escuchadorDeLaVista) {


        daoSeriesRetrofit.obtenerSeriesEnTransmision(new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }

    //PEDIDO MEJOR VALORADAS
    public void obtenerSeriesMejorValoradas(final ResultListener<List<Serie>> escuchadorDeLaVista) {

        daoSeriesRetrofit.obtenerSeriesMejorValoradas(new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                escuchadorDeLaVista.finish(resultado);
            }
        });
    }


    public void obtenerUnEventoDeSeries(String genero,final ResultListener<List<Serie>> escuchadorDeLaVista) {

        switch (genero){

            case "Populares":
                daoSeriesRetrofit.obtenerSeriesPopulares(new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });
                break;
            case "En Transmision":
                daoSeriesRetrofit.obtenerSeriesEnTransmision(new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });
                break;

            case "Mejor Valoradas":
                daoSeriesRetrofit.obtenerSeriesMejorValoradas(new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });
                break;
        }

    }

    public void obtenerSeriesPorCategoria(String genero,final ResultListener<List<Serie>> escuchadorDeLaVista){

        switch (genero){
            case "Accion y Aventura":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("10795", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);

                    }

                });
            case "Animacion":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("16", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });
            case "Comedia":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("35", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });

            case "Crimen":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("80", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });

            case "Documental":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("99", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });

            case "Drama":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("18", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });

            case "Familia":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("10751", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });

            case "Chicos":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("10762", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });

            case "Misterio":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("9648", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });

            case "Noticias":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("10763", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });

            case "Realitys":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("10764", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });

            case "Sci-Fi y Fantasia":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("10765", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });

            case "Soap":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("10766", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });

            case "Talk":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("10767", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });

            case "Guerra y Politica":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("10768", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });

            case "Western":
                daoSeriesRetrofit.obtenerSeriesPorCategoria("37", new ResultListener<List<Serie>>() {
                    @Override
                    public void finish(List<Serie> resultado) {
                        escuchadorDeLaVista.finish(resultado);
                    }
                });
        }
    }

}
