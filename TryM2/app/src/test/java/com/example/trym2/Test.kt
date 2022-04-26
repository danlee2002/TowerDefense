

package com.example.trym2

import com.example.trym2.Model.*
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class Test {
    /**
     * Tests for Username 
     */
    @Test
    fun userNameIsChanged() {
        val User = User();
        User.setName("Bill");
        assertEquals(User.getName(),"Bill");

    }
    /**
     * Tests to see if tower values are initialized properly
     *
     */
    @Test
    fun testTowerDamage(){
        val easy = TowerWeak(1);
        val med = TowerMid(1);
        val strong = TowerStrong(1);

        assertEquals(easy.getAttackDamage(),1);
        assertEquals(med.getAttackDamage(),2);
        assertEquals(strong.getAttackDamage(),3);
        val test: Boolean = (easy.getAttackDamage() < med.getAttackDamage() &&
                med.getAttackDamage() < strong.getAttackDamage())
        assertEquals(test, true)
    }
    @Test 
    fun testMonument(){
        val mon = Monument(1);
        assertEquals(mon.getDamage(), 10);
        assertEquals(mon.getHp(), 50);

    }

   
    @Test
    fun testTowerPrice(){
        val easy = TowerWeak(1);
        val med = TowerMid(1);
        val strong = TowerStrong(1);
        val test: Boolean = (easy.getPrice() < med.getPrice() &&
        med.getPrice() < strong.getPrice() )

        assertEquals(easy.getPrice(), 5);
        assertEquals(med.getPrice(), 10);
        assertEquals(strong.getPrice(), 15);
        assertEquals(test, true);

    }
    @Test
    fun testGoldInitial() {
        val easy = User("a",1);
        val med = User("b",2);
        val strong = User("c",3);
        assertEquals(easy.getGold(), 100);
        assertEquals(med.getGold(), 75);
        assertEquals(strong.getGold(), 50);

    }
    @Test
    fun testEnemyIntialize() {
        val easy = Enemy(1)
        assertEquals(easy.getHp(), 10)
        assertEquals(easy.getDamage(), 10)
        assertEquals(easy.getDifficulty(), 1)

    }

    @Test
    fun testEnemyAttack() {
        val monument = Monument(1);
        val enemy = Enemy(1);
        val test = monument.getHp();
        enemy.attackMonument(monument);
        assertEquals(monument.getHp(),test - enemy.getDamage());

    }
    @Test
    fun testMonumentAttack() {
        val monument = Monument(1);
        val enemy = Enemy(1);
        var test = enemy.getHp();
        while(enemy.getHp() > 0 ) {
            monument.attackEnemy(enemy);
            test -= monument.getDamage();
        }
        assertEquals(enemy.getHp(),test);
        assertEquals(monument.getScore(),1);

    }
    @Test
    fun testTowerAttack() {
        val tower = TowerWeak(1);
        val monument = Monument(1);
        val enemy = Enemy(1);
        var test = enemy.getHp();
        var user: User = User("A",1)
        while(enemy.getHp() > 0 ) {
            tower.attackEnemy(enemy,monument,user);
            test -= tower.getAttackDamage();


        }
        assertEquals(enemy.getHp(),test);
        assertEquals(monument.getScore(),1);

    }
    @Test
    fun testTowerAttackSpeed(){
        val easy = TowerWeak(1);
        val med = TowerMid(1);
        val strong = TowerStrong(1);

        assertEquals(easy.getAttackSpeed(), 1);
        assertEquals(med.getAttackSpeed(), 2);
        assertEquals(strong.getAttackSpeed(), 3);
    }



}
