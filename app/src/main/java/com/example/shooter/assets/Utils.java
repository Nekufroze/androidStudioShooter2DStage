package com.example.shooter.assets;

public class Utils {
    /*
    Retourne la distance entre p1 et p2 qui sont des objets en 2D
     */
    public static double getDistanceBetweenPoints(double p1x, double p1y, double p2x, double p2y) {
        return Math.sqrt(Math.pow(p1x - p2x, 2) + Math.pow(p1y - p2y, 2));
    }
}