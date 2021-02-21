package com.example.jogomemoria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jogomemoria.modelo.Ranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Integer> listaNumeros;
    private int posicaoAtual = 1;
    private int[] colorsButtons;
    private Chronometer timer;
    private int erros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gerarSequencia();
        colorsButtons = new int[]{
                getResources().getColor(R.color.colorB1),
                getResources().getColor(R.color.colorB2),
                getResources().getColor(R.color.colorB3),
                getResources().getColor(R.color.colorB4),
                getResources().getColor(R.color.colorB5),
                getResources().getColor(R.color.colorB6)
        };

        timer = (Chronometer) findViewById(R.id.timer);
        timer.start();
        erros = 0;

    }

    @Override
    protected void onStart() {
        super.onStart();
        shuffleSeq();
        Log.d("SEQUENCIA", listaNumeros.toString());
    }

    private void gerarSequencia(){
        listaNumeros = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            listaNumeros.add(i);
        }
    }

    private void shuffleSeq(){
        Collections.shuffle(listaNumeros);
        Log.d("SEQUENCIA", listaNumeros.toString());
    }

    public void analisarJogada(View view){

        erros++;
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.tela);
        Button button = (Button) view;
        ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar);
        int valorBtn = Integer.parseInt(button.getText().toString());

        if(valorBtn == listaNumeros.get(posicaoAtual - 1)){
            button.setVisibility(View.INVISIBLE);
            layout.setBackgroundColor(colorsButtons[valorBtn - 1]);
            bar.setProgress((102/6)*posicaoAtual);
            posicaoAtual++;
        }
        else{
            layout.setBackgroundColor(Color.WHITE);
            showButtons();
            bar.setProgress(0);
            posicaoAtual = 1;

        }

        if(posicaoAtual == 7){
            timer.stop();
            Intent intent = new Intent(this, Parabens.class);
            if (intent != null){
                intent.putExtra("Tempo", timer.getText());
                intent.putExtra("Pontos", String.format("%d",erros));
                startActivity(intent);
            }
        }
    }

    public void reiniciar(View view){
        timer.start();
        ((ConstraintLayout) findViewById(R.id.tela)).setBackgroundColor(Color.WHITE);
        showButtons();
        ((ProgressBar) findViewById(R.id.progressBar)).setProgress(0);
        posicaoAtual = 1;
        shuffleSeq();
    }


    public void showButtons(){
        ((Button)findViewById(R.id.button1)).setVisibility(View.VISIBLE);
        ((Button)findViewById(R.id.button2)).setVisibility(View.VISIBLE);
        ((Button)findViewById(R.id.button3)).setVisibility(View.VISIBLE);
        ((Button)findViewById(R.id.button4)).setVisibility(View.VISIBLE);
        ((Button)findViewById(R.id.button5)).setVisibility(View.VISIBLE);
        ((Button)findViewById(R.id.button6)).setVisibility(View.VISIBLE);
    }

}