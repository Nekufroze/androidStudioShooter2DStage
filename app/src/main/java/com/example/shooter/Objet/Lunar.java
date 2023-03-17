package com.example.shooter.Objet;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.content.ContextCompat;
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
    public static int getLunar() {
        return sharedPreferences.getInt("lunar", 0);
    }

    public static void setLunar(int lunarPartie) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int lunar = getLunar() + lunarPartie;
        editor.putInt("Lunar", lunar );
        editor.apply();
    }

    @Override
    public void update() {

    }
}