package com.example.shooter.Objet;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.shooter.R;

public class Balle extends Circle {
    private  final  Joueur Tireur;
    public Balle(Context context, Joueur Tireur){
        super(context, ContextCompat.getColor(context, R.color.Balle),
                Math.random()*1000,
                Math.random()*1000,
                30
        );
        this.Tireur = Tireur;}


    @Override
    public void update() {
    }
}
