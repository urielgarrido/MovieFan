package com.example.camilo_romero.pantalladeinicio.DAO;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeNoticias;
import com.example.camilo_romero.pantalladeinicio.Model.Noticia;
import com.example.camilo_romero.pantalladeinicio.utils.NewsHelper;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;
import com.example.camilo_romero.pantalladeinicio.utils.ServiceNoticias;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DAONoticiasRetrofit {
    private Retrofit retrofit;
    private ServiceNoticias serviceNoticias;

    public DAONoticiasRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(NewsHelper.base_url)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
    }

    public void obtenerListaDeNoticiasDePeliculas(String dominios, String busqueda,Integer cantidad, final ResultListener<List<Noticia>> escuchadorDelControlador){
        serviceNoticias = retrofit.create(ServiceNoticias.class);
        Call<ListadoDeNoticias> llamada = serviceNoticias.listaDeNoticiasDePeliculas(dominios,busqueda,cantidad, NewsHelper.api_key);

        llamada.enqueue(new Callback<ListadoDeNoticias>() {
            @Override
            public void onResponse(Call<ListadoDeNoticias> call, Response<ListadoDeNoticias> response) {
                escuchadorDelControlador.finish(response.body().getArticles());
            }

            @Override
            public void onFailure(Call<ListadoDeNoticias> call, Throwable t) {

            }
        });
    }
}
