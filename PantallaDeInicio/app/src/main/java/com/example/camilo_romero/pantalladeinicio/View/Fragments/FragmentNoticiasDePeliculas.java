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

import com.example.camilo_romero.pantalladeinicio.Controler.ControlerNoticias;
import com.example.camilo_romero.pantalladeinicio.Model.Noticia;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.NoticiasAdapter;
import com.example.camilo_romero.pantalladeinicio.utils.NewsHelper;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNoticiasDePeliculas extends Fragment {
    private ControlerNoticias controlerNoticias;
    private RecyclerView recyclerViewListaDeNoticias;
    private LinearLayoutManager linearLayoutManager;
    private NoticiasAdapter noticiasAdapter;
    private Notificador notificador;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        notificador = (Notificador) context;
    }

    public FragmentNoticiasDePeliculas() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        new ControlerNoticias().obtenerListaDeNoticiasDePeliculas(NewsHelper.url_new, "movies",100, new ResultListener<List<Noticia>>() {
            @Override
            public void finish(List<Noticia> resultado) {
                noticiasAdapter.setListaDeNoticias(resultado);
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_noticias_de_peliculas, container, false);

        controlerNoticias = new ControlerNoticias();


        recyclerViewListaDeNoticias = view.findViewById(R.id.recyclerViewListaDeNoticiasFragmentNoticias);
        recyclerViewListaDeNoticias.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerViewListaDeNoticias.setLayoutManager(linearLayoutManager);

        noticiasAdapter = new NoticiasAdapter(new NoticiasAdapter.Notificador() {
            @Override
            public void notificar(String link) {
                notificador.notificador(link);
            }
        });

        recyclerViewListaDeNoticias.setAdapter(noticiasAdapter);


        return view;


    }

    public interface Notificador {
        public void notificador(String url);
    }

}
