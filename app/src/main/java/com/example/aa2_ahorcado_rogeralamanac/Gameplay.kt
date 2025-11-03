package com.example.aa2_ahorcado_rogeralamanac

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class Gameplay : AppCompatActivity() {
    private lateinit var imgAhorcado : ImageView
    private lateinit var levelWord : String
    private lateinit var textLevelWord : TextView
    private lateinit var gridLetters : GridLayout

    private var intentsNum : Int = 6

    private lateinit var myToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameplay)
        myToolbar = findViewById(R.id.toolbarGameplay)
        setSupportActionBar(myToolbar)

        imgAhorcado = findViewById(R.id.imgAhorcado)
        textLevelWord = findViewById(R.id.textWord)
        gridLetters = findViewById(R.id.gridLetras)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_general, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.settings) {
            //Turn on dark / light mode
            true
        } else{
            super.onOptionsItemSelected(item)
        }

    }
}