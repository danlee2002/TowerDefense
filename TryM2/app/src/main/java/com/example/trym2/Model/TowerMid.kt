package com.example.trym2.Model

import java.io.Serializable

class TowerMid(difficulty: Int) : Tower(), Serializable {

    override var attackDamage: Int = 2 * difficulty
    override val attackSpeed: Int = 2 * difficulty
    override val price: Int = 10 * difficulty


}