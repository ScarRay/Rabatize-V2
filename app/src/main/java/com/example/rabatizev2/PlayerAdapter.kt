package com.example.rabatizev2
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.rabatizev2.models.Player
import com.example.rabatizev2.models.Player.Companion.avatars
import kotlin.random.Random


class PlayerAdapter(context: Context, private val players: MutableList<Player>)
    : ArrayAdapter<Player>(context, 0, players) {
    /*
    private val avatars = listOf(
        R.drawable.avatar1,
        R.drawable.avatar2,
        R.drawable.avatar3,
        R.drawable.avatar4,
        R.drawable.avatar5,
        R.drawable.avatar6,
        R.drawable.avatar7
        // ... add other avatar resource IDs as needed
    )*/
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView
            ?: LayoutInflater.from(context).inflate(R.layout.list_item_player, parent, false)

        val playerAvatarImageView: ImageView = itemView.findViewById(R.id.playerAvatarImageView)
        val playerNameTextView: TextView = itemView.findViewById(R.id.playerNameTextView)
        val removeButton: ImageButton = itemView.findViewById(R.id.removeButton)

        val player = players[position]
        playerNameTextView.text = player.name
        playerAvatarImageView.setImageResource(player.avatarResId)

        playerAvatarImageView.setOnClickListener {
            val updatedPlayer = player.withRandomNewAvatar()
            players[position] = updatedPlayer
            notifyDataSetChanged()
        }

        playerNameTextView.setOnClickListener {
            showEditNameDialog(context, player, position)
        }

        removeButton.setOnClickListener {
            players.removeAt(position)
            notifyDataSetChanged()
        }

        return itemView
    }
    private fun showEditNameDialog(context: Context, player: Player, position: Int) {
        val editText = EditText(context)
        editText.setText(player.name)

        AlertDialog.Builder(context)
            .setTitle(R.string.edit_player_name)
            .setView(editText)
            .setPositiveButton("OK") { _, _ ->
                val updatedName = editText.text.toString()
                if (updatedName.isNotBlank()) {
                    val updatedPlayer = player.copy(name = updatedName)
                    players[position] = updatedPlayer
                    notifyDataSetChanged()
                }
            }
            .setNegativeButton(R.string.cancel_edit_name, null)
            .show()
    }

}
