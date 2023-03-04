package com.example.shooter.Objet;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.shooter.GameLoop;
import com.example.shooter.R;
import com.example.shooter.assets.Joystick;
import com.example.shooter.assets.Utils;

/*
La Classe Joueur désigne le Joueur principal, qui est controller grâce à un Joystick virtuel
Cette classe est un héritage de la classe Circle qui est une classe Heriter de GameObject
 */
public class Joueur extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    private final Joystick joystick;


    public Joueur(Context context,Joystick joystick, double positionX, double positionY, double radius){
    super(context, ContextCompat.getColor(context, R.color.joueur),positionX, positionY, radius);
        this.joystick = joystick;
    }


    public void update() {
        // Change la vitesse du Joueur par rapport au Joystick
        velocityX = joystick.getActuatorX() * MAX_SPEED;
        velocityY = joystick.getActuatorY() * MAX_SPEED;
        // Update la position du joueur
        positionX += velocityX;
        positionY += velocityY;

        // Direction du joueur
        if(velocityX !=0 || velocityY !=0){
            // Trouvé le vecteur de X et Y afin de trouvé la direction
            double distance = Utils.getDistanceBetweenPoints(0,0,velocityX,velocityY);
            directionX  = velocityX/distance;
            directionY = velocityY/distance;

        }
    }

}
