package com.example.trym2
import android.util.DisplayMetrics
import com.example.trym2.Model.*
import org.junit.Test
import org.junit.Assert.*
class TestM5 {
    @Test   
    fun testTowerAttack() {
        var test: User = User("John Doe", 1)
        var tower: Tower = TowerWeak(1)
        var monument: Monument = Monument(1)
        var enemy: Enemy = Enemy(1)
        while (enemy.getHp() > 0 ) {
            tower.attackEnemy(enemy,monument, test)

        }
        assertEquals(110,test.getGold())
    }
    @Test
    fun testDeath() {
        var monument: Monument = Monument(1)
        var enemy: Enemy = Enemy(1)
        for (i in 0..10) {
            enemy.attackMonument(monument)
        }
        assertEquals(true,monument.getIsDead())



    }
   

    @Test
    fun testBuy() {
        var test: User = User("Daddy",1)
        var tower: Tower = TowerWeak(1)
        test.buyTower(tower)
        assertEquals(95,test.getGold())

    }
    @Test
    fun testEnemyAttack() {
        var enemy: Enemy = Enemy(1)
        var monument: Monument = Monument(1)
        enemy.attackMonument(monument)
        assertEquals(90, monument.getHp())
    }
    @Test
    fun testMonumentAttack() {
        var enemy: Enemy = Enemy(1)
        var monument: Monument = Monument(1)
        var user: User = User("a",1)
        monument.attackEnemy(enemy, User())
        assertEquals(0, enemy.getHp())
    }
    @Test
    fun testEnemyDead() {
        var enemy: Enemy = Enemy(1)
        var monument: Monument = Monument(1)
        var user: User = User("a",1)
        monument.attackEnemy(enemy, User())
        assertEquals(false, enemy.getAlive())
    }

    @Test
    fun testCanAttack() {
        var enemy: Enemy = Enemy(1)
        var monument: Monument = Monument(1)
        var user: User = User("a",1)
        assertEquals(false, enemy.canAttack())
    }

    @Test
    fun testTowerDamage() {
        var tower: TowerWeak = TowerWeak(1)
        var tower1: TowerMid = TowerMid(1)
        var tower2: TowerStrong = TowerStrong(1)
        assertEquals(1,tower.getAttackDamage())
        assertEquals(2, tower1.getAttackDamage())
        assertEquals(3,tower2.getAttackDamage())

    }
    @Test
    fun testTowerAttackSpeed() {
        var tower: TowerWeak = TowerWeak(1)
        var tower1: TowerMid = TowerMid(1)
        var tower2: TowerStrong = TowerStrong(1)
        assertEquals(1,tower.getAttackSpeed())
        assertEquals(2, tower1.getAttackSpeed())
        assertEquals(3,tower2.getAttackSpeed())

    }
 @Test
    fun testScore(){
        var test: User = User("John Doe", 1)
        var tower: Tower = TowerWeak(1)
        var monument: Monument = Monument(1)
        var enemy: Enemy = Enemy(1)
        while (enemy.getHp() > 0 ) {
            tower.attackEnemy(enemy,monument, test)

        }
        assertEquals(1, monument.getScore())
    }








}
