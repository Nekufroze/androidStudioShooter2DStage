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
    private final Context context;
    private final Joystick joystick;


    public Jeu(Context context){
        super(context);

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.context = context;
         gameLoop = new GameLoop(this, surfaceHolder);
        joystick = new Joystick(550, 1800, 120, 40);

        setFocusable(true);
            }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Handle user input touch event actions
        System.out.println(joystick.getActuatorX());
        System.out.println(joystick.getActuatorY());
        int joystickPointerId = 0;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (joystick.getIsPressed()) {
                    String s = "";
                    // Joystick was pressed before this event -> cast spell
                } else if (joystick.isPressed(event.getX(), event.getY())) {
                    // Joystick is pressed in this event -> setIsPressed(true) and store pointer id
                    joystick.setIsPressed(true);
                } else {
                    String S =":";
                    // Joystick was not previously, and is not pressed in this event -> cast spell
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick.getIsPressed()) {
                    // Joystick was pressed previously and is now moved
                    joystick.setActuator(event.getX(), event.getY());
                }
                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (joystickPointerId == event.getPointerId(event.getActionIndex())) {
                    // joystick pointer was let go off -> setIsPressed(false) and resetActuator()
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

    }


    public void update(){
        joystick.update();

    }
}