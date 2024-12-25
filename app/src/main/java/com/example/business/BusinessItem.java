package com.example.business;

import java.io.Serializable;

public class BusinessItem  implements Serializable {
    private String name;
    private int imageResource;
    private float rating;
    private String address;
    private boolean isFavorite;

    public BusinessItem(String name, int imageResource, float rating, String address) {
        this.name = name;
        this.imageResource = imageResource;
        this.rating = rating;
        this.address = address;
        this.isFavorite = false; // Initialize as not favorited
    }


    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public float getRating() {
        return rating;
    }

    public String getAddress() {
        return address;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
