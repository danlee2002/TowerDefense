package com.example.trym2

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trym2.Model.TowerMid
import com.example.trym2.Model.TowerStrong
import com.example.trym2.Model.TowerWeak
import com.example.trym2.Model.User

class ShopPage : AppCompatActivity() {

    //var maxCountTower: Int = 10
    //var towerNum = arrayOf(0, 0, 0)
    var player = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.trym2.R.layout.shop_page)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        player = (intent.getSerializableExtra("player") as? User)!!
        //maxCountTower -= player.getDifficulty()

        var priceTower1 = findViewById<TextView>(R.id.tower1Text)
        var priceTower2 = findViewById(R.id.tower2Text) as TextView
        var priceTower3 = findViewById(R.id.tower3Text) as TextView

        priceTower1.text = "Gold : ${player.getDifficulty() * 5}"
        priceTower2.text = "Gold : ${player.getDifficulty() * 10}"
        priceTower3.text = "Gold : ${player.getDifficulty() * 15}"

        //Toast.makeText(applicationContext, "difficulty: " + player.getDifficulty().toString(), Toast.LENGTH_LONG).show()

        var buy0 = findViewById(R.id.Tower1) as ImageButton
        var buy1 = findViewById(R.id.Tower2) as ImageButton
        var buy2 = findViewById(R.id.Tower3) as ImageButton

        var gold = findViewById<TextView>(R.id.playerGold1)
        gold.text = player.getGold().toString()
        var towerPrice: Int



        buy0.setOnClickListener {
            //Toast.makeText(applicationContext, "TOWER 1 BOUGHT!", Toast.LENGTH_LONG).show()

            towerPrice = TowerWeak(player.getDifficulty()).price

            if (!hasEnoughGold(towerPrice)) {
                buy0.isEnabled = false
            } else if (!player.overMaxTower()) {
                towerPurchase(towerPrice, 0, gold)
            }
        }

        buy1.setOnClickListener {
            //Toast.makeText(applicationContext, "TOWER 2 BOUGHT!", Toast.LENGTH_LONG).show()

            towerPrice = TowerMid(player.getDifficulty()).price

            if (!hasEnoughGold(towerPrice)) {
                buy1.isEnabled = false
            } else if (!player.overMaxTower()) {
                towerPurchase(towerPrice, 1, gold)
            }
        }

        buy2.setOnClickListener {
            //Toast.makeText(applicationContext, "TOWER 3 BOUGHT!", Toast.LENGTH_LONG).show()

            towerPrice = TowerStrong(player.getDifficulty()).price

            if (!hasEnoughGold(towerPrice)) {
                buy2.isEnabled = false
            } else if (!player.overMaxTower()) {
                towerPurchase(towerPrice, 2, gold)
            }
        }
    }

    fun goToGame(v: View) {
//        when {
//            player.getDifficulty() == 1 -> {
//                val intent = Intent(this, EasyGame::class.java)
//                intent.putExtra("player", player)
//                intent.putExtra("towersBought", towerNum)
//                startActivity(intent)
//            }
//            player.getDifficulty() == 2 -> {
//                val intent = Intent(this, MedGame::class.java)
//                intent.putExtra("player", player)
//                intent.putExtra("towersBought", towerNum)
//                startActivity(intent)
//            }
//            player.getDifficulty() == 3 -> {
//                val intent = Intent(this, HardGame::class.java)
//                intent.putExtra("player", player)
//                intent.putExtra("towersBought", towerNum)
//                startActivity(intent)
//            }
//        }
        val intent = Intent(this, Game::class.java)
        Toast.makeText(applicationContext, player.getETNum().toString() + ": " + player.getMTNum().toString() + ": " + player.getHTNum().toString(), Toast.LENGTH_LONG).show()
        intent.putExtra("player", player)
        //intent.putExtra("towersBought", towerNum)
        startActivity(intent)

    }


    fun quitGame(v: View) {
        finish();
    }

    private fun towerPurchase(price: Int, towerLevel: Int, gold: TextView) {
        var amtGold = player.getGold()
        amtGold -= price
        player.setGold(amtGold)
        gold.text = amtGold.toString()
//        maxCountTower--
        when {
            towerLevel == 0 -> {
                player.setETNum(player.getETNum() + 1)
            }
            towerLevel == 1 -> {
                player.setMTNum(player.getMTNum() + 1)
            }
            towerLevel == 2 -> {
                player.setHTNum(player.getHTNum() + 1)
            }
        }
    }

    private fun hasEnoughGold(minimumGoldNeeded: Int): Boolean {
        if (player.getGold() < minimumGoldNeeded) {
            return false
        }
        return true
    }

}