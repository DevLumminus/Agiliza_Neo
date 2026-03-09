package com.example.agilizaneo

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import java.io.File
import java.util.Calendar
import android.text.InputType
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.button.MaterialButton
import android.widget.PopupMenu

class PageCadastroB : AppCompatActivity() {

    // URI onde a imagem (câmera ou galeria) será armazenada
    //private lateinit var uriImagem: Uri
    private var uriImagem: Uri? = null

    // Campo de imagem clicável
    private lateinit var imgCampoFoto: ImageView

    // Launcher para tirar foto com a câmera
    /*private val tirarFoto =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { sucesso ->
            if (sucesso) {
                imgCampoFoto.setImageURI(uriImagem)
            }
        }*/
    private val tirarFoto =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { sucesso ->
            if (sucesso && uriImagem != null) {
                imgCampoFoto.setImageURI(uriImagem)
                imgCampoFoto.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }

    // Launcher para selecionar imagem da galeria
    /*private val selecionarGaleria =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                uriImagem = it
                imgCampoFoto.setImageURI(it)
            }
        }*/
    private val selecionarGaleria =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                uriImagem = it
                imgCampoFoto.setImageURI(it)
                imgCampoFoto.scaleType = ImageView.ScaleType.CENTER_CROP
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_page_cadastrob)

        // Ajuste de padding para status/navigation bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ===== CAMPO DE DATA =====
        fun configurarCampo(
            view: View,
            titulo: String,
            hint: String,
            tipo: String
        ) {

            val txt = view.findViewById<TextView>(R.id.txtTitulo)
            val til = view.findViewById<TextInputLayout>(R.id.tilCampo)
            val edt = view.findViewById<TextInputEditText>(R.id.edtCampo)

            txt.text = titulo
            til.hint = hint

            when (tipo) {

                "number" -> {
                    edt.inputType = InputType.TYPE_CLASS_NUMBER
                }

                "text" -> {
                    edt.inputType = InputType.TYPE_CLASS_TEXT
                }

                "date" -> {

                    edt.inputType = InputType.TYPE_NULL
                    edt.isFocusable = false
                    edt.isClickable = true

                    edt.setOnClickListener {

                        val calendario = Calendar.getInstance()

                        DatePickerDialog(
                            view.context,
                            { _, year, month, day ->

                                val data = String.format(
                                    "%02d/%02d/%04d",
                                    day,
                                    month + 1,
                                    year
                                )

                                edt.setText(data)
                            },
                            calendario.get(Calendar.YEAR),
                            calendario.get(Calendar.MONTH),
                            calendario.get(Calendar.DAY_OF_MONTH)

                        ).show()
                    }
                }
            }
        }

        configurarCampo(
            findViewById(R.id.campoEnergiaAtiva),
            "Energia Ativa",
            "Ativa",
            "number"
        )

        configurarCampo(
            findViewById(R.id.campoEnergiaInjetada),
            "Energia Reativa",
            "Reativa",
            "number"
        )

        configurarCampo(
            findViewById(R.id.campoDatadeconta),
            "Número de Dias",
            "Selecione a data",
            "date"
        )

        // ===== CAMPO DE IMAGEM =====
        //imgCampoFoto = findViewById(R.id.insertFoto)

        // val alturaOriginal = resources.getDimensionPixelSize(R.dimen.campo_imagem_altura)

//        imgCampoFoto.setOnClickListener {
//            mostrarOpcoesImagem()
//        }

        // ===== MENU SUPERIOR =====
        val btnMenu = findViewById<ImageButton>(R.id.btnMenu)
        btnMenu.setOnClickListener {
            val popup = PopupMenu(this, btnMenu)
            popup.menuInflater.inflate(R.menu.toolbar_menu, popup.menu)
            popup.show()
        }

        // ===== NAVEGAÇÃO =====
        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // ===== Botão de Submit =====
        val btnSubmit = findViewById<MaterialButton>(R.id.btnSubmit)

        btnSubmit.setOnClickListener {

            // Aqui você salva os dados

            Snackbar.make(it, "Valores gravados com sucesso", Snackbar.LENGTH_LONG)
                .show()

        }

        //MENU RODAPÉ

        //AÇÃO DO BOTÃO ADD

        val botaoAdd = findViewById<FrameLayout>(R.id.btAdd)
        botaoAdd.setOnClickListener {
            // Cria uma Intent para abrir a Page1CadastroActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // startActivity inicia a nova Activity sem fechar a MainActivity
        }

        //AÇÃO DO BOTÃO CALENDARIO

        // Botão "Calendario"
        val botaoCalendar = findViewById<FrameLayout>(R.id.btCalendar)
        botaoCalendar.setOnClickListener {   // <-- aqui deve ser botaoCalendar
            val intent = Intent(this, PageRegistros::class.java)
            startActivity(intent)
        }

        //AÇÃO DO BOTÃO ESTATISTICA

        // Botão "Estatistica"
        val botaoEstatistic = findViewById<FrameLayout>(R.id.btEstatistica)
        botaoEstatistic.setOnClickListener {  // <-- aqui deve ser botaoEstatistic
            val intent = Intent(this, PageEstatistica::class.java)
            startActivity(intent)
        }
    }

    // Cria uma URI temporária para a câmera salvar a imagem
//    private fun criarUriImagem(): Uri {
//        val arquivo = File.createTempFile("foto_", ".jpg", cacheDir)
//        return FileProvider.getUriForFile(this, "$packageName.provider", arquivo)
//    }
//
//    // Abre a câmera
//    private fun abrirCamera() {
//        uriImagem = criarUriImagem()
//        tirarFoto.launch(uriImagem!!)
//    }
//
//    // Abre a galeria
//    private fun abrirGaleria() {
//        selecionarGaleria.launch("image/*")
//    }
//
//    // Mostra o diálogo de escolha
//    private fun mostrarOpcoesImagem() {
//        val opcoes = arrayOf("Câmera", "Galeria")
//
//        AlertDialog.Builder(this)
//            .setTitle("Selecionar imagem")
//            .setItems(opcoes) { _, i ->
//                if (i == 0) abrirCamera() else abrirGaleria()
//            }
//            .show()
//    }
}