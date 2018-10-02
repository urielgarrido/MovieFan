package com.example.camilo_romero.pantalladeinicio.View.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDePeliculas;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.DescripcionesDePeliculasFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerDescripcionDePeliculasAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> listaDeFragments;

    public ViewPagerDescripcionDePeliculasAdapter(FragmentManager fm, final ListadoDePeliculas listadoDePeliculas) {
        super(fm);

        listaDeFragments = new ArrayList<>();

        for (Pelicula unaPelicula : listadoDePeliculas.getResults()) {
            DescripcionesDePeliculasFragment descripcionesDePeliculasFragment = DescripcionesDePeliculasFragment.fabricaFragmentDescripcionPeliculasDeInternet(unaPelicula);
            listaDeFragments.add(descripcionesDePeliculasFragment);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return listaDeFragments.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragments.size();
    }

}