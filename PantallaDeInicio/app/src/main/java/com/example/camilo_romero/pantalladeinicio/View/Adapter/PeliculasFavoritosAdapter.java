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
import com.example.camilo_romero.pantalladeinicio.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class PeliculasFavoritosAdapter extends RecyclerView.Adapter {
    private List<Pelicula> listaDePeliculas;
    private Context context;
    private NotificadorHaciaFragmentPeliculasFavoritas notificador;


    public PeliculasFavoritosAdapter(NotificadorHaciaFragmentPeliculasFavoritas notificador){
        listaDePeliculas = new ArrayList<>();
        this.notificador = notificador;

    }

    public void setListaDePeliculas(List<Pelicula> listaDePeliculas) {
        this.listaDePeliculas = listaDePeliculas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_peliculas_favoritas,parent,false);

        PeliculasFavoritasViewHolder peliculasFavoritasViewHolder = new PeliculasFavoritasViewHolder(viewDeLaCelda);

        return peliculasFavoritasViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Pelicula pelicula = listaDePeliculas.get(position);

        PeliculasFavoritasViewHolder peliculasFavoritasViewHolder = (PeliculasFavoritasViewHolder) holder;

        peliculasFavoritasViewHolder.cargarPelicula(pelicula);
    }

    @Override
    public int getItemCount() {
        return listaDePeliculas.size();
    }

    private class PeliculasFavoritasViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagenViewPelicula;
        private TextView textViewTituloDeLaPelicula;

        public PeliculasFavoritasViewHolder(View itemView) {
            super(itemView);
            imagenViewPelicula = itemView.findViewById(R.id.imageViewDeLaPelicula);
            textViewTituloDeLaPelicula = itemView.findViewById(R.id.textViewTituloDeLaPelicula);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListadoDePeliculas listadoDePeliculas = new ListadoDePeliculas(listaDePeliculas);
                   notificador.notificar(listaDePeliculas.get(getAdapterPosition()),listadoDePeliculas,getAdapterPosition());
                }
            });
        }

        public void cargarPelicula(Pelicula pelicula){
            Helper.cargarPortada(pelicula.getPoster_path(),context,imagenViewPelicula);
            textViewTituloDeLaPelicula.setText(pelicula.getTitle());
        }

    }

    public interface NotificadorHaciaFragmentPeliculasFavoritas{
        public void notificar(Pelicula pelicula, ListadoDePeliculas listadoDePeliculas,Integer posicion);
    }
}
