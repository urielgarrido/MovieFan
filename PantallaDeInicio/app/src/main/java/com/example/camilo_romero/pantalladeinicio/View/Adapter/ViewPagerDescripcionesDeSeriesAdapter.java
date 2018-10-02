package com.example.camilo_romero.pantalladeinicio.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeSeries;
import com.example.camilo_romero.pantalladeinicio.Model.Serie;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.DescripcionesDeSeriesFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerDescripcionesDeSeriesAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> listaDeFragments;
    public ViewPagerDescripcionesDeSeriesAdapter(FragmentManager fm, ListadoDeSeries listadoDeSeries) {
        super(fm);

        listaDeFragments = new ArrayList<>();

        for (Serie unaSerie : listadoDeSeries.getResults()) {

            DescripcionesDeSeriesFragment descripcionesDeSeriesFragment = DescripcionesDeSeriesFragment.fabricaFragmentDescripcionSeriesDeInternet(unaSerie);
            listaDeFragments.add(descripcionesDeSeriesFragment);

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
