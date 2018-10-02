package com.example.camilo_romero.pantalladeinicio.View.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.utils.Helper;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;
import com.squareup.picasso.Picasso;

public class BannerEnElMainActivityFragment extends Fragment {
    private ImageView imageViewDeLaPelicula;

    public static final String LINK_DE_LA_IMAGEN ="link de la imagen";

    public BannerEnElMainActivityFragment FabricaDeBannerFragments(String linkDeLaImagen){
        /*
        FABRICA DE FRAGMENT QUE USA EL VIEW PAGER
         */
        BannerEnElMainActivityFragment bannerEnElMainActivityFragment = new BannerEnElMainActivityFragment();

        Bundle bundle = new Bundle();

        bundle.putString(LINK_DE_LA_IMAGEN,linkDeLaImagen);

        bannerEnElMainActivityFragment.setArguments(bundle);

        return bannerEnElMainActivityFragment;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_login for this fragment
        View view = inflater.inflate(R.layout.fragment_banner, container, false);

        imageViewDeLaPelicula = view.findViewById(R.id.imageViewDeLaPeliculaBannerMainActivity);

        Bundle bundle = getArguments();

        String linkDeLaimagen = bundle.getString(LINK_DE_LA_IMAGEN);


        Helper.cargarPortada(linkDeLaimagen,getContext(),imageViewDeLaPelicula);



        return view;
    }

}