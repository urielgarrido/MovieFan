package com.example.camilo_romero.pantalladeinicio.View.Fragments;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.camilo_romero.pantalladeinicio.Controler.ControlerPelicula;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDePeliculas;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.EventosDePeliculasAdapterEnHorizontal;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class ListaDePeliculasEnHorizontalFragment extends Fragment {


    //ATRIBUTOS
    private NotificadorHorizontalDeInternetHaciaMainActivity notificadorHorizontal;
    private TextView generoPeliculas;
    EventosDePeliculasAdapterEnHorizontal eventosDePeliculasAdapterEnHorizontal;

    //CLAVES
    public static final String CLAVE_PEDIDO_PELICULAS = "pedido de peliculas";
    public static final String CLAVE_GENERO_PELICULA = "que viva el futbol Pisculichi";
    public static final String CLAVE_LISTA_DE_PELICULAS = "pedidoeefefef de peliculas";

    public static ListaDePeliculasEnHorizontalFragment creadorDeFragmentsDeInternet(String pedido, String genero) {
        /*
          ESTA ES LA FABRICA DE FRAGMENTS QUE RECIBE EL PEDIDO  Y EL GENERO COMO UN STRING
          Y LUEGO SETEA LOS ARGUMENTOS ASI MISMO Y LOS VUELVE A CONSEGUIR EN EL ONCREATE
         */
        Bundle bundle = new Bundle();


        bundle.putString(CLAVE_PEDIDO_PELICULAS, pedido);
        bundle.putString(CLAVE_GENERO_PELICULA, genero);
        bundle.putSerializable(CLAVE_LISTA_DE_PELICULAS, new ListadoDePeliculas(new ArrayList<Pelicula>()));

        ListaDePeliculasEnHorizontalFragment listaDePeliculasEnHorizontalFragment = new ListaDePeliculasEnHorizontalFragment();
        listaDePeliculasEnHorizontalFragment.setArguments(bundle);

        return listaDePeliculasEnHorizontalFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        final String pedido = bundle.getString(CLAVE_PEDIDO_PELICULAS);
        new ControlerPelicula(getContext()).obtenerPeliculasPorPedido(pedido, new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> resultado) {
                eventosDePeliculasAdapterEnHorizontal.setListaDePeliculas(resultado);
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
        String genero = bundle.getString(CLAVE_GENERO_PELICULA);


        //text views
        generoPeliculas = view.findViewById(R.id.textVIewGenero);
        generoPeliculas.setText(genero);


        //creamos el reciclerView y le especicificmos cual sera su contenedor
        RecyclerView recyclerViewPeliculas = view.findViewById(R.id.recyclerViewListaInicial);
        recyclerViewPeliculas.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPeliculas.setLayoutManager(linearLayoutManager);

        eventosDePeliculasAdapterEnHorizontal = new EventosDePeliculasAdapterEnHorizontal(new EventosDePeliculasAdapterEnHorizontal.NotificadorDeClickHorizontalDeInternetHaciaFragmentHorizontal() {
            @Override
            public void notificarClick(Pelicula unaPeliculaDeInternet, ListadoDePeliculas listadoDePeliculasDeInternet, Integer position) {
                notificadorHorizontal.recibirPeliculaClickeada(unaPeliculaDeInternet, listadoDePeliculasDeInternet, position);


            }

        });

        recyclerViewPeliculas.setAdapter(eventosDePeliculasAdapterEnHorizontal);

        return view;


    }


    //SE SOBREESCRIBE EL ONATTACH PARA QUE EL NOTIFICADOR NO QUEDE NULL
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.notificadorHorizontal = (NotificadorHorizontalDeInternetHaciaMainActivity) context;

    }

    //INTERFAZ NOTIFICADOR QUE IMPLEMENTARÁ EL MAIN ACTIVITY
    public interface NotificadorHorizontalDeInternetHaciaMainActivity {
        void recibirPeliculaClickeada(Pelicula unaPelicula, ListadoDePeliculas listadoDePeliculas, Integer position);
    }

}


