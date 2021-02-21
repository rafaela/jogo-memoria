package com.example.jogomemoria.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jogomemoria.modelo.Ranking;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String DATABASE_NAME = "bd1";
    private static final int DATABASE_ACCESS = 0;

    private static final String SQL_STRUCT = "CREATE TABLE IF NOT EXISTS ranking (id_ INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL, tempo TEXT, pontos INTEGER);";
    private static final String SQL_INSERT = "INSERT INTO ranking(nome,tempo,ponto) VALUES ('%s', '%s', '%d');";
    private static final String SQL_SELECT_ALL = "SELECT * FROM ranking ORDER BY nome;";
    private static final String SQL_CLEAR = "DROP TABLE IF EXISTS ranking;";

    private SQLiteDatabase database;
    private Cursor cursor;
    private int indexID, indexNome, indexTempo, indexPontos;

    public Database(Context context) {
        database = context.openOrCreateDatabase(DATABASE_NAME,DATABASE_ACCESS,null);
        database.execSQL(SQL_STRUCT);
    }

    public void clear(){
        database.execSQL(SQL_CLEAR);
    }

    public void close(){
        database.close();
    }

    public void insert(Ranking ranking){
        String query = String.format(SQL_INSERT,ranking.getNome(),ranking.getTempo(),ranking.getPontos());
        database.execSQL(query);
    }

    public List<Ranking> all(){
        List<Ranking> players = new ArrayList<>();
        Ranking player;

        cursor = database.rawQuery(SQL_SELECT_ALL,null);

        if(cursor.moveToFirst()){
            indexID = cursor.getColumnIndex("id_");
            indexNome = cursor.getColumnIndex("nome");
            indexPontos = cursor.getColumnIndex("erros");
            indexTempo = cursor.getColumnIndex("tempo");

            do{
                player = new Ranking();
                player.setNome(cursor.getString(indexNome));
                player.setPontos(cursor.getInt(indexPontos));
                player.setTempo(cursor.getString(indexTempo));

                players.add(player);

            } while (cursor.moveToNext());
        }

        cursor.close();
        return players;
    }
}
