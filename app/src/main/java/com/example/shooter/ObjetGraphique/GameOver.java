package com.example.shooter.ObjetGraphique;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.shooter.DataBase.Kraken2Database;
import com.example.shooter.Jeu;
import com.example.shooter.Objet.Lunar;
import com.example.shooter.Objet.XP;
import com.example.shooter.R;

public class GameOver {
    private final Context context;

    public GameOver(Context context){
        this.context = context;
        Jeu.pause();
    }
    public void draw(Canvas canvas) {
        String text = "Game Over \nEnnemis tuer : " + Jeu.GetNbEnnemiMort() + "\nXP Acquis : " + Jeu.GetXpPartie() + "\nXP Total : " + Jeu.getLastXP() + "\nLunar Acquis : "
                        + Jeu.GetLunarPartie() + "\nLunar Total : " + Jeu.getLastLunar();
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
    }
}
