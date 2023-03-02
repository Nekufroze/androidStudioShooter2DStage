package com.example.shooter.Objet;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.shooter.R;

/*
Class Ennemi qui va créer un ennemi qui ira toujours dans notre direction.
C'est une classe Fille de Circle Qui est notre classe mère mais qui est aussi la classe fille
de GameObject
 */
public class Ennemi extends Circle {
    private static final double SPEED_PIXELS_PER_SECOND = Joueur.SPEED_PIXELS_PER_SECOND * 0.85;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / 120;
    private final Joueur joueur;

    public Ennemi(Context context, Joueur joueur, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.ennemi), positionX, positionY, radius);
        this.joueur = joueur;
    }

    @Override
    public void update() {
/*
 Change la direction et la vitesse de l'ennemi dans notre direction
 */
        // Vecteur de l'enemmi au Joueur (en X et Y)
        double distanceToJoueurX = joueur.getPositionX() - positionX;
        double distanceToJoueurY = joueur.getPositionY() - positionY;

        // Calcul de la distance entre l'ennemi et le Joueur
        double distanceToJoueur = GameObject.getDistanceBetweenObjects(this, joueur);
        // Calcul de la direction de l'ennemi au Joueur

        double directionX = distanceToJoueurX/distanceToJoueur;
        double directionY = distanceToJoueurY/distanceToJoueur;

        // Mettre le joueur dans la direction du Joueur
        if(distanceToJoueur > 0){
            velocityX = directionX*MAX_SPEED;
            velocityY = directionY*MAX_SPEED;
            }else   {
                velocityX = 0;
                velocityY = 0;
            }

        positionX += velocityX;
        positionY += velocityY;
    }
}
