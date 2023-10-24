package com.example.rabatizev2.models

import com.example.rabatizev2.R
import java.io.Serializable
import kotlin.random.Random

data class Player(val name: String, val avatarResId: Int): Serializable {
    companion object {
        val avatars = listOf(
            R.drawable.avatar1,
            R.drawable.avatar2,
            R.drawable.avatar3,
            R.drawable.avatar4,
            R.drawable.avatar5,
            R.drawable.avatar6,
            R.drawable.avatar7
            // ... add other avatar resource IDs as needed
        )

        fun createRandomAvatarPlayer(name: String): Player {
            val randomAvatar = avatars[Random.nextInt(avatars.size)]
            return Player(name, randomAvatar)
        }
    }
    fun withRandomNewAvatar(): Player {
        var newAvatar: Int
        do {
            newAvatar = avatars[Random.nextInt(avatars.size)]
        } while (newAvatar == this.avatarResId)
        return Player(this.name, newAvatar)
    }
}

