package com.example.trym2.Model

class Monument {
    private var hp: Int = 100;
    private var damage: Int = 10; //Monument will not be doing damage
    private var difficulty: Int = 0;
    private var score: Int = 0;
    private var x: Int = 0
    private var y: Int = 0
    private var range: Int = 10
    var isAlive : Boolean = true;


    constructor(difficulty: Int) {
        this.difficulty = difficulty
        this.hp -= (difficulty - 1) *25
        //this.damage = this.damage / difficulty;

    }

    constructor(difficulty: Int, x: Int, y: Int) {
        this.difficulty = difficulty
        this.x  = x
        this.y = y
//        this.hp = this.hp - ((difficulty - 1) * 25)
        //this.damage = this.damage / difficulty;

    }


    fun getHp(): Int {
        return hp
    }

    fun getScore(): Int {
        return score
    }


    fun getDamage(): Int {
        return damage
    }

    fun setHp(hp: Int) {
        this.hp = hp;
        checkLife()
    }

    fun setScore(score: Int) {
        this.score = score;

    }

    fun setDamage(damage: Int) {
        this.damage = damage;
    }

    fun attackEnemy(target: Enemy) {
        if (Math.sqrt(
                Math.pow(target.getX().toDouble(), 2.0) + Math.pow(
                    target.getY().toDouble(),
                    2.0
                )
            ) <= range
        ) {
            target.setHp(target.getHp() - this.damage);
            if (target.getHp() <= 0) {
                this.score++;

            }
        }
    }
    fun attackBoss(target: Boss) {
        if (Math.sqrt(
                Math.pow(target.getX().toDouble(), 2.0) + Math.pow(
                    target.getY().toDouble(),
                    2.0
                )
            ) <= range
        ) {
            target.setHp(target.getHp() - this.damage);
            if (target.getHp() <= 0) {
                this.score++;

            }
        }
    }

        fun attackEnemy(target: Enemy,user: User) {
            if (Math.sqrt(Math.pow(target.getX().toDouble(), 2.0) + Math.pow(target.getY().toDouble(), 2.0)) <= range) {
                target.setHp(target.getHp() - this.damage);
                if (target.getHp() <= 0) {
                    this.score++;
                    user.setGold(user.getGold() + 1)

                }
            }

    }


    fun checkLife() {
        isAlive = hp > 0
    }
    fun getIsDead(): Boolean {
        return hp <= 0
    }


}