package com.example.business;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView textViewName;

    public FavoriteItemViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView4);
        textViewName = itemView.findViewById(R.id.textView_image_name);
    }

    public void bindData(BusinessItem item) {
        imageView.setImageResource(item.getImageResource());
        textViewName.setText(item.getName());
    }
}
