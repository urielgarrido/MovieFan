package com.example.camilo_romero.pantalladeinicio.View.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.camilo_romero.pantalladeinicio.Controler.ControlerPelicula;
import com.example.camilo_romero.pantalladeinicio.Controler.ControlerSeries;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.ViewPagerBannerMainActivityAdapter;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPeliculas extends Fragment {

    private ListaDePeliculasEnHorizontalFragment listaDePeliculasEnHorizontalEstrenos;
    private ListaDePeliculasEnHorizontalFragment listaDePeliculasEnHorizontalPopulares;
    private ListaDePeliculasEnHorizontalFragment listaDePeliculasEnHorizontalEstrenadas;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_login for this fragment
        View view = inflater.inflate(R.layout.fragment_peliculas, container, false);

        listaDePeliculasEnHorizontalEstrenos = ListaDePeliculasEnHorizontalFragment.creadorDeFragmentsDeInternet(TMDBHelper.pedido_upcoming, "Estrenos");


        ListaDePeliculasEnHorizontalFragment listaDePeliculasEnHorizontalEstrenos = ListaDePeliculasEnHorizontalFragment.creadorDeFragmentsDeInternet(TMDBHelper.pedido_upcoming,"Estrenos");

        crearTransactionYInsertarDentroDelFragmentPeliculas(listaDePeliculasEnHorizontalEstrenos);

        ListaDePeliculasEnHorizontalFragment listaDePeliculasEnHorizontalPopulares = ListaDePeliculasEnHorizontalFragment.creadorDeFragmentsDeInternet(TMDBHelper.pedido_popular,"Populares");

        crearTransactionYInsertarDentroDelFragmentPeliculas(listaDePeliculasEnHorizontalPopulares);

        ListaDePeliculasEnHorizontalFragment listaDePeliculasEnHorizontalEstrenadas = ListaDePeliculasEnHorizontalFragment.creadorDeFragmentsDeInternet(TMDBHelper.pedido_now_playing,"En cartelera");

        crearTransactionYInsertarDentroDelFragmentPeliculas(listaDePeliculasEnHorizontalEstrenadas);


        return view;
    }


    public void crearTransactionYInsertarDentroDelFragmentPeliculas(Fragment fragment) {
        /*
        ESTE METODO SIRVE PARA CREAR LA TRANSACCION DE UN FRAGMENT SIN ARGUMENTOS LO USO PARA LOS FRAGMENTS HORIZONTALES
        YA QUE ESOS FRAGMENTS RECIBEN SUS ATRIBUTOS POR LA FABRICA DE FRAGMENTS
         */
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.linearLayoutContenedorDeFragments_fragmentpeliculas, fragment);
        fragmentTransaction.commit();
    }

}