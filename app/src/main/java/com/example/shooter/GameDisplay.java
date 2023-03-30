package com.example.shooter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.example.shooter.Objet.GameObject;

public class GameDisplay {
    private double gameToDisplayCoordinateOffsetX;
    private double gameToDisplayCoordinateOffsetY;
    private static double displayCenterX = 0;
    private static double displayCenterY = 0;
    private final GameObject centerObject;


    public GameDisplay(int widhtPixels, int heightPixels, GameObject centerObject) {
        this.centerObject = centerObject;
        displayCenterX = widhtPixels/2.0;
        displayCenterY = heightPixels/2.0;
    }

    public void update(){
        double gameCenterX = centerObject.getPositionX();
        double gameCenterY = centerObject.getPositionY();
        gameToDisplayCoordinateOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinateOffsetY = displayCenterY - gameCenterY;
    }
    public double gameToDisplayCoordinatesX(double positionX) {
        return positionX + gameToDisplayCoordinateOffsetX;
    }

    public double gameToDisplayCoordinatesY(double positionY ) {
        return positionY + gameToDisplayCoordinateOffsetY;
    }

    public static double getDisplayX(Context context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels/2;
    }
    public static double getDisplayY(Context context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
