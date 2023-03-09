package com.example.shooter.Pages;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.shooter.Jeu;
import com.example.shooter.R;

public class MainActivity extends AppCompatActivity {
    private Jeu jeu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button_Play = findViewById(R.id.btn_Play);
        Button button_Inv = findViewById(R.id.btn_Inv);
        Button button_Shop = findViewById(R.id.btn_Shop);

        button_Play.setOnClickListener(v -> {
            jeu = new Jeu(this);
            setContentView(jeu);
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
    @Override
    protected void onPause() {
        jeu.pause();
        super.onPause();
    }
    @Override
    public void onBackPressed(){
        // Si l'on appuie sur le bouton retour du téléphone aucune action ne sera réaliser
    }
}