package com.example.camilo_romero.pantalladeinicio.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.BannerEnElMainActivityFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerBannerMainActivityAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> listaDeFragments;




    public ViewPagerBannerMainActivityAdapter(FragmentManager fm, List<Pelicula> listadoDePeliculasDeInternet) {
        super(fm);
        listaDeFragments = new ArrayList<>();
        for (Pelicula unaPelicula : listadoDePeliculasDeInternet) {
            BannerEnElMainActivityFragment bannerEnElMainActivityFragment = new BannerEnElMainActivityFragment().FabricaDeBannerFragments(unaPelicula.getBackdrop_path());
            listaDeFragments.add(bannerEnElMainActivityFragment);

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
