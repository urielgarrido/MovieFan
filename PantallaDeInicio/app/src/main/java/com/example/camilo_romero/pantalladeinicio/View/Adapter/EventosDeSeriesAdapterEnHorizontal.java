package com.example.camilo_romero.pantalladeinicio.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeSeries;
import com.example.camilo_romero.pantalladeinicio.Model.Serie;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EventosDeSeriesAdapterEnHorizontal extends RecyclerView.Adapter {
    //ATRIBUTOS
    private List<Serie> listaDeSeries;
    private NotificadorDeClickHorizontalDeSeriestHaciaFragmentHorizontal notificador;
    private Context context;


    public void setListaDeSeries(List<Serie> listaDeSeries) {
        this.listaDeSeries = listaDeSeries;
        notifyDataSetChanged();
    }

    //CONSTRUCTOR QUE RECIBE POR PARÁMETRO UNA LISTA DE PELICULAS Y UN NOTIFICADOR
    public EventosDeSeriesAdapterEnHorizontal(NotificadorDeClickHorizontalDeSeriestHaciaFragmentHorizontal notificador) {
        listaDeSeries = new ArrayList<>();
        this.notificador = notificador;
    }

    //ON CREATE VIEW HOLDER
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_recycler_view_horizontal,parent,false);
        SeriesViewHolder seriesViewHolder = new SeriesViewHolder(viewDeLaCelda);

        return seriesViewHolder;
    }

    //ON BIND VIEW HOLDER
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Serie unaSerie = listaDeSeries.get(position);
        SeriesViewHolder seriesViewHolder = (SeriesViewHolder) holder;
        seriesViewHolder.cargarSerie(unaSerie);
    }

    //GET ITEM COUNT
    @Override
    public int getItemCount() {
        return listaDeSeries.size();
    }

    private class SeriesViewHolder extends RecyclerView.ViewHolder{
        private ImageView imagenViewImagenPelicula;

        public SeriesViewHolder(View itemView) {
            super(itemView);
            imagenViewImagenPelicula = itemView.findViewById(R.id.imageViewPelicula);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notificador.notificarClick(listaDeSeries.get(getAdapterPosition()), new ListadoDeSeries(listaDeSeries), getAdapterPosition());
                }
            });

        }
        public void cargarSerie(Serie unaSerie){

            Picasso.with(context)
                    .load(TMDBHelper.baseURLImagenes + TMDBHelper.IMAGE_SIZE_W154 + unaSerie.getPoster_path())
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_not_loaded)
                    .into(imagenViewImagenPelicula);


        }



    }

    //INTERFAZ NOTIFICADOR DEL CLICK
    public interface NotificadorDeClickHorizontalDeSeriestHaciaFragmentHorizontal {
        void notificarClick(Serie unaSerie, ListadoDeSeries listadoDeSeries, Integer position);
    }

}

