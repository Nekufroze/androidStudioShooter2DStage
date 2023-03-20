package com.example.shooter.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Kraken2Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "game_data.db";
    private static final int DATABASE_VERSION = 1;

    private static final String XP_TABLE_NAME = "xp_table";
    private static final String LUNAR_TABLE_NAME = "lunar_table";

    private static final String XP_COLUMN_ID = "id";
    private static final String XP_COLUMN_VALUE = "xp_value";

    private static final String LUNAR_COLUMN_ID = "id";
    private static final String LUNAR_COLUMN_VALUE = "lunar_value";

    public Kraken2Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String xpTableCreateQuery = "CREATE TABLE " + XP_TABLE_NAME + " (" +
                XP_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                XP_COLUMN_VALUE + " INTEGER);";

        String lunarTableCreateQuery = "CREATE TABLE " + LUNAR_TABLE_NAME + " (" +
                LUNAR_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LUNAR_COLUMN_VALUE + " INTEGER);";

        db.execSQL(xpTableCreateQuery);
        db.execSQL(lunarTableCreateQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + XP_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LUNAR_TABLE_NAME);
        onCreate(db);
    }

    public void saveXp(int xp) {
        SQLiteDatabase db = getWritableDatabase();
        String insertQuery = "INSERT INTO " + XP_TABLE_NAME + " (" +
                XP_COLUMN_VALUE + ") VALUES (" + xp + ");";
        db.execSQL(insertQuery);
        db.close();
    }

    public void saveLunar(int lunar) {
        SQLiteDatabase db = getWritableDatabase();
        String insertQuery = "INSERT INTO " + LUNAR_TABLE_NAME + " (" +
                LUNAR_COLUMN_VALUE + ") VALUES (" + lunar + ");";
        db.execSQL(insertQuery);
        db.close();
    }
    public int getLastXp() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + XP_COLUMN_VALUE + " FROM " + XP_TABLE_NAME +
                " ORDER BY " + XP_COLUMN_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        int lastXp = -1;
        if (cursor.moveToFirst()) {
            lastXp = cursor.getInt(cursor.getColumnIndex(XP_COLUMN_VALUE));
        }
        cursor.close();
        db.close();
        return lastXp;
    }

    public int getLastLunar() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT " + LUNAR_COLUMN_VALUE + " FROM " + LUNAR_TABLE_NAME +
                " ORDER BY " + LUNAR_COLUMN_ID + " DESC LIMIT 1";
        Cursor cursor = db.rawQuery(query, null);
        int lastLunar = -1;
        if (cursor.moveToFirst()) {
            lastLunar = cursor.getInt(cursor.getColumnIndex(LUNAR_COLUMN_VALUE));
        }
        cursor.close();
        db.close();
        return lastLunar;
    }

}
