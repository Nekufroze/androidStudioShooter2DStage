package com.example.shooter.ObjetGraphique;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.shooter.R;

public class GameOver {
    private final Context context;

    public GameOver(Context context){
        this.context = context;
    }

    public void draw(Canvas canvas) {
        String text = "Game Over";

        float x = 250;
        float y = 250;

        Paint paint = new Paint();
        int couleur = ContextCompat.getColor(context , R.color.GameOver);
        paint.setColor(couleur);
        float textSize = 100;
        paint.setTextSize(textSize);
        canvas.drawText(text, x,y,paint);
    }
}
