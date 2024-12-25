package com.example.business;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;
    private List<BusinessItem> favoriteItems;
    private TextView emptyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_list);

        recyclerView = findViewById(R.id.recyclerView_ffavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        emptyMessage = findViewById(R.id.empty_message);
        favoriteItems = new ArrayList<>();

        // Populate favorite items from the main business items list
        for (BusinessItem item : BusinessListActivity.businessItems) {
            if (item.isFavorite()) {
                favoriteItems.add(item);
            }
        }

        // Initialize adapter with favorite items
        adapter = new FavoritesAdapter(this, favoriteItems);
        recyclerView.setAdapter(adapter);

        // Show empty message if the list is empty
        if (favoriteItems.isEmpty()) {
            emptyMessage.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyMessage.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
