package com.example.shooter.Pages;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.shooter.Jeu;
import com.example.shooter.R;

public class Level_Selector extends AppCompatActivity {

    // Déclaration du nom des levels
    String[] lesLVL = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40"};
    private Jeu jeu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selector);

        GridView mongridview = findViewById(R.id.gridView1);
        //Declaration d'un adaptateur qui servira à stocker les données à afficher dans le GridView.
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        // Edit du nombre de colonne
        mongridview.setNumColumns(1);

        for (String s : lesLVL) {
            adapter.add("Niveau : " + s);
        }
            //Associer l'adaptateur au gridview
            mongridview.setAdapter(adapter);

        mongridview.setOnItemClickListener((parent, v, position, id) -> {
            jeu = new Jeu(this);
            setContentView(jeu);
        });

        }
}