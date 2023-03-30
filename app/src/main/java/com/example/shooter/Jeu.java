package com.example.shooter;
import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.shooter.Objet.Balle;
import com.example.shooter.Objet.Circle;
import com.example.shooter.Objet.Ennemi;
import com.example.shooter.Objet.Joueur;
import com.example.shooter.Objet.Lunar;
import com.example.shooter.Objet.XP;
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

    private int joystickPointerid = 0;
    protected final SurfaceHolder surfaceHolder;
    private static GameLoop gameLoop;
    private final GameOver gameOver;
    private final GameDisplay gameDisplay;
    private final Joystick joystick;
    private final Joueur joueur;
    private final List<Ennemi> ListeEnnemi = new ArrayList<>();
    private final List<Balle> ListeBalle = new ArrayList<>();
    private final List<XP> ListeXP = new ArrayList<>();
    private final List<Lunar> ListeLunar = new ArrayList<>();

    private final int minLunar = 1;
    private final int maxLunar = 3;
    private final int randomLunar = (int) (Math.random() * (maxLunar - minLunar )) + minLunar;
    private static int NbLunarP = 0;
    private static int NbXP = 0;


    private int BalleATirer = 0;

    protected static double Nbennemi_Minute = 30;
    protected static int Nbennemi_Spawn = 0;
    protected static int NbEnnemiMort = 0;

    public Jeu(Context context){
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        gameLoop = new GameLoop(this, surfaceHolder);
        gameOver = new GameOver(context);
        joystick = new Joystick((int) (GameDisplay.getDisplayX(getContext()))/3, (int) ((int) GameDisplay.getDisplayY(getContext())*0.75), 120, 40);
        System.out.println((int) GameDisplay.getDisplayX(getContext())+"  "+ (int) GameDisplay.getDisplayY(getContext()));
        SpriteSheet spriteSheet = new SpriteSheet(context);
        joueur = new Joueur(getContext(),joystick, 500,1000,30, spriteSheet.getJoueurSprite());

        // initialize La vue du jeu et ce centre sur le joueur
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, joueur);
        setFocusable(true);
            }

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
            Balle.setPuissanceBalle(Balle.getPuissanceBalle());
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
        // Clear the canvas
        // Draw new content if the player is still alive
        if (joueur.GetPVRestant() > 0) {
            super.draw(canvas);
            joueur.draw(canvas, gameDisplay);
            joystick.draw(canvas);
            for (Ennemi ennemi : ListeEnnemi) {
                ennemi.draw(canvas, gameDisplay);
            }
            for (Balle balle : ListeBalle) {
                balle.draw(canvas, gameDisplay);
            }
            for (XP xp : ListeXP){
                xp.draw(canvas, gameDisplay);
            }
            for (Lunar lunar : ListeLunar){
                lunar.draw(canvas, gameDisplay);
            }
        } else {
            // Draw gameOver screen
            canvas.drawColor(Color.BLACK);
            try {
                gameOver.draw(canvas);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static double GetNbennemi_Minute() {
        if (NbEnnemiMort % 20 == 0 && NbEnnemiMort != 0){
            Nbennemi_Minute = Nbennemi_Minute *1.25;
            Ennemi.setPvEnnemi(Ennemi.getPvEnnemi() + 1);
        }
        return Nbennemi_Minute;
    }
    public static int GetXpPartie(){
        return NbXP;
    }
    public static int GetXPTot(){return XP.getXP() + GetXpPartie();}
    public static int GetLunarPartie(){
        return NbLunarP;
    }
    public static int GetLunarTot(){ return  Lunar.getLunar() + GetLunarPartie();}
    public static int GetNbEnnemiMort(){ return NbEnnemiMort;}


    public void update(){
        joystick.update();
        joueur.update();
        // Stop d'update le jeu lorsque le joueur est mort.
        if(joueur.GetPVRestant() <= 0){
            try {
                XP.setXP(GetXpPartie());
                Lunar.setLunar(GetLunarPartie());
                gameLoop.stopLoop();
                wait(100);
                gameOver.SetGameOver();
                return;
            }catch (Exception e){
                e.printStackTrace();
            }
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
            Ennemi ennemi = iteratorEnnemi.next();
            if(Circle.isColliding(ennemi, joueur)){
                joueur.setPVRestant(joueur.GetPVRestant() - 1);
                iteratorEnnemi.remove();
                continue;
            }
            Iterator<Balle> iteratorBalle = ListeBalle.iterator();
            while (iteratorBalle.hasNext()){
                 Circle balle = iteratorBalle.next();
                if (Circle.isColliding(balle, ennemi)){
                    if(ennemi.GetPVRestant() <= Balle.getPuissanceBalle()){
                        ListeXP.add(new XP(getContext(), ennemi));
                        for (int i = 0; i < randomLunar; i++) {
                            ListeLunar.add(new Lunar(getContext(), ennemi));
                        }
                        NbEnnemiMort++;
                        Nbennemi_Spawn++;
                        System.out.println(GetNbennemi_Minute());
                        System.out.println(NbEnnemiMort);
                        iteratorBalle.remove();
                        iteratorEnnemi.remove();
                        break;
                    }
                    else{
                        ennemi.setPVRestant(ennemi.GetPVRestant()- Balle.getPuissanceBalle());
                        iteratorBalle.remove();
                    }
                }
            }
        }
        Iterator<Lunar>LunarIterator = ListeLunar.iterator();
        while (LunarIterator.hasNext()){
            Lunar lunar = LunarIterator.next();
            if(Circle.isColliding(lunar, joueur)){
                LunarIterator.remove();
                NbLunarP = NbLunarP + 1;
                System.out.println(NbLunarP + " Lunar");
            }
        }
        Iterator<XP> xpIterator = ListeXP.iterator();
        while (xpIterator.hasNext()){
            XP xp = xpIterator.next();
            if(Circle.isColliding(xp,joueur)){
                xpIterator.remove();
                NbXP = NbXP + 1;
                System.out.println(NbXP + " xp");
            }
        }
        gameDisplay.update();
    }
    public static void pause() {
        gameLoop.stopLoop();
    }
}