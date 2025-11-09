package com.example.aa2_ahorcado_rogeralamanac

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val layout = findViewById<View>(R.id.rootLayout)

        //Al clickar en cualquier lugar de la pantalla, va al level selector
        layout.setOnClickListener {
            val intent = Intent(this, LevelSelector::class.java)
            startActivity(intent)
            finish()
        }
    }
}