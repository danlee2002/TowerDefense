package com.example.trym2.Model

import android.util.DisplayMetrics
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate


class Map {

    private val device: DisplayMetrics = DisplayMetrics()
    private val width: Int = device.widthPixels
    private val height: Int = device.heightPixels

    private var tileMap: Array<Array<Tile<Enemy>>> = arrayOf<Array<Tile<Enemy>>>();

    private var user: User? = null
    private val timer: Timer = Timer("movement")
    private var enemyCount: Int = 0
    private var enemyList: Array<Enemy?> = arrayOfNulls<Enemy>(100)


    constructor(user: User, enemy: Array<Enemy?>) {
        for (i in 0..12) {
            for (j in 0..14) {
                tileMap[i][j] = Tile<Enemy>(i, j);
            }
        }
        this.enemyList = enemy;
        enemyCount = enemy.size
    }


    fun move() {

        //edge cases 2,4,6
        if (enemyCount == 0 || enemyList == null) {
            throw java.lang.NullPointerException("Enemy Count is 0 or EnemyList is null")

        }
        while (enemyCount >= 0) {
            timer.scheduleAtFixedRate(100, 1000) {
                moveHelper(--enemyCount);
            }
        }


    }

    private fun moveHelper(pos: Int) {
        val y = arrayOf(2, 3, 4, 5, 6)
        for (i in y) {
            for (j in 0..12) {
                if (i % 2 == 0) {
                    tileMap[i][j].setValue(enemyList[pos])
                    var dX = tileMap[i][j].getdX()
                    var dY = tileMap[i][j].getdY()
                    if (dY != null) {
                        enemyList[pos]!!.setY(dY)

                    }
                    if (dX != null) {
                        enemyList[pos]!!.setX(dX)
                    }

                    for (i in 1..20) {
                        timer.scheduleAtFixedRate(0, 100) {
                            enemyList[pos]!!.setX(enemyList[pos]!!.getX() + 1);

                        }
                    }


                    if (j != 0) {
                        tileMap[i][j - 1].setValue(null)
                    }

                } else {
                    if (i == 3 && j == 11) {
                        tileMap[i][j].setValue(enemyList[pos])
                        tileMap[i - 1][11].setValue(null)
                        for (i in 1..20) {
                            timer.scheduleAtFixedRate(0, 100) {
                                enemyList[pos]!!.setY(enemyList[pos]!!.getY() + 1);

                            }
                        }
                    }
                    if (i == 5 && j == 0) {
                        tileMap[i][j].setValue(enemyList[pos])
                        tileMap[i - 1][0].setValue(null)
                        for (i in 1..20) {
                            timer.scheduleAtFixedRate(0, 100) {
                                enemyList[pos]!!.setY(enemyList[pos]!!.getY() + 1);

                            }
                        }

                    }

                }


            }

        }

    }


}