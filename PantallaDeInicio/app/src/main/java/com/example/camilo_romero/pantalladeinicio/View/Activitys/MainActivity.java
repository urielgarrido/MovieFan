package com.example.camilo_romero.pantalladeinicio.View.Activitys;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.camilo_romero.pantalladeinicio.R;

import com.example.camilo_romero.pantalladeinicio.View.Fragments.FragmentNoticiasDePeliculas;
import com.example.camilo_romero.pantalladeinicio.View.Fragments.ListaDeNoticiasEnHorizontalFragment;


public class MainActivity extends AppCompatActivity implements  FragmentNoticiasDePeliculas.Notificador,ListaDeNoticiasEnHorizontalFragment.Notificador{

    private FragmentNoticiasDePeliculas fragmentNoticiasDePeliculas;
    private BottomNavigationView bottomNavigationView;
    private Intent intent;


    //ON CREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        //ENCONTRANDO IDS

        bottomNavigationView = findViewById(R.id.BottomNavigationViewMainActivity);
        bottomNavigationView.performClick();

        //ESTE ES EL FRAGMENT QUE SE CARGA DENTRO DE LA ACTIVITY Y EL RESPONSABLE MOSTRAR EL VIEWPAGER Y LOS DEMAS FRAGMENTS HORIZONTALES

        fragmentNoticiasDePeliculas = new FragmentNoticiasDePeliculas();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorMainActivityFragment, fragmentNoticiasDePeliculas);
        fragmentTransaction.commit();



        bottomNavigationView.setSelectedItemId(R.id.opcionNoticias);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.opcionpeliculas:
                        intent = new Intent(MainActivity.this, PeliculasActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.opcionSeries:
                        intent = new Intent(MainActivity.this, SeriesActivity.class);
                        startActivity(intent);
                }
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.login:
                startActivity(new Intent(this,LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void notificador(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(url));
        startActivity(intent);

    }
}


