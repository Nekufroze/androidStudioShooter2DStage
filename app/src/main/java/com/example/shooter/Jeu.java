package com.example.shooter;
import androidx.annotation.NonNull;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.shooter.assets.Joystick;
/*
 Sert à gérer le jeu et update le render des objets...
 */

public class Jeu extends SurfaceView implements SurfaceHolder.Callback {
    private final GameLoop gameLoop;
    private final Joystick joystick;
    private final Joueur joueur;

    public Jeu(Context context){
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

         gameLoop = new GameLoop(this, surfaceHolder);
        joystick = new Joystick(550, 1800, 120, 40);
        joueur = new Joueur(getContext(), 500,500,30);
        setFocusable(true);
            }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

    switch (event.getAction()){
        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_MOVE:
            joueur.setPosition((double)event.getX(),(double)event.getY());
            return true;
    }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated( SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        joystick.draw(canvas);
        joueur.draw(canvas);

    }


    public void update(){
        joystick.update();
        joueur.update();

    }
}