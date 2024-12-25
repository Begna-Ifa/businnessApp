package com.example.business;

import java.util.List;

public class ElectronicsItem {
    private String name;
    private List<Integer> imageResources;
    private float rating;

    public ElectronicsItem(String name, List<Integer> imageResources, float rating) {
        this.name = name;
        this.imageResources = imageResources;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getImageResources() {
        return imageResources;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
