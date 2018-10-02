package com.example.camilo_romero.pantalladeinicio.View.Activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.camilo_romero.pantalladeinicio.Model.ListadoDeSeries;
import com.example.camilo_romero.pantalladeinicio.Model.Serie;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.DescripcionesDeSeriesFragment;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.FragmentsSeries;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.GenerosDePeliculasFragment;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.GenerosDeSeriesFragment;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.ListaDeSeriesEnHorizontalFragment;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.GenerosDeSeriesAdapterEnVertical;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SeriesActivity extends AppCompatActivity implements
        ListaDeSeriesEnHorizontalFragment.NotificadorHorizontalDeSeriesHaciaSeriesActivity,
        GenerosDeSeriesFragment.NotificadorHaciaSeriesActivity {
    private BottomNavigationView bottomNavigationView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);


        bottomNavigationView = findViewById(R.id.BottomNavigationView_SeriesActivity);
        navigationView = findViewById(R.id.NavigationViewSeriesActivity);
        drawerLayout = findViewById(R.id.contenedorDeSeriesActivity);
        bottomNavigationView.setSelectedItemId(R.id.opcionSeries);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                cargarCategorias(item.getTitle().toString());
                drawerLayout.closeDrawers();
                return false;
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.opcionNoticias:
                        intent = new Intent(SeriesActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.opcionpeliculas:
                        intent = new Intent(SeriesActivity.this, PeliculasActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });


        FragmentsSeries fragmentsSeries = new FragmentsSeries();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedor_activityseries, fragmentsSeries);
        fragmentTransaction.commit();


        //CREA EL FRAGMENT QUE CONTIENE EL RECYCLER POPULARES
        ListaDeSeriesEnHorizontalFragment listaDeSeriesPopularesHorizontal = ListaDeSeriesEnHorizontalFragment.creadorDeFragmentsDeSeriesEnHorizontal("Populares");

        cargarFragmentDentroDelLinearLayoutDelFragmentSeries(listaDeSeriesPopularesHorizontal);

        //CREA EL FRAGMENT QUE CONTIENE EL RECYCLER EN TRANSMISION
        ListaDeSeriesEnHorizontalFragment listaDeSeriesEnTransmision = ListaDeSeriesEnHorizontalFragment.creadorDeFragmentsDeSeriesEnHorizontal("En Transmision");

        cargarFragmentDentroDelLinearLayoutDelFragmentSeries(listaDeSeriesEnTransmision);

        //CREA EL FRAGMENTE QUE CONTIENE EL RECYCLER MEJOR VALORADAS
        ListaDeSeriesEnHorizontalFragment listaDeSeriesMejorValoradas = ListaDeSeriesEnHorizontalFragment.creadorDeFragmentsDeSeriesEnHorizontal("Mejor Valoradas");

        cargarFragmentDentroDelLinearLayoutDelFragmentSeries(listaDeSeriesMejorValoradas);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

    }

    private void cargarCategorias(String genero) {

        if (!(genero.equals("Favoritos"))&&!(genero.equals("Iniciar Sesión/Cerrar Sesión"))) {
          /*
        ESTE METODO ES EL QUE SE ENCARGA DE CARGAR LOS FRAGMENTS QUE USAMOS PARA LAS CATEGORIAS DEL EL NAVIGATION VIEW
        RECIBE LA LISTA Y EL GENERO LO METE DENTRO DE UN BUNDLE Y LO LLEVA HACIA GENEROS DE PELICULAS FRAGMENT DONDE LEE LOS DATOS
        DEL BUNDLE
         */

            GenerosDeSeriesFragment generosDeSeriesFragment = new GenerosDeSeriesFragment();


            Bundle bundle = new Bundle();

            bundle.putString(GenerosDeSeriesFragment.CLAVE_CATEGORIA, genero);

            generosDeSeriesFragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();


            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.contenedor_activityseries, generosDeSeriesFragment).addToBackStack(null);
            fragmentTransaction.commit();
        }
        if (genero.equals("Iniciar Sesión/Cerrar Sesión")){
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser!=null){
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                Toast.makeText(this, "Se deslogueó correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }else startActivity(new Intent(this,LoginActivity.class));
        }
    }


    @Override
    public void recibirSerieClickeadaYLista(Serie unaSerie, ListadoDeSeries listadoDeSeries, Integer position) {
        Intent intent = new Intent(this, DescripcionesDeSeriesActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(DescripcionesDeSeriesFragment.CLAVE_OBJETO_SERIE, unaSerie);
        bundle.putSerializable(DescripcionesDeSeriesActivity.CLAVE_LISTA_SERIES, listadoDeSeries);
        bundle.putInt(DescripcionesDeSeriesActivity.CLAVE_POSITION_SERIES, position);

        intent.putExtras(bundle);

        startActivity(intent);
    }


    public void cargarFragmentDentroDelLinearLayoutDelFragmentSeries(ListaDeSeriesEnHorizontalFragment listaDeSeriesEnHorizontalFragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.LinearLayoutContenedorDeFragments_fragmentseries, listaDeSeriesEnHorizontalFragment);
        fragmentTransaction.commit();

    }


}
