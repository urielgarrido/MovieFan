package com.example.camilo_romero.pantalladeinicio.View.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.camilo_romero.pantalladeinicio.Controler.ControlerSeries;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeSeries;
import com.example.camilo_romero.pantalladeinicio.Model.Serie;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.EventosDeSeriesAdapterEnHorizontal;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.List;

public class ListaDeSeriesEnHorizontalFragment extends Fragment {
    //ATRIBUTOS
    private NotificadorHorizontalDeSeriesHaciaSeriesActivity notificador;
    private EventosDeSeriesAdapterEnHorizontal eventosDeSeriesAdapterEnHorizontal;
    private TextView generoSeries;

    //CLAVES

    public static final String CLAVE_GENERO_SERIE = "que viva el futbol Pisculichi";

    public static ListaDeSeriesEnHorizontalFragment creadorDeFragmentsDeSeriesEnHorizontal(String genero) {
        /*
          ESTA ES LA FABRICA DE FRAGMENTS QUE RECIBE LA LISTA Y EL GENERO COMO UN STRING
          Y LUEGO SETEA LOS ARGUMENTOS ASI MISMO Y LOS VUELVE A CONSEGUIR EN EL ONCREATE
         */
        Bundle bundle = new Bundle();

        bundle.putString(CLAVE_GENERO_SERIE, genero);

        ListaDeSeriesEnHorizontalFragment listaDeSeriesEnHorizontalFragment = new ListaDeSeriesEnHorizontalFragment();
        listaDeSeriesEnHorizontalFragment.setArguments(bundle);

        return listaDeSeriesEnHorizontalFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        String genero = (String) bundle.get(CLAVE_GENERO_SERIE);

        new ControlerSeries().obtenerUnEventoDeSeries(genero, new ResultListener<List<Serie>>() {
            @Override
            public void finish(List<Serie> resultado) {
                eventosDeSeriesAdapterEnHorizontal.setListaDeSeries(resultado);
            }
        });

    }

    //ON CREATE VIEW
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_generos_vertical, container, false);

        Bundle bundle = getArguments();

        //obtenemos los datos del bundle
        String genero = bundle.getString(CLAVE_GENERO_SERIE);

        //creamos el reciclerView y le especicificmos cual sera su contenedor
        RecyclerView recyclerViewSeries = view.findViewById(R.id.recyclerViewListaInicial);
        recyclerViewSeries.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewSeries.setLayoutManager(linearLayoutManager);

        //text views
        generoSeries = view.findViewById(R.id.textVIewGenero);
        generoSeries.setText(genero);


        /*
        CREO EL ADAPTER Y LE DOY EL CONTENEDOR DE LA LISTA DE PELICULAS Y LA INTERFAZ QUE NOTIFICA EL CLICK
         */
        eventosDeSeriesAdapterEnHorizontal = new EventosDeSeriesAdapterEnHorizontal(new EventosDeSeriesAdapterEnHorizontal.NotificadorDeClickHorizontalDeSeriestHaciaFragmentHorizontal() {
            @Override
            public void notificarClick(Serie unaSerie, ListadoDeSeries listadoDeSeries, Integer position) {
                notificador.recibirSerieClickeadaYLista(unaSerie, listadoDeSeries, position);
            }
        });

        //LE SETEO EL ADAPTER AL RECYCLER VIEW
        recyclerViewSeries.setAdapter(eventosDeSeriesAdapterEnHorizontal);

        return view;


    }


    //SE SOBREESCRIBE EL ONATTACH PARA QUE EL NOTIFICADOR NO QUEDE NULL
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.notificador = (NotificadorHorizontalDeSeriesHaciaSeriesActivity) context;

    }

    //INTERFAZ NOTIFICADOR QUE IMPLEMENTARÁ EL MAIN ACTIVITY
    public interface NotificadorHorizontalDeSeriesHaciaSeriesActivity {
        void recibirSerieClickeadaYLista(Serie unaSerie, ListadoDeSeries listadoDeSeries, Integer position);
    }

}


