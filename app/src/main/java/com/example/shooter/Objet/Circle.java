package com.example.shooter.Objet;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.shooter.GameDisplay;

/*
Circle est une class mise en Abstract ne pouvant donc pas être créer (Class Mère) elle réalise
la création visuel des objets
 */
public abstract class Circle extends GameObject {

    protected final double radius;
    protected final Paint paint;

    public Circle(int color, double positionX, double positionY, double radius) {
        super(positionX, positionY);
        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);
    }
    /*
        Fonction permettant de savoir si deux objets sont en collision en fonction de la distance entre les rayons des deux objets
        getDistanceBetweenObjects --> GameObject
    */
    public static boolean isColliding(Circle obj1, Circle obj2) {
        double distance = getDistanceBetweenObjects(obj1,obj2);
        double distanceToCollision = obj1.getRadius() + obj2.getRadius();
        if (distance < distanceToCollision)
            return true;
        else
            return false;
    }

    private double getRadius() {
        return radius;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawCircle(
                (float)gameDisplay.gameToDisplayCoordinatesX(positionX),
                (float)gameDisplay.gameToDisplayCoordinatesY(positionY),
                (float)radius,
                        paint);
    }
}
