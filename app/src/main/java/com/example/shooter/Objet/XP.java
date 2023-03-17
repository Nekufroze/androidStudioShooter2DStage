package com.example.shooter.Objet;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.content.ContextCompat;

import com.example.shooter.R;

public class XP extends Circle{

    private static SharedPreferences sharedPreferences;

    public XP(Context context, Ennemi ennemi){
        super(ContextCompat.getColor(context, R.color.XP),
                ennemi.getPositionX(),
                ennemi.getPositionY(),
                10
        );
        sharedPreferences = context.getSharedPreferences("SaveKraken2D", Context.MODE_PRIVATE);
    }
    public static int getXP() {
        return sharedPreferences.getInt("XP", 0);
    }

    public static void setXP(int xpJ) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int xp = getXP() + xpJ;
        editor.putInt("XP", xp);
        editor.apply();
    }

    @Override
    public void update() {

    }
}
