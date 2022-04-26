package com.example.trym2.Presenter

interface I_ShopPresenter {

    fun buyWeakTower() : Int
    fun buyMidTower() : Int
    fun buyStrTower() : Int
    fun hasEnoughGold(minGoldNeeded : Int) : Boolean
    fun towerPurchase(price : Int, towerLevel : Int)
    fun goToGame()
}