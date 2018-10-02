package com.example.camilo_romero.pantalladeinicio.View.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.camilo_romero.pantalladeinicio.Controler.ControlerSeries;
import com.example.camilo_romero.pantalladeinicio.Model.Serie;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.ViewPagerDescripcionesDeSeriesAdapter;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentsSeries extends Fragment {

    ControlerSeries controlerSeries;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_login for this fragment
        View view = inflater.inflate(R.layout.fragment_series, container, false);







        return view;
    }



}