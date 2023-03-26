package com.example.shooter.GameData;

import android.content.Context;
import android.content.SharedPreferences;

public class GameDatas {
    private static SharedPreferences sharedPreferences;
    private static int LunarJ = 0;
    public GameDatas(Context context) {
        sharedPreferences = context.getSharedPreferences("SaveKraken2D", Context.MODE_PRIVATE);
        LunarJ = getLunar();
    }

    public int getLunar() {
        try {
            return sharedPreferences.getInt("Lunar", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getXP() {
        try {
            return sharedPreferences.getInt("XP", 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int getPuissanceBalle() {
        try {
            return sharedPreferences.getInt("Puissance_Balle", 1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;
    }
    public void setPuissanceBalle(int puissanceBalle) {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            if(puissanceBalle == 0){
                editor.putInt("Puissance_Balle", 1);
                editor.apply();
                System.out.println(1 + " Puissance_Balle");
            }else {
                editor.putInt("Puissance_Balle", puissanceBalle);
                editor.apply();
                System.out.println(puissanceBalle + " Puissance_Balle");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void setLunar(int lunarPartie) {
        try {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            int lunar = LunarJ + lunarPartie;
            editor.putInt("Lunar", lunar);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
