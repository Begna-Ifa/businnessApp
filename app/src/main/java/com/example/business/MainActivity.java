package com.example.business;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private Button buttonFoods, buttonClothing, buttonEntertainment, buttonElectronics;
    private GridView gridView;
    private List<ElectronicsItem> electronicsItems;
    private List<FoodItem> foodItems;
    private List<ClothItem> clothItems;
    private List<EntertainmentItem> entertainmentItems;

    private ElectronicsAdapter electronicsAdapter;
    private FoodAdapter foodAdapter;
    private ClothAdapter clothAdapter;
    private EntertainmentAdapter entertainmentAdapter;

    private List<BusinessItem> favoriteItems; // New favorite items list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView1);
        buttonFoods = findViewById(R.id.button_foods);
        buttonClothing = findViewById(R.id.button_clothing);
        buttonEntertainment = findViewById(R.id.button_entertainment);
        buttonElectronics = findViewById(R.id.button_electronics);
        gridView = findViewById(R.id.image_grid);
        ImageView home = findViewById(R.id.home_button);
        ImageView list = findViewById(R.id.list_button);
        ImageView favorite = findViewById(R.id.favorites_button);
        ImageView profile = findViewById(R.id.profile_button);
        ImageView settings = findViewById(R.id.settings_button);

        // Initialize adapters and data lists
        electronicsItems = getElectronicsItems();
        foodItems = getFoodItems();
        clothItems = getClothItems();
        entertainmentItems = getEntertainmentItems();

        electronicsAdapter = new ElectronicsAdapter(MainActivity.this, electronicsItems);
        foodAdapter = new FoodAdapter(MainActivity.this, foodItems);
        clothAdapter = new ClothAdapter(MainActivity.this, clothItems);
        entertainmentAdapter = new EntertainmentAdapter(MainActivity.this, entertainmentItems);

        // Set default adapter
        gridView.setAdapter(electronicsAdapter);

        favoriteItems = new ArrayList<>(); // Initialize favorite items list

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the items based on the search query
                filterItems(newText);
                return true;
            }
        });

        buttonElectronics.setOnClickListener(v -> {
            gridView.setAdapter(electronicsAdapter);
        });

        buttonFoods.setOnClickListener(v -> {
            gridView.setAdapter(foodAdapter);
        });

        buttonClothing.setOnClickListener(v -> {
            gridView.setAdapter(clothAdapter);
        });

        buttonEntertainment.setOnClickListener(v -> {
            gridView.setAdapter(entertainmentAdapter);
        });

        profile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, user_profile.class);
            startActivity(intent);
        });

        settings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, setting.class);
            startActivity(intent);
        });

        list.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BusinessListActivity.class);
            startActivity(intent);
        });

        home.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // Handle favorite button click
        favorite.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
            startActivity(intent);
        });

        // Handle item click to add to favorites
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            if (gridView.getAdapter() == electronicsAdapter) {
                ElectronicsItem clickedItem = electronicsItems.get(position);
                intent.putExtra("name", clickedItem.getName());
                intent.putExtra("imageResource", clickedItem.getImageResources().get(0));
                intent.putExtra("rating", clickedItem.getRating());
                intent.putExtra("address", "Electronics Address"); // Adjust as per your data
            } else if (gridView.getAdapter() == foodAdapter) {
                FoodItem clickedItem = foodItems.get(position);
                intent.putExtra("name", clickedItem.getName());
                intent.putExtra("imageResource", clickedItem.getImageResources().get(0));
                intent.putExtra("rating", 0.0f); // Adjust as per FoodItem attributes
                intent.putExtra("address", "Food Address"); // Adjust as per your data
            } else if (gridView.getAdapter() == clothAdapter) {
                ClothItem clickedItem = clothItems.get(position);
                intent.putExtra("name", clickedItem.getName());
                intent.putExtra("imageResource", clickedItem.getImageResources().get(0));
                intent.putExtra("rating", 0.0f); // Adjust as per ClothItem attributes
                intent.putExtra("address", "Cloth Address"); // Adjust as per your data
            } else if (gridView.getAdapter() == entertainmentAdapter) {
                EntertainmentItem clickedItem = entertainmentItems.get(position);
                intent.putExtra("name", clickedItem.getName());
                intent.putExtra("imageResource", clickedItem.getImageResources().get(0));
                intent.putExtra("rating", 0.0f); // Adjust as per EntertainmentItem attributes
                intent.putExtra("address", "Entertainment Address"); // Adjust as per your data
            }
            startActivity(intent);
        });

        favorite.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
            intent.putExtra("favoriteItems", new ArrayList<>(favoriteItems)); // Pass favoriteItems list
            startActivity(intent);
        });

    }

    private void addToFavorites(String name, int imageResource, float rating, String address) {
        BusinessItem newItem = new BusinessItem(name, imageResource, rating, address);
        newItem.setFavorite(true);
        favoriteItems.add(newItem);
        // Optionally show a confirmation message
        Toast.makeText(this, name + " added to favorites", Toast.LENGTH_SHORT).show();
    }

    private void filterItems(String query) {
        List<ElectronicsItem> filteredElectronics = new ArrayList<>();
        List<FoodItem> filteredFood = new ArrayList<>();
        List<ClothItem> filteredCloth = new ArrayList<>();
        List<EntertainmentItem> filteredEntertainment = new ArrayList<>();

        // Filter ElectronicsItems
        for (ElectronicsItem item : electronicsItems) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredElectronics.add(item);
            }
        }
        electronicsAdapter = new ElectronicsAdapter(MainActivity.this, filteredElectronics);
        gridView.setAdapter(electronicsAdapter);

        // Filter FoodItems
        for (FoodItem item : foodItems) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredFood.add(item);
            }
        }
        foodAdapter = new FoodAdapter(MainActivity.this, filteredFood);
        gridView.setAdapter(foodAdapter);

        // Filter ClothItems
        for (ClothItem item : clothItems) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredCloth.add(item);
            }
        }
        clothAdapter = new ClothAdapter(MainActivity.this, filteredCloth);
        gridView.setAdapter(clothAdapter);

        // Filter EntertainmentItems
        for (EntertainmentItem item : entertainmentItems) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredEntertainment.add(item);
            }
        }
        entertainmentAdapter = new EntertainmentAdapter(MainActivity.this, filteredEntertainment);
        gridView.setAdapter(entertainmentAdapter);
    }

    private List<ElectronicsItem> getElectronicsItems() {
        List<ElectronicsItem> items = new ArrayList<>();
        items.add(new ElectronicsItem("Laptop", Arrays.asList(R.drawable.elect5, R.drawable.elect5), 4.0f));
        items.add(new ElectronicsItem("Smartphone", Arrays.asList(R.drawable.electronics, R.drawable.smartwatch), 4.5f));
        items.add(new ElectronicsItem("Tablet", Arrays.asList(R.drawable.tablet, R.drawable.tablet), 3.5f));
        items.add(new ElectronicsItem("Smartwatch", Arrays.asList(R.drawable.electro3, R.drawable.electro3), 4.2f));
        items.add(new ElectronicsItem("Camera", Arrays.asList(R.drawable.elect2, R.drawable.camera), 3.8f));
        items.add(new ElectronicsItem("Headphones", Arrays.asList(R.drawable.earphone, R.drawable.earphone), 4.7f));
        items.add(new ElectronicsItem("Printer", Arrays.asList(R.drawable.printer, R.drawable.printer), 4.1f));
        items.add(new ElectronicsItem("Monitor", Arrays.asList(R.drawable.electronics, R.drawable.monitor), 4.3f));
        return items;
    }

    private List<FoodItem> getFoodItems() {
        List<FoodItem> items = new ArrayList<>();
        items.add(new FoodItem("Pizza", Arrays.asList(R.drawable.food, R.drawable.patas)));
        items.add(new FoodItem("Burger", Arrays.asList(R.drawable.burger, R.drawable.burger)));
        items.add(new FoodItem("Pasta", Arrays.asList(R.drawable.patas, R.drawable.patas)));

        items.add(new FoodItem("Salad", Arrays.asList(R.drawable.salad, R.drawable.salad)));
        items.add(new FoodItem("Sandwich", Arrays.asList(R.drawable.sandwich, R.drawable.sandwich)));
        items.add(new FoodItem("Ice Cream", Arrays.asList(R.drawable.icecream, R.drawable.icecream)));
        items.add(new FoodItem("Cake", Arrays.asList(R.drawable.cake, R.drawable.cake)));
        return items;
    }

    private List<ClothItem> getClothItems() {
        List<ClothItem> items = new ArrayList<>();
        items.add(new ClothItem("T-Shirt", Arrays.asList(R.drawable.cloths55, R.drawable.cloths55)));
        items.add(new ClothItem("Jeans", Arrays.asList(R.drawable.jeans, R.drawable.jeans)));
        items.add(new ClothItem("Jacket", Arrays.asList(R.drawable.jacket, R.drawable.jeans)));
        items.add(new ClothItem("Shoes", Arrays.asList(R.drawable.shoe, R.drawable.shoe)));
        items.add(new ClothItem("Hat", Arrays.asList(R.drawable.hat, R.drawable.hat)));
        items.add(new ClothItem("Dress", Arrays.asList(R.drawable.cloths55, R.drawable.jeans)));
        items.add(new ClothItem("Socks", Arrays.asList(R.drawable.socks, R.drawable.socks)));
        items.add(new ClothItem("Skirt", Arrays.asList(R.drawable.skirt, R.drawable.skirt)));
        return items;
    }

    private List<EntertainmentItem> getEntertainmentItems() {
        List<EntertainmentItem> items = new ArrayList<>();
        items.add(new EntertainmentItem("Movie", Arrays.asList(R.drawable.movie, R.drawable.movie)));
        items.add(new EntertainmentItem("Concert", Arrays.asList(R.drawable.conceret, R.drawable.conceret)));
        items.add(new EntertainmentItem("Theater", Arrays.asList(R.drawable.theater, R.drawable.theater)));
        items.add(new EntertainmentItem("Sports", Arrays.asList(R.drawable.sport, R.drawable.sport)));
        items.add(new EntertainmentItem("Exhibition", Arrays.asList(R.drawable.enterta3, R.drawable.enterta3)));
        items.add(new EntertainmentItem("Music Festival", Arrays.asList(R.drawable.enterta3, R.drawable.enterta3)));
        items.add(new EntertainmentItem("Comedy Show", Arrays.asList(R.drawable.movie, R.drawable.movie)));
        items.add(new EntertainmentItem("Art Gallery", Arrays.asList(R.drawable.entertai2, R.drawable.entertai2)));
        return items;
    }
}
