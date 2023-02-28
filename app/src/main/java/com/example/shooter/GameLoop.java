package com.example.shooter;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {

    private boolean isRunning = false;
    private final SurfaceHolder surfaceHolder;
    private final Jeu jeu;
    public GameLoop(Jeu jeu, SurfaceHolder surfaceHolder) {

        this.jeu = jeu;
        this.surfaceHolder = surfaceHolder;
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

        }
    }
}
