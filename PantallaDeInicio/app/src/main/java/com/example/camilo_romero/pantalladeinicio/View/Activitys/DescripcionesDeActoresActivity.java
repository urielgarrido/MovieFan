package com.example.camilo_romero.pantalladeinicio.View.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeActores;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.ViewPagerDescripcionesDeActores;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.DescripcionesDeActoresFragment;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.ListaDeNoticiasEnHorizontalFragment;

public class DescripcionesDeActoresActivity extends AppCompatActivity {

    public static final String CLAVE_LISTA_ACTORES = "clave lista de actores";
    public static final String CLAVE_POSITION_ACTOR = "clave posicion de actor";

    private FloatingActionButton floatingActionButton;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripciones_de_actores);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        //ENCONTRANDO IDS
        viewPager = findViewById(R.id.viewPagerDescripcionesDeActores_activitydescripcionesdeactores);

        /*
        ESTO RECIBE EL INTENT QUE VIENE DESDE LAS INTERFACES IMPLEMENTADAS EN EL MAIN ACTIVITY
        ESAS INTERFACES SE DISPARAN CUANDO SE TOCA SOBRE ALGUNO DE LOS DOS FRAGMENTS
        */
        Intent intent = getIntent();

        Bundle unBundle = intent.getExtras();
        ListadoDeActores listadoDeActores = (ListadoDeActores) unBundle.getSerializable(CLAVE_LISTA_ACTORES);
        Integer position = unBundle.getInt(CLAVE_POSITION_ACTOR);



        /*
        ESTE ES EL VIEW PAGER QUE PERMITE NAVEGAR ENTRE ACTORES EN LA DESCRIPCION
         */

        ViewPagerDescripcionesDeActores viewPagerDescripcionesDeActores = new ViewPagerDescripcionesDeActores(getSupportFragmentManager(),listadoDeActores);
        viewPager.setAdapter(viewPagerDescripcionesDeActores);
        viewPager.setCurrentItem(position);
        /*
        FRAGMENT QUE CONTIENE EL RECYCLER VIEW DE NOTICIAS
         */

        ListaDeNoticiasEnHorizontalFragment listaDeNoticiasEnHorizontalFragment =  ListaDeNoticiasEnHorizontalFragment.fabricaDeFragment(listadoDeActores.getCast().get(position).getName());

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.linearLayoutContenedorDeFragmentsNoticias_fragmentdescripcionesdeactores, listaDeNoticiasEnHorizontalFragment);

        fragmentTransaction.commit();







    }

    



}
