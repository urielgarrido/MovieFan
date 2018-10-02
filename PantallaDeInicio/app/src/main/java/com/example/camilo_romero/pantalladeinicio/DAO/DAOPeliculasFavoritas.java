package com.example.camilo_romero.pantalladeinicio.DAO;

import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.utils.Helper;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DAOPeliculasFavoritas {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseAuth user;

    public DAOPeliculasFavoritas(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child(Helper.CLAVE_REFERENCIA).child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }
    public void obtenerListaDeFavoritos(final ResultListener<List<Pelicula>> escuchadorDelControlador){
        reference.addValueEventListener(new ValueEventListener() {

            List<Pelicula> listaDePeliculasFavoritas = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Pelicula unaPelicula = snapshot.getValue(Pelicula.class);

                    listaDePeliculasFavoritas.add(unaPelicula);

                }
                escuchadorDelControlador.finish(listaDePeliculasFavoritas);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
