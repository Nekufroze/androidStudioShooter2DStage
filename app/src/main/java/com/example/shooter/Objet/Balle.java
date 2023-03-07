package com.example.shooter.Objet;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.shooter.GameLoop;
import com.example.shooter.R;

public class Balle extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 1000.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    public Balle(Context context, Joueur Tireur){
        super(ContextCompat.getColor(context, R.color.Balle),
                Tireur.getPositionX(),
                Tireur.getPositionY(),
                15
        );
        velocityX = Tireur.getDirectionX()*MAX_SPEED;
        velocityY = Tireur.getDirectionY()*MAX_SPEED;

    }


    @Override
    public void update() {
    positionX += velocityX;
    positionY += velocityY;
    }
}
