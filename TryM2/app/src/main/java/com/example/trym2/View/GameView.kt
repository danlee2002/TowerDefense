package com.example.trym2.View

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.trym2.Model.*
import com.example.trym2.Presenter.GamePresenter
import com.example.trym2.Presenter.I_GamePresenter
import com.example.trym2.R

class GameView : AppCompatActivity(), I_GameView {

    private lateinit var easyTowerNumber : TextView
    private lateinit var medTowerNumber : TextView
    private lateinit var hardTowerNumber : TextView

    private lateinit var easyTowerPrice : TextView
    private lateinit var medTowerPrice : TextView
    private lateinit var hardTowerPrice : TextView

    private lateinit var gold : TextView

    private lateinit var monumentHP : TextView
    private lateinit var heart1 : ImageView
    private lateinit var heart2 : ImageView
    private lateinit var heart3 : ImageView
    private lateinit var heart4 : ImageView


    private lateinit var enemyChar : ImageView
    private lateinit var secondEnemy : ImageView

    lateinit var presenter : I_GamePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        findView()
        initPresenter((intent.getSerializableExtra("player") as? User)!!)

    }

    private fun initPresenter(player : User) {
        presenter = GamePresenter(this)
    }

    private fun findView() {
        gold = findViewById<TextView>(R.id.playerGold)

        easyTowerNumber = findViewById<TextView>(R.id.easyTowerNum)
        medTowerNumber = findViewById<TextView>(R.id.medTowerNum)
        hardTowerNumber = findViewById<TextView>(R.id.hardTowerNum)

        easyTowerPrice = findViewById<TextView>(R.id.buy1)
        medTowerPrice = findViewById<TextView>(R.id.buy2)
        hardTowerPrice = findViewById<TextView>(R.id.buy3)

        heart1 = findViewById<ImageView>(R.id.Heart1)
        heart2 = findViewById<ImageView>(R.id.Heart2)
        heart3 = findViewById<ImageView>(R.id.Heart3)
        heart4 = findViewById<ImageView>(R.id.Heart4)

        heart3.setImageResource(R.drawable.full)
        heart4.setImageResource(R.drawable.full)

        monumentHP = findViewById(R.id.gateHealth)

        enemyChar = findViewById(R.id.monster)
        enemyChar.translationY = 50f

        secondEnemy = findViewById(R.id.monster2)
        secondEnemy.translationY = 50f
    }


    override fun setGold(myGold: Int) {
        gold.text = myGold.toString()
    }

    override fun setETView(num: Int) {
        easyTowerNumber.text = num.toString()
    }

    override fun setMTView(num: Int) {
        medTowerNumber.text = num.toString()
    }

    override fun setHTView(num: Int) {
        hardTowerNumber.text = num.toString()
    }

    override fun setHeart(difficulty: Int) {
        when (difficulty){
            1 -> {
                heart1.setImageResource(R.drawable.full)
                heart2.setImageResource(R.drawable.full)
            }
            2 -> {
                heart1.setImageResource(R.drawable.zero)
                heart2.setImageResource(R.drawable.full)
            }
            3 -> {
                heart1.setImageResource(R.drawable.zero)
                heart2.setImageResource(R.drawable.zero)
            }

        }
    }

    override fun setTowerPrice(weak: Int, mid: Int, str: Int) {
        easyTowerPrice.text = weak.toString()
        medTowerPrice.text = mid.toString()
        hardTowerPrice.text = str.toString()
    }

    override fun setMonumentHP(hp: Int) {
        monumentHP.text = hp.toString()
    }

    // FINISHED CONVERTIGN onCreate METHOD


}