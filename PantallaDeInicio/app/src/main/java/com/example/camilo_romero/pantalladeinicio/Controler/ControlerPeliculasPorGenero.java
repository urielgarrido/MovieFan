package com.example.camilo_romero.pantalladeinicio.Controler;

import com.example.camilo_romero.pantalladeinicio.DAO.DAOPeliculasRetrofit;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDePeliculas;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;

import java.util.List;

/**
 * Created by DH on 28/5/2018.
 */

public class ControlerPeliculasPorGenero {

    //ATRIBUTOS
    private Integer paginaActual;
    private static final Integer PAGE_SIZE = 20;
    private Boolean hayPaginas = true;
    private DAOPeliculasRetrofit daoPeliculasRetrofit;

    //CONSTRUCTOR


    public ControlerPeliculasPorGenero() {
        paginaActual = 1;
    }

    //MÉTODO OBTENER PAGINA DE PELICULAS POR CATEGORIA
    //QUE PIDE UNA LISTA DE PELICULAS QUE PERTENECEN A LA MISMA CATEGORIA
    public void getPagePeliculasPorGenero(String id_Categoria, final ResultListener<ListadoDePeliculas> escuchadorDeLaVista) {

        daoPeliculasRetrofit = new DAOPeliculasRetrofit();

        switch (id_Categoria) {

            case "Ciencia Ficción":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_SCIENCE_FICTION, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                        if(resultado.getTotal_pages() > paginaActual){
                        escuchadorDeLaVista.finish(resultado);
                        setPaginaActual(paginaActual+1);
                    }
                }}, paginaActual);
                break;

            case "Acción":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_ACTION, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                            if(resultado.getTotal_pages() > paginaActual){
                                escuchadorDeLaVista.finish(resultado);
                                setPaginaActual(paginaActual+1);
                            }
                    }
                }, paginaActual);
                break;

            case "Aventuras":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_ADVENTURE, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                        if(resultado.getTotal_pages() > paginaActual){
                            escuchadorDeLaVista.finish(resultado);
                            setPaginaActual(paginaActual+1);
                        }
                    }
                }, paginaActual);
                break;

            case "Animacion":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_ANIMATION, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                        if(resultado.getTotal_pages() > paginaActual){
                            escuchadorDeLaVista.finish(resultado);
                            setPaginaActual(paginaActual+1);
                        }
                    }
                }, paginaActual);
                break;

            case "Comedia":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_COMEDY, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                        if(resultado.getTotal_pages() > paginaActual){
                            escuchadorDeLaVista.finish(resultado);
                            setPaginaActual(paginaActual+1);
                        }
                    }
                }, paginaActual);
                break;

            case "Crimen":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_CRIME, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                        if(resultado.getTotal_pages() > paginaActual){
                            escuchadorDeLaVista.finish(resultado);
                            setPaginaActual(paginaActual+1);
                        }
                    }
                }, paginaActual);
                break;

            case "Documentales":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_DOCUMENTARY, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                        if(resultado.getTotal_pages() > paginaActual){
                            escuchadorDeLaVista.finish(resultado);
                            setPaginaActual(paginaActual+1);
                        }
                    }
                }, paginaActual);
                break;

            case "Drama":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_DRAMA, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                        if(resultado.getTotal_pages() > paginaActual){
                            escuchadorDeLaVista.finish(resultado);
                            setPaginaActual(paginaActual+1);
                        }
                    }
                }, paginaActual);
                break;

            case "Familia":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_FAMILY, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                        if(resultado.getTotal_pages() > paginaActual){
                            escuchadorDeLaVista.finish(resultado);
                            setPaginaActual(paginaActual+1);
                        }
                    }
                }, paginaActual);
                break;

            case "Fantasia":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_FANTASY, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                        if(resultado.getTotal_pages() > paginaActual){
                            escuchadorDeLaVista.finish(resultado);
                            setPaginaActual(paginaActual+1);
                        }
                    }
                }, paginaActual);
                break;

            case "Historia":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_HISTORY, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                        if(resultado.getTotal_pages() > paginaActual){
                            escuchadorDeLaVista.finish(resultado);
                            setPaginaActual(paginaActual+1);
                        }
                    }
                }, paginaActual);
                break;

            case "Horror":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_HORROR, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                        if(resultado.getTotal_pages() > paginaActual){
                            escuchadorDeLaVista.finish(resultado);
                            setPaginaActual(paginaActual+1);
                        }
                    }
                }, paginaActual);
                break;

            case "Musica":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_MUSIC, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                        if(resultado.getTotal_pages() > paginaActual){
                            escuchadorDeLaVista.finish(resultado);
                            setPaginaActual(paginaActual+1);
                        }
                    }
                }, paginaActual);
                break;

            case "Misterio":
                daoPeliculasRetrofit.getPagePeliculasPorGeneroDAO(TMDBHelper.MOVIE_GENRE_MYSTERY, new ResultListener<ListadoDePeliculas>() {
                    @Override
                    public void finish(ListadoDePeliculas resultado) {
                        if(resultado.getTotal_pages() > paginaActual){
                            escuchadorDeLaVista.finish(resultado);
                            setPaginaActual(paginaActual+1);
                        }
                    }
                }, paginaActual);
                break;
        }

    }

    public Boolean isAnyPageAvailable(){
        return hayPaginas;
    }

    public Integer getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(Integer paginaActual) {
        this.paginaActual = paginaActual;
    }
}
