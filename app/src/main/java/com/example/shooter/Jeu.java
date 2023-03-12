package com.example.shooter;
import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.shooter.Objet.Balle;
import com.example.shooter.Objet.Circle;
import com.example.shooter.Objet.Ennemi;
import com.example.shooter.Objet.Joueur;
import com.example.shooter.ObjetGraphique.GameOver;
import com.example.shooter.ObjetGraphique.Joystick;
import com.example.shooter.assets.SpriteSheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
 Sert à gérer le jeu et update le render des objets...
 */
public class Jeu extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoop gameLoop;
    private int joystickPointerid = 0;
    private final Joystick joystick;
    private final Joueur joueur;
    private final List<Ennemi> ListeEnnemi = new ArrayList<>();
    private final List<Balle> ListeBalle = new ArrayList<>();
    private int BalleATirer = 0;
    protected static double Nbennemi_Minute = 30;
    protected static int Nbennemi_Spawn = 0;
    protected static int NbEnnemiMort = 0;
    protected final SurfaceHolder surfaceHolder;
    private final GameOver gameOver;
    private final GameDisplay gameDisplay;


    public Jeu(Context context){
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        gameLoop = new GameLoop(this, surfaceHolder);
        gameOver = new GameOver(context);

        joystick = new Joystick(350, 1800, 120, 40);
        SpriteSheet spriteSheet = new SpriteSheet(context);
        joueur = new Joueur(getContext(),joystick, 500,1000,30, spriteSheet.getJoueurSprite());

        // initialize La vue du jeu et ce centre sur le joueur
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, joueur);
        setFocusable(true);
            }
    public static double GetNbennemi_Minute() {
        if (NbEnnemiMort % 20 == 0 && NbEnnemiMort != 0){
            Nbennemi_Minute = Nbennemi_Minute *1.25;
        }
        return Nbennemi_Minute;
    }
    public static int GetNbEnnemiMort(){return  NbEnnemiMort;}
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    performClick();
    switch (event.getActionMasked()){
        // Cas ou le joystick est pressé
        case MotionEvent.ACTION_DOWN:
        case MotionEvent.ACTION_POINTER_DOWN:
            // Si le joystick étais déja pressé avant Il faut donc tiré
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
    public boolean performClick() {
        // handle click event
        return super.performClick();
    }

    @Override
    public void surfaceCreated( SurfaceHolder holder) {
        if(gameLoop.getState().equals(Thread.State.TERMINATED)){
            SurfaceHolder surfaceholder = getHolder();
            surfaceholder.addCallback(this);
            gameLoop = new GameLoop(this, surfaceHolder);
        }
        gameLoop.startLoop();
    }
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        joueur.draw(canvas, gameDisplay);
        joystick.draw(canvas);
        // Fin du jeu si le joueur meurt
        if(joueur.GetPVRestant() <=0){
            gameOver.draw(canvas);
        }

        for (Ennemi ennemi : ListeEnnemi) {
                ennemi.draw(canvas, gameDisplay);
            }
            for (Balle balle : ListeBalle) {
                balle.draw(canvas, gameDisplay);
            }
    }

    public void update(){
        joystick.update();
        joueur.update();

        // Stop d'update le jeu lorsque le joueur est mort.
        if(joueur.GetPVRestant() <= 0){
            return;
        }
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
        Iterator<Ennemi> iteratorEnnemi = ListeEnnemi.iterator();
        while (iteratorEnnemi.hasNext()){
            Circle ennemi = iteratorEnnemi.next();
            if(Circle.isColliding(ennemi, joueur)){
                joueur.setPVRestant(joueur.GetPVRestant() - 1);
                iteratorEnnemi.remove();
                continue;
            }
            Iterator<Balle> iteratorBalle = ListeBalle.iterator();
            while (iteratorBalle.hasNext()){
                Circle balle = iteratorBalle.next();
                if (Circle.isColliding(balle, ennemi)){
                    NbEnnemiMort++;
                    Nbennemi_Spawn++;
                    System.out.println(GetNbennemi_Minute());
                    System.out.println(NbEnnemiMort);
                    iteratorBalle.remove();
                    iteratorEnnemi.remove();
                    break;
                }
            }
        }
        gameDisplay.update();
    }

    public void pause() {
        gameLoop.stopLoop();
    }
}