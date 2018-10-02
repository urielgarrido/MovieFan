package com.example.camilo_romero.pantalladeinicio.utils;

import com.example.camilo_romero.pantalladeinicio.Model.Actor;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeActores;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeSeries;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceActores {

    @GET("movie/{movie_id}/credits")
    Call<ListadoDeActores> listaDeActoresDePeliculas(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key

    );

    @GET("tv/{tv_id}/credits")
    Call<ListadoDeActores> listaDeActoresDeSeries(
            @Path("tv_id") int tv_id,
            @Query("api_key") String api_key

    );

    @GET("person/{person_id}")
    Call<Actor> actorPorID(
            @Path("person_id") int actor_id,
            @Query("api_key") String api_key
    );


}
