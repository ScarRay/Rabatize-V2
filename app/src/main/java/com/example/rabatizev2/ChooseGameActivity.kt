package com.example.rabatizev2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ListView
import com.example.rabatizev2.models.Player

class ChooseGameActivity : AppCompatActivity() {

    private lateinit var adapter: PlayerAdapter
    private lateinit var playersListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_game)

        playersListView = findViewById(R.id.playListView)

        // Get the players list passed from MainActivity
        val players = intent.getSerializableExtra("players") as? MutableList<Player>

        // Set up the adapter and ListView
        adapter = PlayerAdapter(this, players ?: mutableListOf())
        playersListView.adapter = adapter
    }
}
