package com.example.rabatizev2
// GameModeAdapter.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rabatizev2.models.GameMode

class GameModeAdapter(private val gameModes: List<GameMode>) :
    RecyclerView.Adapter<GameModeAdapter.GameModeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameModeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.game_mode_item, parent, false)
        return GameModeViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameModeViewHolder, position: Int) {
        val gameMode = gameModes[position]
        holder.gameModeName.text = gameMode.name
        holder.gameModeImage.setImageResource(gameMode.imageResource)
    }

    override fun getItemCount(): Int {
        return gameModes.size
    }

    inner class GameModeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameModeImage: ImageView = itemView.findViewById(R.id.gameModeImage)
        val gameModeName: TextView = itemView.findViewById(R.id.gameModeName)
    }
}
