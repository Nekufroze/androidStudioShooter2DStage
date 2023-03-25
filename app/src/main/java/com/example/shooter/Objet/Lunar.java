package com.example.shooter.Objet;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.content.ContextCompat;

import com.example.shooter.Jeu;
import com.example.shooter.R;

public class Lunar extends Circle{
    private static SharedPreferences sharedPreferences;

    public Lunar(Context context, Ennemi ennemi){
        super(ContextCompat.getColor(context, R.color.Lunar),
                ennemi.getPositionX()+ ((int) (Math.random() * (80 - 5 )) + 5),
                ennemi.getPositionY()+ ((int) (Math.random() * (80 - 5 )) + 5),
                10
        );
        sharedPreferences = context.getSharedPreferences("SaveKraken2D", Context.MODE_PRIVATE);
    }
    public static void setLunar(int lunarPartie) {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            int lunar = getLunar() + lunarPartie;
            editor.putInt("Lunar", lunar);
            editor.apply();
            System.out.println(lunar + " SetLunar");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static int getLunar() {
        try {
            return sharedPreferences.getInt("Lunar", 0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public void update() {
    }
}