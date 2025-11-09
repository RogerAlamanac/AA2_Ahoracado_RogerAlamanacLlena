package com.example.aa2_ahorcado_rogeralamanac

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.Level
import models.LevelAdapter

class LevelSelector : AppCompatActivity() {
    private lateinit var myToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level_selector)
        //Seteamos list de niveles
        val levels = listOf(
            Level(getString(R.string.wordLevel1), getString(R.string.wordLevel1).length),
            Level(getString(R.string.wordLevel2), getString(R.string.wordLevel2).length),
            Level(getString(R.string.wordLevel3), getString(R.string.wordLevel3).length),
            Level(getString(R.string.wordLevel4), getString(R.string.wordLevel4).length),
            Level(getString(R.string.wordLevel5), getString(R.string.wordLevel5).length),
            Level(getString(R.string.wordLevel6), getString(R.string.wordLevel6).length),
            Level(getString(R.string.wordLevel7), getString(R.string.wordLevel7).length),
            Level(getString(R.string.wordLevel8), getString(R.string.wordLevel8).length),
            Level(getString(R.string.wordLevel9), getString(R.string.wordLevel9).length),
            Level(getString(R.string.wordLevel10), getString(R.string.wordLevel10).length),
            Level(getString(R.string.wordLevel11), getString(R.string.wordLevel11).length),
            Level(getString(R.string.wordLevel12), getString(R.string.wordLevel12).length)
        )
        myToolbar = findViewById(R.id.toolbarLevelSelector)
        setSupportActionBar(myToolbar)

        //Inicializamos RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.levels_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LevelAdapter(levels)
    }

    //Funciones para interactuar con la toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_general, menu)
        return true
    }

    //Al clicar, se cambia el modo oscuro / claro
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.darkLightItem) {
            changeDarkMode()
            true
        } else{
            super.onOptionsItemSelected(item)
        }

    }

    //Detectar modo claro / oscuro
    private fun changeDarkMode() {
        val currentMode = AppCompatDelegate.getDefaultNightMode()
        if (currentMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}