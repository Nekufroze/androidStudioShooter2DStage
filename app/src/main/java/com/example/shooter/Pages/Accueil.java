package com.example.shooter.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.shooter.R;

@SuppressWarnings("ALL")
public class Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        Button btn_Start = findViewById(R.id.btn_Start);
        btn_Start.setOnClickListener(v -> {
            Intent myIntent = new Intent(Accueil.this, MainActivity.class);
            startActivity(myIntent);
        });
    }
}