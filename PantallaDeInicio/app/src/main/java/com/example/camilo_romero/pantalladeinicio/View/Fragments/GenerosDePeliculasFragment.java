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

import com.example.camilo_romero.pantalladeinicio.Controler.ControlerPeliculasPorGenero;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDePeliculas;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.Model.Serie;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.GenerosDePeliculasAdapterEnVertical;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class GenerosDePeliculasFragment extends Fragment {

    //CONSTANTE
    public static final String CLAVE_GENERO_INTERNET = "peliculas genero internet";

    //ATRIBUTOS
    private TextView tituloDeLaCategoria;
    private List<Serie> listaDePeliculasDeInternet;
    private NotificadorDeGenerosFragmentDePeliculasHaciaMainActivity notificadorDeGenerosFragmentDePeliculasHaciaMainActivity;
    private ControlerPeliculasPorGenero controlerPeliculasPorGenero;
    private RecyclerView recyclerViewGeneros;
    private Boolean isLoading = false;
    //private ProgressBar progressBar;
    private String nombreDeLaCategoria;
    private GenerosDePeliculasAdapterEnVertical adapterGenerosPeliculasVertical;
    private GridLayoutManager gridLayoutManager;


    //ON ATTACH
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.notificadorDeGenerosFragmentDePeliculasHaciaMainActivity = (NotificadorDeGenerosFragmentDePeliculasHaciaMainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //ON CREATE VIEW
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment_login for this fragment
        View view = inflater.inflate(R.layout.fragment_categorias, container, false);

        listaDePeliculasDeInternet = new ArrayList<>();
        tituloDeLaCategoria = view.findViewById(R.id.textViewTituloDeLaCategoria);


        Bundle bundle = getArguments();
        //Obtiene el nombre de la categoria del bundle
        nombreDeLaCategoria = bundle.getString(CLAVE_GENERO_INTERNET);
        //Setea el --TEXT VIEW-- con el nombre de la categoría obtenido
        tituloDeLaCategoria.setText(nombreDeLaCategoria);
        //Instancia el --RECYCLER VIEW-- pegandole a la vista
        recyclerViewGeneros = view.findViewById(R.id.recyclerViewVertical);
        recyclerViewGeneros.setHasFixedSize(true);
        //Le crea y setea un GRID LAYOUT MANAGER
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewGeneros.setLayoutManager(gridLayoutManager);
        //Crea una nueva instancia del controller
        controlerPeliculasPorGenero = new ControlerPeliculasPorGenero();
        //Creo y seteo el --ADAPTER-- con el constructor que pide sólo el Notificador de Géneros Adapter
        //Luego hay que setearle los resultados del pedido.
        adapterGenerosPeliculasVertical = new GenerosDePeliculasAdapterEnVertical(
                new GenerosDePeliculasAdapterEnVertical.NotificadorDeGenerosAdapterDePeliculasHaciaGenerosDePeliculasFragment() {
                    @Override
                    public void notificarClick(Pelicula unaPeliculaDeInternet, ListadoDePeliculas listadoDePeliculasDeInternet, Integer position) {
                        notificadorDeGenerosFragmentDePeliculasHaciaMainActivity.recibirPeliculaClickeada(unaPeliculaDeInternet, listadoDePeliculasDeInternet, position);
                    }
                });

        //SETEA EL --ADAPTER-- AL --RECYCLER VIEW--
        recyclerViewGeneros.setAdapter(adapterGenerosPeliculasVertical);
        //CREA EL LINEAR LAYOUT MANAGER Y SE LO SETEA AL RECYCLER
        /*linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewGeneros.setLayoutManager(linearLayoutManager);*/

        //SE PIDE LA PRIMERA PAGE DE PELICULAS PARA MOSTRAR
        getNewPage();

        //LE SETEA AL RECYCLER EL LISTENER DEL SCROLL (cuando se scrollea, escucha y pide una nueva página)
        recyclerViewGeneros.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (isLoading) {
                    return;
                }

                Integer posicionActual = gridLayoutManager.findLastVisibleItemPosition();
                Integer ultimaCelda = gridLayoutManager.getItemCount();

                if (posicionActual >= (ultimaCelda - 4)) {
                    getNewPage();
                }
            }
        });



        //ESTE ES EL METODO ANTERIOR UTILIZADO PARA OBTENER PELICULAS (SIN PAGINACION)
        /*controlerPeliculasPorGenero.obtenerPeliculasPorGenero(nombreDeLaCategoria, new ResultListener<ListadoDePeliculas>() {
            @Override
            public void finish(List<Pelicula> resultado) {

                final GenerosDePeliculasAdapterEnVertical generosDePeliculasAdapterEnVertical = new GenerosDePeliculasAdapterEnVertical(resultado, new GenerosDePeliculasAdapterEnVertical.NotificadorDeGenerosAdapterDePeliculasHaciaGenerosDePeliculasFragment() {
                    @Override
                    public void notificarClick(Pelicula unaPeliculaDeInternet, ListadoDePeliculas listadoDePeliculasDeInternet, Integer position) {

                        notificadorDeGenerosFragmentDePeliculasHaciaMainActivity.recibirPeliculaClickeada(unaPeliculaDeInternet,listadoDePeliculasDeInternet,position);

                    }
                });
             recyclerViewGeneros.setAdapter(generosDePeliculasAdapterEnVertical);

            }
        });
        */


        return view;
    }


    //GET NEW PAGE
        /*  Este método hace lo siguiente:
            1) Consulta al CONTROLLER si hay alguna página disponible para pedir.
            2) Pone isLoading en true, setea la Progress Bar y le da visibilidad
            3) Le pide al CONTROLLER que obtenga más películas
            4) Con el resultado en la implementación del listener le dice al ADAPTER que
            agregue lo que obtuvo a la lista, y hace un notify data set change (avisa el cambio).
            5) Pone el isLoading en false y quita la Progress Bar haciendo lo inverso del paso 2.
         */

    public void getNewPage() {

        if (controlerPeliculasPorGenero.isAnyPageAvailable()) {
            isLoading = true;
            //ACA VA LA PROGRESS BAR
            controlerPeliculasPorGenero.getPagePeliculasPorGenero(nombreDeLaCategoria, new ResultListener<ListadoDePeliculas>() {
                @Override
                public void finish(ListadoDePeliculas resultado) {
                    adapterGenerosPeliculasVertical.agregarPeliculasALaLista(resultado.getResults());
                    adapterGenerosPeliculasVertical.notifyDataSetChanged();
                    isLoading = false;
                }
            });

        }
    }

    //INTERFAZ QUE NOTIFICA LA PELICULA QUE FUE CLICKEADA
    public interface NotificadorDeGenerosFragmentDePeliculasHaciaMainActivity {
        void recibirPeliculaClickeada(Pelicula unaPelicula, ListadoDePeliculas listadoDePeliculas, Integer position);
    }


}
