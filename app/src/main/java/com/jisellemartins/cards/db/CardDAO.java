package com.jisellemartins.cards.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jisellemartins.cards.models.Card;

import java.util.ArrayList;
import java.util.List;

public class CardDAO {
    private SQLiteDatabase write;
    private SQLiteDatabase read;
    private DBHelper helper;
    private ContentValues contentValues;

    public CardDAO(Context context) {
        helper = new DBHelper(context);
        write = helper.getWritableDatabase();
        read = helper.getReadableDatabase();
    }

    public List<Card> selectAll() {
        List<Card> lstCard = new ArrayList<>();
        String query = "SELECT * FROM " + DBHelper.TABLE_CARDS + ";";
        Cursor cursor = read.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Card card = new Card();
            card.setId(cursor.getInt(cursor.getColumnIndex("id")));
            card.setNumber(cursor.getLong(cursor.getColumnIndex("number")));
            card.setValidity(cursor.getString(cursor.getColumnIndex("validity")));
            card.setCodSecutiry(cursor.getString(cursor.getColumnIndex("codSecutiry")));
            card.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            card.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));

            lstCard.add(card);
        }
        return lstCard;
    }

    public List<Card> selectCard(int id) {
        List<Card> lstCard = new ArrayList<>();
        String query = "SELECT * FROM " + DBHelper.TABLE_CARDS + " WHERE id = " + id + ";";
        Cursor cursor = read.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Card card = new Card();
            card.setId(cursor.getInt(cursor.getColumnIndex("id")));
            card.setNumber(cursor.getLong(cursor.getColumnIndex("number")));
            card.setValidity(cursor.getString(cursor.getColumnIndex("validity")));
            card.setCodSecutiry(cursor.getString(cursor.getColumnIndex("codSecutiry")));
            card.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            card.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));

            lstCard.add(card);
        }
        return lstCard;
    }

    public boolean insert(Card card) {
        contentValues = new ContentValues();
        contentValues.put("number", card.getNumber());
        contentValues.put("validity", card.getValidity());
        contentValues.put("codSecutiry", card.getCodSecutiry());
        contentValues.put("password", card.getPassword());
        contentValues.put("descricao", card.getDescricao());
        try {
            long result = write.insert(DBHelper.TABLE_CARDS, null, contentValues);
            return result != -1;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM " + DBHelper.TABLE_CARDS + " WHERE id = " + id + ";";
        read.execSQL(sql);
        return true;
    }
}
