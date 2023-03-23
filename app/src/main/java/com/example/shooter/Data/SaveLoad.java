package com.example.shooter.Data;
import com.example.shooter.Objet.Lunar;
import com.example.shooter.Objet.XP;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;

public class SaveLoad {
    static Lunar lunar;
    static XP xp;
    public SaveLoad(Lunar lunar, XP xp) {
        SaveLoad.xp = xp;
        SaveLoad.lunar = lunar;
    }
public static void saveXP() {
    try {
        FileOutputStream fileOut = new FileOutputStream("./saveXP.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fileOut);
        Kraken2DStorage storage = new Kraken2DStorage();
        storage.XP = xp.getLastXP();
        oos.writeObject(storage);
        int i = storage.XP;
        System.out.print(i);
        fileOut.close();
        oos.close();
    } catch (Exception e) {
        System.out.println("Problème SaveXP ");
        e.printStackTrace();
    }
}
public static void LoadXP(){
        try{
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(new File("saveXP.txt").toPath()));
            Kraken2DStorage storage = (Kraken2DStorage)ois.readObject();
            xp.setXP(storage.XP);
        }catch(Exception e){
            System.out.println("Problème Load XP");
        }
}
public void saveLunar(){
    try {
        FileOutputStream fileOut = new FileOutputStream("./saveLunar.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fileOut);
        Kraken2DStorage storage = new Kraken2DStorage();
        storage.XP = xp.getLastXP();
        oos.writeObject(storage);
    }
    catch (Exception e){
        System.out.println("Problème SaveXP ");
    }
}
public static void LoadLunar(){
        try{
            ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(new File("saveLunar.dat").toPath()));
            Kraken2DStorage storage = (Kraken2DStorage)ois.readObject();
            lunar.setLunar(storage.Lunar);
        }catch(Exception e){
            System.out.println("Problème Load XP");
            }
    }
}
