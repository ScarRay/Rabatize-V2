package com.example.rabatizev2

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import com.example.rabatizev2.models.Player
import com.example.rabatizev2.models.Player.Companion.avatars
import java.util.Locale
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var playersList: ListView
    private lateinit var adapter: PlayerAdapter

    val ENGLISH = Locale("en")
    val FRENCH = Locale("fr")

    private var players = mutableListOf<Player>()
    private val placeholderNames: Array<String> by lazy {
        resources.getStringArray(R.array.placeholder_names)
    }
    private var shuffledNames = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val randomAvatar = avatars[Random.nextInt(avatars.size)]
        repeat(4) {
            addNewPlayer()
        }

        playersList = findViewById(R.id.playersList)
        adapter = PlayerAdapter(this, players)
        playersList.adapter = adapter

        val addButton: Button = findViewById(R.id.expandButton)
        addButton.text = getString(R.string.add_player)
        addButton.setOnClickListener {
            val randomAvatar = Player.avatars[Random.nextInt(Player.avatars.size)]
            val playerName = getRandomPlaceholderName()
            players.add(Player(playerName, randomAvatar))
            adapter.notifyDataSetChanged()
        }

        val languageSwitchButton: ImageButton = findViewById(R.id.languageSwitchButton)
        languageSwitchButton.setOnClickListener {
            switchLanguage()
        }

        val playButton: Button = findViewById(R.id.playButton)
        playButton.setOnClickListener {
            val intent = Intent(this, ChooseGameActivity::class.java)
            intent.putExtra("players", ArrayList(players))
            startActivity(intent)
        }

    }
    private fun getRandomPlaceholderName(): String {
        if (shuffledNames.isEmpty()) {
            shuffledNames = placeholderNames.toMutableList()
            shuffledNames.shuffle()
        }
        val name = shuffledNames[0]
        shuffledNames.removeAt(0)
        return name
    }
    private fun switchLanguage() {
        val currentLocale = resources.configuration.locale
        val newLocale = if (currentLocale.language == Locale.ENGLISH.language) FRENCH else ENGLISH
        // Update UI elements to reflect the language change

        updateAppLocale(newLocale)
        val languageSwitchButton: ImageButton = findViewById(R.id.languageSwitchButton)
        if (newLocale.language == Locale.ENGLISH.language) {
            languageSwitchButton.setImageResource(R.drawable.french_flag)
        } else {
            languageSwitchButton.setImageResource(R.drawable.american_flag)
        }
    }
    private fun updateAppLocale(locale: Locale) {
        // Change the app's locale to the new locale
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        // Update other UI elements as needed
        val addPlayerButton: Button = findViewById(R.id.expandButton)
        addPlayerButton.text = getString(R.string.add_player)

        val subTitleText: TextView = findViewById(R.id.subTitleText)
        subTitleText.text = getString(R.string.sub_title_app)

        val playButton: Button = findViewById(R.id.playButton)
        playButton.text = getString(R.string.play)

    }
    private fun addNewPlayer() {
        val playerName = getRandomPlaceholderName()  // This gives players names like Player 1, Player 2, etc.
        val newPlayer = Player.createRandomAvatarPlayer(playerName)
        players.add(newPlayer)
    }

}