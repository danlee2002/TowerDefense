package com.example.trym2.View

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.trym2.MainPage
import com.example.trym2.Model.User
import com.example.trym2.R

class WinGameView : AppCompatActivity() {
    private var player = User()

    private lateinit var damageTaken : TextView
    private lateinit var enemyKilled : TextView
    private lateinit var goldSpent : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.trym2.R.layout.activity_winscreen)
        player = (intent.getSerializableExtra("player") as? User)!!
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        damageTaken = findViewById(R.id.w_damage)
        enemyKilled = findViewById(R.id.w_enemyKilled)
        goldSpent = findViewById(R.id.w_goldspent)

        damageTaken.text = player.player_stats.getDamaget().toString()
        enemyKilled.text = player.player_stats.getEnemyK().toString()
        goldSpent.text = player.player_stats.getGolds().toString()
    }

    fun restartGame(v: View) {
        startActivity(Intent(this, MainPage::class.java))
    }
}