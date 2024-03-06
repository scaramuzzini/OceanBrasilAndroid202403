package com.oceanbrasil.androidintro

import android.content.Intent
import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvResultado = findViewById<TextView>(R.id.texto)
        val etNome = findViewById<EditText>(R.id.editTextNome)
        val btOK = findViewById<Button>(R.id.button2)

        val novaTelaIntent = Intent(this, ResultadoActivity::class.java)
        btOK.setOnClickListener {
            if (etNome.text.isBlank()) {
                val tg = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
                tg.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
                etNome.error = "Digite um nome válido!"
            } else {
                // tvResultado.text = etNome.text
                novaTelaIntent.putExtra("NOME_DIGITADO", etNome.text.toString())
                startActivity(novaTelaIntent)
            }
        }

        val btAbrirJogo = findViewById<Button>(R.id.btAbrirJogo)
        btAbrirJogo.setOnClickListener {
            // Abir a tela do jogo
            val jogoIntent = Intent(this, JogoDaVelhaActivity::class.java)
            startActivity(jogoIntent)
        }

        val btAbrirMapa = findViewById<Button>(R.id.btAbrirMapa)
        btAbrirMapa.setOnClickListener {
            val mapaIntent = Intent(this, MapsActivity::class.java)
            startActivity(mapaIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        showToast("onStart() foi chamado")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}