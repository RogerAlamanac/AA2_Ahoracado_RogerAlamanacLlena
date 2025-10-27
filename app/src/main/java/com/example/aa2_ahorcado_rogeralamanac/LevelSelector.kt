package com.example.aa2_ahorcado_rogeralamanac

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import models.Level
import models.LevelAdapter

class LevelSelector : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.level_selector)

        val levels = listOf(
            Level("ENTI", "4", R.drawable.ic_launcher_foreground),
            Level("ENTI", "4", R.drawable.ic_launcher_foreground),
            Level("ENTI", "4", R.drawable.ic_launcher_foreground),
            Level("ENTI", "4", R.drawable.ic_launcher_foreground),
            Level("ENTI", "4", R.drawable.ic_launcher_foreground),
            Level("ENTI", "4", R.drawable.ic_launcher_foreground),
            Level("ENTI", "4", R.drawable.ic_launcher_foreground),
            Level("ENTI", "4", R.drawable.ic_launcher_foreground),
            Level("ENTI", "4", R.drawable.ic_launcher_foreground),
            Level("ENTI", "4", R.drawable.ic_launcher_foreground),
            Level("ENTI", "4", R.drawable.ic_launcher_foreground),
            Level("ENTI", "4", R.drawable.ic_launcher_foreground),
            Level("ENTI", "4", R.drawable.ic_launcher_foreground)
        )

        val recyclerView: RecyclerView = findViewById(R.id.levels_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LevelAdapter(levels)
    }
}