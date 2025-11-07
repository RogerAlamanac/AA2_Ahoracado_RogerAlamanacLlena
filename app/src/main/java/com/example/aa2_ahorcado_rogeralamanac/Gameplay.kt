package com.example.aa2_ahorcado_rogeralamanac

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.helper.widget.Grid

class Gameplay : AppCompatActivity() {
    //Variables del layout del gameplay
    private lateinit var imgAhorcado : ImageView
    private lateinit var levelWord : String
    private lateinit var textLevelWord : TextView
    private lateinit var letterGrid : GridLayout
    private  var usedLetters: Array<Char> = arrayOf<Char>();

    private var intentsLeft : Int = 6


    private lateinit var myToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameplay)
        myToolbar = findViewById(R.id.toolbarGameplay)
        setSupportActionBar(myToolbar)

        imgAhorcado = findViewById(R.id.imgAhorcado)
        textLevelWord = findViewById(R.id.textWord)
        letterGrid = findViewById(R.id.letterGrid)

        //Detecta el string que envia el level selector para saber cual es la palabra actual
        levelWord = intent.getStringExtra("currentWord") ?: "Android"
        setKeyboard()
        updateWord()
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
    private fun setKeyboard(){
        var letters = 'A'..'Z'
        //Bucle que añade botones (las letras) a la gridLayout
        for (letter in letters){
            val buttonLetter = Button(this)
            buttonLetter.text = letter.toString()
            buttonLetter.textSize = 18f
            buttonLetter.setBackgroundColor(getColor(R.color.grey))
            buttonLetter.setTextColor(getColor(R.color.black))
            buttonLetter.setOnClickListener {
                gameManager(letter, buttonLetter);
            }
            //Añadimos el boton en la grid
            letterGrid.addView(buttonLetter)
        }
    }

    private fun gameManager(letter: Char, button: Button){
        //Si la letra esta usada, salimos de la funcion; Si no, la añadimos en el array de letras usadas
        if (letter in usedLetters) return
        usedLetters = usedLetters.plus(letter)

        //Si la letra escogida esta en la palabra, se pone en verde; Si no, se pone en rojo y se gasta una vida
        if(letter in levelWord) {
            button.setBackgroundColor(getColor(R.color.green))
        }
        else{
            button.setBackgroundColor(getColor(R.color.red))
            intentsLeft--
            //updateImage()
        }

        button.setEnabled(false);
        updateWord()
        //isEnd()
    }

    private fun updateWord(){
        var textToPrint = " ";
        for(it in levelWord){
            if(it in usedLetters) {
                textToPrint += "$it"
            }
            else {
                textToPrint += "_ "
            }
        }

        textLevelWord.text = textToPrint;
    }
    private fun updateImage(){
    //val currentImage = resources.getIdentifier("img_ahorcadofinal_${6-intentsLeft}", "drawable", packageName)
    }
    private fun onClickedButton(letterButton: Button){

    }
}