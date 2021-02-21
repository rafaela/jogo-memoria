package com.example.jogomemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.jogomemoria.database.Database;
import com.example.jogomemoria.modelo.Ranking;

public class RankingActivity extends AppCompatActivity {

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        ListView listView = (ListView) findViewById(R.id.lista);

        db = new Database(this);
        String [] itens = new String[db.all().size()];
        int i = 0;
        for (Ranking p : db.all())
            itens[i++] = String.format("Nome: %s | Tempo: %s",p.getNome(),p.getTempo());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itens);

        listView.setAdapter(adapter);

    }

    public void reiniciar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        if (intent != null)
            startActivity(intent);
    }

    public void listaPontuacao(View view){
        ListView listView = (ListView) findViewById(R.id.lista);
        String [] itens = new String[db.all().size()];
        Button botaoHistorico = (Button) findViewById(R.id.botaoHistorico);
        int i = 0;
        for (Ranking p : db.all()) {
                itens[i++] = String.format("Nome: %s | Tempo: %s",p.getNome(),p.getTempo());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,itens);
        listView.setAdapter(adapter);
    }

    public void limparHistorico(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Confirmação");
        builder.setMessage("Deseja apagar o Ranking?");
        builder.setPositiveButton("Limpar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.clear();
                        ListView listView = (ListView) findViewById(R.id.lista);
                        listView.setAdapter(null);
                    }
                });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}