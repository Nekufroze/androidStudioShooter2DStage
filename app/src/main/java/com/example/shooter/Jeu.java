package com.example.shooter;
import androidx.annotation.NonNull;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.shooter.Objet.Ennemi;
import com.example.shooter.Objet.Joueur;
import com.example.shooter.assets.Joystick;
/*
 Sert à gérer le jeu et update le render des objets...
 */

public class Jeu extends SurfaceView implements SurfaceHolder.Callback {
    private final GameLoop gameLoop;
    private final Joystick joystick;
    private final Joueur joueur;
    private final Ennemi ennemi;
    public Jeu(Context context){
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

         gameLoop = new GameLoop(this, surfaceHolder);
        joystick = new Joystick(550, 1800, 120, 40);
        joueur = new Joueur(getContext(),joystick, 500,500,30);
        ennemi = new Ennemi(getContext(),joueur, 500,200,30);
        setFocusable(true);
            }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

    switch (event.getAction()){
        case MotionEvent.ACTION_DOWN:
            if(joystick.isPressed((double)event.getX(),(double)event.getY())){
                joystick.setIsPressed(true);
            }
            return true;
        case MotionEvent.ACTION_MOVE:
            if(joystick.getIsPressed()){
                joystick.setActuator((double)event.getX(),(double)event.getY());
            }
            return true;
        case MotionEvent.ACTION_UP: {
               joystick.setIsPressed(false);
               joystick.resetActuator();
               return true;
            }

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
        ennemi.draw(canvas);

    }


    public void update(){
        joystick.update();
        joueur.update();
        ennemi.update();

    }
}