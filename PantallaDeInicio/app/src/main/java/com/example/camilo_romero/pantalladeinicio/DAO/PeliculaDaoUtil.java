package com.example.camilo_romero.pantalladeinicio.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import com.example.camilo_romero.pantalladeinicio.Model.Pelicula;
import com.example.camilo_romero.pantalladeinicio.utils.ResultListener;

import java.util.List;

public class PeliculaDaoUtil {
    private DAODatabase database;

    public PeliculaDaoUtil(Context context) {
        this.database = DAODatabase.getInstance(context);
    }

    public void deletePelicula(Pelicula pelicula, ResultListener<Boolean> resultListener) {
        DeletePeliculaAsynk deletePersonAsync = new DeletePeliculaAsynk(pelicula, resultListener);
        deletePersonAsync.execute();
    }

    public void insertPelicula(Pelicula pelicula, ResultListener<Long> resultListener) {
        InsertPeliculaAsync insertPeliculaAsync = new InsertPeliculaAsync(pelicula,resultListener);
        insertPeliculaAsync.execute();
    }

    public void insertPelicula(List<Pelicula> pelicula, ResultListener<Long[]> resultListener) {
        InsertPeliculasAsync insertPeliculaAsync = new InsertPeliculasAsync(pelicula,resultListener);
        insertPeliculaAsync.execute();
    }

    public void getTodasLasPeliculas(ResultListener<List<Pelicula>> resultListener) {
        LoadPeliculaAsync loadPeliculaAsync = new LoadPeliculaAsync(resultListener);
        loadPeliculaAsync.execute();
    }

    public void updatePelicula(Pelicula pelicula, ResultListener<Boolean> resultListener) {
        UpdatePeliculaAsync updatePeliculaAsync = new UpdatePeliculaAsync(pelicula,resultListener);
        updatePeliculaAsync.execute();
    }

    private class DeletePeliculaAsynk extends AsyncTask<Void, Void, Integer> {
        private ResultListener<Boolean> resultListener;
        private Pelicula pelicula;

        public DeletePeliculaAsynk(Pelicula pelicula, ResultListener<Boolean> resultListener) {
            this.resultListener = resultListener;
            this.pelicula = pelicula;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return database.serviceROOMPeliculas().deletePelicula(pelicula);
        }

        @Override
        protected void onPostExecute(Integer deletedRows) {
            if (deletedRows >= 0) {
                resultListener.finish(true);
            } else {
                resultListener.finish(false);
            }
        }
    }

    private class InsertPeliculaAsync extends AsyncTask<Void, Void, Long> {

        private ResultListener<Long> resultListener;
        private Pelicula pelicula;

        public InsertPeliculaAsync(Pelicula pelicula, ResultListener<Long> resultListener) {
            this.resultListener = resultListener;
            this.pelicula = pelicula;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            try {
                return database.serviceROOMPeliculas().insertPelicula(pelicula);
            } catch (SQLiteConstraintException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Long insertId) {
            resultListener.finish(insertId);
        }
    }

    private class InsertPeliculasAsync extends AsyncTask<Void, Void, Long[]> {

        private ResultListener<Long[]> resultListener;
        private List<Pelicula> pelicula;

        public InsertPeliculasAsync(List<Pelicula> pelicula, ResultListener<Long[]> resultListener) {
            this.resultListener = resultListener;
            this.pelicula = pelicula;
        }

        @Override
        protected Long[] doInBackground(Void... voids) {
            try {
                return database.serviceROOMPeliculas().insertListPelicula(pelicula);
            } catch (SQLiteConstraintException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Long[] insertId) {
            resultListener.finish(insertId);
        }
    }

    private class LoadPeliculaAsync extends AsyncTask<Void, Void, List<Pelicula>> {

        private ResultListener<List<Pelicula>> resultListener;

        public LoadPeliculaAsync(ResultListener<List<Pelicula>> resultListener) {
            this.resultListener = resultListener;
        }

        @Override
        protected List<Pelicula> doInBackground(Void... voids) {
            return database.serviceROOMPeliculas().getPeliculaDeROOM();
        }

        @Override
        protected void onPostExecute(List<Pelicula> personList) {
            resultListener.finish(personList);
        }
    }
    private class UpdatePeliculaAsync extends AsyncTask<Void, Void, Integer> {

        private ResultListener<Boolean> resultListener;
        private Pelicula pelicula;

        public UpdatePeliculaAsync(Pelicula pelicula, ResultListener<Boolean> resultListener) {
            this.resultListener = resultListener;
            this.pelicula = pelicula;
        }

        @Override
        protected Integer doInBackground(Void... voids) {

            return database.serviceROOMPeliculas().updatePelicula(pelicula);
        }


        @Override
        protected void onPostExecute(Integer updatedRows) {
            if (updatedRows > 0) {
                resultListener.finish(true);
            } else {
                resultListener.finish(false);
            }
        }
    }
}
