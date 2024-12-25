package com.example.business;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BusinessAdapter extends ArrayAdapter<BusinessItem> {

    private Context context;
    private int resource;
    private List<BusinessItem> items;
    private OnFavoriteClickListener favoriteClickListener;
    private OnRatingBarChangeListener ratingBarChangeListener;
    private OnItemClickListener itemClickListener;

    public BusinessAdapter(Context context, int resource, List<BusinessItem> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        BusinessItem currentItem = items.get(position);

        // Initialize views from my_business.xml
        ImageView imageView = listItemView.findViewById(R.id.imageView4);
        ImageView favoriteButton = listItemView.findViewById(R.id.button_favorite);
        RatingBar ratingBar = listItemView.findViewById(R.id.ratingBar_image);
        TextView textViewName = listItemView.findViewById(R.id.textView_image_name);

        // Set data to views
        imageView.setImageResource(currentItem.getImageResource());
        favoriteButton.setImageResource(currentItem.isFavorite() ? R.drawable.favorite_filled : R.drawable.favorite);
        favoriteButton.setOnClickListener(v -> {
            // Toggle favorite status
            boolean isFavorite = !currentItem.isFavorite();
            currentItem.setFavorite(isFavorite);
            // Update the favorite button icon
            favoriteButton.setImageResource(isFavorite ? R.drawable.favorite_filled : R.drawable.favorite);
            if (favoriteClickListener != null) {
                favoriteClickListener.onFavoriteClick(position, isFavorite);
            }
        });



        ratingBar.setRating(currentItem.getRating());
        ratingBar.setOnRatingBarChangeListener((bar, rating, fromUser) -> {
            // Handle rating bar change
            if (ratingBarChangeListener != null && fromUser) {
                ratingBarChangeListener.onRatingChanged(position, rating);
            }
        });

        textViewName.setText(currentItem.getName());

        return listItemView;
    }

    public void setOnFavoriteClickListener(OnFavoriteClickListener listener) {
        this.favoriteClickListener = listener;
    }

    public void setOnRatingBarChangeListener(OnRatingBarChangeListener listener) {
        this.ratingBarChangeListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public interface OnFavoriteClickListener {
        void onFavoriteClick(int position, boolean isFavorite);
    }

    public interface OnRatingBarChangeListener {
        void onRatingChanged(int position, float rating);
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
