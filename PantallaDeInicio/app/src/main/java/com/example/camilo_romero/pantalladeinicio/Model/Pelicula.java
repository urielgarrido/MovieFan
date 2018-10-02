package com.example.camilo_romero.pantalladeinicio.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Pelicula implements Serializable {
    private String key;
    @PrimaryKey (autoGenerate = true)
    private Integer id;
    private Double vote_average;
    @ColumnInfo
    private String title;
    private String poster_path;
    private String overview;
    private String backdrop_path;



    public Pelicula(String key, Integer id, Double vote_average, String title, String poster_path, String overview, String backdrop_path) {
        this.key = key;
        this.id = id;
        this.vote_average = vote_average;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
    }

    public Pelicula(){

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public Integer getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "vote_average='" + vote_average + '\'' +
                "title='" + title + '\'' +
                ",poster_path='" + poster_path + '\'' +
                ", overview=" + overview +
                '}';
    }
}
