package com.example.trym2.View

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trym2.Game
import com.example.trym2.Model.User
import com.example.trym2.Presenter.I_ShopPresenter
import com.example.trym2.Presenter.ShopPresenter
import com.example.trym2.R

class ShopView: AppCompatActivity(), I_ShopView {

    private lateinit var priceTower1 : TextView
    private lateinit var priceTower2 : TextView
    private lateinit var priceTower3 : TextView
    private lateinit var tower1 : ImageButton
    private lateinit var tower2 : ImageButton
    private lateinit var tower3 : ImageButton
    private lateinit var quit : ImageButton
    private lateinit var button : Button
    private lateinit var gold : TextView
    lateinit var presenter : I_ShopPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.trym2.R.layout.shop_page)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        findView()
        initPresenter((intent.getSerializableExtra("player") as? User)!!)
        setOnClickListener()
    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun initPresenter(player : User) {
        if (player == null) {
            Toast.makeText(applicationContext, "IT'S NULL", Toast.LENGTH_LONG).show()
        }
        presenter = ShopPresenter(this, player)
    }

    private fun findView() {
        priceTower1 = findViewById<TextView>(R.id.tower1Text)
        priceTower2 = findViewById<TextView>(R.id.tower2Text)
        priceTower3 = findViewById<TextView>(R.id.tower3Text)

        tower1 = findViewById<ImageButton>(R.id.Tower1)
        tower2 = findViewById<ImageButton>(R.id.Tower2)
        tower3 = findViewById<ImageButton>(R.id.Tower3)
        quit = findViewById<ImageButton>(R.id.quit)

        button = findViewById<Button>(R.id.continueToGame)

        gold = findViewById<TextView>(R.id.playerGold1)
        //Toast.makeText(applicationContext, "HELLO", Toast.LENGTH_LONG).show()
    }

    private fun setOnClickListener() {
        tower1.setOnClickListener {
            if (presenter.buyWeakTower() == 0) {
                tower1.isEnabled = false
            }
        }

        tower2.setOnClickListener {
            if (presenter.buyMidTower() == 0) {
                tower2.isEnabled = false
            }
        }

        tower3.setOnClickListener {
            if (presenter.buyStrTower() == 0) {
                tower3.isEnabled = false
            }
        }

        quit.setOnClickListener {
            finish()
        }

        button.setOnClickListener {
            presenter.goToGame()
        }
    }

    override fun setView(towerPrice1 : Int, towerPrice2 : Int, towerPrice3 : Int, myGold : Int) {
        priceTower1.text = "Gold : $towerPrice1"
        priceTower2.text = "Gold : $towerPrice2"
        priceTower3.text = "Gold : $towerPrice3"
        gold.text = myGold.toString()
    }

    override fun showMyGold(myGold : Int) {
        gold.text = myGold.toString()
    }

    // *** Not sure with this + I have no idea with passing the data which is stored in the Presenter
    fun goToNextPage(player: User) {
        val intent = Intent(this, Game::class.java)
        intent.putExtra("player", player)
        startActivity(intent)
    }

}