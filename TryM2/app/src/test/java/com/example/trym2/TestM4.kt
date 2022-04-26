package com.example.trym2

import android.util.DisplayMetrics
import com.example.trym2.Model.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TestM4{

    @Test
    fun testMaxTowerNum() {
        val eell = User("eell",1);
        val mell = User("mell",2);
        val hell = User("hell",3);
        assertEquals(eell.getMaxTowerNum(), 10);
        assertEquals(mell.getMaxTowerNum(), 8);
        assertEquals(hell.getMaxTowerNum(), 6);
    }

    //Shravan Cheekati Tests
    @Test
    fun testIfMonumentDead() {
        val testGate = Monument(2)
        testGate.setHp(0)
        assertEquals(false, testGate.isAlive)
    }

    @Test
    fun testMonumentScore() {
        val enemy1 = Enemy(2)
        val enemy2 = Enemy(2)
        val testGate = Monument(2)

        testGate.setDamage(1000)

        testGate.attackEnemy(enemy1)
        testGate.attackEnemy(enemy2)

        assertEquals(2, testGate.getScore())


    }

    @Test
    fun testEnemyHp() {
        val enemy = Enemy(1);
        val tower = TowerWeak(1);
        val monument = Monument(1);
        tower.attackEnemy(enemy,monument,User("a",1));
        assertEquals(9, enemy.getHp())

    }

    @Test
    fun testMoveX() {
        val enemy = Enemy(1);
        enemy.moveHorizontal(100)

        assertEquals(enemy.getX(),100)
        assertEquals(0,enemy.getY())
    }

    @Test
    fun testMoveY() {
        val enemy = Enemy(1);
        enemy.moveVertical(100)
        assertEquals(enemy.getY(),100)
        assertEquals(0,enemy.getX())
    }

    @Test
    fun testMonumentAttack() {
        val enemy = Enemy(1);
        val enemy1 = Enemy(1, 100, 100);
        val mon = Monument(1,10,10)
        mon.attackEnemy(enemy)
        mon.attackEnemy(enemy1)
        assertEquals(0, enemy.getHp())
        assertEquals(10, enemy1.getHp())

    }
    @Test
    fun testEnemyLocation(){
        val enemy = Enemy(1);
        enemy.setX(2)
        enemy.setY(3)

        assertEquals(enemy.getX(), 2)
        assertEquals(enemy.getY(), 3)
    }
    @Test
    fun testTile(){
        val tileT = Tile<Int>(1,2);
        assertEquals((DisplayMetrics().widthPixels / 14)* tileT.getX()!!, tileT.getdX());
        assertEquals(DisplayMetrics().heightPixels / 14 * tileT.getY()!!, tileT.getdY());
    }
    @Test
    fun testTowerCount()  {
        val user: User = User("a",1)
        val user1: User = User("a",2)
        val user2: User = User("a",3)
        assertEquals(10, user.getMaxTowerNum())
        assertEquals(8, user1.getMaxTowerNum())
        assertEquals(6, user2.getMaxTowerNum())

    }

}
