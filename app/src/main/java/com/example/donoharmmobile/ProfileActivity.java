package com.example.donoharmmobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        for (String line: clients.split("\n")) {
            String[] fields = line.split(",");
            String clientId = getIntent().getStringExtra("clientId");
            if (Objects.equals(fields[12], clientId)) {
                EditText login = findViewById(R.id.profileLogin);
                EditText password = findViewById(R.id.profilePassword);
                EditText name = findViewById(R.id.profileName);
                EditText phone = findViewById(R.id.profilePhone);
                EditText email = findViewById(R.id.profileEmail);
                EditText date = findViewById(R.id.profileDate);
                EditText age = findViewById(R.id.profileAge);
                login.setText(fields[1]);
                password.setText(fields[2]);
                name.setText(fields[0]);
                phone.setText(fields[8]);
                email.setText(fields[4]);
                date.setText(fields[11]);
                if (!Objects.equals(clientId, "Id")) {
                    int year = Integer.parseInt("13/12/2000".split("/")[0]);
                    age.setText(String.valueOf(2023 - year));
                }
            }
        }

    }


}