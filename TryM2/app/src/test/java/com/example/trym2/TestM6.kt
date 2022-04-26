package com.example.trym2
import android.util.DisplayMetrics
import com.example.trym2.Model.*
import org.junit.Test
import org.junit.Assert.*
class TestM6 {
    @Test
    fun testUpgradeTowerDamage() {
        val tower1 = TowerMid(1)
        val user = User("Bill",1)
        tower1.upgrade(user)
        assertEquals(7,tower1.getAttackDamage())

    }

    @Test
    fun testUpgradeTowerGold() {
        val tower1 = TowerMid(1)
        val user = User("Bill",1)
        tower1.upgrade(user)
        assertEquals(95, user.getGold())
    }

    @Test
    fun testWonGame() {
        var boss = Boss(1)
        var tower = TowerMid(1)
        var mon = Monument(1)
        var user = User("test",1)
        while(boss.getHp() > 0 ) {
            tower.attackBoss(boss, mon, user)
        }
        assertEquals(true, user.getWon())

    }
    @Test
    fun testCanAttack() {
        var boss: Boss = Boss(1)
        var monument: Monument = Monument(1)
        var user: User = User("a",1)
        assertEquals(false, boss.canAttack())
    }

    @Test
    fun testScore(){
        var test: User = User("User", 1)
        var tower: Tower = TowerWeak(1)
        var monument: Monument = Monument(1)
        var boss: Boss = Boss(1)
        while (boss.getHp() > 0 ) {
            tower.attackBoss(boss, monument, test)

        }
        assertEquals(1, monument.getScore())
    }
    @Test
    fun testBossDead() {
        var boss =  Boss(1)
        var tower =  TowerWeak(1)
        var mon = Monument(1)
        var user = User("a",1)
        while (boss.getHp() > 0) {
            tower.attackBoss(boss, mon,  user)
        }

        assertEquals(false, boss.getAlive())
    }

    @Test
    fun testBossAttack() {
        var boss = Boss(1)
        var monument = Monument(1)
        boss.attackMonument(monument)
        assertEquals(90,monument.getHp())

    }
    @Test
    fun testMonumentAttack() {
        var monument = Monument(1)
        var boss = Boss(1)
        monument.attackBoss(boss)
        assertEquals(10,boss.getHp())

    }
    @Test
    fun testMoveX() {
        val boss = Boss(1);
        boss.moveHorizontal(100)

        assertEquals(boss.getX(),100)
        assertEquals(0,boss.getY())
    }

    @Test
    fun testMoveY() {
        val boss = Enemy(1);
        boss.moveVertical(100)
        assertEquals(boss.getY(),100)
        assertEquals(0,boss.getX())
    }




}