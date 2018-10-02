package com.example.camilo_romero.pantalladeinicio.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDePeliculas;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GenerosDePeliculasAdapterEnVertical extends RecyclerView.Adapter {

    //ATRIBUTOS
    private List<Pelicula> listaDePeliculas;
    private NotificadorDeGenerosAdapterDePeliculasHaciaGenerosDePeliculasFragment notificador;
    private Context context;


    //CONSTRUCTOR QUE SÓLO PIDE EL NOTIFICADOR DE GENEROS ADAPTER
    public GenerosDePeliculasAdapterEnVertical(NotificadorDeGenerosAdapterDePeliculasHaciaGenerosDePeliculasFragment notificador) {
        listaDePeliculas = new ArrayList<>();
        this.notificador = notificador;
    }

    //LOS TRES METODOS QUE DEBE IMPLEMENTAR EL ADAPTER
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_recycler_view_vertical,parent,false);
        PeliculasViewHolderDeInternet peliculasViewHolderDeInternet = new PeliculasViewHolderDeInternet(viewDeLaCelda);

        return peliculasViewHolderDeInternet;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pelicula pelicula = listaDePeliculas.get(position);
        PeliculasViewHolderDeInternet peliculasViewHolderDeInternet = (PeliculasViewHolderDeInternet) holder;
        peliculasViewHolderDeInternet.cargarPelicula(pelicula);

    }

    @Override
    public int getItemCount() {
        return listaDePeliculas.size();
    }


    private class PeliculasViewHolderDeInternet extends RecyclerView.ViewHolder{
        private ImageView imagenViewPelicula;
        private TextView textViewTituloDeLaPelicula;


        public PeliculasViewHolderDeInternet(View itemView) {
            super(itemView);
            imagenViewPelicula = itemView.findViewById(R.id.imageViewDeLaPelicula);
            textViewTituloDeLaPelicula = itemView.findViewById(R.id.textViewTituloDeLaPelicula);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notificador.notificarClick(listaDePeliculas.get(getAdapterPosition()),new ListadoDePeliculas(listaDePeliculas),getAdapterPosition());
                }
            });
        }
        public void cargarPelicula(Pelicula unaPelicula){
            textViewTituloDeLaPelicula.setText(unaPelicula.getTitle());

            Picasso.with(context)
                    .load(TMDBHelper.baseURLImagenes + TMDBHelper.IMAGE_SIZE_W154 + unaPelicula.getPoster_path())
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_not_loaded)
                    .into(imagenViewPelicula);

        }



    }


    //AGREGAR PELICULAS A LA LISTA
    public void agregarPeliculasALaLista(List<Pelicula> listaDePeliculas){
        this.listaDePeliculas.addAll(listaDePeliculas);
    }

    //INTERFAZ NOTIFICADOR DEL CLICK
    public interface NotificadorDeGenerosAdapterDePeliculasHaciaGenerosDePeliculasFragment {
        void notificarClick(Pelicula unaPelicula, ListadoDePeliculas listadoDePeliculas, Integer position);
    }

}
