package com.example.trym2.Presenter

import com.example.trym2.Model.*
import com.example.trym2.View.GameView
import com.example.trym2.View.LoginView
import com.example.trym2.View.ShopView

class GamePresenter : I_GamePresenter {

    var player = User()
    lateinit var gate : Monument
    private var towerPrice1 : Int = 0
    private var towerPrice2 : Int = 0
    private var towerPrice3 : Int = 0
    private var myGold : Int = 0

    lateinit var gameView: GameView

    constructor(gameView: GameView, player: User) : this(gameView) {
        setPlayer(player)
    }

    constructor(gameView: GameView) {
        this.gameView = gameView
    }

    @JvmName("setPlayer2")
    private fun setPlayer(player: User) {
        this.player = player
        this.gate = Monument(player.getDifficulty())
        setValues(player)
    }

    private fun setValues(player : User) {
        this.towerPrice1 = TowerWeak(player.getDifficulty()).price
        this.towerPrice2 = TowerMid(player.getDifficulty()).price
        this.towerPrice3 = TowerStrong(player.getDifficulty()).price
        this.myGold = player.getGold()

        gameView.setGold(myGold)
        gameView.setETView(player.getETNum())
        gameView.setMTView(player.getMTNum())
        gameView.setHTView(player.getHTNum())
        gameView.setHeart(player.getDifficulty())
        gameView.setTowerPrice(towerPrice1, towerPrice2, towerPrice3)
        gameView.setMonumentHP(gate.getHp())
    }

}