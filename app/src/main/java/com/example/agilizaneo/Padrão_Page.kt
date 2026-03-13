package com.example.agilizaneo

import android.content.Intent
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.google.android.material.bottomnavigation.BottomNavigationView

open class BaseActivity : AppCompatActivity() {

    fun setupMenuRodape() {
        // 1. Localiza o BottomNavigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
            ?: findViewById<View>(R.id.menuPrincipal)?.findViewById(R.id.bottomNavigation)

        // 2. MARCAR O ÍCONE CORRETO AUTOMATICAMENTE
        // Verifica qual é a classe atual (this) e marca o ID correspondente
        // No arquivo Padrão_Page.kt, dentro da fun setupMenuRodape()

// 2. MARCAR O ÍCONE CORRETO AUTOMATICAMENTE
        bottomNav?.apply {
            val itemParaMarcar = when (this@BaseActivity) {
                is MainActivity -> R.id.add
                is PageInsightCalendar -> R.id.calendar
                is PageEstatistica -> R.id.grafic
                // Se for uma dessas páginas, retornamos null para apagar tudo
                is PageConfig, is PageHelp -> null
                else -> null
            }

            if (itemParaMarcar != null) {
                setOnItemSelectedListener(null)
                selectedItemId = itemParaMarcar
            } else {
                // Lógica para "apagar" o menu:
                // Tornamos os itens não-checáveis momentaneamente para limpar a seleção
                menu.setGroupCheckable(0, true, false)
                for (i in 0 until menu.size()) {
                    menu.getItem(i).isChecked = false
                }
                menu.setGroupCheckable(0, true, true)
            }
        }

        // 3. Listener de Clique (Navegação)
        bottomNav?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.add -> {
                    if (this !is MainActivity) {
                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                        overridePendingTransition(0, 0)
                    }
                    true
                }
                R.id.calendar -> {
                    if (this !is PageInsightCalendar) {
                        val intent = Intent(this, PageInsightCalendar::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                        overridePendingTransition(0, 0)
                    }
                    true
                }
                R.id.grafic -> {
                    if (this !is PageEstatistica) {
                        val intent = Intent(this, PageEstatistica::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NO_ANIMATION)
                        startActivity(intent)
                        overridePendingTransition(0, 0)
                    }
                    true
                }
                else -> false
            }
        }
    }

    fun setupMenuSuperior(){
        // ... (seu código do menu superior permanece o mesmo)
        val btnMenu = findViewById<ImageButton>(R.id.btnMenu)

        btnMenu?.setOnClickListener {

            val popup = PopupMenu(this, btnMenu)

            popup.menuInflater.inflate(R.menu.toolbar_menu, popup.menu)

            popup.setOnMenuItemClickListener { item ->

                when(item.itemId) {

                    R.id.MenuAjuda -> {
                        startActivity(Intent(this, PageHelp::class.java))
                        true
                    }

                    R.id.MenuConfig -> {
                        startActivity(Intent(this, PageConfig::class.java))
                        true
                    }

                    else -> false
                }
            }

            popup.show()
        }

        findViewById<ImageButton>(R.id.btnBack)?.setOnClickListener {
            if (this !is MainActivity) {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(intent)
                overridePendingTransition(0, 0)
            }
        }
    }
}