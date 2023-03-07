package com.example.shooter.Objet;

import android.graphics.Canvas;

public abstract class GameObject {
    protected double positionX;
    protected double positionY;
    protected double velocityX;
    protected double velocityY;
    protected double directionX = 1;
    protected double directionY = 1;
    public GameObject(double positionX, double positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    protected static double getDistanceBetweenObjects(GameObject obj1, GameObject obj2) {
        /*
        Théorème de Pythagore pour obtenir la distance entre les deux objets
        qui retourne la racine carré de la distance entre obj1(Joueur) et obj2(Ennemi)
         */
        return Math.sqrt(
                Math.pow(obj2.getPositionX()- obj1.getPositionX(),2) +
                        Math.pow(obj2.getPositionY()- obj1.getPositionY(),2)
        );
    }
    protected double getDirectionX(){
        return directionX;
    }
    protected double getDirectionY(){
        return directionY;
    }
    public abstract void draw(Canvas canvas);
    public abstract void update();

    public double getPositionX() {
        return positionX;
    }
    public double getPositionY() {
        return positionY;
    }

}




