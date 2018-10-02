package com.example.camilo_romero.pantalladeinicio.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDePeliculas;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class EventosDePeliculasAdapterEnHorizontal extends RecyclerView.Adapter {

    //ATRIBUTOS
    private List<Pelicula> listaDePeliculas;
    private NotificadorDeClickHorizontalDeInternetHaciaFragmentHorizontal notificador;
    private Context context;

    public void setListaDePeliculas(List<Pelicula> listaDePeliculas) {
        this.listaDePeliculas = listaDePeliculas;
        notifyDataSetChanged();
    }

    public List<Pelicula> getListaDePeliculas() {
        return listaDePeliculas;
    }

    //CONSTRUCTOR QUE RECIBE POR PARÁMETRO UNA LISTA DE PELICULAS Y UN NOTIFICADOR
    public EventosDePeliculasAdapterEnHorizontal(NotificadorDeClickHorizontalDeInternetHaciaFragmentHorizontal notificador) {
        this.notificador = notificador;
        listaDePeliculas = new ArrayList<>();
    }

    //ON CREATE VIEW HOLDER
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_recycler_view_horizontal,parent,false);
        PeliculasdDeInternetViewHolder peliculasdDeInternetViewHolder = new EventosDePeliculasAdapterEnHorizontal.PeliculasdDeInternetViewHolder(viewDeLaCelda);

        return peliculasdDeInternetViewHolder;
    }

    //ON BIND VIEW HOLDER
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pelicula unaPelicula = listaDePeliculas.get(position);
        EventosDePeliculasAdapterEnHorizontal.PeliculasdDeInternetViewHolder peliculasdDeInternetViewHolder = (EventosDePeliculasAdapterEnHorizontal.PeliculasdDeInternetViewHolder) holder;
        peliculasdDeInternetViewHolder.cargarPelicula(unaPelicula);
    }

    //GET ITEM COUNT
    @Override
    public int getItemCount() {
        return listaDePeliculas.size();
    }

    private class PeliculasdDeInternetViewHolder extends RecyclerView.ViewHolder{
        private ImageView imagenViewImagenPelicula;

        public PeliculasdDeInternetViewHolder(View itemView) {
            super(itemView);
            imagenViewImagenPelicula = itemView.findViewById(R.id.imageViewPelicula);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notificador.notificarClick(listaDePeliculas.get(getAdapterPosition()), new ListadoDePeliculas(listaDePeliculas), getAdapterPosition());
                }
            });

        }
        public void cargarPelicula(Pelicula unaPelicula){

            Helper.cargarPosters(unaPelicula.getPoster_path(),context,imagenViewImagenPelicula);

        }
    }

    //INTERFAZ NOTIFICADOR DEL CLICK
    public interface NotificadorDeClickHorizontalDeInternetHaciaFragmentHorizontal {
        void notificarClick(Pelicula unaPelicula, ListadoDePeliculas listadoDePeliculas, Integer position);
    }

}
