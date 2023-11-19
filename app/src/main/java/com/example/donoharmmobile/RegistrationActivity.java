package com.example.donoharmmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        findViewById(R.id.registrationButton).setOnClickListener(vies -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("clientId", "Id");
            startActivity(intent);
        });
    }
}