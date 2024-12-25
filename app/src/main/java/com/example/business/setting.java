package com.example.business;// setting.java
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class setting extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private ImageView aboutImageView, notificationImageView, helpImageView;
    private Button aboutButton, notificationButton, helpButton, changeBackgroundButton;
    private LinearLayout mainLayout; // Main layout to change background color

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        // Initialize views
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        aboutImageView = findViewById(R.id.imageView);
        notificationImageView = findViewById(R.id.imageView2);
        helpImageView = findViewById(R.id.imageView1);
        aboutButton = findViewById(R.id.button);
        notificationButton = findViewById(R.id.button2);
        helpButton = findViewById(R.id.button1);
        changeBackgroundButton = findViewById(R.id.button_change_background);
        mainLayout = findViewById(R.id.main_layout); // Initialize main layout

        // Set up toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbarTitle.setText("WOLKITE LOCAL BUSINESSES");

        // Set click listeners for buttons
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display custom AlertDialog for About information
                showInformationDialog("About Wolkite Local Businesses",
                        "Welcome to Wolkite Local Businesses app! This app aims to promote local businesses and connect customers with nearby" +
                                " services and products. Discover a variety of offerings from local vendors and support your community.");
            }
        });

        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display custom AlertDialog for Notification information
                showInformationDialog("Notification Preferences",
                        "Manage your notification preferences here. Stay updated with the latest promotions, events, and new arrivals from your favorite local businesses.");
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Display custom AlertDialog for Help & Support information
                showInformationDialog("Help & Support",
                        "Need assistance? Our support team is ready to help you with any questions or issues you encounter " +
                                "while using the app. Feel free to reach out via email at support@wolkitebusinesses.com.");
            }
        });

        // Handle button click to change background color
        changeBackgroundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate a random color (you can modify this logic)
                int color = getRandomColor();

                // Set background color of main layout
                mainLayout.setBackgroundColor(color);
            }
        });
    }

    private void showInformationDialog(String title, String message) {
        // Create AlertDialog.Builder and set custom layout
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialogue_information, null);
        builder.setView(dialogView);

        // Initialize views inside custom layout
        TextView dialogTitle = dialogView.findViewById(R.id.dialog_title);
        TextView dialogMessage = dialogView.findViewById(R.id.dialog_message);

        // Set title and message
        dialogTitle.setText(title);
        dialogMessage.setText(message);

        // Add OK button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        // Create and show AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Method to generate a random color (you can modify this method)
    private int getRandomColor() {
        int[] colors = {Color.BLACK, Color.WHITE, Color.YELLOW}; // Define your colors here
        int randomIndex = (int) (Math.random() * colors.length);
        return colors[randomIndex];
    }
}
