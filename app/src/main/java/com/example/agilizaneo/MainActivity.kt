package com.example.agilizaneo

// Importações necessárias
import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.DynamicColors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Aplica cores dinâmicas do Material You, se disponível
        DynamicColors.applyToActivitiesIfAvailable(application)

        // Ativa layout edge-to-edge (conteúdo sob a barra de status e navegação)
        enableEdgeToEdge()

        // Define o layout principal da activity
        setContentView(R.layout.activity_main)

        // Ajusta o padding do layout principal (R.id.main) para respeitar as barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets // retorna os insets sem modificações
        }

        // 🔹 Configurando o botão do menu de 3 pontinhos
        // Recupera a referência do ImageButton no layout
        val btnMenu = findViewById<ImageButton>(R.id.btnMenu)

        // Define o clique do botão
        btnMenu.setOnClickListener {
            // Cria um PopupMenu ancorado no próprio botão
            val popupMenu = PopupMenu(this, btnMenu)

            // Infla o menu a partir do XML (res/menu/menu_overflow.xml)
            popupMenu.menuInflater.inflate(R.menu.toolbar_menu, popupMenu.menu)

            // Define o comportamento quando um item do menu é clicado
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.MenuAjuda -> {
                        // Aqui você coloca a ação que deve acontecer ao clicar em "Ajuda"
                        true // true indica que o clique foi tratado
                    }
                    R.id.MenuConfig -> {
                        // Aqui você coloca a ação que deve acontecer ao clicar em "Configurações"
                        true
                    }
                    else -> false // para outros itens não tratados
                }
            }

            // Exibe o menu suspenso
            popupMenu.show()
        }
    }
}
