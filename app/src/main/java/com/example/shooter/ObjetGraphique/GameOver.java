package com.example.shooter.ObjetGraphique;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.shooter.GameDisplay;
import com.example.shooter.Jeu;

import com.example.shooter.R;

public class GameOver {
    private final Context context;
    private final int rectLeft = (int) (GameDisplay.getDisplayX()*2/10);
    private final int rectTop = (int) (GameDisplay.getDisplayY()*0.75);
    private final int rectRight = (int) (GameDisplay.getDisplayX()*8/10);
    private final int rectBottom = (int) (GameDisplay.getDisplayY()*0.85);
    private boolean isPressed = false;
    private static boolean GameOver = false;


    public GameOver(Context context){
        this.context = context;
        Jeu.pause();
    }
    public void draw(Canvas canvas) {
        String text = "Game Over \nEnnemis tuer : " + Jeu.GetNbEnnemiMort() + "\nXP Acquis : " + Jeu.GetXpPartie() + "\nXP Total : "
                        + Jeu.GetXPTot() + "\nLunar Acquis : " + Jeu.GetLunarPartie() + "\nLunar Total : " + Jeu.GetLunarTot();
        float x = 250;
        float y = 250;

        Paint paint = new Paint();
        int couleur = ContextCompat.getColor(context , R.color.GameOver);
        paint.setColor(couleur);
        float textSize = 80;
        paint.setTextSize(textSize);
        for (String line: text.split("\n")) {
            canvas.drawText(line, x, y, paint);
            y += paint.descent() - paint.ascent();
        }
        // Création du rectangle de renvoie à la page principale.
        // Texte à afficher
        String home = "Home";
        // Créer l'objet Paint pour le Texte et le rectange
        Paint rectPaint = new Paint();
        rectPaint.setColor(0xFF6200EE);
        rectPaint.setStyle(Paint.Style.FILL);
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(80);
        // Dessine le rectangle
        RectF rectF = new RectF(rectLeft, rectTop, rectRight, rectBottom);
        int cornerRadius = 20;
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, rectPaint);
        // Dessine le texte au milieu du rectangle
        float textWidth = textPaint.measureText(home);
        float x2 = rectLeft + (rectRight - rectLeft - textWidth) / 2;
        float y2 = rectTop + (rectBottom - rectTop + textPaint.getTextSize()) / 2;
        canvas.drawText(home, x2, y2, textPaint);
    }

    private boolean isInside(float x, float y) {
        return x > rectLeft && x < rectRight && y > rectTop && y < rectBottom;
    }
    private void SetGameOver(){
        GameOver = true;
    }
    public static boolean GetGameOver(){
        return GameOver;
    }
}
