package com.example.camilo_romero.pantalladeinicio.DAO;

import com.example.camilo_romero.pantalladeinicio.Model.Actor;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeActores;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;
import com.example.camilo_romero.pantalladeinicio.utils.ServiceActores;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOActoresRetrofit {
    private Retrofit retrofit;
    private ServiceActores serviceActores;
    public DAOActoresRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(TMDBHelper.baseUrl)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
    }

    public void obtenerListaDeActoresDePeliculas(Integer idDeLaPelicula, final ResultListener<List<Actor>> escuchadorDelControlador){
        serviceActores = retrofit.create(ServiceActores.class);
        Call<ListadoDeActores> llamada = serviceActores.listaDeActoresDePeliculas(idDeLaPelicula,TMDBHelper.apiKey);

        llamada.enqueue(new Callback<ListadoDeActores>() {
            @Override
            public void onResponse(Call<ListadoDeActores> call, Response<ListadoDeActores> response) {
                 escuchadorDelControlador.finish(response.body().getCast());
            }

            @Override
            public void onFailure(Call<ListadoDeActores> call, Throwable t) {

            }
        });

    }
    public void obtenerListaDeActoresDeSeries(Integer idDeLaSerie, final ResultListener<List<Actor>> escuchadorDelControlador){
        serviceActores = retrofit.create(ServiceActores.class);
        Call<ListadoDeActores> llamada = serviceActores.listaDeActoresDeSeries(idDeLaSerie,TMDBHelper.apiKey);

        llamada.enqueue(new Callback<ListadoDeActores>() {
            @Override
            public void onResponse(Call<ListadoDeActores> call, Response<ListadoDeActores> response) {
                escuchadorDelControlador.finish((response.body().getCast()));
            }

            @Override
            public void onFailure(Call<ListadoDeActores> call, Throwable t) {

            }
        });
    }
    public void obtenerActorPorID(Integer IDDelActor,final ResultListener<Actor> escuchadorDelControlador){
        serviceActores = retrofit.create(ServiceActores.class);
        Call<Actor> llamada = serviceActores.actorPorID(IDDelActor,TMDBHelper.apiKey);

        llamada.enqueue(new Callback<Actor>() {
            @Override
            public void onResponse(Call<Actor> call, Response<Actor> response) {
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<Actor> call, Throwable t) {

            }
        });
    }
}
