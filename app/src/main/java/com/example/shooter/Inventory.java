package com.example.shooter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inventory extends AppCompatActivity {

    private Button button_Play;
    private Button button_Inv;
    private Button button_Shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        button_Play = findViewById(R.id.btn_Play);
        button_Inv = findViewById(R.id.btn_Inv);
        button_Shop = findViewById(R.id.btn_Shop);

        button_Play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(Inventory.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

        button_Inv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(Inventory.this, Inventory.class);
                startActivity(myIntent);
            }
        });

        button_Shop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(Inventory.this, Shop.class);
                startActivity(myIntent);
            }
        });

    }
}