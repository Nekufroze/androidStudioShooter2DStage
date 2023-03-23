package com.example.shooter.Objet;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.content.ContextCompat;

import com.example.shooter.R;

public class XP extends Circle{
    static int XP = 0;
    static int LastXP;
    public XP(Context context, Ennemi ennemi){
        super(ContextCompat.getColor(context, R.color.XP),
                ennemi.getPositionX(),
                ennemi.getPositionY(),
                10
        );
    }
    public void setXP(int xp){
        XP = xp;
    }
    public static int getXP(){
        return XP;
    }
    public static void setLastXP(int lastXP){
        LastXP = lastXP;
    }
    public int getLastXP(){
        return LastXP + XP;
    }
    @Override
    public void update() {

    }
}
