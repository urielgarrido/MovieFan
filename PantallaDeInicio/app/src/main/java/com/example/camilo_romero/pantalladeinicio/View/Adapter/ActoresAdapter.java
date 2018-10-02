package com.example.camilo_romero.pantalladeinicio.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.camilo_romero.pantalladeinicio.Model.Actor;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeActores;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.utils.Helper;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ActoresAdapter extends RecyclerView.Adapter {
    private List<Actor> listaDeActores;
    Context context;
    private NotificadorDeClickDeActorHaciaDescripcionesDeSeries notificador;

    public void setListaDeActores(List<Actor> listaDeActores) {
        this.listaDeActores = listaDeActores;
        notifyDataSetChanged();
    }

    public ActoresAdapter(NotificadorDeClickDeActorHaciaDescripcionesDeSeries notificador) {
        listaDeActores = new ArrayList<>();
        this.context = context;
        this.notificador = notificador;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_actor, parent, false);

        ActorViewHolder actorViewHolder = new ActorViewHolder(viewDeLaCelda);

        return actorViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Actor actor = listaDeActores.get(position);

        ActorViewHolder actorViewHolder = (ActorViewHolder) holder;

        actorViewHolder.cargarActor(actor);


    }

    @Override
    public int getItemCount() {
        return listaDeActores.size();
    }

    private class ActorViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombreDelActor;
        private ImageView imageViewPosterDelActor;

        public ActorViewHolder(View itemView) {
            super(itemView);
            textViewNombreDelActor = itemView.findViewById(R.id.textViewNombreDelActor);
            imageViewPosterDelActor = itemView.findViewById(R.id.imageViewPosterDelActor);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notificador.notificarClick(listaDeActores.get(getAdapterPosition()), new ListadoDeActores(listaDeActores), getAdapterPosition());
                }
            });
        }

        public void cargarActor(Actor actor) {
            textViewNombreDelActor.setText(actor.getName());

            Helper.cargarActores(actor.getProfile_path(), context, imageViewPosterDelActor);
        }
    }

        public interface NotificadorDeClickDeActorHaciaDescripcionesDeSeries {
            public void notificarClick(Actor unActor, ListadoDeActores listadoDeActores, Integer position);
        }
    }




