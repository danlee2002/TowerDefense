package com.example.trym2.Model

abstract class Tower() {

    // Depends on the level of difficulty
    abstract val attackSpeed: Int

    // Depends on the level of difficulty
    abstract var attackDamage: Int

    // Depends on the level of difficulty
    abstract val price: Int
    private var canAttack: Boolean = false
    

    // This should be the same, but we should change the value
    // Distance from the tower to the Enemy
    val attackRange: Int = 20

    @JvmName("getAttackDamage1")
    fun getAttackDamage(): Int {
        return attackDamage
    }

    @JvmName("getAttackSpeed1")
    fun getAttackSpeed(): Int {
        return attackSpeed
    }

    @JvmName("getPrice1")
    fun getPrice(): Int {
        return price

    }

    
    fun attackEnemy(target: Enemy, mon: Monument, user: User) {
        target.setHp(target.getHp() - this.attackDamage);
        if (target.getHp() <= 0) {
            mon.setScore(mon.getScore() + 1);
            user.setGold(user.getGold() + target.getGold())

        }


    }
    fun attackBoss(target: Boss, mon: Monument, user: User) {
        target.setHp(target.getHp() - this.attackDamage);
        if (target.getHp() <= 0) {
            mon.setScore(mon.getScore() + 1);
            user.setGold(user.getGold() + target.getGold())
            user.setWon(true)
        }


    }
    fun upgrade(user: User) {
        this.attackDamage +=5
        user.setGold(user.getGold() - 5)


    }

}

