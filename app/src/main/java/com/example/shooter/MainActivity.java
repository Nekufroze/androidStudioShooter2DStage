package com.example.shooter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_Play = findViewById(R.id.btn_Play);
        Button button_Inv = findViewById(R.id.btn_Inv);
        Button button_Shop = findViewById(R.id.btn_Shop);

        button_Play.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, Level_Selector.class);
            startActivity(myIntent);
        });

        button_Inv.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, Inventory.class);
            startActivity(myIntent);
        });

        button_Shop.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, Shop.class);
            startActivity(myIntent);
        });


    }
}