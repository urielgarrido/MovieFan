package com.example.camilo_romero.pantalladeinicio.View.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.camilo_romero.pantalladeinicio.R;

public class PantallaDeCargaActivity extends AppCompatActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_de_carga);
        imageView = findViewById(R.id.imagen_splash);
        ViewCompat.animate(imageView).scaleX(2.0f).scaleY(2.0f).setDuration(2000).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new  Intent(PantallaDeCargaActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }

}
