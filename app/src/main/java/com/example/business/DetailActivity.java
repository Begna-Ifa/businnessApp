package com.example.business;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_detail);

        // Retrieve extras from intent
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("name");
            int imageResource = intent.getIntExtra("imageResource", 0);
            String address = intent.getStringExtra("address");
            final String contact = intent.getStringExtra("contact"); // Retrieve contact number

            // Update UI elements with retrieved data
            ImageView imageViewBusiness = findViewById(R.id.imageView_business);
            TextView textViewBusinessName = findViewById(R.id.textView_business_name);
            TextView textViewBusinessAddress = findViewById(R.id.textView_business_address);
            TextView textViewContact = findViewById(R.id.textView_business_contact_label); // TextView to display contact
            Button buttonContact = findViewById(R.id.button_contact_business);
            Button buttonSubmitReview = findViewById(R.id.button_submit_review);

            imageViewBusiness.setImageResource(imageResource);
            textViewBusinessName.setText(name);
            textViewBusinessAddress.setText(address);

            // Concatenate label and contact number
            String contactLabel = "Contact: " + contact;
            textViewContact.setText(contactLabel); // Set concatenated label and contact number in TextView

            // Handle contact button click to open dialer
            buttonContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open dialer with the provided contact number
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse("tel:" + contact));
                    startActivity(dialIntent);
                }
            });

            // Handle submit review button click
            buttonSubmitReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate to review activity or layout
                    Intent reviewIntent = new Intent(DetailActivity.this, review.class);
                    startActivity(reviewIntent);
                }
            });
        }
    }
}


