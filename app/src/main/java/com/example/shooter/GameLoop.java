package com.example.shooter;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameLoop extends Thread {
    // Nombre maximum d'update par seconde du jeu
    public static final double MAX_UPS = 60.0;

    protected boolean isRunning = false;
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


    public void stopLoop(){
        isRunning = false;
        try {
            join();
        }catch (InterruptedException e){
        e.printStackTrace();
        }
    }
    @Override
    public void run() {
        super.run();
        // Gameloop / Moteur du jeu
        Canvas canvas = null;
            while (isRunning) {
                try {
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        jeu.update();
                        jeu.draw(canvas);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }finally {
                    if (canvas != null){
                        try {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
}
