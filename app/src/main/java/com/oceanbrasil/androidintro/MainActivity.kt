package com.oceanbrasil.androidintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvTexto = findViewById<TextView>(R.id.texto)


        val botao = findViewById<Button>(R.id.button2)
        botao.setText("Ola!")
        botao.setOnClickListener {
            tvTexto.setText("outra coisa")
            Toast.makeText(this,"clicou...",Toast.LENGTH_SHORT).show()
        }
    }
}