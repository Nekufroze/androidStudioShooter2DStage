package com.example.shooter;
import androidx.annotation.NonNull;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.shooter.Objet.Balle;
import com.example.shooter.Objet.Circle;
import com.example.shooter.Objet.Ennemi;
import com.example.shooter.Objet.Joueur;
import com.example.shooter.assets.Joystick;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
 Sert à gérer le jeu et update le render des objets...
 */

public class Jeu extends SurfaceView implements SurfaceHolder.Callback {
    private final GameLoop gameLoop;
    private int joystickPointerid = 0;
    private final Joystick joystick;
    private final Joueur joueur;
    private final List<Ennemi> ListeEnnemi = new ArrayList<>();
    private final List<Balle> ListeBalle = new ArrayList<>();
    private int BalleATirer = 0;
    public Jeu(Context context){
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);
        joystick = new Joystick(350, 1800, 120, 40);
        joueur = new Joueur(getContext(),joystick, 500,1000,30);
        setFocusable(true);
            }

    public static double Nbennemi() {
        return 20;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

    switch (event.getActionMasked()){
        // Cas ou le joystick est pressé
        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_POINTER_DOWN:
            // Si le joystick étais déja préssé avant Il faut donc tiré
            if(joystick.getIsPressed()){
                BalleATirer++;
            } else if(joystick.isPressed(event.getX(), event.getY())){
                joystickPointerid = event.getPointerId(event.getActionIndex());
                joystick.setIsPressed(true);
            }else {
                // Cas si le joystick n'a jamais été pressé
                BalleATirer++;            }
            return true;
            // cas ou le joystick est bougé
        case MotionEvent.ACTION_MOVE:
            if(joystick.getIsPressed()){
                joystick.setActuator(event.getX(), event.getY());
            }
            return true;
            // Cas ou le joystick n'est plus pressé
        case MotionEvent.ACTION_UP:
        case MotionEvent.ACTION_POINTER_UP:
            if(joystickPointerid == event.getPointerId(event.getActionIndex())){
                joystick.setIsPressed(false);
                joystick.resetActuator();
            }
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
        for (Ennemi ennemi : ListeEnnemi){
            ennemi.draw(canvas);
        }
        for (Balle balle : ListeBalle){
            balle.draw(canvas);
        }

    }

    public void update(){
        joystick.update();
        joueur.update();
        // Spawn un ennemi lorsqu'il doit spawn
    if(Ennemi.readyToSpawn()){
        ListeEnnemi.add(new Ennemi(getContext(), joueur));
    }
        while (BalleATirer > 0){
        ListeBalle.add(new Balle(getContext(),joueur));
            BalleATirer--;
    }
    // Update l'état de chaque ennemi et des balles
        for (Ennemi ennemi : ListeEnnemi){
            ennemi.update();
        }
        for (Balle balle : ListeBalle){
            balle.update();
        }

        // Fonction Qui va permettre d'enlever l'ennemi si il est touché par le tir du joueur
        // Pour le moment l'ennemi sera supprimé lorsqu'il touche le joueur
        Iterator<Ennemi> iteratorEnnemi = ListeEnnemi.iterator();
        while (iteratorEnnemi.hasNext()){
            Circle ennemi = iteratorEnnemi.next();
            if (Circle.isColliding(ennemi ,joueur)){
                continue;
            }
            Iterator<Balle> iteratorBalle = ListeBalle.iterator();
            while (iteratorBalle.hasNext()){
                Circle balle = iteratorBalle.next();
                if (Circle.isColliding(balle, ennemi)){
                    iteratorBalle.remove();
                    iteratorEnnemi.remove();
                    break;
                }
            }
        }
    }
}