package com.example.shooter.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shooter.R;

public class Shop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Button button_Play = findViewById(R.id.btn_Play);
        Button button_Inv = findViewById(R.id.btn_Inv);
        Button button_Shop = findViewById(R.id.btn_Shop);

        button_Play.setOnClickListener(v -> {
            Intent myIntent = new Intent(Shop.this, MainActivity.class);
            startActivity(myIntent);
        });

        button_Inv.setOnClickListener(v -> {
            Intent myIntent = new Intent(Shop.this, Inventory.class);
            startActivity(myIntent);
        });

        button_Shop.setOnClickListener(v -> {
            Intent myIntent = new Intent(Shop.this, Shop.class);
            startActivity(myIntent);
        });

    }
}