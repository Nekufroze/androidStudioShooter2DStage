package com.example.shooter.Objet;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.shooter.GameDisplay;
import com.example.shooter.GameLoop;
import com.example.shooter.Jeu;
import com.example.shooter.ObjetGraphique.BarrePV;
import com.example.shooter.ObjetGraphique.BarrePVEnnemi;
import com.example.shooter.R;

/*
Class Ennemi qui va créer un ennemi qui ira toujours dans notre direction.
C'est une classe Fille de Circle Qui est notre classe mère mais qui est aussi la classe fille
de GameObject
 */
public class Ennemi extends Circle {
    private static final double SPEED_PIXELS_PER_SECOND = Joueur.SPEED_PIXELS_PER_SECOND * 0.55;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private static final double SPAWNS_PER_MINUTE = Jeu.GetNbennemi_Minute();
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    public static int MAX_PV = 2;
    private int PointsDeVie = MAX_PV;
    private final Joueur joueur;
    private final BarrePVEnnemi barrePVEnnemi;


    public Ennemi(Context context, Joueur joueur) {
        super(
                ContextCompat.getColor(context, R.color.ennemi),
                (Math.random()*1000)+1000,
                (Math.random()*1000)+1000,
                30
        );
            this.barrePVEnnemi = new BarrePVEnnemi(context, this);
            this.joueur = joueur;
    }

    /*
    readyToSpawn regarde si un nouvel ennemi doit pawn, suivant le niveau de difficulté choisie ( A IMPLEMENTER )
    Le spawn sera réalisé par minute
     */
    public static boolean readyToSpawn() {
        if (updatesUntilNextSpawn <= 0) {
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        } else {
            updatesUntilNextSpawn --;
            return false;
        }
    }

    public static int getPvEnnemi() {
        return MAX_PV;
    }
    public static void setPvEnnemi(int pvEnnemi) {
        MAX_PV = pvEnnemi;
        System.out.print("PV ENNEMI = " + MAX_PV);
    }


    @Override
    public void draw(Canvas canvas, GameDisplay gameDisplay) {
            super.draw(canvas, gameDisplay);
    barrePVEnnemi.draw(canvas, gameDisplay);
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

        // Mettre l'ennemi dans la direction du Joueur
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

    public int GetPVRestant() {
        return PointsDeVie;
    }

    public void setPVRestant(int PointsDeVie) {
        if(PointsDeVie >= 0){
            this.PointsDeVie = PointsDeVie;
        }
    }
}
