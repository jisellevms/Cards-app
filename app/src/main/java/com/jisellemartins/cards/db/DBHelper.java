package com.jisellemartins.cards.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int VERSION_DATA_BASE = 1;
    public static final String NAME_DATA_BASE = "database.db";

    public static final String TABLE_CARDS = " cards ";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS ";


    public DBHelper(@Nullable Context context) {
        super(context, NAME_DATA_BASE, null, VERSION_DATA_BASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String cardsTable = "CREATE TABLE IF NOT EXISTS" + TABLE_CARDS +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " number LONG," +
                "validity TEXT," +
                "codSecutiry TEXT," +
                "password TEXT," +
                "descricao TEXT," +
                "key TEXT );";

        db.execSQL(cardsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE + TABLE_CARDS);
        onCreate(db);
    }
}
