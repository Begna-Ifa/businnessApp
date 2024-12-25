package com.example.business;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class edit_profile extends AppCompatActivity {

    private TextView toolbarTitle;
    private ImageView profileImageView;
    private EditText nameEdit, emailEdit, phoneEdit;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile); // Ensure the layout file is named activity_business.xml

        // Initialize views
        profileImageView = findViewById(R.id.imageView3);
        nameEdit = findViewById(R.id.name_edit);
        emailEdit = findViewById(R.id.email_edit);
        phoneEdit = findViewById(R.id.phone_edit);
        confirmButton = findViewById(R.id.confirm_button);

        // Set toolbar title

        // Set click listener for confirm button
        confirmButton.setOnClickListener(v -> {
            // Handle Confirm button click
            String name = nameEdit.getText().toString();
            String email = emailEdit.getText().toString();
            String phone = phoneEdit.getText().toString();

            // Implement the desired functionality with the collected data
        });
    }
}
