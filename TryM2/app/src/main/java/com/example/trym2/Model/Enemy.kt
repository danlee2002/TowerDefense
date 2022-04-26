package com.example.trym2.Model

import android.util.DisplayMetrics
import java.lang.IllegalArgumentException
import com.example.trym2.Model.Monument
import java.lang.Math.abs
import java.util.*
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate


open class Enemy {
    private val device: DisplayMetrics = DisplayMetrics();
    private var hp: Int = 10;
    private var damage: Int = 10;
    private var difficulty: Int = 0;
    private var x: Int = 0;
    private var y: Int = 0;
    private var goldValue: Int = 10
    private var dx: Int = 0
    private var dy: Int = 0
    private var alive: Boolean = true
    private var canAttack: Boolean = true
    private val timer: Timer = Timer("movement")
    private val inRange: Boolean = true

    private var path: HashMap<Int, Coordinate> = HashMap<Int, Coordinate>(5)

    constructor(difficulty: Int) {
        this.difficulty = difficulty;
        this.hp = this.hp * difficulty;
        this.damage = this.damage * difficulty;
        this.goldValue *= difficulty



    }
    constructor() {

    }

    constructor(difficulty: Int, x: Int, y: Int) {
        this.difficulty = difficulty
        this.hp = this.hp * difficulty
        this.damage = this.damage * difficulty
        this.x = x
        this.y = y
        this.goldValue *= difficulty
//        path.put(0, Coordinate(-1000, 2))
//        path.put(1, Coordinate(1000, 2))
//        path.put(2, Coordinate(1, 2))
//        path.put(3, Coordinate(1, 2))
//        path.put(4, Coordinate(1, 2))


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

    fun setDamage(damage: Int) {
        this.damage = damage
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
    fun moveHorizontalTime() {

    }
    fun moveVerticalTime() {

    }



    /*
    fun movePath() {
        for (i in 0..5) {
            path.get(i)
            if (i % 2 == 0) {
                this.moveHorizontal(
                    path.get(i)!!.getDist(),

                )
            } else {
                this.moveVertical(
                    path.get(i)!!.getDist(),

                )

            }

        }
    }
    */

    private fun Timer.scheduleAtFixedRate(i: Int, period: Int, function: () -> Unit) {

    }
}




