package com.example.business;

import java.util.List;

public class FavoriteItem {
    private String name;
    private List<Integer> imageResources;

    public FavoriteItem(String name, List<Integer> imageResources) {
        this.name = name;
        this.imageResources = imageResources;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getImageResources() {
        return imageResources;
    }
}
