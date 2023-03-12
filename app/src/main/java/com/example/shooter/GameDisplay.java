package com.example.shooter;

import com.example.shooter.Objet.GameObject;

public class GameDisplay {
    private double gameToDisplayCoordinateOffsetX;
    private double gameToDisplayCoordinateOffsetY;
    private final double displayCenterX;
    private final double displayCenterY;
    private final GameObject centerObject;


    public GameDisplay(int widhtPixels, int heightPixels, GameObject centerObject){
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
}
