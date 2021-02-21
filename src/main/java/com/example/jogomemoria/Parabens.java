package com.example.jogomemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jogomemoria.modelo.Ranking;

public class Parabens extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parabens);
        ((TextView)findViewById(R.id.tempo)).setText("Tempo: " + getIntent().getStringExtra("Tempo"));
        ((TextView)findViewById(R.id.erros)).setText("Tentativas: " + getIntent().getStringExtra("Pontos"));
    }

    public void reiniciar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        if (intent != null)
            startActivity(intent);
    }

    public void salvarPlacar(View view){
        Intent intent = new Intent(this, PontuacaoActivity.class);
        if (intent != null) {
            intent.putExtra("Tempo", getIntent().getStringExtra("Tempo"));
            intent.putExtra("Pontos", getIntent().getStringExtra("Pontos"));
            startActivity(intent);
        }
    }

    public void visualizaHistorico(View view){
        Intent intent = new Intent(this, RankingActivity.class);
        if (intent != null) {
            startActivity(intent);
        }
    }
}