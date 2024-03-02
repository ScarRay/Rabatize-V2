// GameModeSelectionActivity.kt
package com.example.rabatizev2


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rabatizev2.GameModeAdapter
import com.example.rabatizev2.R
import com.example.rabatizev2.models.GameMode

class GameModeSelectionActivity : AppCompatActivity() {

    private val gameModes = listOf(
        GameMode("Game Mode 1", R.drawable.avatar1),
        GameMode("Game Mode 2", R.drawable.avatar2),
        GameMode("Game Mode 3", R.drawable.avatar3),
        GameMode("Game Mode 4", R.drawable.avatar4)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_mode_selection)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = GameModeAdapter(gameModes)
    }
}
