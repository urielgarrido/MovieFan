package com.example.camilo_romero.pantalladeinicio.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.pantalladeinicio.Model.Noticia;
import com.example.camilo_romero.pantalladeinicio.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NoticiasAdapter extends RecyclerView.Adapter {
    private List<Noticia> listaDeNoticias;
    private Context context;
    private Notificador notificador;



    public void setListaDeNoticias(List<Noticia> listaDeNoticias) {
        this.listaDeNoticias = listaDeNoticias;
        notifyDataSetChanged();
    }

    public NoticiasAdapter(Notificador notificador) {
        this.notificador = notificador;
        listaDeNoticias = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_noticias,parent,false);
        NoticiasDePeliculasViewHolder noticiasDePeliculasViewHolder = new NoticiasDePeliculasViewHolder(viewDeLaCelda);


        return noticiasDePeliculasViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Noticia unaNoticia = listaDeNoticias.get(position);
        NoticiasDePeliculasViewHolder noticiasDePeliculasViewHolder = (NoticiasDePeliculasViewHolder) holder;
        noticiasDePeliculasViewHolder.cargarNoticiaDePelicula(unaNoticia);

    }

    @Override
    public int getItemCount() {
        return listaDeNoticias.size();
    }
    private class NoticiasDePeliculasViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTituloDeLaNoticia;
        private ImageView imageViewImagenDeLaNoticia;
        private TextView textViewDescripcionDeLaNoticia;

        public NoticiasDePeliculasViewHolder(View itemView) {
            super(itemView);
            textViewTituloDeLaNoticia = itemView.findViewById(R.id.textViewTituloDeLaNoticiaCeldaNoticias);
            imageViewImagenDeLaNoticia = itemView.findViewById(R.id.imageViewDeLaNoticiaCeldaNoticias);
            textViewDescripcionDeLaNoticia = itemView.findViewById(R.id.texViewDescripcionDeLaNoticiaCeldaNoticias);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notificador.notificar(listaDeNoticias.get(getAdapterPosition()).getUrl());
                }
            });

        }
        public void cargarNoticiaDePelicula(Noticia unaNoticiaDeUnaPelicula){
            textViewTituloDeLaNoticia.setText(unaNoticiaDeUnaPelicula.getTitle());

            Picasso.with(context)
                    .load(unaNoticiaDeUnaPelicula.getUrlToImage())
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_not_loaded)
                    .into(imageViewImagenDeLaNoticia);

            textViewDescripcionDeLaNoticia.setText(unaNoticiaDeUnaPelicula.getDescription());
        }
    }
    public interface Notificador {
        public void notificar(String link);
    }
}
