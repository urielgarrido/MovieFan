package com.example.camilo_romero.pantalladeinicio.utils;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeTrailers;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceTrailer {

    @GET("movie/{id_movie}/videos")
    Call<ListadoDeTrailers> trailersDepeliculaPorID(
            @Path("id_movie") Integer idPelicula,
            @Query("api_key") String api_key
    );
}
