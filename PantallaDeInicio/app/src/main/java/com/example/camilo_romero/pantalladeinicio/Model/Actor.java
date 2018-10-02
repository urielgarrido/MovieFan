package com.example.camilo_romero.pantalladeinicio.Model;

import java.io.Serializable;

public class Actor implements Serializable {
    private String character;
    private String name;
    private String profile_path;
    private String birthday;
    private String place_of_birth;
    private Integer id;

    public String getCharacter() {
        return character;
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public Integer getId() {
        return id;
    }

    public String getBirthday() { return birthday; }

    public String getPlace_of_birth() { return place_of_birth; }
}
