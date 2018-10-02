package com.example.camilo_romero.pantalladeinicio.DAO;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeSeries;
import com.example.camilo_romero.pantalladeinicio.Model.Serie;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;
import com.example.camilo_romero.pantalladeinicio.utils.ServiceSeries;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOSeriesRetrofit {

    //ATRIBUTOS
    Retrofit retrofit;
    ServiceSeries serviceSeries;

    //CONSTRUCTOR
    public DAOSeriesRetrofit(){
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(TMDBHelper.baseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(okHttpClient.build()).build();
    }

    //METODO OBTENER SERIES POPULARES
    public void obtenerSeriesPopulares(final ResultListener<List<Serie>> escuchadorDelControlador){
        serviceSeries = retrofit.create(ServiceSeries.class);
        Call<ListadoDeSeries> llamada = serviceSeries.listaDeSeriesPopulares(TMDBHelper.apiKey,TMDBHelper.language_ENGLISH,1);

        llamada.enqueue(new Callback<ListadoDeSeries>() {
            @Override
            public void onResponse(Call<ListadoDeSeries> call, Response<ListadoDeSeries> response) {
                escuchadorDelControlador.finish(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ListadoDeSeries> call, Throwable t) {

            }
        });
    }

    //METODO OBTENER SERIES EN TRANSMISION
    public void obtenerSeriesEnTransmision(final ResultListener<List<Serie>> escuchadorDelControlador){
        serviceSeries = retrofit.create(ServiceSeries.class);
        Call<ListadoDeSeries> llamada = serviceSeries.listaDeSeriesEnTransmision(TMDBHelper.apiKey);

        llamada.enqueue(new Callback<ListadoDeSeries>() {
            @Override
            public void onResponse(Call<ListadoDeSeries> call, Response<ListadoDeSeries> response) {
                escuchadorDelControlador.finish(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ListadoDeSeries> call, Throwable t) {

            }
        });
    }

    //METODO OBTENER SERIES MEJOR VALORADAS
    public void obtenerSeriesMejorValoradas(final ResultListener<List<Serie>> escuchadorDelControlador){
        serviceSeries = retrofit.create(ServiceSeries.class);
        Call<ListadoDeSeries> llamada = serviceSeries.listaDeSeriesMejorValoradas(TMDBHelper.apiKey, 1);

        llamada.enqueue(new Callback<ListadoDeSeries>() {
            @Override
            public void onResponse(Call<ListadoDeSeries> call, Response<ListadoDeSeries> response) {
                escuchadorDelControlador.finish(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ListadoDeSeries> call, Throwable t) {

            }
        });
    }
    public void obtenerSeriesPorCategoria(String categoria, final ResultListener<List<Serie>> escuchadorDelControlador){
        serviceSeries = retrofit.create(ServiceSeries.class);
        Call<ListadoDeSeries> llamada = serviceSeries.listaDeSeriesPorCategoria(TMDBHelper.apiKey,categoria);

        llamada.enqueue(new Callback<ListadoDeSeries>() {
            @Override
            public void onResponse(Call<ListadoDeSeries> call, Response<ListadoDeSeries> response) {
                escuchadorDelControlador.finish(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ListadoDeSeries> call, Throwable t) {

            }
        });
    }

}
