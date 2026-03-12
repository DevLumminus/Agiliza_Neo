package com.example.agilizaneo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.color.DynamicColors
import com.google.android.material.button.MaterialButton
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        val botaoAdd = findViewById<MaterialButton>(R.id.BotaoAdd)
        botaoAdd.setOnClickListener {
            val intent = Intent(this, PageCadastroB::class.java)
            startActivity(intent)
        }

        // Botão "Calendario"
        val botaoCalendar = findViewById<BottomNavigationView>(R.id.calendar)
        botaoCalendar.setOnClickListener {   // <-- aqui deve ser botaoCalendar
            val intent = Intent(this, PageRegistros::class.java)
            startActivity(intent)
        }
//
//        // Botão "Estatistica"
        val botaoEstatistic = findViewById<BottomNavigationView>(R.id.grafic)
        botaoEstatistic.setOnClickListener {  // <-- aqui deve ser botaoEstatistic
            val intent = Intent(this, PageEstatistica::class.java)
            startActivity(intent)
        }

    }
}
