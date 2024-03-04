package com.oceanbrasil.androidintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)
        //Bind do textview encontrado no layout
        val txtResultado = findViewById<TextView>(R.id.textViewResultado)
        val btVoltar = findViewById<Button>(R.id.buttonVoltar)
        //Recuperando o parametro enviado
        val nomeDigitado = getIntent().getStringExtra("NOME_DIGITADO")
        if (nomeDigitado != null) {
            Log.d("resultado", nomeDigitado)
        } else {
            Log.d("resultado", "Nao recebi o parametro NOME_DIGITADO")
        }
        txtResultado.text = nomeDigitado

        btVoltar.setOnClickListener {
            finish()
        }

    }
}