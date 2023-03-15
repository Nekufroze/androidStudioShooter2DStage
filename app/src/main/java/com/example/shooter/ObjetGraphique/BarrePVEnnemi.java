package com.example.shooter.ObjetGraphique;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.shooter.GameDisplay;
import com.example.shooter.Objet.Ennemi;
import com.example.shooter.R;

public class BarrePVEnnemi {
    private final Ennemi ennemi;
    private final float largeur;
    private final int hauteur;
    private final int marge;
    private final Paint bordurePaint;
    private final Paint ViePaint;

    public BarrePVEnnemi(Context context, Ennemi ennemi){
        this.ennemi = ennemi;
        this.largeur = 100;
        this.hauteur = 20;
        this.marge = 2;
        // Bordure Couleur
        this.bordurePaint = new Paint();
        int bordureCouleur = ContextCompat.getColor(context, R.color.BarrePvBordure);
        bordurePaint.setColor(bordureCouleur);
        // Pv Couleur
        this.ViePaint = new Paint();
        int VieCouleur = ContextCompat.getColor(context, R.color.PVCouleur);
        ViePaint.setColor(VieCouleur);
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        float x = (float) ennemi.getPositionX();
        float y = (float) ennemi.getPositionY();
        float distanceToJoueur = 35;
        float PVRestantPourcentage = (float)ennemi.GetPVRestant()/ Ennemi.MAX_PV;
        float bordureG, bordureH, bordureD, bordureBas;

        bordureG = x - largeur/2;
        bordureD = x + largeur/2;
        bordureBas = y - distanceToJoueur;
        bordureH = bordureBas - hauteur;
        // Bordure
        canvas.drawRect(
                (float)gameDisplay.gameToDisplayCoordinatesX(bordureG),
                (float)gameDisplay.gameToDisplayCoordinatesY(bordureH),
                (float)gameDisplay.gameToDisplayCoordinatesX(bordureD),
                (float)gameDisplay.gameToDisplayCoordinatesY(bordureBas),
                bordurePaint);
        // PV
        float VieG, VieH,VieD,VieBas, LargeurVie, HauteurVie;
        LargeurVie = largeur - 2*marge;
        HauteurVie = hauteur - marge;
        VieG = bordureG + marge;
        VieD = VieG + LargeurVie*PVRestantPourcentage;
        VieH = bordureBas - HauteurVie;
        VieBas = bordureBas - marge;
        canvas.drawRect(
                (float)gameDisplay.gameToDisplayCoordinatesX(VieG),
                (float)gameDisplay.gameToDisplayCoordinatesY(VieH),
                (float)gameDisplay.gameToDisplayCoordinatesX(VieD),
                (float)gameDisplay.gameToDisplayCoordinatesY(VieBas),
                ViePaint);


    }
}
