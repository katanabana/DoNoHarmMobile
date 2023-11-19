package com.example.donoharmmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.loginButton).setOnClickListener(view -> {
            EditText loginWidget = findViewById(R.id.loginLogin);
            EditText passwordWidget = findViewById(R.id.loginPassword);
            String login = loginWidget.getText().toString();
            String password = passwordWidget.getText().toString();
            String clients = "";
            try {
                InputStream stream = getAssets().open("Client.csv");
                int size = stream.available();
                byte[] buffer = new byte[size];
                stream.read(buffer);
                stream.close();
                clients = new String(buffer, StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int i = 0;
            for (String line: clients.split("\n")) {
                if (i != 0) {
                    String[] fields = line.split(",");
                    if (Objects.equals(fields[1], login) && Objects.equals(fields[2], password)) {
                        Intent intent = new Intent(this, ProfileActivity.class);
                        intent.putExtra("clientId", fields[12]);
                        startActivity(intent);
                    }
                }
                i++;
            }
        });
    }
}