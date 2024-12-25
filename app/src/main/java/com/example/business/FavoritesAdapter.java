package com.example.business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoriteItemViewHolder> {

    private Context context;
    private List<BusinessItem> favoriteItems;

    public FavoritesAdapter(Context context, List<BusinessItem> favoriteItems) {
        this.context = context;
        this.favoriteItems = favoriteItems;
    }

    @NonNull
    @Override
    public FavoriteItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_business, parent, false);
        return new FavoriteItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteItemViewHolder holder, int position) {
        BusinessItem item = favoriteItems.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return favoriteItems.size();
    }
}
