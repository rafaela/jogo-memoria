package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Parabens extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parabens);

        View view = findViewById(R.id.tela);
    }

    public void reiniciar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        if (intent != null)
            startActivity(intent);
    }
}