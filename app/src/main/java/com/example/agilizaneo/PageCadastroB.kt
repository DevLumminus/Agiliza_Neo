package com.example.agilizaneo
// Define o pacote da aplicação. Todos os arquivos Kotlin pertencem a um pacote.

import android.app.DatePickerDialog
import android.content.Intent
import java.util.Calendar
import android.os.Bundle
import android.widget.ImageButton
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.PopupMenu
import com.google.android.material.textfield.TextInputEditText


// Importa classes necessárias:
// - Bundle: para receber o estado salvo da Activity
// - ImageButton e FrameLayout: referências de botões e containers do layout
// - enableEdgeToEdge: para permitir que a interface use toda a tela (sob status bar e nav bar)
// - AppCompatActivity: classe base para Activities compatíveis com versões antigas do Android
// - ViewCompat e WindowInsetsCompat: para lidar com bordas, padding e barras do sistema

class PageCadastroB : AppCompatActivity() {
    // Define a Activity Page1Cadastro, que herda comportamentos de AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Chamando o onCreate da classe pai para inicializar a Activity corretamente

        enableEdgeToEdge()
        // Permite que o layout ocupe toda a tela, incluindo atrás da status bar e navigation bar

        setContentView(R.layout.activity_page_cadastrob)
        // Define o layout XML que será exibido nesta Activity

        // Ajusta padding para respeitar barras do sistema (status bar, nav bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Declara a contante para receber o campo
        val edtNDias = findViewById<TextInputEditText>(R.id.edtNDias)

        // Define um listener de clique para o campo edtNDias
        // Ou seja: quando o usuário tocar no campo, esse bloco será executado
        edtNDias.setOnClickListener {

            // Cria uma instância do calendário com a data atual do sistema
            // Isso será usado como data inicial do DatePicker
            val calendario = Calendar.getInstance()

            // Cria o DatePickerDialog (janela de seleção de data)
            val datePicker = DatePickerDialog(
                this, // Contexto (geralmente a Activity)

                // Listener chamado quando o usuário CONFIRMA a data no calendário
                { _, year, month, dayOfMonth ->

                    // Formata a data escolhida para o padrão brasileiro dd/MM/aaaa
                    // %02d -> dois dígitos com zero à esquerda
                    // %04d -> quatro dígitos para o ano
                    val dataFormatada = String.format(
                        "%02d/%02d/%04d",
                        dayOfMonth,
                        month + 1, // month começa em 0 (janeiro = 0), por isso somamos 1
                        year
                    )

                    // Define a data formatada no campo de texto
                    edtNDias.setText(dataFormatada)
                },

                // Ano inicial exibido no calendário
                calendario.get(Calendar.YEAR),

                // Mês inicial exibido no calendário
                // (também começa em 0)
                calendario.get(Calendar.MONTH),

                // Dia inicial exibido no calendário
                calendario.get(Calendar.DAY_OF_MONTH)
            )

            // Exibe o DatePickerDialog na tela
            datePicker.show()
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

        // 🔹 Botão de voltar → fecha Page1 e retorna à MainActivity
        val botaoLogo = findViewById<ImageButton>(R.id.btnBack)
        botaoLogo.setOnClickListener {
            // Cria uma Intent para abrir a Page1CadastroActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // startActivity inicia a nova Activity sem fechar a MainActivity
        }


        val botaoAdd = findViewById<FrameLayout>(R.id.btAdd)
        botaoAdd.setOnClickListener {
            // Cria uma Intent para abrir a Page1CadastroActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // startActivity inicia a nova Activity sem fechar a MainActivity
        }

        // Botão "Calendario"
        val botaoCalendar = findViewById<FrameLayout>(R.id.btCalendar)
        botaoCalendar.setOnClickListener {   // <-- aqui deve ser botaoCalendar
            val intent = Intent(this, PageRegistros::class.java)
            startActivity(intent)
        }

        // Botão "Estatistica"
        val botaoEstatistic = findViewById<FrameLayout>(R.id.btEstatistica)
        botaoEstatistic.setOnClickListener {  // <-- aqui deve ser botaoEstatistic
            val intent = Intent(this, PageEstatistica::class.java)
            startActivity(intent)
        }
    }
}
