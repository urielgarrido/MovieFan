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
public class ListaDeNoticiasEnHorizontalFragment extends Fragment {
    private RecyclerView recyclerView;
    NoticiasAdapter noticiasAdapter;
    private Notificador notificador;
    public static final String CLAVE_BUSQUEDA = "clave";


    public static ListaDeNoticiasEnHorizontalFragment fabricaDeFragment(String busqueda) {
        ListaDeNoticiasEnHorizontalFragment listaDeNoticiasEnHorizontalFragment = new ListaDeNoticiasEnHorizontalFragment();

        Bundle bundle = new Bundle();


        bundle.putString(CLAVE_BUSQUEDA, busqueda);

        listaDeNoticiasEnHorizontalFragment.setArguments(bundle);

        return listaDeNoticiasEnHorizontalFragment;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        notificador = (Notificador) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        String busqueda = (String) bundle.get(CLAVE_BUSQUEDA);

        new ControlerNoticias().obtenerListaDeNoticiasDePeliculas(NewsHelper.url_new, busqueda, 5, new ResultListener<List<Noticia>>() {
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
        final View view = inflater.inflate(R.layout.fragment_lista_de_noticias_en_horizontal, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewListaDeNoticias_fragmentlistadenoticias);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

        noticiasAdapter = new NoticiasAdapter(new NoticiasAdapter.Notificador() {
            @Override
            public void notificar(String link) {
                notificador.notificador(link);
            }
        });

        recyclerView.setAdapter(noticiasAdapter);


        return view;
    }

    public interface Notificador{
        public void notificador(String url);
    }
}
