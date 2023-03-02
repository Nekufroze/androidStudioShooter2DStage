package com.example.shooter.Objet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/*
Circle est une class mise en Abstract ne pouvant donc pas être créer (Classe Mère) elle réalise
la création visuel des objets
 */
public abstract class Circle extends GameObject {

    protected final double radius;
    protected final Paint paint;

    public Circle(Context context, int color, double positionX, double positionY, double radius) {
        super(positionX, positionY);
        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);
    }
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX,(float)positionY,(float)radius,paint);
    }
}
