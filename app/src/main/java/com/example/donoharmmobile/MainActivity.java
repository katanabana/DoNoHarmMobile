package com.example.donoharmmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.signInButton).setOnClickListener(view -> {
            startActivity(new Intent(this, LoginActivity.class));
        });
        findViewById(R.id.signUpButton).setOnClickListener(view -> {
            startActivity(new Intent(this, RegistrationActivity.class));
        });

        ArrayList<String> newsInformation = new ArrayList<>();
        newsInformation.add("Новости:");
        String news = "";
        try {
            InputStream stream = getAssets().open("news.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            news = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] lines = news.split("\n");
        for (String line: lines) {
            String[] items = line.split("\"");
            if (items.length == 5) {
                newsInformation.add(items[3]);
            }
        }
        newsInformation.add("\n");
        newsInformation.add("Услуги:\n");
        String services = "";
        try {
            InputStream stream = getAssets().open("Service.csv");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            services = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String information = String.join("\n", newsInformation) + services;
        TextView infoWidget = findViewById(R.id.information);
        infoWidget.setText(information);
    }
}