package com.example.trym2.Model
import java.io.Serializable

class Stats : Serializable {

    private var enemyKilled : Int = 0
    private var goldspent : Int = 0
    private var damageTaken : Int = 0

    constructor() {

    }

    fun setEnemyK(killed : Int) {
        enemyKilled = killed
    }
    fun getEnemyK() : Int {
        return enemyKilled
    }

    fun setGolds(gold : Int) {
        goldspent = gold
    }
    fun getGolds(): Int {
        return goldspent
    }

    fun setDamaget(damage : Int) {
        damageTaken = damage
    }
    fun getDamaget(): Int {
        return damageTaken
    }
}