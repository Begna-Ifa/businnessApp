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

public class ElectronicsAdapter extends BaseAdapter {

    private Context mContext;
    private List<ElectronicsItem> mElectronicsList;
    private boolean isFavorite = false;

    public ElectronicsAdapter(Context context, List<ElectronicsItem> electronicsList) {
        mContext = context;
        mElectronicsList = electronicsList;
    }

    @Override
    public int getCount() {
        return mElectronicsList.size();
    }

    @Override
    public Object getItem(int position) {
        return mElectronicsList.get(position);
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

        ElectronicsItem currentItem = (ElectronicsItem) getItem(position);

        // Set item data
        if (!currentItem.getImageResources().isEmpty()) {
            holder.imageViewItem.setImageResource(currentItem.getImageResources().get(0));
        }
        holder.textViewItemName.setText(currentItem.getName());
        holder.ratingBarItem.setRating(currentItem.getRating());

        // Handle item click
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("name", currentItem.getName());
            intent.putExtra("imageResource", currentItem.getImageResources().get(0));
            intent.putExtra("address", "Wolkite"); // Replace with actual address
            intent.putExtra("contact", "+251-944-523-786"); // Replace with actual contact information
            mContext.startActivity(intent);
        });


        // Handle favorite button click
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

        // Handle rating change
        holder.ratingBarItem.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    currentItem.setRating(rating);
                    showNotification("Rating changed", "New rating: " + rating);
                }
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
