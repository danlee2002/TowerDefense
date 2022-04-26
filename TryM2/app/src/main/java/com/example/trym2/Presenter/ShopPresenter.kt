package com.example.trym2.Presenter

import android.media.AsyncPlayer
import com.example.trym2.Model.TowerMid
import com.example.trym2.Model.TowerStrong
import com.example.trym2.Model.TowerWeak
import com.example.trym2.Model.User
import com.example.trym2.View.LoginView
import com.example.trym2.View.ShopView

class ShopPresenter : I_ShopPresenter{

    var player = User()
    private var towerPrice1 : Int = 0
    private var towerPrice2 : Int = 0
    private var towerPrice3 : Int = 0
    private var myGold : Int = 0

    lateinit var shopView : ShopView

    constructor(shopView: ShopView, player: User) : this(shopView) {
        setPlayer(player)
//        this.shopView = shopView
    }

    constructor(shopView: ShopView) {
        this.shopView = shopView
    }

    @JvmName("setPlayer1")
    private fun setPlayer(player: User) {
        this.player = player
        setValues(player)
    }

    private fun setValues(player : User) {
        this.towerPrice1 = TowerWeak(player.getDifficulty()).price
        this.towerPrice2 = TowerMid(player.getDifficulty()).price
        this.towerPrice3 = TowerStrong(player.getDifficulty()).price
        this.myGold = player.getGold()

        shopView.setView(towerPrice1, towerPrice2, towerPrice3, myGold)

    }

    override fun buyWeakTower() : Int {
        player.player_stats.setGolds(player.player_stats.getGolds() + towerPrice1)
        return buyTower(towerPrice1, 1)
    }

    override fun buyMidTower() : Int {
        player.player_stats.setGolds(player.player_stats.getGolds() + towerPrice2)
        return buyTower(towerPrice2, 2)
    }

    override fun buyStrTower() : Int {
        player.player_stats.setGolds(player.player_stats.getGolds() + towerPrice3)
        return buyTower(towerPrice3, 3)
    }


    private fun buyTower(towerPrice : Int, towerLevel : Int) : Int {
        if (!hasEnoughGold(towerPrice)) {
            shopView.showMessage("Not Enough Money")
            return 0
        } else if (!player.overMaxTower()) {
            towerPurchase(towerPrice, towerLevel)
            return 1
        }
        return 2
    }

    override fun hasEnoughGold(minGoldNeeded : Int): Boolean {
        if (player.getGold() < minGoldNeeded) {
            return false
        }
        return true
    }

    override fun towerPurchase(price: Int, towerLevel: Int) {
        myGold -= price
        player.setGold(myGold)
        shopView.showMyGold(myGold)
        when {
            towerLevel == 1 -> {
                player.setETNum(player.getETNum() + 1)
            }
            towerLevel == 2 -> {
                player.setMTNum(player.getMTNum() + 1)
            }
            towerLevel == 3 -> {
                player.setHTNum(player.getHTNum() + 1)
            }
        }
    }

    override fun goToGame() {
        shopView.showMessage("Bought -> " + player.getETNum().toString() + ": " + player.getMTNum().toString() + ": " + player.getHTNum().toString())
        shopView.goToNextPage(player)
    }
}