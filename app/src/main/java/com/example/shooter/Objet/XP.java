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
    }
    @Override
    public void update() {
    }
}
