package com.example.shooter.Objet;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.content.ContextCompat;

import com.example.shooter.R;

public class XP extends Circle{
    public XP(Context context, Ennemi ennemi){
        super(ContextCompat.getColor(context, R.color.XP),
                ennemi.getPositionX(),
                ennemi.getPositionY(),
                10
        );
        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

    }
    @Override
    public void update() {
    }
    public static void SetXpJoueur(int xp){
        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);

    public static int GetXpJoueur(){
        return XPJoueur;
    }
}