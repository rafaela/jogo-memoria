package com.example.jogomemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.jogomemoria.database.Database;
import com.example.jogomemoria.modelo.Ranking;
import com.google.android.material.textfield.TextInputEditText;

public class PontuacaoActivity extends AppCompatActivity {

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this, "ranking.toString()", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontuacao);

        db = new Database(this);
    }

    public void salvar(View view){
        String tempo = getIntent().getStringExtra("Tempo");
        int pontos = Integer.parseInt(getIntent().getStringExtra("Pontos"));
        TextInputEditText nome = (TextInputEditText)findViewById(R.id.nome);
        Ranking ranking = new Ranking();

        ranking.setTempo(tempo);
        ranking.setPontos(pontos);
        ranking.setNome(nome.getText().toString());
        Toast.makeText(this, ranking.toString(), Toast.LENGTH_LONG).show();
        db.insert(ranking);
        db.close();

        Intent intent = new Intent(this, Parabens.class);
        if (intent != null)
            startActivity(intent);
    }
}