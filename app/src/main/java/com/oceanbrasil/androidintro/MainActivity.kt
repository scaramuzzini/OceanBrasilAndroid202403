package com.oceanbrasil.androidintro

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvResultado = findViewById<TextView>(R.id.texto)
        val etNome = findViewById<EditText>(R.id.editTextNome)
        val btOK = findViewById<Button>(R.id.button2)
        btOK.setOnClickListener {
            if (etNome.text.isBlank()) {
                val tg = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
                tg.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
                etNome.error = "Digite um nome válido!"
            } else {
                tvResultado.text = etNome.text
            }
        }
    }
}