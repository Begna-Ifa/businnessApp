package com.example.business;

public class ItemDetails {
    private String name;
    private String address;
    private String description;
    private String contact;
    private int imageResId;

    public ItemDetails(String name, String address, String description, String contact, int imageResId) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.contact = contact;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getContact() {
        return contact;
    }

    public int getImageResId() {
        return imageResId;
    }
}
