package com.example.camilo_romero.pantalladeinicio.View.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.camilo_romero.pantalladeinicio.Controler.ControlerSeries;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeSeries;
import com.example.camilo_romero.pantalladeinicio.Model.Serie;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.GenerosDeSeriesAdapterEnVertical;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenerosDeSeriesFragment extends Fragment {
    public static final String CLAVE_CATEGORIA = "categoria";

    private GenerosDeSeriesAdapterEnVertical generosDeSeriesAdapter;

    private TextView textViewNombreDeLaCategoria;
    private RecyclerView recyclerView;

    private String categoria;

    private GridLayoutManager gridLayoutManager;

    private NotificadorHaciaSeriesActivity notificador;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificador = (NotificadorHaciaSeriesActivity) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        categoria = bundle.getString(CLAVE_CATEGORIA);
        generosDeSeriesAdapter = new GenerosDeSeriesAdapterEnVertical(new GenerosDeSeriesAdapterEnVertical.NotificadorHaciaFragmentCategoriasDeSeries() {
            @Override
            public void notificar(Serie unaSerie, ListadoDeSeries listadoDeSeries, Integer posicion) {
                notificador.recibirSerieClickeadaYLista(unaSerie,listadoDeSeries,posicion);
            }
        });

        new ControlerSeries().obtenerSeriesPorCategoria(categoria, new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                generosDeSeriesAdapter.setListaDeSeries(resultado);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_generos_de_series, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewListaInicial_seriescategorias);
        textViewNombreDeLaCategoria = view.findViewById(R.id.textVIewGenero_seriescategorias);

        textViewNombreDeLaCategoria.setText(categoria);

        gridLayoutManager = new GridLayoutManager(getContext(),2);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(generosDeSeriesAdapter);




        return view;
    }
    public interface NotificadorHaciaSeriesActivity {
        public void recibirSerieClickeadaYLista(Serie unaSerie,ListadoDeSeries listadoDeSeries,Integer posicion);
    }
}
