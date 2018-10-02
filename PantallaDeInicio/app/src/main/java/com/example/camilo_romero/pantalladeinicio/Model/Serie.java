package com.example.camilo_romero.pantalladeinicio.Model;

import java.io.Serializable;

public class Serie implements Serializable {
    private String poster_path;
    private Double popularity;
    private Integer id;
    private String backdrop_path;
    private Double vote_average;
    private String overview;
    private String name;



    public String getPoster_path() {
        return poster_path;
    }

    public Double getPopularity() {
        return popularity;
    }

    public Integer getId() {
        return id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public String getName() {
        return name;
    }
}
