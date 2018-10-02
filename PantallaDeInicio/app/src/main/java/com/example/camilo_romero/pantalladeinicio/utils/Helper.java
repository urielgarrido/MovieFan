package com.example.camilo_romero.pantalladeinicio.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.camilo_romero.pantalladeinicio.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class Helper {


    public static final String CLAVE_REFERENCIA = "favoritos";


    public static boolean estaUsando4G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }
    public static boolean estaUsandoWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;

    }


    public static void cargarPortada(String url,Context context,ImageView imageView){
        if (estaUsandoWifi(context)){
            Glide.with(context)
                    .load(TMDBHelper.baseURLImagenes + TMDBHelper.IMAGE_SIZE_W780 + url)
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_not_loaded)
                    .into(imageView);
        }else {
            Glide.with(context)
                    .load(TMDBHelper.baseURLImagenes + TMDBHelper.IMAGE_SIZE_W154 + url)
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_not_loaded)
                    .into(imageView);
        }
    }

    public static void cargarPosters(String url, Context context, ImageView imageView){
        if (estaUsandoWifi(context)){
            Glide.with(context)
                    .load(TMDBHelper.baseURLImagenes + TMDBHelper.IMAGE_SIZE_W300 + url)
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_not_loaded)
                    .into(imageView);
        }else {
            Glide.with(context)
                    .load(TMDBHelper.baseURLImagenes + TMDBHelper.IMAGE_SIZE_W500 + url)
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_not_loaded)
                    .into(imageView);
        }
    }

    public static void cargarActores(String url, Context context, ImageView imageView){
        if (estaUsandoWifi(context)){
            Glide.with(context)
                    .load(TMDBHelper.baseURLImagenes + TMDBHelper.IMAGE_SIZE_W154 + url)
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_not_loaded)
                    .into(imageView);

        }else {
            Glide.with(context)
                    .load(TMDBHelper.baseURLImagenes + TMDBHelper.IMAGE_SIZE_W92 + url)
                    .placeholder(R.drawable.image_loading)
                    .error(R.drawable.image_not_loaded)
                    .into(imageView);
        }
    }
}
