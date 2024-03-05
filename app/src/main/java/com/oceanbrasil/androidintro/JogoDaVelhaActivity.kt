package com.oceanbrasil.androidintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class JogoDaVelhaActivity : AppCompatActivity() {

    private var rodada = 0
    private var vezJogadorX = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo_da_velha)

        //Bind dos elementos
        val tvInstrucoes = findViewById<TextView>(R.id.instrucoes)
        val btReset = findViewById<Button>(R.id.btReset)

        val bt1 = findViewById<Button>(R.id.bt1)
        // continua ...
        val bt9 = findViewById<Button>(R.id.bt9)

        bt1.setOnClickListener {
            if (bt1.text.toString() == "") {
                if (vezJogadorX) {
                    bt1.text = "X"
                    vezJogadorX = false
                } else {
                    bt1.text = "O"
                    vezJogadorX = true
                }
            }
        }
    }
}