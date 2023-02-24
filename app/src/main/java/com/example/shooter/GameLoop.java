package com.example.shooter;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {

    private boolean isRunning = false;
    private final SurfaceHolder surfaceHolder;
    private final Jeu jeu;
    private double averageFPS;
    public GameLoop(Jeu jeu, SurfaceHolder surfaceHolder) {

        this.jeu = jeu;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageFPS() {
        return averageFPS;
    }

    public void startLoop(){
        isRunning = true;
        start();
    }
    @Override
    public void run(){
        super.run();

        int framecount = 0;

        long startTime;
        long elapsedTime;

        // Gameloop / Moteur du jeu
        Canvas canvas;
        startTime = System.currentTimeMillis();
        while(isRunning){
            try{
                canvas = surfaceHolder.lockCanvas();
                jeu.update();
                jeu.draw(canvas);
                surfaceHolder.unlockCanvasAndPost(canvas);
            }catch (IllegalArgumentException e){
                e.printStackTrace();
            }

            framecount++;
            // Affichage des Images Par Secondes
            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000){
                averageFPS = framecount /(1E-3*elapsedTime);
                framecount = 0;
                startTime = System.currentTimeMillis();
            }

        }
    }
}
