package com.example.business;

import java.util.List;

public class ClothItem {
    private String name;
    private List<Integer> imageResources;
    private float rating;


    public ClothItem(String name, List<Integer> imageResources) {
        this.name = name;
        this.imageResources = imageResources;
        this.rating = rating;

    }

    public String getName() {
        return name;
    }
    public float getRating() {
        return rating;
    }


    public List<Integer> getImageResources() {
        return imageResources;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }

}
