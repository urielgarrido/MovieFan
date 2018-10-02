package com.example.camilo_romero.pantalladeinicio.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.camilo_romero.pantalladeinicio.Model.Actor;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeActores;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.DescripcionesDeActoresFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerDescripcionesDeActores extends FragmentStatePagerAdapter {

    private List<Fragment> listaDeFragments;

    public ViewPagerDescripcionesDeActores(FragmentManager fm, final ListadoDeActores listadoDeActores) {
        super(fm);

        listaDeFragments = new ArrayList<>();

        for (Actor unActor : listadoDeActores.getCast()) {
            DescripcionesDeActoresFragment descripcionesDeActoresFragment = DescripcionesDeActoresFragment.fabricaDeActoresFragment(unActor);
            listaDeFragments.add(descripcionesDeActoresFragment);
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


    public List<Fragment> getListaDeFragments() {
        return listaDeFragments;
    }

}

