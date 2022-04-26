package com.example.trym2.View

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.trym2.MainPage
import android.animation.*
import android.widget.TextView
import android.widget.Toast
import com.example.trym2.Model.*
import com.example.trym2.R
import kotlinx.android.synthetic.main.activity_gameover.*

class GameOverView : AppCompatActivity() {
    private var player = User()

    private lateinit var damageTaken : TextView
    private lateinit var enemyKilled : TextView
    private lateinit var goldSpent : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.trym2.R.layout.activity_gameover)
        player = (intent.getSerializableExtra("player") as? User)!!
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        damageTaken = findViewById(R.id.damage)
        enemyKilled = findViewById(R.id.enemyKilled)
        goldSpent = findViewById(R.id.goldspent)

        damageTaken.text = player.player_stats.getDamaget().toString()
        enemyKilled.text = player.player_stats.getEnemyK().toString()
        goldSpent.text = player.player_stats.getGolds().toString()
    }

    fun restartGame(v: View) {
        startActivity(Intent(this, MainPage::class.java))
    }
}