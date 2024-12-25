package com.example.business;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;

import java.util.List;

public class FoodAdapter extends BaseAdapter {

    private Context mContext;
    private List<FoodItem> mFoodList;
    private boolean isFavorite = false;

    public FoodAdapter(Context context, List<FoodItem> foodList) {
        mContext = context;
        mFoodList = foodList;
    }

    @Override
    public int getCount() {
        return mFoodList.size();
    }

    @Override
    public Object getItem(int position) {
        return mFoodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.my_business, parent, false);
            holder = new ViewHolder();
            holder.imageViewItem = convertView.findViewById(R.id.imageView4);
            holder.textViewItemName = convertView.findViewById(R.id.textView_image_name);
            holder.ratingBarItem = convertView.findViewById(R.id.ratingBar_image);
            holder.imageViewFavorite = convertView.findViewById(R.id.button_favorite);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        FoodItem currentItem = (FoodItem) getItem(position);

        // Assuming there's only one image to display for simplicity
        // You might want to implement a view pager or carousel for multiple images
        if (!currentItem.getImageResources().isEmpty()) {
            holder.imageViewItem.setImageResource(currentItem.getImageResources().get(0));
        }

        holder.textViewItemName.setText(currentItem.getName());
        holder.ratingBarItem.setRating(currentItem.getRating());

        holder.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFavorite = !isFavorite;

                if (isFavorite) {
                    holder.imageViewFavorite.setImageResource(R.drawable.favorite_filled);
                } else {
                    holder.imageViewFavorite.setImageResource(R.drawable.favorite);
                }

                showNotification("Favorite Clicked", "Item " + (isFavorite ? "added to" : "removed from") + " favorites");
            }
        });

        if (isFavorite) {
            holder.imageViewFavorite.setImageResource(R.drawable.favorite_filled);
        } else {
            holder.imageViewFavorite.setImageResource(R.drawable.favorite);
        }

        holder.ratingBarItem.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    currentItem.setRating(rating);
                    showNotification("Rating changed", "New rating: " + rating);
                }
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("name", currentItem.getName());
                intent.putExtra("imageResource", currentItem.getImageResources().get(0));
                intent.putExtra("address", "Food Address"); // Replace with actual address
                intent.putExtra("contact", "+1234567890"); // Replace with actual contact information
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    private void showNotification(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, "channel_id")
                .setSmallIcon(R.drawable.pocket)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        notificationManager.notify(1, builder.build());
    }

    static class ViewHolder {
        ImageView imageViewItem;
        TextView textViewItemName;
        RatingBar ratingBarItem;
        ImageView imageViewFavorite;
    }
}
