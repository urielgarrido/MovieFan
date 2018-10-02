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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.pantalladeinicio.Controler.ControlerActor;
import com.example.camilo_romero.pantalladeinicio.Controler.ControlerPelicula;
import com.example.camilo_romero.pantalladeinicio.Model.Actor;
import com.example.camilo_romero.pantalladeinicio.Model.Cartelera;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeActores;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.Model.Trailer;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.ActoresAdapter;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.CarteleraAdapter;
import com.example.camilo_romero.pantalladeinicio.utils.Helper;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class DescripcionesDePeliculasFragment extends Fragment {
    public static final String CLAVE_OBJETO_PELICULA_INTERNET = "pelicula de internet";
    private static final int CANTIDAD_RESULTADOS_A_MOSTRAR = 6;
    public static final String CLAVE_OBJETO_TRAILER="trailer de pelicula";

    private Pelicula pelicula;
    private Trailer trailer;

    private TextView textViewTituloDeLaPelicula;
    private TextView textViewDescripcionDeLaPelicula;
    private ImageView imageViewImagenDeLaPelicula;
    private ImageButton imageButtonYoutube;
    private com.getbase.floatingactionbutton.FloatingActionButton fab_favoritos,fab_compartir;
    private NotificadorDelFloatinActionButton notificadorDelFloatinActionButton;
    private NotificarClickYoutube notificarClickYoutube;

    private TextView textViewTituloDeLaCartelera;
    private RecyclerView recyclerViewListaDeCarteleras;

    private ActoresAdapter actoresAdapter;
    private CarteleraAdapter carteleraAdapter;
    private RecyclerView recyclerViewActores;
    private PeliculaNotificadorDeActorHaciaMainActivity notificador;


    public static DescripcionesDePeliculasFragment fabricaFragmentDescripcionPeliculasDeInternet(Pelicula unaPelicula) {
        /*
        FABRICA DE FRAGMENTS DE DESCRIPCIONES EL CUAL RECIBE UNA PELICULA Y SE LA SETEA COMO ARGUMENTO AL FRAGMENT PARA PODER RECIBIRLA EN EL ONCREATEVIEW
         */
        DescripcionesDePeliculasFragment descripcionesDePeliculasFragment = new DescripcionesDePeliculasFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable(CLAVE_OBJETO_PELICULA_INTERNET, unaPelicula);

        descripcionesDePeliculasFragment.setArguments(bundle);

        return descripcionesDePeliculasFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle bundle = getArguments();

        pelicula = (Pelicula) bundle.getSerializable(CLAVE_OBJETO_PELICULA_INTERNET);



        new ControlerActor().obtenerListaDeActoresDePeliculas(pelicula.getId(), new ResultListener<List<Actor>>() {
            @Override
            public void finish(List<Actor> resultado) {
                actoresAdapter.setListaDeActores(resultado);
            }
        });
        setArguments(bundle);
        internet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_login for this fragment
        final View view = inflater.inflate(R.layout.fragment_descripciones_de_peliculas, container, false);

        textViewTituloDeLaPelicula = view.findViewById(R.id.textViewTituloPelicula);
        textViewDescripcionDeLaPelicula = view.findViewById(R.id.textViewDescripcionPelicula);
        imageViewImagenDeLaPelicula = view.findViewById(R.id.imageViewPelicula);
        fab_favoritos=view.findViewById(R.id.fab_favoritos);
        fab_compartir=view.findViewById(R.id.fab_compartir);
        recyclerViewActores = view.findViewById(R.id.recyclerViewListaDeActores);
        imageButtonYoutube=view.findViewById(R.id.boton_youtube);

        textViewTituloDeLaCartelera = view.findViewById(R.id.textViewTituloDeLaCartelera_fragmentdescripcionesdepeliculas);
        recyclerViewListaDeCarteleras = view.findViewById(R.id.recyclerViewListaDeCarteleras_fragmentdescripcionesdepeliculas);


        //Bundle bundle = getArguments();


        textViewTituloDeLaPelicula.setText(pelicula.getTitle());
        textViewDescripcionDeLaPelicula.setText(pelicula.getOverview());


        Helper.cargarPortada(pelicula.getBackdrop_path(), getContext(), imageViewImagenDeLaPelicula);


        fab_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificadorDelFloatinActionButton.clickDelFloatingActionButtonFavoritos(pelicula);
            }
        });

        fab_compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificadorDelFloatinActionButton.clickDelFloatingActionButtonCompartir();
            }
        });
        imageButtonYoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificarClickYoutube.clickDelBotonPlayYoutube(pelicula);
            }
        });

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

        recyclerViewListaDeCarteleras.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerViewListaDeCarteleras.setLayoutManager(linearLayoutManager1);

        carteleraAdapter = new CarteleraAdapter();

        recyclerViewListaDeCarteleras.setAdapter(carteleraAdapter);


        return view;
    }

    private void internet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builderTitulo = new StringBuilder();
                final List<Cartelera> listaDeCartelera = new ArrayList<>();
                Document doc = null;
                try {
                    doc = Jsoup.connect("https://www.google.com.ar/search?q=" + pelicula.getTitle()).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                final Elements cine = doc.select("a.vk_bk.lr-s-din");
                final Elements horarios = doc.select("div.lr_c_s");

                Elements titulo = doc.select("span.vk_bk.lr_c_h");


                    builderTitulo.append(titulo.text());

                    Integer tamanoDeArrayCine = cine.size() - 1;
                    int resultado = cine.size();
                    if(resultado >= CANTIDAD_RESULTADOS_A_MOSTRAR){
                        resultado = CANTIDAD_RESULTADOS_A_MOSTRAR;
                    }


                    for (Integer i = 0; i < resultado; i++) {
                        listaDeCartelera.add(new Cartelera(cine.get(i).text(), horarios.get(i).text()));

                    }




                /*runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textViewTituloDeLaCartelera.setText(builderTitulo.toString());
                        carteleraAdapter.setListaDeCarteleras(listaDeCartelera);


                    }
                });*/
            }
        }).start();
    }

    public interface NotificadorDelFloatinActionButton {
        void clickDelFloatingActionButtonFavoritos(Pelicula unaPelicula);
        void clickDelFloatingActionButtonCompartir();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificadorDelFloatinActionButton = (DescripcionesDePeliculasFragment.NotificadorDelFloatinActionButton) context;
        notificador = (PeliculaNotificadorDeActorHaciaMainActivity) context;
        notificarClickYoutube=(NotificarClickYoutube)context;
    }

    public interface PeliculaNotificadorDeActorHaciaMainActivity {
        public void notificarClickDeActor(Actor unActor, ListadoDeActores listadoDeActores, Integer position);
    }

    public interface NotificarClickYoutube{
        void clickDelBotonPlayYoutube(Pelicula pelicula);
    }

}
