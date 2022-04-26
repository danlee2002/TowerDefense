package com.example.trym2.Model
class Boss {
    private var hp: Int = 20;
    private var damage: Int = 10;
    private var difficulty: Int = 0;
    private var x: Int = 0;
    private var y: Int = 0;
    private var goldValue: Int = 10
    private var dx: Int = 0
    private var dy: Int = 0
    private var alive: Boolean = true
    private var canAttack: Boolean = true
    private val inRange: Boolean = true

    constructor(difficulty: Int) {
        this.difficulty = difficulty;
        this.hp = this.hp * difficulty;
        this.damage = this.damage * difficulty;
        this.goldValue *= difficulty
    }


    fun getHp(): Int {
        return hp
    }

    fun getDifficulty(): Int {
        return difficulty
    }

    fun getDamage(): Int {
        return damage
    }

    fun setHp(hp: Int) {
        this.hp = hp;
    }

    fun setX(x: Int) {
        this.x = x
    }

    fun setY(y: Int) {
        this.y = y
    }

    fun getY(): Int {
        return this.y
    }

    fun getX(): Int {
        return this.x
    }
    fun getAlive(): Boolean {
        if (hp  <= 0) {
            return false
        }
        return true
    }
    fun canAttack(): Boolean {
        if (hp  <= 0 || inRange) {
            return false
        }
        return true
    }

    fun attackMonument(target: Monument) {
        target.setHp(target.getHp() - this.damage);
    }

    fun moveHorizontal(x: Int) {
        this.x +=x
    }

    fun moveVertical(y: Int) {
        this.y += y
    }
    fun getGold(): Int {
        return this.goldValue
    }
}