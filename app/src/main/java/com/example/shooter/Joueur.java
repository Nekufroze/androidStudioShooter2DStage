package com.example.shooter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.shooter.assets.Joystick;

public class Joueur {
    private static final double SPEED_PIXELS_PER_SECOND = 800.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / 60;
    private double positionX;
    private double positionY;
    private final double radius;
    private final Paint paint;
    private double velocityX;
    private double velocityY;
    public Joueur(Context context, double positionX, double positionY, double radius){
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.joueur);
        paint.setColor(color);
    }
    public void draw(Canvas canvas) {
    canvas.drawCircle((float)positionX,(float)positionY,(float)radius,paint);
    }

    public void update(Joystick joystick) {
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;
        positionX += velocityX;
        positionY += velocityY;

    }

    public void setPosition(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;

    }
}
