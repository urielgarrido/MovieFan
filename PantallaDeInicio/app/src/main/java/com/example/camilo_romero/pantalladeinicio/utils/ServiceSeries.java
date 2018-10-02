package com.example.camilo_romero.pantalladeinicio.utils;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeSeries;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceSeries {
    @GET("tv/popular")
    Call<ListadoDeSeries> listaDeSeriesPopulares(
            @Query("api_key") String api_key,
            @Query("language") String idioma,
            @Query("page") int pagina
    );
    @GET("tv/on_the_air")
    Call<ListadoDeSeries> listaDeSeriesEnTransmision(
            @Query("api_key") String api_key
    );
    @GET("tv/top_rated")
    Call<ListadoDeSeries> listaDeSeriesMejorValoradas(
            @Query("api_key") String api_key,
            @Query("page") int pagina
    );
/*
    https://api.themoviedb.org/3/discover/tv?api_key=8f5f296308f1eb322bf913a9a5b059c2&with_genres=18
*/
    @GET("discover/tv")
    Call<ListadoDeSeries> listaDeSeriesPorCategoria(
            @Query("api_key") String api_key,
            @Query("with_genres") String categoria
    );

}
