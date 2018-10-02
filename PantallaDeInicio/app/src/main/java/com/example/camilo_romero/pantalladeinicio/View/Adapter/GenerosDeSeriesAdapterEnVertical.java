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
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeSeries;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.Model.Serie;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GenerosDeSeriesAdapterEnVertical extends RecyclerView.Adapter {
    private Context context;
    private List<Serie> listaDeSeries;
    private NotificadorHaciaFragmentCategoriasDeSeries notificador;


    public GenerosDeSeriesAdapterEnVertical(NotificadorHaciaFragmentCategoriasDeSeries notificador) {
        listaDeSeries = new ArrayList<>();
        this.notificador = notificador;
    }


    public void setListaDeSeries(List<Serie> listaDeSeries) {
        this.listaDeSeries = listaDeSeries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_recycler_view_vertical,parent,false);

        SeriesViewHolder seriesViewHolder = new SeriesViewHolder(viewDeLaCelda);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Serie serie = listaDeSeries.get(position);

        SeriesViewHolder seriesViewHolder = (SeriesViewHolder) holder;

        seriesViewHolder.cargarSerie(serie);
    }

    @Override
    public int getItemCount() {
        return listaDeSeries.size();
    }

    private class SeriesViewHolder extends RecyclerView.ViewHolder{
        private ImageView imagenViewPelicula;
        private TextView textViewTituloDeLaPelicula;


        public SeriesViewHolder(View itemView) {
            super(itemView);
            imagenViewPelicula = itemView.findViewById(R.id.imageViewDeLaPelicula);
            textViewTituloDeLaPelicula = itemView.findViewById(R.id.textViewTituloDeLaPelicula);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notificador.notificar(listaDeSeries.get(getAdapterPosition()),new ListadoDeSeries(listaDeSeries),getAdapterPosition());
                }
            });
        }
        public void cargarSerie(Serie unaSerie){
            textViewTituloDeLaPelicula.setText(unaSerie.getName());

            Picasso.with(context)
                    .load(TMDBHelper.baseURLImagenes + TMDBHelper.IMAGE_SIZE_W154 + unaSerie.getPoster_path())
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_not_loaded)
                    .into(imagenViewPelicula);

        }



    }

    public interface NotificadorHaciaFragmentCategoriasDeSeries {
        public void notificar(Serie unaSerie, ListadoDeSeries listadoDeSeries,Integer posicion);
    }
}
