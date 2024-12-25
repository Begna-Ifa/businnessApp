package com.example.business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class user_profile  extends Activity {

    private TextView userNameTextView, userEmailTextView, userPhoneTextView;
    private Button editProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        // Initialize views
        userNameTextView = findViewById(R.id.user_name);
        userEmailTextView = findViewById(R.id.user_email);
        userPhoneTextView = findViewById(R.id.user_phone);
        editProfileButton = findViewById(R.id.btn_edit_profile);

        // Set click listener for edit profile button
        editProfileButton.setOnClickListener(v -> {
            // Start EditProfileActivity
            Intent intent = new Intent(user_profile.this, edit_profile.class);
            startActivity(intent);
        });
    }

    // Method to handle edit profile action
    private void editProfile() {
        // Replace this toast with your logic to navigate to edit profile screen
        Toast.makeText(this, "Edit Profile Clicked", Toast.LENGTH_SHORT).show();
    }
}
