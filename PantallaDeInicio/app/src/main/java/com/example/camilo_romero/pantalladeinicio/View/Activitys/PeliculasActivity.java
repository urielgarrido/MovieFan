package com.example.camilo_romero.pantalladeinicio.View.Activitys;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.camilo_romero.pantalladeinicio.Controler.ControlerPelicula;
import com.example.camilo_romero.pantalladeinicio.DAO.PeliculaDaoUtil;
import com.example.camilo_romero.pantalladeinicio.Model.ListadoDePeliculas;
import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.R;
import com.example.camilo_romero.pantalladeinicio.View.Adapter.EventosDePeliculasAdapterEnHorizontal;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.DescripcionesDePeliculasFragment;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.FragmentPeliculas;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.FragmentPeliculasFavoritas;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.GenerosDePeliculasFragment;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.ListaDePeliculasEnHorizontalFragment;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.ListaDeSeriesEnHorizontalFragment;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;
import com.example.camilo_romero.pantalladeinicio.utils.TMDBHelper;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class PeliculasActivity extends AppCompatActivity implements
        ListaDePeliculasEnHorizontalFragment.NotificadorHorizontalDeInternetHaciaMainActivity,
        GenerosDePeliculasFragment.NotificadorDeGenerosFragmentDePeliculasHaciaMainActivity {

    private ControlerPelicula controlerPelicula;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    private Intent intent;
    private EventosDePeliculasAdapterEnHorizontal adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.BottonNavigationViewPeliculasActivty);
        navigationView = findViewById(R.id.NavigationViewPeliculasActivity);
        drawerLayout = findViewById(R.id.contenedorPeliculasActivityDrawerLayout);


        navigationView.setNavigationItemSelectedListener(new ListenerMenu());

        controlerPelicula = new ControlerPelicula(this);

        FragmentPeliculas fragmentPeliculas = new FragmentPeliculas();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contenedorDeFragmentsPelicula_ActivityPeliculas, fragmentPeliculas);
        fragmentTransaction.commit();

        bottomNavigationView.setSelectedItemId(R.id.opcionpeliculas);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.opcionNoticias:
                        intent = new Intent(PeliculasActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.opcionSeries:
                        intent = new Intent(PeliculasActivity.this, SeriesActivity.class);
                        startActivity(intent);
                        break;

                }
                return false;

            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }


    @Override
    public void recibirPeliculaClickeada(Pelicula unaPelicula, ListadoDePeliculas listadoDePeliculas, Integer position) {
        Intent intent = new Intent(this, DescripcionesDePeliculasActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable(DescripcionesDePeliculasFragment.CLAVE_OBJETO_PELICULA_INTERNET, unaPelicula);
        bundle.putSerializable(DescripcionesDePeliculasActivity.CLAVE_LISTA_PELICULAS_INTERNET, listadoDePeliculas);
        bundle.putInt(DescripcionesDePeliculasActivity.CLAVE_POSITION_PELICULA_INTERNET, position);

        intent.putExtras(bundle);


        startActivity(intent);

    }

    private void cargarCategorias(String genero) {

        if (!(genero.equals("Favoritos"))&&!(genero.equals("Iniciar Sesión/Cerrar Sesión"))) {
          /*
        ESTE METODO ES EL QUE SE ENCARGA DE CARGAR LOS FRAGMENTS QUE USAMOS PARA LAS CATEGORIAS DEL EL NAVIGATION VIEW
        RECIBE LA LISTA Y EL GENERO LO METE DENTRO DE UN BUNDLE Y LO LLEVA HACIA GENEROS DE PELICULAS FRAGMENT DONDE LEE LOS DATOS
        DEL BUNDLE
         */

            GenerosDePeliculasFragment generosDePeliculasFragment = new GenerosDePeliculasFragment();


            Bundle bundle = new Bundle();

            bundle.putString(GenerosDePeliculasFragment.CLAVE_GENERO_INTERNET, genero);

            generosDePeliculasFragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.contenedorDeFragmentsPelicula_ActivityPeliculas, generosDePeliculasFragment).addToBackStack(null);
            fragmentTransaction.commit();
        }
        if (genero.equals("Iniciar Sesión/Cerrar Sesión")){
            FirebaseAuth  mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser!=null){
                FirebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();
                Toast.makeText(this, "Se deslogueó correctamente", Toast.LENGTH_SHORT).show();
                finish();
            }else startActivity(new Intent(this,LoginActivity.class));
        }
    }


    public class ListenerMenu implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FirebaseAuth  mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();

            cargarCategorias(item.getTitle().toString());
            if (item.getItemId() == R.id.favoritos) {
                if(currentUser!=null){
                    FragmentPeliculasFavoritas fragmentPeliculasFavoritas = FragmentPeliculasFavoritas.fabricaDePeliculasFavoritas();

                    FragmentManager fragmentManager = getSupportFragmentManager();

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.contenedorDeFragmentsPelicula_ActivityPeliculas, fragmentPeliculasFavoritas).addToBackStack(null);
                    fragmentTransaction.commit();
                }else Toast.makeText(PeliculasActivity.this, "No estas logueado", Toast.LENGTH_SHORT).show();
            }
            drawerLayout.closeDrawers();
            return false;

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
