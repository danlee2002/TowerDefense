package com.example.trym2.Model

import java.io.Serializable

class User : Serializable {
    private var username: String = ""
    private var difficulty: Int = 0
    private var gold: Int = 0
    private var towerNum = arrayOf(0, 0, 0)
    private var maxTowerNum = 0
    private var boughtTowerNum = 0
    private var won = false
    var player_stats = Stats()

    constructor() {
        username = ""
        difficulty = 0
        gold = 0
        maxTowerNum = 0
    }

    constructor(name: String, difficulty: Int) {
        this.username = name
        this.difficulty = difficulty

        when (difficulty) {
            1 -> {
                setGold(100)
                setMaxTowerNum(10)
            }
            2 -> {
                setGold(75)
                setMaxTowerNum(11)
            }
            3 -> {
                setGold(50)
                setMaxTowerNum(11)
            }
        }
    }

    fun setGold(amtGold: Int) {
        this.gold = amtGold
    }

    fun getGold(): Int {
        return gold
    }

    fun setName(name: String) {
        this.username = name
    }

    fun setDifficulty(diff: Int) {
        this.difficulty = diff
        updateGold()
        updateMaxTowerNum()
    }

    fun setdiff(diff: Int) {
        this.difficulty = diff
        updateMaxTowerNum()
    }

    fun getDifficulty(): Int {
        return difficulty
    }

    fun getName(): String {
        return this.username
    }

    private fun updateGold() {
        when (difficulty) {
            1 -> setGold(100)
            2 -> setGold(75)
            3 -> setGold(50)
        }
    }

    private fun updateMaxTowerNum() {
        when (difficulty) {
            1 -> setMaxTowerNum(11)
            2 -> setMaxTowerNum(11)
            3 -> setMaxTowerNum(11)
        }
    }

    private fun setMaxTowerNum(num : Int) {
        this.maxTowerNum = num
    }

    fun getMaxTowerNum() : Int {
        return maxTowerNum
    }

    fun setETNum(num : Int) {
        if (this.towerNum[0] < num) {
            this.boughtTowerNum++
        }
        this.towerNum[0] = num;
        //updateBoughtTowerNum()
    }

    fun getETNum() : Int {
        return towerNum[0]
    }

    fun buyTower(tower: Tower) {
        this.gold -= tower.getPrice()

    }

    fun setMTNum(num : Int) {
        if (this.towerNum[1] < num) {
            this.boughtTowerNum++
        }
        this.towerNum[1] = num
        //updateBoughtTowerNum()
        //this.boughtTowerNum++
    }

    fun getMTNum() : Int {
        return towerNum[1]
    }

    fun setHTNum(num : Int) {
        if (this.towerNum[2] < num) {
            this.boughtTowerNum++
        }
        this.towerNum[2] = num
        //updateBoughtTowerNum()
        //this.boughtTowerNum++
    }

    fun getHTNum() : Int {
        return towerNum[2]
    }
    fun getWon(): Boolean {
        return this.won
    }

    fun overMaxTower() : Boolean {
        //return (this.towerNum[0] + this.towerNum[1] + this.towerNum[2] >= this.maxTowerNum)
        return (this.boughtTowerNum >= this.maxTowerNum)
    }

    fun updateBoughtTowerNum() {
        this.boughtTowerNum = this.towerNum[0] + this.towerNum[1] + this.towerNum[2]
    }

    fun getBoughtTower() : Int {
        return this.boughtTowerNum
    }

    fun setBoughtTower(num : Int) {
        this.boughtTowerNum = num
    }
    fun setWon(won: Boolean){
        this.won = won

    }
}