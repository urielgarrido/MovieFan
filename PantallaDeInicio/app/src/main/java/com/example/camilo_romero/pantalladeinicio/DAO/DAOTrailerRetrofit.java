package com.example.camilo_romero.pantalladeinicio.DAO;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeTrailers;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.Model.Trailer;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;
import com.example.camilo_romero.pantalladeinicio.utils.ServicePelicula;
import com.example.camilo_romero.pantalladeinicio.utils.ServiceTrailer;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAOTrailerRetrofit {

    private Retrofit retrofit;
    private ServiceTrailer serviceTrailer;

    public DAOTrailerRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(TMDBHelper.baseUrl)
                .addConverterFactory(GsonConverterFactory.create());


        retrofit = builder.client(httpClient.build()).build();
    }


    public void obtenerTrailersDePeliculasPorID(Integer id, final ResultListener<List<Trailer>> escuchadorDelControlador){
        serviceTrailer  = retrofit.create(ServiceTrailer.class);
        Call<ListadoDeTrailers> llamada = serviceTrailer.trailersDepeliculaPorID(id, TMDBHelper.apiKey);
        llamada.enqueue(new Callback<ListadoDeTrailers>() {
            @Override
            public void onResponse(Call<ListadoDeTrailers> call, Response<ListadoDeTrailers> response) {
                escuchadorDelControlador.finish(response.body().getResults());
            }

            @Override
            public void onFailure(Call<ListadoDeTrailers> call, Throwable t) {

            }
        });
    }
}
