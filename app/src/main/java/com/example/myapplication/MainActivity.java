package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Integer> listaNumeros;
    private int posicaoAtual = 1;
    private int[] colorsButtons;
    private boolean firstButtonClicked = false;
    private String player_name = "";

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
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(this, Parabens.class);
            if (intent != null){
                startActivity(intent);
            }
            reiniciar(view);
        }

    }

    public void reiniciar(View view){
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
