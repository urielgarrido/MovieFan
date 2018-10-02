package com.example.camilo_romero.pantalladeinicio.View.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.camilo_romero.pantalladeinicio.Model.Actor;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeActores;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeSeries;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.ViewPagerDescripcionDePeliculasAdapter;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.ViewPagerDescripcionesDeSeriesAdapter;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.DescripcionesDeActoresFragment;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.DescripcionesDePeliculasFragment;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.DescripcionesDeSeriesFragment;

public class DescripcionesDeSeriesActivity extends AppCompatActivity implements DescripcionesDeSeriesFragment.NotificarClickDelFloatingActionButton,DescripcionesDeSeriesFragment.SerieNotificadorDeActorClickeadaHaciaMainActivity {
    public static final String CLAVE_LISTA_SERIES = "clave lista de series";
    public static final String CLAVE_POSITION_SERIES = "clave posicion ";

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        TIENE SU PROPIO LAYOUT CREADO PERO POR CUESTIONES DE TIEMPO VOY A USAR EL QUE YA ESTA CREADO
         */
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
        ListadoDeSeries listadoDeSeries = (ListadoDeSeries) unBundle.getSerializable(CLAVE_LISTA_SERIES);
        Integer position = unBundle.getInt(CLAVE_POSITION_SERIES);


        //cargarFragmentDetalle(unBundle);

        /*
        ESTE ES EL VIEW PAGER QUE PERMITE NAVEGAR ENTRE PELICULAS EN LA DESCRIPCION
         */
        ViewPagerDescripcionesDeSeriesAdapter viewPagerDescripcionesDeSeriesAdapter = new ViewPagerDescripcionesDeSeriesAdapter(getSupportFragmentManager(),listadoDeSeries);
        viewPager.setAdapter(viewPagerDescripcionesDeSeriesAdapter);
        viewPager.setCurrentItem(position);

    }

    private void cargarFragmentDetalle(Bundle unBundle) {
        /*
        ESTE METODO SE ENCARGA DE RECIBIR UN BUNDLE Y CREAR UN FRAGMENT PARA CARGAR LA DESCRIPCION
         */
        DescripcionesDeSeriesFragment descripcionesDeSeriesFragment = new DescripcionesDeSeriesFragment();

        descripcionesDeSeriesFragment.setArguments(unBundle);

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.contenedorDescripcionesDePeliculasFragment, descripcionesDeSeriesFragment);

        fragmentTransaction.commit();
    }

    @Override
    public void notificarClickDeActor(Actor unActor, ListadoDeActores listadoDeActores, Integer position) {
        /*
        ESTE METODO VIENE DE LA INTEFACE DEL FRAGMENT ACTORES Y LE NOTIFICA A LA ACTIVITY QUE UN ACTOR FUE CLICKEADO
         */
        Intent intent = new Intent(this,DescripcionesDeActoresActivity.class);

        Bundle bundle = new Bundle();

        bundle.putInt(DescripcionesDeActoresActivity.CLAVE_POSITION_ACTOR,position);
        bundle.putSerializable(DescripcionesDeActoresActivity.CLAVE_LISTA_ACTORES,listadoDeActores);
        bundle.putSerializable(DescripcionesDeActoresFragment.CLAVE_ACTOR,unActor);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public void clickFABFavoritos() {
        Toast.makeText(this, "Tocó favoritos", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void clickFABCompartir() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
       // share.putExtra(Intent.EXTRA_SUBJECT, "Compartir en Instagram");
        share.putExtra(Intent.EXTRA_TEXT, "Hola como estan");
        startActivity(Intent.createChooser(share, "Compartir en"));
    }
}

