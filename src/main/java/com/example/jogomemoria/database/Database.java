package com.example.jogomemoria.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jogomemoria.modelo.Ranking;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String DATABASE_NAME = "bdjogo";
    private static final int DATABASE_ACCESS = 0;

    private static final String SQL_STRUCT = "CREATE TABLE IF NOT EXISTS ranking (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " nome TEXT NOT NULL, pontucao INTERGER NOT NULL, posicao INTERGER NOT NULL, tempo INTERGER NOT NULL)";

    private static final String SQL_INSERT = "INSERT INTO ranking (nome, pontucao, posicao, tempo)" +
            "VALUES ('%s', '%d', '%d', '%d')";

    private static final String SQL_SELECT_ALL = "SELECT * FROM ranking ORDER BY posicao";

    private static final String SQL_CLEAR = "DROP TABLE IF EXISTS ranking";

    private SQLiteDatabase database;
    private Cursor cursor;
    private int indexID, indexNome, indexPontuacao, indexPosicao, indexTempo;

    public Database(Context context) {
        database = context.openOrCreateDatabase(DATABASE_NAME, DATABASE_ACCESS, null);
        database.execSQL(SQL_STRUCT);
    }

    public void clear(){
        database.execSQL(SQL_CLEAR);
    }

    public void close(){
        database.close();
    }

    public void insert(Ranking ranking){
        String query = String.format(SQL_INSERT, ranking.getNome(), ranking.getPontos(), ranking.getPosicao());
        database.execSQL(query);
    }

    public List<Ranking> all(){
        List<Ranking> lista = new ArrayList<>();
        Ranking ranking;
        cursor = database.rawQuery(SQL_SELECT_ALL, null);
        if(cursor.moveToFirst()){
            indexID = cursor.getColumnIndex("id");
            indexNome = cursor.getColumnIndex("nome");
            indexPontuacao = cursor.getColumnIndex("pontuacao");
            indexPosicao = cursor.getColumnIndex("posicao");
            indexTempo = cursor.getColumnIndex("tempo");
            do{
                ranking = new Ranking(cursor.getString(indexNome), cursor.getInt(indexPontuacao), cursor.getInt(indexPosicao), cursor.getInt(indexTempo));
                lista.add(ranking);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}
