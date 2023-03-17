package com.example.shooter.Objet;
import android.content.Context;
import androidx.core.content.ContextCompat;
import com.example.shooter.R;

public class Lunar extends Circle{



    public Lunar(Context context, Ennemi ennemi){

        super(ContextCompat.getColor(context, R.color.Lunar),
                ennemi.getPositionX()+ ((int) (Math.random() * (80 - 5 )) + 5),
                ennemi.getPositionY()+ ((int) (Math.random() * (80 - 5 )) + 5),
                10
        );
    }
    @Override
    public void update() {
    }
}
