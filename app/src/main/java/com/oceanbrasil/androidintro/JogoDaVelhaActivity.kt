package com.oceanbrasil.androidintro

import android.animation.ValueAnimator
import android.media.AudioManager
import android.media.ToneGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.core.animation.addListener

class JogoDaVelhaActivity : AppCompatActivity() {

    private var rodada = 0
    private var vezJogadorX = true
    private val tabuleiro = Array(3) { arrayOfNulls<Button>(3) }
    private lateinit var tvInstrucoes: TextView
    private lateinit var btReset: Button
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo_da_velha)

        //Bind dos elementos
        btReset = findViewById<Button>(R.id.btReset)
        tvInstrucoes = findViewById<TextView>(R.id.instrucoes)

        for (i in 0..2) {
            for (j in 0..2) {
                val buttonID = "bt${i * 3 + j + 1}"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                tabuleiro[i][j] = findViewById(resID)
                tabuleiro[i][j]?.setOnClickListener { v -> toqueBotao(v) }
            }
        }

        btReset.setOnClickListener {
            reiniciarJogo()
        }
    }

    private fun toqueBotao(bt: View) {
        if ((bt as Button).text.toString() != "") {
            return
        }
        if (vezJogadorX) {
            bt.text = "X"
        } else {
            bt.text = "O"
        }
        rodada++

        if (verificaVitoria()) {
            vitoriaDoJogador()
        } else if (rodada == 9) {
            empate()
        } else {
            vezJogadorX = !vezJogadorX
            atualizaStatus()
        }
    }

    private fun atualizaStatus() {
        tvInstrucoes.text = if (vezJogadorX) "Vez do jogador 1" else "Vez do jogador 2"
    }

    private fun empate() {
        tvInstrucoes.text = "Empate!"
    }

    private fun vitoriaDoJogador() {
        if (vezJogadorX) {
            tvInstrucoes.text = "Vitória do Jogador 1"
        } else {
            tvInstrucoes.text = "Vitória do Jogador 2"
        }
        val tg = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        tg.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
        animateTextView(tvInstrucoes)
    }

    private fun reiniciarJogo() {
        rodada = 0
        vezJogadorX = true
        atualizaStatus()
        for (i in 0..2) {
            for (j in 0..2) {
                tabuleiro[i][j]?.text = ""
            }
        }
    }

    private fun verificaVitoria(): Boolean {
        val tb = Array(3) { arrayOfNulls<String>(3) }

        for (i in 0..2) {
            for (j in 0..2) {
                tb[i][j] = tabuleiro[i][j]?.text.toString()
            }
        }

        for (i in 0..2) {
            // Linhas
            if (tb[i][0] == tb[i][1] && tb[i][0] == tb[i][2] && tb[i][0] != "") {
                return true
            }
            // Colunas
            if (tb[0][i] == tb[1][i] && tb[0][i] == tb[2][i] && tb[0][i] != "") {
                return true
            }
        }

        // Diagonal principal
        if (tb[0][0] == tb[1][1] && tb[0][0] == tb[2][2] && tb[0][0] != "") {
            return true
        }
        // Diagional secundária
        if (tb[0][2] == tb[1][1] && tb[0][2] == tb[2][0] && tb[0][2] != "") {
            return true
        }

        return false
    }

    fun animateTextView(textView: TextView) {
        val expandedSize = 56f // Expanded text size in SP
        val retractedSize = 36f // Retracted text size in SP

        // Animation from retracted size to expanded size
        val animatorExpand = ValueAnimator.ofFloat(retractedSize, expandedSize).apply {
            duration = 2000 // Duration in milliseconds
            interpolator = AccelerateDecelerateInterpolator() // Smooth animation
            addUpdateListener {
                val animatedValue = it.animatedValue as Float
                textView.textSize = animatedValue
            }
        }

        // Animation from expanded size to retracted size
        val animatorRetract = ValueAnimator.ofFloat(expandedSize, retractedSize).apply {
            duration = 2000 // Duration in milliseconds
            interpolator = AccelerateDecelerateInterpolator() // Smooth animation
            addUpdateListener {
                val animatedValue = it.animatedValue as Float
                textView.textSize = animatedValue
            }
        }

        // Listener to start retract animation once expand animation finishes
        animatorExpand.addListener(onEnd = {
            animatorRetract.start()
            reiniciarJogo()
        })

        animatorExpand.start() // Start the expand animation
    }
}