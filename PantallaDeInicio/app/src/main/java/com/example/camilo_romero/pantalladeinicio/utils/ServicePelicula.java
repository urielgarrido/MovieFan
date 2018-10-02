package com.example.camilo_romero.pantalladeinicio.utils;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDePeliculas;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServicePelicula {

    @GET("movie/popular")
    Call<ListadoDePeliculas> listaDePeliculasPopulares(
            @Query("api_key") String api_key,
            @Query("language") String idioma,
            @Query("page") int pagina);


    @GET("genre/{id_genero}/movies")
    Call<ListadoDePeliculas> listaDeGeneros(
            @Path("id_genero") String id_genero,
            @Query("api_key") String api_key,
            @Query("language") String idioma,
            @Query("page") Integer page
    );

    @GET("movie/upcoming")
    Call<ListadoDePeliculas> listaDePeliculasEstreno(
            @Query("api_key") String api_key,
            @Query("language") String idioma,
            @Query("page") int pagina);

    @GET("movie/now_playing")
    Call<ListadoDePeliculas> listaDeEstrenadas(
            @Query("api_key") String api_key,
            @Query("language") String idioma,
            @Query("page") int pagina);



    @GET("movie/{pedido}")
    Call<ListadoDePeliculas> listaDePeliculasAPedido(
            @Path("pedido") String pedido,
            @Query("api_key") String api_key,
            @Query("language") String idioma,
            @Query("page") int pagina);





}
