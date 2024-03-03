package com.oceanbrasil.androidintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvResultado = findViewById<TextView>(R.id.texto)
        val etNome = findViewById<EditText>(R.id.editTextNome)
        val btOK = findViewById<Button>(R.id.button2)
        btOK.setOnClickListener {
            tvResultado.text = etNome.text
        }
    }
}