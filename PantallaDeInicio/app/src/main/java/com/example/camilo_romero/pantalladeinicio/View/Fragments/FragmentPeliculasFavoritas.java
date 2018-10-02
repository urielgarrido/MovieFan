package com.example.camilo_romero.pantalladeinicio.View.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.camilo_romero.pantalladeinicio.Controler.ControlerPeliculasFavoritas;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDePeliculas;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.PeliculasFavoritosAdapter;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPeliculasFavoritas extends Fragment {


    private static PeliculasFavoritosAdapter peliculaFavoritasAdapter;
    private static NotificadorHaciaPeliculasActivity notificador;
    private RecyclerView recyclerViewFavoritos;


    public FragmentPeliculasFavoritas() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificador = (NotificadorHaciaPeliculasActivity) context;
    }

    public static FragmentPeliculasFavoritas fabricaDePeliculasFavoritas() {
        FragmentPeliculasFavoritas fragmentPeliculasFavoritas = new FragmentPeliculasFavoritas();
        peliculaFavoritasAdapter = new PeliculasFavoritosAdapter(new PeliculasFavoritosAdapter.NotificadorHaciaFragmentPeliculasFavoritas() {
            @Override
            public void notificar(Pelicula pelicula,ListadoDePeliculas listadoDePeliculas,Integer posicion) {

                notificador.recibirPeliculaClickeada(pelicula,listadoDePeliculas,posicion);
            }
        });


        new ControlerPeliculasFavoritas().obtenerPeliculasFavoritas((Context) notificador,new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                peliculaFavoritasAdapter.setListaDePeliculas(resultado);
            }
        });


        return fragmentPeliculasFavoritas;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_peliculas_favoritas, container, false);

        recyclerViewFavoritos = view.findViewById(R.id.recyclerViewFavoritos_fregmentpeliculasfavoritas);

        recyclerViewFavoritos.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);

        recyclerViewFavoritos.setLayoutManager(linearLayoutManager);

        recyclerViewFavoritos.setAdapter(peliculaFavoritasAdapter);


        return view;
    }

    public interface NotificadorHaciaPeliculasActivity{
        public void recibirPeliculaClickeada(Pelicula unaPelicula, ListadoDePeliculas listadoDePeliculas,Integer position);
    }
}
