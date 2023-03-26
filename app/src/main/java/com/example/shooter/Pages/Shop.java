package com.example.shooter.Pages;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shooter.GameData.GameDatas;
import com.example.shooter.Objet.Balle;
import com.example.shooter.Objet.Lunar;
import com.example.shooter.R;

public class Shop extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Button button_Play = findViewById(R.id.btn_Play);
        Button button_Inv = findViewById(R.id.btn_Inv);
        Button button_Shop = findViewById(R.id.btn_Shop);
        Button button_Achat = findViewById(R.id.btn_Achat);
        TextView textView_Lunar = findViewById(R.id.textView_Lunar);
        TextView textView_PuissanceBalle = findViewById(R.id.textView_PuissanceBalle);
        GameDatas gameDatas = new GameDatas(this);
        Intent i = new Intent(Shop.this, Shop.class);
        textView_Lunar.setText("Lunar : " + gameDatas.getLunar());
        textView_PuissanceBalle.setText("Puissance de la balle : " + gameDatas.getPuissanceBalle());

        button_Achat.setOnClickListener(v -> {
            if(gameDatas.getLunar() > 1000){
                gameDatas.setLunar(-1000);
                gameDatas.setPuissanceBalle(gameDatas.getPuissanceBalle()+1);
                finish();
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);
                Toast.makeText(this, "Achat Effectuer.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Manque de Lunar.", Toast.LENGTH_SHORT).show();
            }
        });

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