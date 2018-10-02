package com.example.camilo_romero.pantalladeinicio.View.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.pantalladeinicio.Controler.ControlerActor;
import com.example.camilo_romero.pantalladeinicio.Model.Actor;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeActores;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.Model.Serie;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.ActoresAdapter;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescripcionesDeSeriesFragment extends Fragment {
    public static final String CLAVE_OBJETO_SERIE = "Serie";


    private TextView textViewTituloDeLaPelicula;
    private TextView textViewDescripcionDeLaPelicula;
    private ImageView imageViewImagenDeLaPelicula;
    private com.getbase.floatingactionbutton.FloatingActionButton fab_compartir,fab_favoritos;
    private ActoresAdapter actoresAdapter;
    private RecyclerView recyclerViewActores;
    private SerieNotificadorDeActorClickeadaHaciaMainActivity notificador;
    private NotificarClickDelFloatingActionButton notificarFAB;


    public static DescripcionesDeSeriesFragment fabricaFragmentDescripcionSeriesDeInternet(Serie unaSerie) {
        /*
        FABRICA DE FRAGMENTS DE DESCRIPCIONES EL CUAL RECIBE UNA PELICULA Y SE LA SETEA COMO ARGUMENTO AL FRAGMENT PARA PODER RECIBIRLA EN EL ONCREATEVIEW
         */
        DescripcionesDeSeriesFragment descripcionesDeSeriesFragment = new DescripcionesDeSeriesFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable(CLAVE_OBJETO_SERIE, unaSerie);

        descripcionesDeSeriesFragment.setArguments(bundle);

        return descripcionesDeSeriesFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();

        Serie serie = (Serie) bundle.getSerializable(CLAVE_OBJETO_SERIE);


        new ControlerActor().obtenerListaDeActoresDeSeries(serie.getId(), new ResultListener<List<Actor>>() {
            @Override
            public void finish(List<Actor> resultado) {
                actoresAdapter.setListaDeActores(resultado);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_login for this fragment
         /*
        TIENE SU PROPIO LAYOUT CREADO PERO POR CUESTIONES DE TIEMPO VOY A USAR EL QUE YA ESTA CREADO
         */
        final View view = inflater.inflate(R.layout.fragment_descripciones_de_peliculas, container, false);


        textViewTituloDeLaPelicula = view.findViewById(R.id.textViewTituloPelicula);
        textViewDescripcionDeLaPelicula = view.findViewById(R.id.textViewDescripcionPelicula);
        imageViewImagenDeLaPelicula = view.findViewById(R.id.imageViewPelicula);
        recyclerViewActores = view.findViewById(R.id.recyclerViewListaDeActores);
        fab_compartir=view.findViewById(R.id.fab_compartir);
        fab_favoritos=view.findViewById(R.id.fab_favoritos);

        fab_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificarFAB.clickFABFavoritos();
            }
        });
        fab_compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificarFAB.clickFABCompartir();
            }
        });

        Bundle bundle = getArguments();

        final Serie unaSerie = (Serie) bundle.getSerializable(CLAVE_OBJETO_SERIE);

        textViewTituloDeLaPelicula.setText(unaSerie.getName());
        textViewDescripcionDeLaPelicula.setText(unaSerie.getOverview());

        Picasso.with(getContext())
                .load(TMDBHelper.baseURLImagenes + TMDBHelper.IMAGE_SIZE_W500 + unaSerie.getBackdrop_path())
                .placeholder(R.drawable.image_loading)
                .error(R.drawable.image_not_loaded)
                .into(imageViewImagenDeLaPelicula);




        /*
        CREACION Y ARMADO DEL RECYCLER VIEW DE ACTORES
         */


        recyclerViewActores.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewActores.setLayoutManager(linearLayoutManager);

        actoresAdapter = new ActoresAdapter(new ActoresAdapter.NotificadorDeClickDeActorHaciaDescripcionesDeSeries() {
            @Override
            public void notificarClick(Actor unActor, ListadoDeActores listadoDeActores, Integer position) {
                notificador.notificarClickDeActor(unActor, listadoDeActores, position);
            }
        });

        recyclerViewActores.setAdapter(actoresAdapter);


        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificador = (SerieNotificadorDeActorClickeadaHaciaMainActivity) context;
        notificarFAB=(NotificarClickDelFloatingActionButton) context;
    }

    public interface SerieNotificadorDeActorClickeadaHaciaMainActivity {
        public void notificarClickDeActor(Actor unActor, ListadoDeActores listadoDeActores, Integer position);
    }
    public interface NotificarClickDelFloatingActionButton{
        void clickFABFavoritos();
        void clickFABCompartir();
    }
}