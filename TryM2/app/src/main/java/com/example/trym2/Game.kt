package com.example.trym2

import android.animation.*
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.trym2.Model.*
import com.example.trym2.View.GameOverView
import com.example.trym2.View.WinGameView
import kotlin.concurrent.timer

class Game : AppCompatActivity(), GameMap {

    private var enemy = Enemy()
    private var spending : Int = 0
    private var player = User()
    //var towerNum = arrayOf(0, 0, 0)
    private var gate = Monument(player.getDifficulty())
    private var started = 0;
    private val ani = AnimatorSet()
    private var isPass = 0
    private var enemykilled : Int = 0
    private var initialHealth : Int = 0
    private var upgrade = 1

    private var weakTowerPrice: Int = 0
    private var midTowerPrice: Int = 0
    private var strTowerPrice: Int = 0

    private var weakMonster1 = Enemy(player.getDifficulty())
    private var weakMonster2 = Enemy(player.getDifficulty())
    private var weakMonster3 = Enemy(player.getDifficulty())

    private lateinit var gold : TextView
    private lateinit var easyTowerNumber : TextView
    private lateinit var medTowerNumber : TextView
    private lateinit var hardTowerNumber : TextView

    private lateinit var easyTowerPrice : TextView
    private lateinit var medTowerPrice : TextView
    private lateinit var hardTowerPrice : TextView

    private lateinit var enemyChar :ImageView
    private lateinit var secondEnemy : ImageView

    override var anotherTowerClicked = false                          // --> Need to use this
    //override var place = arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)   // --> Need to use this

    override var place = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    private var towerAttacks = arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)

    //   private var enemyList : Array<Enemy?> = arrayOfNulls<Enemy>(10)

    //   lateinit var monumenthealth : Button
    private lateinit var monumentHP : TextView

    private lateinit var heart1 : ImageView
    private lateinit var heart2 : ImageView
    private lateinit var heart3 : ImageView
    private lateinit var heart4 : ImageView

    //timer
    // private var timer: Timer = Timer()

    private val yduration : Long = 2000                             // --> Need to use this
    private val xduration : Long = 8000                             // --> Need to use this

//    private lateinit var enemyCharList : Array<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.trym2.R.layout.game)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        player = (intent.getSerializableExtra("player") as? User)!!
       // Toast.makeText(applicationContext, player.getGold().toString(), Toast.LENGTH_LONG).show()
        player.setdiff(player.getDifficulty())
        //Toast.makeText(applicationContext, player.getMaxTowerNum().toString(), Toast.LENGTH_LONG).show()

        gate = Monument(player.getDifficulty())
        initialHealth = gate.getHp()
        enemy = Enemy(player.getDifficulty())
        // towerNum = (intent.getSerializableExtra("towersBought") as Array<Int>)!!

//        Toast.makeText(applicationContext, towerNum[0].toString(), Toast.LENGTH_LONG).show()
//        Toast.makeText(applicationContext, towerNum[1].toString(), Toast.LENGTH_LONG).show()
//        Toast.makeText(applicationContext, towerNum[2].toString(), Toast.LENGTH_LONG).show()


        gold = findViewById<TextView>(R.id.playerGold)
        gold.text = player.getGold().toString()

        easyTowerNumber = findViewById<TextView>(R.id.easyTowerNum)
        medTowerNumber = findViewById<TextView>(R.id.medTowerNum) as TextView
        hardTowerNumber = findViewById<TextView>(R.id.hardTowerNum) as TextView

        easyTowerNumber.text = player.getETNum().toString()
        medTowerNumber.text = player.getMTNum().toString()
        hardTowerNumber.text = player.getHTNum().toString()

        easyTowerPrice = findViewById<TextView>(R.id.buy1)
        medTowerPrice = findViewById<TextView>(R.id.buy2)
        hardTowerPrice = findViewById<TextView>(R.id.buy3)

        heart1 = findViewById<ImageView>(R.id.Heart1)
        heart2 = findViewById<ImageView>(R.id.Heart2)
        heart3 = findViewById<ImageView>(R.id.Heart3)
        heart4 = findViewById<ImageView>(R.id.Heart4)

        heart3.setImageResource(R.drawable.full)
        heart4.setImageResource(R.drawable.full)


        when (player.getDifficulty()){
            1 -> {
                heart1.setImageResource(R.drawable.full)
                heart2.setImageResource(R.drawable.full)
            }
            2 -> {
                heart1.setImageResource(R.drawable.zero)
                heart2.setImageResource(R.drawable.full)
            }
            3 -> {
                heart1.setImageResource(R.drawable.zero)
                heart2.setImageResource(R.drawable.zero)
            }

        }

        monumentHP = findViewById(R.id.gateHealth)
        monumentHP.text = gate.getHp().toString()

        weakTowerPrice = TowerWeak(player.getDifficulty()).getPrice()
        midTowerPrice = TowerMid(player.getDifficulty()).getPrice()
        strTowerPrice = TowerStrong(player.getDifficulty()).getPrice()

        easyTowerPrice.text = "$${weakTowerPrice}"
        medTowerPrice.text = "$${midTowerPrice}"
        hardTowerPrice.text = "$${strTowerPrice}"


        //ONLY A WAY TO TEST THE GAME OVER SCREEN!! REMOVE WHEN ENEMY IMPLEMENTED
//        monumenthealth = findViewById<Button>(R.id.monumenthealthdec)
//        monumentHP = findViewById<TextView>(R.id.gateHealth)
//
//        monumenthealth.setOnClickListener {
//            monumentHP.text = gate.getHp().toString()
//            gate.setHp(gate.getHp() - 10)
//            if (gate.getHp() <= 0) {
//                startActivity(Intent(this, GameOverView::class.java))
//            }
//        }


//        //
//        for (i in 0..enemyList.size) {
//            enemyList[i] = Enemy(player.getDifficulty())
//            enemyList[i]?.movePath()
//            enemyList[i]?.getX()
//            enemyList[i]?.getY()
//
//
//        }

        /**       enemyList[0] = Enemy(player.getDifficulty())
        enemyChar = findViewById(R.id.monster)
        enemyChar.x = (enemyList[0]?.getX())!!.toFloat()
        enemyChar.y = 200F
         **/
        // enemyList[0]?.movePath()

//        timer.scheduleAtFixedRate(0,1000) {
//            enemyChar.x = (enemyList[0]?.getX())!!.toFloat()
//            enemyChar.y = (enemyList[0]?.getY())!!.toFloat()
//
//        }


        enemyChar = findViewById(R.id.monster)
        enemyChar.translationY = 50f

//        secondEnemy = findViewById(R.id.monster2)
//        secondEnemy.translationY = 50f

    }

    private fun animation(v: View, difficulty: Int) {

        // ** WHEN WE USE ONLY ONE ENEMY **
        // For easy -> weak 1 * 1 | middle 1 | boss 1
        // For mid  -> weak 1 * 2 | middle 1 | boss 1
        // For hard -> weak 1 * 3 | middle 1 | boss 1


        // ** IF WE USE 2ND ENEMY **
        // For easy -> weak 1 * 1 | middle 1 * 1 | boss 1 * 1
        // For mid  -> weak 2 * 2 | middle 2 * 1 | boss 2 * 1
        // For hard -> weak 2 * 3 | middle 2 * 1 | boss 2 * 1

        monsterMove(enemyChar, difficulty, 1)
//        if (difficulty >= 2) {
//            secondEnemy.visibility = View.VISIBLE
//            monsterMove(secondEnemy, difficulty)
//        }


    }

    private fun checkRangeX(character: ImageView, minX: Float, maxX: Float, yVal: Float) : Boolean {
        if (character.translationX > minX && character.translationX <= maxX
            && character.translationY == yVal) {
            return true
        }
        return false
    }

    private fun checkRangeY(character: ImageView, minY: Float, maxY: Float, xVal: Float) : Boolean {
        if (character.translationY > minY && character.translationY <= maxY
            && character.translationY == xVal) {
            return true
        }
        return false
    }

    private fun checkAttack(num: Int) {
        //Toast.makeText(applicationContext, "Tower${num + 1} attack", Toast.LENGTH_LONG).show()
        if (towerAttacks[num] != 0) {
            towerAttacks[num] = 0
            attackEnemy(place[num])
            Toast.makeText(applicationContext, "HP: " + enemy.getHp().toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun monsterMove(character : ImageView, difficulty: Int, count: Int) {
        var count = count

        val animatorRight1 = ValueAnimator.ofFloat(0f, 2350f)
        animatorRight1.addUpdateListener {
            val value = it.animatedValue as Float
            character.translationX = value

            if (checkRangeX(character, 0f, 385f, 50f) && place[0] != 0) {
                checkAttack(0)
            }
            if (checkRangeX(character, 385f, 700f, 50f) && place[3] != 0) {
                checkAttack(3)
            }
            if (checkRangeX(character, 700f, 1300f, 50f) && place[1] != 0) {
                checkAttack(1)
            }
            if (checkRangeX(character, 1300f, 1700f, 50f) && place[4] != 0) {
                checkAttack(4)
            }
            if (checkRangeX(character, 1700f, 1900f, 50f) && place[2] != 0) {
                checkAttack(2)
            }
            if (checkRangeX(character, 1900f, 2300f, 50f) && place[5] != 0) {
                checkAttack(5)
            }
            if (character.translationX == 2350f) {
                character.scaleX = -1f
            }
        }
        animatorRight1.duration = xduration
        val animatorDown1 = ValueAnimator.ofFloat(50f, 400f)

        animatorDown1.addUpdateListener {
            val value = it.animatedValue as Float
            character.translationY = value

            if (checkRangeY(character, 50f, 400f, 2350f) && place[5] != 0) {
                checkAttack(5)
            }
        }
        animatorDown1.duration = yduration

        val animatorLeft1 = ValueAnimator.ofFloat(2350f, 0f)

        animatorLeft1.addUpdateListener {
            val value = it.animatedValue as Float
            character.translationX = value

            if (checkRangeX(character, 1700f, 2000f, 400f) && place[8] != 0) {
                checkAttack(8)
            }
            if (checkRangeX(character, 1200f, 1700f, 400f) && place[4] != 0) {
                checkAttack(4)
            }
            if (checkRangeX(character, 900f, 1200f, 400f) && place[7] != 0) {
                checkAttack(7)
            }
            if (checkRangeX(character, 400f, 900f, 400f) && place[3] != 0) {
                checkAttack(3)
            }
            if (checkRangeX(character, 0f, 400f, 400f) && place[6] != 0) {
                checkAttack(6)
            }
            if (character.translationX == 0f) {
                character.scaleX = 1f
            }
        }
        animatorLeft1.duration = xduration

        val animatorDown2 = ValueAnimator.ofFloat(400f, 700f)
        character.scaleX = 1f
        animatorDown2.addUpdateListener {
            val value = it.animatedValue as Float
            character.translationY = value

            if (checkRangeY(character, 400f, 700f, 0f) && place[6] != 0) {
                checkAttack(6)
            }
        }
        animatorDown2.duration = yduration

        val animatorRight2 = ValueAnimator.ofFloat(0f, 2200f)
        animatorRight2.addUpdateListener {
            val value = it.animatedValue as Float
            character.translationX = value
            //println("(${enemyChar.translationX}, ${enemyChar.translationY})")

            if (checkRangeX(character, 50f, 380f, 700f) && place[6] != 0) {
                checkAttack(6)
            }
            if (checkRangeX(character, 380f, 700f, 700f) && place[9] != 0) {
                checkAttack(9)
            }
            if (checkRangeX(character, 700f, 1340f, 700f) && place[7] != 0) {
                checkAttack(7)
            }
            if (checkRangeX(character, 1340f, 1680f, 700f) && place[10] != 0) {
                checkAttack(10)
            }
            if (checkRangeX(character, 1680f, 2100f, 700f) && place[8] != 0) {
                checkAttack(8)
            }
            if (checkRangeX(character, 2100f, 2150f, 700f)) {
                isPass = 1
            }
//            var x  = character.translationX
//            var y = character.translationY

            // When it hits the monument
           // if (checkRangeX(character, 2100f, 2200f, 700f))
//            var x  = character.translationX
//            var y = character.translationY
//            if (character.translationX == 2200f && character.translationY == 700f) {
//                isPass = 1
//                attackMonument(count)
//            }
        }

        animatorRight2.duration = xduration
        ani.playSequentially(animatorRight1, animatorDown1, animatorLeft1, animatorDown2, animatorRight2)
        ani.interpolator = LinearInterpolator()

        ani.addListener(object : AnimatorListenerAdapter() {
            private var mCanceled = false

            override fun onAnimationStart(animation: Animator) {
              //  c = max(c, count)
                character.visibility = View.VISIBLE
                //Toast.makeText(applicationContext, "COUNT : $count", Toast.LENGTH_LONG).show()
                //Toast.makeText(applicationContext, "START LOOP" + enemy.getHp().toString(), Toast.LENGTH_LONG).show()
                towerAttacks = arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
                var newEnemy = Enemy(difficulty)
                enemy.setHp(newEnemy.getHp() + 2 * (count - 1))
                isPass = 0
                mCanceled = false
                //ani.playSequentially(animatorRight1, animatorDown1, animatorLeft1, animatorDown2, animatorRight2)
            }

            override fun onAnimationCancel(animation: Animator) {
//                mCanceled = true
//                if (mCanceled) {
//                }
//                if (count == 2) {
//                    character.setImageResource(R.drawable.monster2)
//                } else if (count == 3) {
//                    character.setImageResource(R.drawable.bossmonster)
//                } else if (count >= 4){
//                    ani.removeAllListeners() // You WIN
//                }
//                count++
//                //mCanceled = false // DELETE MAYBE
//                Toast.makeText(applicationContext, "CANCEL", Toast.LENGTH_LONG).show()
////                    mCanceled = false
//                character.translationX = 0f
//                character.translationY = 50f
//                enemyChar.visibility = View.VISIBLE
//                ani.playSequentially(animatorRight1, animatorDown1, animatorLeft1, animatorDown2, animatorRight2)
//                ani.start()
            }

            override fun onAnimationEnd(animation: Animator) {
                //Toast.makeText(applicationContext, "count:$count", Toast.LENGTH_LONG).show()
                if (isPass == 1) {
                    //Toast.makeText(applicationContext, "pass:$count", Toast.LENGTH_LONG).show()
                    attackMonument(count)
                }

                if (gate.getHp() <= 0) {
                    goToNewPage("LOSE")
                }

                when {
                    count == 1 -> character.setImageResource(R.drawable.monster2)
                    count == 2 -> character.setImageResource(R.drawable.bossmonster)
                    count == 3 -> character.setImageResource(R.drawable.finalboss)
                    //count >= 4 -> goToNewPage("WIN") // YOU WIN
                }

                if (count >= 4 && gate.getHp() > 0) {
                    goToNewPage("WIN")
                }

                //Toast.makeText(applicationContext, "END", Toast.LENGTH_LONG).show()
                count++
                ani.start()
//                if (!mCanceled) {
//                    It was inside this loop
//                }
            }

        })
        ani.start()
    }

    fun goToNewPage(result : String) {
        ani.removeAllListeners()
        enemyChar.visibility = View.INVISIBLE
        if (result == "WIN") {
            Toast.makeText(applicationContext, "YOU WIN THE GAME!!!!!", Toast.LENGTH_LONG).show()
            val winintent = Intent(this, WinGameView::class.java)
            winintent.putExtra("player", player)
            startActivity(winintent)
        } else {
            val loseintent = Intent(this, GameOverView::class.java)
            loseintent.putExtra("player", player)
            startActivity(loseintent)
        }

    }

    fun attackEnemy(towerLevel: Int) {
        var color = when {
            upgrade == 1 -> Color.RED
            upgrade == 2 -> Color.BLUE
            else -> Color.BLACK
        }

        enemyChar.setColorFilter(color)

        when (towerLevel) {
            1 -> enemy.setHp(enemy.getHp() - TowerWeak(player.getDifficulty()).attackDamage - (upgrade - 1) * 2)
            2 -> enemy.setHp(enemy.getHp() - TowerMid(player.getDifficulty()).attackDamage - (upgrade - 1) * 2)
            3 -> enemy.setHp(enemy.getHp() - TowerStrong(player.getDifficulty()).attackDamage - (upgrade - 1) * 2)
        }
        var second : Double = 0.0
        timer(period = 100, initialDelay = 100) {
          second += 0.5
            if (second == 0.5) {
                enemyChar.clearColorFilter()
            }
            enemyChar.setColorFilter(color)
            if (second == 1.0) {
                enemyChar.clearColorFilter()
                cancel()
            }
        }
        if (enemy.getHp() <= 0) {
            enemyChar.visibility = View.INVISIBLE
            player.player_stats.setEnemyK(player.player_stats.getEnemyK() + 1)
            updateGold(player.getGold() + (player.getDifficulty()*10))
            ani.end()
        }
        enemyChar.clearColorFilter();
    }

    fun updateGold (newgold : Int) {
        player.setGold(newgold)
        gold.text = player.getGold().toString()
    }

    private fun attackMonument(count: Int) {
        monumentHP = findViewById(R.id.gateHealth)

        var damage = enemy.getDamage() * count
        //

        gate.setHp(gate.getHp() - damage)
        monumentHP.text = gate.getHp().toString()
        //enemyChar.visibility = View.INVISIBLE

        //player.player_stats.setEnemyK(enemykilled)
        player.player_stats.setDamaget(initialHealth - gate.getHp())
        player.player_stats.setGolds(player.player_stats.getGolds() + spending)

        Toast.makeText(applicationContext, "enemyK = ${player.player_stats.getEnemyK()}", Toast.LENGTH_LONG).show()
        Toast.makeText(applicationContext, "Damage = ${player.player_stats.getDamaget()}", Toast.LENGTH_LONG).show()
        Toast.makeText(applicationContext, "Golds = ${player.player_stats.getGolds()}", Toast.LENGTH_LONG).show()

        //Toast.makeText(applicationContext, "hp = ${gate.getHp()}", Toast.LENGTH_LONG).show()

//        if (gate.getHp() > 0) {
//            showMonumentHP(gate.getHp())
//        } else if (gate.getHp() <= 0){
//            goToNewPage("LOSE")
//        }

//        if (count == 4) {
//            goToNewPage("WIN")
//        }

        if (gate.getHp() > 0) {
            showMonumentHP(gate.getHp())
        } else {
            goToNewPage("LOSE")
        }
        if (count == 4) {
            goToNewPage("WIN")
        }

    }



    fun showMonumentHP(hp : Int) {
        val full = R.drawable.full
        val half = R.drawable.half
        val zero = R.drawable.zero

        when {
            hp == 100 -> setHPImage(full, full, full, full)
            (hp < 100 && hp > 85) -> setHPImage(half, full, full, full)
            (hp <= 85 && hp > 75) -> setHPImage(zero, full, full, full)
            (hp <= 75 && hp > 50) -> setHPImage(zero, half, full, full)
            (hp <= 50 && hp > 37.5) -> setHPImage(zero, zero, full, full)
            (hp <= 37.5 && hp > 25) -> setHPImage(zero, zero, half, full)
            (hp <= 25 && hp > 12.5) -> setHPImage(zero, zero, zero, full)
            (hp <= 12.5 && hp > 0) -> setHPImage(zero, zero, zero, half)
            hp == 0 -> setHPImage(zero, zero, zero, zero)
        }
    }

    private fun setHPImage(first: Int, second: Int, third: Int, fourth: Int) {
        heart1.setImageResource(first)
        heart2.setImageResource(second)
        heart3.setImageResource(third)
        heart4.setImageResource(fourth)
    }

    fun ewTower(v: View) {
        if (started == 1 && !anotherTowerClicked) {
            anotherTowerClicked = true
            if (player.getETNum() > 0) {
                val tower = R.drawable.tower1
                clickTower(tower, 1)
                player.setETNum(player.getETNum() - 1)
                easyTowerNumber.text = player.getETNum().toString()
                //   Toast.makeText(applicationContext, " towers left: " + towerNum[0].toString(), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Out of Weak Towers!!!", Toast.LENGTH_LONG).show()
                anotherTowerClicked = false
            }
        }
    }

    fun emTower(v: View) {
        if (started == 1 && !anotherTowerClicked) {
            anotherTowerClicked = true
            if (player.getMTNum() > 0) {
                val tower = R.drawable.tower2
                clickTower(tower, 2)
                player.setMTNum(player.getMTNum() - 1)
                medTowerNumber.text = player.getMTNum().toString()
                // Toast.makeText(applicationContext, "Mid towers left: " + towerNum[1].toString(), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Out of Mid Towers!!!", Toast.LENGTH_LONG).show()
                anotherTowerClicked = false
            }
        }
    }


    fun esTower(v: View) {
        if (started == 1 && !anotherTowerClicked) {
            anotherTowerClicked = true
            if (player.getHTNum() > 0) {
                val tower = R.drawable.tower3
                clickTower(tower, 3)
                player.setHTNum(player.getHTNum() - 1)
                hardTowerNumber.text = player.getHTNum().toString()
                //  Toast.makeText(applicationContext, "Hard towers left: " + towerNum[2].toString(), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Out of Hard Towers!!!", Toast.LENGTH_LONG).show()
                anotherTowerClicked = false
            }
        }
    }

    fun weakTowerBuy(v: View) {
        if (started == 1) {
            if (!player.overMaxTower()) {
                if (hasEnoughGold(weakTowerPrice)) {
                    player.setGold(player.getGold() - weakTowerPrice)
                    player.setETNum(player.getETNum() + 1)
                    gold.text = player.getGold().toString()
                    easyTowerNumber.text = player.getETNum().toString()
                    spending += weakTowerPrice
                    //Toast.makeText(applicationContext, "MAX: ${player.getMaxTowerNum()} || CURR: ${player.getBoughtTower()}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "NO MORE MONEY", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(applicationContext, "BOUGHT TOO MANY TOWERS", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun midTowerBuy(v: View) {
        if (started == 1) {
            if (!player.overMaxTower()) {
                if (hasEnoughGold(midTowerPrice)) {
                    player.setGold(player.getGold() - midTowerPrice)
                    player.setMTNum(player.getMTNum() + 1)
                    gold.text = player.getGold().toString()
                    medTowerNumber.text = player.getMTNum().toString()
                    spending += midTowerPrice
                    //Toast.makeText(applicationContext, "MAX: ${player.getMaxTowerNum()} || CURR: ${player.getBoughtTower()}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "NO MORE MONEY", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(applicationContext, "BOUGHT TOO MANY TOWERS", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun strTowerBuy(v: View) {
        if (started == 1) {
            if (!player.overMaxTower()) {
                if (hasEnoughGold(strTowerPrice)) {
                    player.setGold(player.getGold() - strTowerPrice)
                    player.setHTNum(player.getHTNum() + 1)
                    gold.text = player.getGold().toString()
                    hardTowerNumber.text = player.getHTNum().toString()
                    spending += strTowerPrice
                    //Toast.makeText(applicationContext, "MAX: ${player.getMaxTowerNum()} || CURR: ${player.getBoughtTower()}", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "NO MORE MONEY", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(applicationContext, "BOUGHT TOO MANY TOWERS", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun upgradeTower(v: View) {
        if (upgrade == 1 && player.getGold() >= 60) {
            upgrade = 2
            Toast.makeText(applicationContext, "UPGRADE", Toast.LENGTH_LONG).show()
            updateGold(player.getGold() - 60)
            spending += 60

//            weakTowerPrice *= 2
//            midTowerPrice *= 2
//            strTowerPrice *= 2
//
//            easyTowerPrice.text = "$${weakTowerPrice}"
//            medTowerPrice.text = "$${midTowerPrice}"
//            hardTowerPrice.text = "$${strTowerPrice}"
        }
    }

//    fun canBuyTower() : Boolean {
//        Toast.makeText(applicationContext, "MAX: ${player.getMaxTowerNum()} || CURR: ${player.getBoughtTower()}", Toast.LENGTH_LONG).show()
//        return (player.getMaxTowerNum() > player.getBoughtTower())
//    }

    fun hasEnoughGold(price: Int) : Boolean {
        return (player.getGold() >= price)
    }

    override fun clickTower(towerResource: Int, towerLevel : Int) {
        var clicked = 0
        //Toast.makeText(applicationContext, towerLevel.toString(), Toast.LENGTH_LONG).show()
        val btn1 = findViewById<Button>(R.id.towerPlace1)
        val btn2 = findViewById<Button>(R.id.towerPlace2)
        val btn3 = findViewById<Button>(R.id.towerPlace3)
        val btn4 = findViewById<Button>(R.id.towerPlace4)
        val btn5 = findViewById<Button>(R.id.towerPlace5)
        val btn6 = findViewById<Button>(R.id.towerPlace6)
        val btn7 = findViewById<Button>(R.id.towerPlace7)
        val btn8 = findViewById<Button>(R.id.towerPlace8)
        val btn9 = findViewById<Button>(R.id.towerPlace9)
        val btn10 = findViewById<Button>(R.id.towerPlace10)
        val btn11 = findViewById<Button>(R.id.towerPlace11)

        if (place[0] == 0) {
            btn1.setOnClickListener(View.OnClickListener {
                if (clicked == 0) {
                    btn1.setBackgroundResource(towerResource);
                    place[0] = towerLevel;
                    clicked = 1
                    anotherTowerClicked = false
                }
            })
        }
        if (place[1] == 0) {
            btn2.setOnClickListener(View.OnClickListener {
                if (clicked == 0) {
                    btn2.setBackgroundResource(towerResource);
                    place[1] = towerLevel;
                    clicked = 1
                    anotherTowerClicked = false
                }
            })
        }
        if (place[2] == 0) {
            btn3.setOnClickListener(View.OnClickListener {
                if (clicked == 0) {
                    btn3.setBackgroundResource(towerResource);
                    place[2] = towerLevel;
                    clicked = 1
                    anotherTowerClicked = false
                }
            })
        }
        if (place[3] == 0) {
            btn4.setOnClickListener(View.OnClickListener {
                if (clicked == 0) {
                    btn4.setBackgroundResource(towerResource);
                    place[3] = towerLevel;
                    clicked = 1
                    anotherTowerClicked = false
                }
            })
        }
        if (place[4] == 0) {
            btn5.setOnClickListener(View.OnClickListener {
                if (clicked == 0) {
                    btn5.setBackgroundResource(towerResource);
                    place[4] = towerLevel;
                    clicked = 1
                    anotherTowerClicked = false
                }
            })
        }
        if (place[5] == 0) {
            btn6.setOnClickListener(View.OnClickListener {
                if (clicked == 0) {
                    btn6.setBackgroundResource(towerResource);
                    place[5] = towerLevel;
                    clicked = 1
                    anotherTowerClicked = false
                }
            })
        }
        if (place[6] == 0) {
            btn7.setOnClickListener(View.OnClickListener {
                if (clicked == 0) {
                    btn7.setBackgroundResource(towerResource);
                    place[6] = towerLevel;
                    clicked = 1
                    anotherTowerClicked = false
                }
            })
        }
        if (place[7] == 0) {
            btn8.setOnClickListener(View.OnClickListener {
                if (clicked == 0) {
                    btn8.setBackgroundResource(towerResource);
                    place[7] = towerLevel
                    clicked = 1
                    anotherTowerClicked = false
                }
            })
        }
        if (place[8] == 0) {
            btn9.setOnClickListener(View.OnClickListener {
                if (clicked == 0) {
                    btn9.setBackgroundResource(towerResource);
                    place[8] = towerLevel
                    clicked = 1
                    anotherTowerClicked = false
                }
            })
        }
        if (place[9] == 0) {
            btn10.setOnClickListener(View.OnClickListener {
                if (clicked == 0) {
                    btn10.setBackgroundResource(towerResource);
                    place[9] = towerLevel
                    clicked = 1
                    anotherTowerClicked = false
                }
            })
        }

        if (place[10] == 0) {
            btn11.setOnClickListener {
                if (clicked == 0) {
                    btn11.setBackgroundResource(towerResource);
                    place[10] = towerLevel
                    clicked = 1
                    anotherTowerClicked = false
                }
            }
        }
    }

    fun startCombat(v: View) {
        val combat = findViewById<ImageView>(R.id.startcombat) as ImageView


        started = 1;
        combat.visibility = View.INVISIBLE

        animation(v, player.getDifficulty())
    }

    fun quitGame(v: View) {
        finish()
    }

}