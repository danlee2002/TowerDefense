package com.example.trym2.Model

interface GameMap {

    var place: Array<Int>
    var anotherTowerClicked : Boolean

    fun clickTower(towerLevel : Int, level: Int)
}