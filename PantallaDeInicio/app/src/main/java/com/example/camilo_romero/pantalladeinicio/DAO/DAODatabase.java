package com.example.camilo_romero.pantalladeinicio.DAO;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.utils.ServiceROOM_Peliculas;

@Database(entities = {Pelicula.class},version = 1)
public abstract class DAODatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "database-name";
    private static DAODatabase INSTANCE;

    public abstract ServiceROOM_Peliculas serviceROOMPeliculas();

    public static DAODatabase getInstance(Context context){
        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    DAODatabase.class,DATABASE_NAME).build();
        }
     return INSTANCE;
    }

}
