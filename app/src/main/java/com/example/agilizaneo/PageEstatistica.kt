package com.example.agilizaneo

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PageEstatistica : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ativa layout edge-to-edge (conteúdo sob status bar e navigation bar)
        enableEdgeToEdge()

        // Define o layout da activity
        setContentView(R.layout.activity_page_estatistica)

        // Ajusta padding do layout principal para respeitar barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 🔹 Botão de voltar (logo ou ícone) → fecha PageEstatistica e retorna à MainActivity
        val botaoLogo = findViewById<ImageButton>(R.id.btnBack)
        botaoLogo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // startActivity inicia a MainActivity sem fechar a PageEstatistica
        }

        // 🔹 Botão "Add" da bottom bar → também retorna à MainActivity
        val botaoAdd = findViewById<FrameLayout>(R.id.btAdd)
        botaoAdd.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // 🔹 Botão "Calendario" da bottom bar → abre PageRegistros
        val botaoCalendar = findViewById<FrameLayout>(R.id.btCalendar)
        botaoCalendar.setOnClickListener {
            val intent = Intent(this, PageRegistros::class.java)
            startActivity(intent)
        }

        // 🔹 Botão "Estatistica" da bottom bar → abre novamente PageEstatistica
        val botaoEstatistic = findViewById<FrameLayout>(R.id.btEstatistica)
        botaoEstatistic.setOnClickListener {
            val intent = Intent(this, PageEstatistica::class.java)
            startActivity(intent)
        }
    }
}
