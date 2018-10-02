package com.example.camilo_romero.pantalladeinicio.DAO;


import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.utils.ServicePelicula;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDePeliculas;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOPeliculasRetrofit {
    private Retrofit retrofit;
    private ServicePelicula servicePelicula;

    public DAOPeliculasRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(TMDBHelper.baseUrl)
                .addConverterFactory(GsonConverterFactory.create());


        retrofit = builder.client(httpClient.build()).build();
    }

    public void obtenerPeliculasPopulares(final ResultListener<List<Pelicula>> escuchadorDelControlador) {
        servicePelicula = retrofit.create(ServicePelicula.class);
        Call<ListadoDePeliculas> llamada = servicePelicula.listaDePeliculasPopulares(TMDBHelper.apiKey,TMDBHelper.language_ENGLISH,1);

        llamada.enqueue(new Callback<ListadoDePeliculas>() {
            @Override
            public void onResponse(Call<ListadoDePeliculas> call, Response<ListadoDePeliculas> response) {
                escuchadorDelControlador.finish(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ListadoDePeliculas> call, Throwable t) {
                List<Pelicula> listaDePeliculasDeInternet = new ArrayList<>();
                escuchadorDelControlador.finish(listaDePeliculasDeInternet);
            }
        });
    }

    //----EL SIGUIENTE MÉTODO PIDE UNA NUEVA PÁGINA DE PELICULAS POR CATEGORÍA
    //OBTENER PELICULAS POR CATEGORIA --PAGINADA--
    public void getPagePeliculasPorGeneroDAO(String id_genero, final ResultListener<ListadoDePeliculas> escuchadorDelControlador,
                                             Integer page){
        servicePelicula = retrofit.create(ServicePelicula.class);
        Call<ListadoDePeliculas> llamada = servicePelicula.listaDeGeneros(id_genero, TMDBHelper.apiKey,TMDBHelper.language_SPANISH, page);

        llamada.enqueue(new Callback<ListadoDePeliculas>() {
            @Override
            public void onResponse(Call<ListadoDePeliculas> call, Response<ListadoDePeliculas> response) {
                escuchadorDelControlador.finish(response.body());
            }

            @Override
            public void onFailure(Call<ListadoDePeliculas> call, Throwable t) {

            }
        });

    }


    //OBTENER PELICULAS A ESTRENARSE
    public void obtenerPeliculasAEstrenarse(final ResultListener<List<Pelicula>> escuchadorDelControlador){
        servicePelicula = retrofit.create(ServicePelicula.class);
        Call<ListadoDePeliculas> llamada = servicePelicula.listaDePeliculasEstreno(TMDBHelper.apiKey,TMDBHelper.language_ENGLISH,1);

        llamada.enqueue(new Callback<ListadoDePeliculas>() {
            @Override
            public void onResponse(Call<ListadoDePeliculas> call, Response<ListadoDePeliculas> response) {
                escuchadorDelControlador.finish(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ListadoDePeliculas> call, Throwable t) {

            }
        });
    }
    public void obtenerPeliculasEstrenadas(final ResultListener<List<Pelicula>> escuchadorDelControlador){
        servicePelicula = retrofit.create(ServicePelicula.class);
        Call<ListadoDePeliculas> llamada = servicePelicula.listaDeEstrenadas(TMDBHelper.apiKey,TMDBHelper.language_SPANISH,1);

        llamada.enqueue(new Callback<ListadoDePeliculas>() {
            @Override
            public void onResponse(Call<ListadoDePeliculas> call, Response<ListadoDePeliculas> response) {
                escuchadorDelControlador.finish((response.body().getResults()));
            }

            @Override
            public void onFailure(Call<ListadoDePeliculas> call, Throwable t) {

            }
        });
    }

    public void obtenerPeliculasAPedido(String pedido, final ResultListener<List<Pelicula>> escuchadorDelControlador){
        servicePelicula = retrofit.create(ServicePelicula.class);
        Call<ListadoDePeliculas> llamada = servicePelicula.listaDePeliculasAPedido(pedido,TMDBHelper.apiKey,TMDBHelper.language_SPANISH,1);

        llamada.enqueue(new Callback<ListadoDePeliculas>() {
            @Override
            public void onResponse(Call<ListadoDePeliculas> call, Response<ListadoDePeliculas> response) {
                escuchadorDelControlador.finish(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ListadoDePeliculas> call, Throwable t) {

            }
        });
    }


}
