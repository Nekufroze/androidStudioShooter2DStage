package com.example.shooter.Objet;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.shooter.GameDisplay;
import com.example.shooter.GameLoop;
import com.example.shooter.ObjetGraphique.BarrePV;
import com.example.shooter.R;
import com.example.shooter.ObjetGraphique.Joystick;
import com.example.shooter.assets.Sprite;
import com.example.shooter.assets.Utils;

/*
La class Joueur désigne le joueur principal, qui est contrôlé grâce à un Joystick virtuel
Cette class est un héritage de la class Circle qui est une class hériter de GameObject
 */
public class Joueur extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    public static final int MAX_PV = 2;
    private int PointsDeVie = MAX_PV;
    private final Sprite sprite;
    private final Joystick joystick;
    private final BarrePV barrePV;


    public Joueur(Context context,Joystick joystick, double positionX, double positionY, double radius, Sprite sprite){
    super(ContextCompat.getColor(context, R.color.joueur),positionX, positionY, radius);
        this.joystick = joystick;
        this.barrePV = new BarrePV(context, this);
        this.sprite = sprite;
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
    public void draw(Canvas canvas, GameDisplay gameDisplay){
        sprite.draw(
                canvas,
                (int)gameDisplay.gameToDisplayCoordinatesX(getPositionX())-64,
                (int)gameDisplay.gameToDisplayCoordinatesY(getPositionY()) -40
        );
        barrePV.draw(canvas, gameDisplay);
    }
    public int GetPVRestant() {
        return PointsDeVie;
    }
    public void setPVRestant(int PointsDeVie) {
        if(PointsDeVie >= 0){
            this.PointsDeVie = PointsDeVie;
        }
    }
}
