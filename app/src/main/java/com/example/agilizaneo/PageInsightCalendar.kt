package com.example.agilizaneo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class PageInsightCalendar : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ativa layout edge-to-edge (conteúdo sob status bar e navigation bar)
        enableEdgeToEdge()

        // Define o layout da activity
        setContentView(R.layout.activity_page_insight_calendar)

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

        //==== MENU SUPERIOR ====
        setupMenuSuperior()

        //==== MENU RODAPÉ ====
        setupMenuRodape()

    }
}
