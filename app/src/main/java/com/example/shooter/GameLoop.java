package com.example.shooter;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {

    private boolean isRunning = false;
    private SurfaceHolder surfaceHolder;
    private Jeu jeu;
    private double averageUPS;
    private double averageFPS;
    public GameLoop(Jeu jeu, SurfaceHolder surfaceHolder) {

        this.jeu = jeu;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() {
        return averageUPS;
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

        int updateCount = 0;
        int framecount = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

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

            updateCount++;
            framecount++;

            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000){
                averageUPS = updateCount /(1E-3*elapsedTime);
                averageFPS = framecount /(1E-3*elapsedTime);
                updateCount = 0;
                framecount = 0;
                startTime = System.currentTimeMillis();
            }

        }
    }
}
