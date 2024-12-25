package com.example.business;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class review extends Activity {

    private EditText messageEditText;
    private Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        // Initialize EditText and Button
        messageEditText = findViewById(R.id.list_view);
        sendMessageButton = findViewById(R.id.send_message_button);

        // Set click listener for the button
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    // Method to handle send message action
    // Method to handle send message action
    private void sendMessage() {
        String message = messageEditText.getText().toString().trim();
        if (!message.isEmpty()) {
            // Create intent to open default SMS app
            Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
            smsIntent.setData(Uri.parse("smsto:"));  // Correct URI scheme for SMS
            smsIntent.putExtra("sms_body", message); // Correct extra key for SMS body

            if (smsIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(smsIntent);
            } else {
                Toast.makeText(this, "No SMS app available", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
        }
    }

}
