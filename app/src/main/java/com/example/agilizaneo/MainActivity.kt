package com.example.agilizaneo

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.DynamicColors
import com.google.android.material.button.MaterialButton
import android.widget.ImageButton

class MainActivity : BaseActivity() {
    // Define a MainActivity, que é a tela inicial da aplicação
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Chamando o onCreate da classe pai para inicializar a Activity corretamente

        DynamicColors.applyToActivitiesIfAvailable(application)
        // Aplica cores dinâmicas do Material You, se o dispositivo suportar

        enableEdgeToEdge()
        // Permite que o layout ocupe toda a tela, incluindo atrás da status bar e navigation bar

        setContentView(R.layout.activity_main)
        // Define o layout XML da MainActivity

        // Ajusta padding para respeitar barras do sistema (status bar, nav bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //==== MENU SUPERIOR ====
        setupMenuSuperior()

        // Botão "Vamos Agilizar"
        val botaoAdd = findViewById<MaterialButton>(R.id.BotaoAdd)
        botaoAdd.setOnClickListener {
            val intent = Intent(this, PageCadastroB::class.java)
            startActivity(intent)
        }

        //==== MENU RODAPÉ ====
        setupMenuRodape()

    }
}
