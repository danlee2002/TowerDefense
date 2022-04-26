package com.example.trym2.Model

import java.io.Serializable

class TowerStrong(difficulty: Int) : Tower(), Serializable {

    override var attackDamage: Int = 3 * difficulty
    override val attackSpeed: Int = 3 * difficulty
    override val price: Int = 15 * difficulty


}