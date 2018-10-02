package com.example.camilo_romero.pantalladeinicio.utils;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeNoticias;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServiceNoticias {

    @GET("everything")
    Call<ListadoDeNoticias> listaDeNoticiasDePeliculas(
            @Query("domains") String inicio,
            @Query("q") String busqueda,
            @Query("pageSize") int cantidadDeResultados,
            @Query("apikey") String api_key



    );


}
