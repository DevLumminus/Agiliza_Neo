package com.example.agilizaneo
// Define o pacote da aplicação. Todos os arquivos Kotlin pertencem a este pacote.

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.DynamicColors
// Importações necessárias:
// - Intent: para navegar entre Activities
// - Bundle: para receber o estado salvo da Activity
// - ImageButton e AppCompatButton: referências de botões do layout
// - PopupMenu: menu suspenso para a toolbar
// - enableEdgeToEdge: permite que a interface ocupe toda a tela
// - AppCompatActivity: base para Activities compatíveis com versões antigas do Android
// - ViewCompat e WindowInsetsCompat: para lidar com padding e barras do sistema
// - DynamicColors: aplica cores do Material You (Android 12+)

class MainActivity : AppCompatActivity() {
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

        // 🔹 Botão do menu de 3 pontinhos na toolbar
        val btnMenu = findViewById<ImageButton>(R.id.btnMenu)
        btnMenu.setOnClickListener {
            // Cria um menu suspenso ancorado no botão
            val popupMenu = PopupMenu(this, btnMenu)
            // Infla o menu a partir do XML (res/menu/toolbar_menu.xml)
            popupMenu.menuInflater.inflate(R.menu.toolbar_menu, popupMenu.menu)

            // Define o comportamento ao clicar nos itens do menu
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.MenuAjuda -> true   // Pode adicionar ação de "Ajuda" aqui
                    R.id.MenuConfig -> true  // Pode adicionar ação de "Configurações" aqui
                    else -> false            // Outros itens não tratados
                }
            }

            // Exibe o menu suspenso
            popupMenu.show()
        }

        // Botão "Vamos Agilizar"
        val botaoAdd = findViewById<AppCompatButton>(R.id.BotaoAdd)
        botaoAdd.setOnClickListener {
            val intent = Intent(this, PageCadastroB::class.java)
            startActivity(intent)
        }

        // Botão "Calendario"
//        val botaoCalendar = findViewById<FrameLayout>(R.id.btCalendar)
//        botaoCalendar.setOnClickListener {   // <-- aqui deve ser botaoCalendar
//            val intent = Intent(this, PageRegistros::class.java)
//            startActivity(intent)
//        }
//
//        // Botão "Estatistica"
//        val botaoEstatistic = findViewById<FrameLayout>(R.id.btEstatistica)
//        botaoEstatistic.setOnClickListener {  // <-- aqui deve ser botaoEstatistic
//            val intent = Intent(this, PageEstatistica::class.java)
//            startActivity(intent)
//        }

    }
}
