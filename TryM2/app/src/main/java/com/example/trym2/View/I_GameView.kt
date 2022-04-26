package com.example.trym2.View

interface I_GameView {

    fun setGold(myGold: Int)
    fun setETView(num: Int)
    fun setMTView(num: Int)
    fun setHTView(num: Int)
    fun setHeart(difficulty: Int)
    fun setTowerPrice(weak: Int, mid: Int, str: Int)
    fun setMonumentHP(hp: Int)

}