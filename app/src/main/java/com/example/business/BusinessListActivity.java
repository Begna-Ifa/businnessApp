package com.example.business;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.List;

public class BusinessListActivity extends AppCompatActivity {

    private ListView listView;
    private BusinessAdapter adapter;
    public static List<BusinessItem> businessItems;
    private static final String CHANNEL_ID = "business_notification_channel";
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        // Initialize ListView and data
        listView = findViewById(R.id.list_view);
        businessItems = new ArrayList<>();

        // Hardcoded business items for demonstration
        businessItems.add(new BusinessItem("Tablet", R.drawable.elect5, 3.5f, "Address 1"));
        businessItems.add(new BusinessItem("Clothing", R.drawable.cloths55, 4.0f, "Address 2"));
        businessItems.add(new BusinessItem("Food", R.drawable.patas, 4.5f, "Address 3"));
        businessItems.add(new BusinessItem("Electronics", R.drawable.electronics, 3.5f, "Address 4"));
        businessItems.add(new BusinessItem("Entertainment", R.drawable.enterte5, 4.0f, "Address 5"));
        businessItems.add(new BusinessItem("More Food", R.drawable.food, 4.5f, "Address 6"));
        businessItems.add(new BusinessItem("More Electronics", R.drawable.elect5, 3.5f, "Address 7"));
        businessItems.add(new BusinessItem("More Clothing", R.drawable.cloths55, 4.0f, "Address 8"));
        businessItems.add(new BusinessItem("More Entertainment", R.drawable.enterte5, 4.5f, "Address 9"));
        // Add more items as needed

        // Initialize custom adapter
        adapter = new BusinessAdapter(this, R.layout.my_business, businessItems);
        listView.setAdapter(adapter);

        // Set item click listener
        listView.setOnItemClickListener((parent, view, position, id) -> {
            BusinessItem selectedItem = businessItems.get(position);

            // Navigate to BusinessDetailsActivity
            Intent intent = new Intent(BusinessListActivity.this, DetailActivity.class);
            intent.putExtra("businessItem", (CharSequence) selectedItem);
            startActivity(intent);
        });

        // Example of handling favorite button click in adapter (if implemented in adapter)
        adapter.setOnFavoriteClickListener((position, isFavorite) -> {
            BusinessItem clickedItem = businessItems.get(position);
            if (isFavorite) {
                // Handle favorite action (e.g., update database, show notification)
                showNotification(clickedItem.getName() + " added to favorites");
            } else {
                // Handle unfavorite action (e.g., update database, show notification)
                showNotification(clickedItem.getName() + " removed from favorites");
            }
        });

        // Example of handling rating bar change in adapter (if implemented in adapter)
        adapter.setOnRatingBarChangeListener((position, rating) -> {
            BusinessItem ratedItem = businessItems.get(position);
            // Handle rating change action (e.g., update database)
            showNotification("Rated " + ratedItem.getName() + ": " + rating);
        });

        // Create notification channel (for devices targeting Android O and above)
        createNotificationChannel();
    }

    private void showNotification(String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.pocket)
                .setContentTitle("Business App Notification")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    // Create a notification channel, required for Android Oreo and higher
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
