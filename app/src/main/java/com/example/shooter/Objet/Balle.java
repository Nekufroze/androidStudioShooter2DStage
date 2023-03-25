package com.example.shooter.Objet;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.core.content.ContextCompat;

import com.example.shooter.GameLoop;
import com.example.shooter.R;

public class Balle extends Circle {
    private static SharedPreferences sharedPreferences;

    public static final double SPEED_PIXELS_PER_SECOND = 1000.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    public Balle(Context context, Joueur Tireur){
        super(ContextCompat.getColor(context, R.color.Balle),
                Tireur.getPositionX(),
                Tireur.getPositionY(),
                15
        );
        setPuissanceBalle(getPuissanceBalle());
        sharedPreferences = context.getSharedPreferences("SaveKraken2D", Context.MODE_PRIVATE);
        velocityX = Tireur.getDirectionX()*MAX_SPEED;
        velocityY = Tireur.getDirectionY()*MAX_SPEED;

    }

    public static void setPuissanceBalle(int puissanceBalle) {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if(puissanceBalle == 0){
                editor.putInt("Puissance_Balle", 1);
                editor.apply();
                System.out.println(1 + " Puissance_Balle");
            }else {
                editor.putInt("Puissance_Balle", puissanceBalle);
                editor.apply();
                System.out.println(puissanceBalle + " Puissance_Balle");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static int getPuissanceBalle() {
        try {
            return sharedPreferences.getInt("Puissance_Balle", 1);
        }catch (Exception e){
            e.printStackTrace();
        }
            return 1;
    }
    @Override
    public void update() {
    positionX += velocityX;
    positionY += velocityY;
    }
}
