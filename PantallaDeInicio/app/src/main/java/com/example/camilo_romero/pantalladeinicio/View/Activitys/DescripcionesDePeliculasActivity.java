package com.example.camilo_romero.pantalladeinicio.View.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.camilo_romero.pantalladeinicio.Model.Actor;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeActores;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDePeliculas;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.Model.Trailer;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.ViewPagerDescripcionDePeliculasAdapter;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.DescripcionesDeActoresFragment;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.DescripcionesDePeliculasFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DescripcionesDePeliculasActivity extends AppCompatActivity implements DescripcionesDePeliculasFragment.NotificadorDelFloatinActionButton, DescripcionesDePeliculasFragment.PeliculaNotificadorDeActorHaciaMainActivity, DescripcionesDePeliculasFragment.NotificarClickYoutube {

    public static final String CLAVE_LISTA_PELICULAS_INTERNET = "claveListaPeliculasinternet";
    public static final String CLAVE_POSITION_PELICULA_INTERNET = "claveIdPeliculaClickeadainternet";

    private ViewPager viewPager;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripciones_de_peliculas);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        //ENCONTRANDO IDS
        viewPager = findViewById(R.id.viewPager_DescripcionesActivity);




        /*
        ESTO RECIBE EL INTENT QUE VIENE DESDE LAS INTERFACES IMPLEMENTADAS EN EL MAIN ACTIVITY
        ESAS INTERFACES SE DISPARAN CUANDO SE TOCA SOBRE ALGUNO DE LOS DOS FRAGMENTS
        */
        Intent intent = getIntent();

        Bundle unBundle = intent.getExtras();
        ListadoDePeliculas listadoDePeliculas = (ListadoDePeliculas) unBundle.getSerializable(CLAVE_LISTA_PELICULAS_INTERNET);
        Integer position = unBundle.getInt(CLAVE_POSITION_PELICULA_INTERNET);


        /*
        ESTE ES EL VIEW PAGER QUE PERMITE NAVEGAR ENTRE PELICULAS EN LA DESCRIPCION
         */

        ViewPagerDescripcionDePeliculasAdapter viewPagerDescripcionDePeliculasAdapter = new ViewPagerDescripcionDePeliculasAdapter(getSupportFragmentManager(), listadoDePeliculas);
        viewPager.setAdapter(viewPagerDescripcionDePeliculasAdapter);
        viewPager.setCurrentItem(position);


    }

    @Override
    public void notificarClickDeActor(Actor unActor, ListadoDeActores listadoDeActores, Integer position) {
        /*
        ESTE METODO VIENE DE LA INTEFACE DEL FRAGMENT ACTORES Y LE NOTIFICA A LA ACTIVITY QUE UN ACTOR FUE CLICKEADO
         */
        Intent intent = new Intent(this, DescripcionesDeActoresActivity.class);

        Bundle bundle = new Bundle();

        bundle.putInt(DescripcionesDeActoresActivity.CLAVE_POSITION_ACTOR, position);
        bundle.putSerializable(DescripcionesDeActoresActivity.CLAVE_LISTA_ACTORES, listadoDeActores);
        bundle.putSerializable(DescripcionesDeActoresFragment.CLAVE_ACTOR, unActor);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public void clickDelBotonPlayYoutube(Pelicula pelicula) {
        Intent intent = new Intent(DescripcionesDePeliculasActivity.this, YoutubeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(YoutubeActivity.VIDEO_ID, pelicula.getId());
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void clickDelFloatingActionButtonFavoritos(Pelicula unaPelicula) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(!(user == null)){
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("favoritos").child(user.getUid());
            databaseReference.push().setValue(new Pelicula(unaPelicula.getKey(),unaPelicula.getId(),unaPelicula.getVote_average(),unaPelicula.getTitle(),unaPelicula.getPoster_path(),unaPelicula.getOverview(),unaPelicula.getBackdrop_path()));
            Toast.makeText(this, "agregada a favoritos", Toast.LENGTH_SHORT).show();
        }else {
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

    @Override
    public void clickDelFloatingActionButtonCompartir() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
       // share.putExtra(Intent.EXTRA_SUBJECT, "Compartir en Instagram");
        share.putExtra(Intent.EXTRA_TEXT,"Hola!");
        startActivity(Intent.createChooser(share, "Compartir en"));
    }
}

