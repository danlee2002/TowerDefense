package com.example.trym2.Model

import java.io.Serializable

class TowerWeak(difficulty: Int) : Tower(), Serializable {

    override var attackDamage: Int = 1 * difficulty
    override val attackSpeed: Int = 1 * difficulty
    override val price: Int = 5 * difficulty




}