package com.example.camilo_romero.pantalladeinicio.View.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.camilo_romero.pantalladeinicio.Model.Cartelera;
import com.example.camilo_romero.pantalladeinicio.R;

import java.util.ArrayList;
import java.util.List;

public class CarteleraAdapter extends RecyclerView.Adapter {
    private List<Cartelera> listaDeCarteleras;
    private Context context;

    public void setListaDeCarteleras(List<Cartelera> listaDeCarteleras) {
        this.listaDeCarteleras = listaDeCarteleras;
        notifyDataSetChanged();
    }

    public CarteleraAdapter() {
        listaDeCarteleras = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View viewDeLaCelda = layoutInflater.inflate(R.layout.celda_cartelera,parent,false);

        CarteleraViewHolder carteleraViewHolder = new CarteleraViewHolder(viewDeLaCelda);

        return carteleraViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Cartelera cartelera = listaDeCarteleras.get(position);

        CarteleraViewHolder carteleraViewHolder = (CarteleraViewHolder) holder;

        carteleraViewHolder.cargarCartelera(cartelera);

    }

    @Override
    public int getItemCount() {
        return listaDeCarteleras.size();
    }

    private class CarteleraViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNombreDelCine;
        private TextView textViewHorariosDeReproduccion;
        public CarteleraViewHolder(View itemView) {
            super(itemView);
            textViewNombreDelCine = itemView.findViewById(R.id.textViewNombreDeLCine_celdacartelera);
            textViewHorariosDeReproduccion = itemView.findViewById(R.id.textViewHorariosDeReproduccion_celdacartelera);

        }

        public void cargarCartelera(Cartelera cartelera){
            textViewNombreDelCine.setText(cartelera.getCine());
            textViewHorariosDeReproduccion.setText(cartelera.getListaDeHorarios());

        }
    }
}
