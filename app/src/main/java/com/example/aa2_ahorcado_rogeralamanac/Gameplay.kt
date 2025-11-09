package com.example.aa2_ahorcado_rogeralamanac

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar

class Gameplay : AppCompatActivity() {
    //Variables del layout del gameplay
    private lateinit var imgAhorcado : ImageView
    private lateinit var textLevelWord : TextView
    private lateinit var letterGrid : GridLayout
    private lateinit var gameplayLayout : View
    private lateinit var myToolbar: Toolbar

    //Variables del sistema (que no se ven fisicamente en el layout)
    private var intentsLeft : Int = 6
    private var usedLetters: Array<Char> = arrayOf()
    private lateinit var levelWord : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gameplay)
        myToolbar = findViewById(R.id.toolbarGameplay)
        setSupportActionBar(myToolbar)

        gameplayLayout = findViewById(R.id.gameplayLayout)
        imgAhorcado = findViewById(R.id.imgAhorcado)
        textLevelWord = findViewById(R.id.textWord)
        letterGrid = findViewById(R.id.letterGrid)

        //Si hay alguna instancia guardada actualizamos UI (para manejo de modo claro / oscuro)
        if (savedInstanceState != null) {
            levelWord = savedInstanceState.getString("KEY_WORD", "DEFAULT")
            intentsLeft = savedInstanceState.getInt("KEY_INTENTS")

            val restoredLettersList = savedInstanceState.getStringArrayList("KEY_USED_LETTERS")
            //Pasamos el array de string a array de char
            usedLetters = restoredLettersList?.map { it.single() }?.toTypedArray() ?: arrayOf()

            // Actualizamos la UI y el teclado
            setKeyboard()
            updateWord()
            updateImage()

        } else { //Si es la primera carga, se inicializa la escena
            levelWord = intent.getStringExtra("currentWord").toString()
            setKeyboard()
            updateWord()
        }
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


    private fun setKeyboard(){
        letterGrid.removeAllViews() // Limpia la vista si estás recreando la Activity
        val letters = 'A'..'Z'
        //Seteamos el teclado mediante un bucle, que spawnea botones con la letra correspondiente
        for (letter in letters){
            val buttonLetter = Button(this)
            buttonLetter.text = letter.toString()
            buttonLetter.textSize = 18f
            buttonLetter.setBackgroundColor(getColor(R.color.grey))
            buttonLetter.setTextColor(getColor(R.color.black))

            if (letter in usedLetters) {
                buttonLetter.isEnabled = false
                //Si la letra esta en la palabra se vuelve verde / en caso contrario se vuelve rojo
                if (letter in levelWord) {
                    buttonLetter.setBackgroundColor(getColor(R.color.green))
                } else {
                    buttonLetter.setBackgroundColor(getColor(R.color.red))
                }
            } else {
                buttonLetter.setBackgroundColor(getColor(R.color.grey))
                buttonLetter.setTextColor(getColor(R.color.black))
            }

            buttonLetter.setOnClickListener {
                gameManager(letter, buttonLetter)
            }

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
            updateImage()

        }

        button.isEnabled = false
        updateWord()
        isEnd()
    }

    //Funcion para actualizar la palabra
    private fun updateWord(){
        var textToPrint = " "
        for(it in levelWord){
            if(it in usedLetters) {
                textToPrint += "$it"
            }
            else {
                textToPrint += "_ "
            }
        }
        textLevelWord.text = textToPrint
    }

    private fun updateImage(){

        val currentImage = when(intentsLeft){
            6->R.drawable.img_ahorcado_0
            5->R.drawable.img_ahorcado_1
            4->R.drawable.img_ahorcado_2
            3->R.drawable.img_ahorcado_3
            2->R.drawable.img_ahorcado_4
            1->R.drawable.img_ahorcado_5
            else -> R.drawable.img_ahorcado_dead
        }
        imgAhorcado.setImageResource(currentImage)
    }

    //Funcion que comprueba si el jugador pierde o gana
    private fun isEnd(){
        if(intentsLeft == 0){
           endMessage(R.string.youLoseText)
        } else if(levelWord.all{ it in usedLetters}){
            endMessage(R.string.youWonText)
        }
    }

    private fun endMessage(message: Int){
        //Creamos el "pop-up" del mensaje you win / you lose
        val popUpMessage : AlertDialog = AlertDialog.Builder(this)
            .setTitle(message)
            .setMessage(R.string.touchScreenText)
            .setCancelable(true)
            .create()

        // Detectar click para volver al levelSelector (cuando se "cancela", clicando en la pantalla)
        popUpMessage.setOnCancelListener {
            val intent = Intent(this, LevelSelector::class.java)
            startActivity(intent)
            finish()
        }
        popUpMessage.show()
    }

    //Funcion para que al cambiar de modo oscuro / claro no se reinicie la escena
    override fun onSaveInstanceState(outState: Bundle) {
        // Guardamos la palabra secreta, intentos y letras usadas
        outState.putString("KEY_WORD", levelWord)

        outState.putInt("KEY_INTENTS", intentsLeft)

        val lettersToSave = ArrayList(usedLetters.map { it.toString() })
        outState.putStringArrayList("KEY_USED_LETTERS", lettersToSave)

        super.onSaveInstanceState(outState)
    }
}