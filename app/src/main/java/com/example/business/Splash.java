package com.example.business;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        // Finding the "Get Started" button
        Button getStartedButton = findViewById(R.id.getStartedButton);

        // Setting OnClickListener to the button
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the main activity
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                // Finish the current activity to prevent returning to it by pressing back button
                finish();
            }
        });
    }
}
