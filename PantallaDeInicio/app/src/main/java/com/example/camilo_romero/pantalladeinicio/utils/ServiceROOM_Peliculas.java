package com.example.camilo_romero.pantalladeinicio.utils;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;

import java.util.List;

@Dao
public interface ServiceROOM_Peliculas {

    @Query("SELECT * FROM Pelicula")
    List<Pelicula> getPeliculaDeROOM();

    @Query("SELECT * FROM Pelicula WHERE title=:title")
    List<Pelicula> getPeliculaPorTituloDeROOM(String title);

    @Insert
    Long insertPelicula (Pelicula pelicula);

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    Long[] insertListPelicula(List<Pelicula> peliculaList);

    @Delete
    int deletePelicula(Pelicula pelicula);

    @Update
    int updatePelicula(Pelicula pelicula);
}
