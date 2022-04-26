package com.example.trym2.View

interface I_ShopView {

    fun showMessage(message : String)
    fun setView(towerPrice1 : Int, towerPrice2 : Int, towerPrice3 : Int, myGold : Int)
    fun showMyGold(myGold: Int)
}