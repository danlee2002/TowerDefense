# Test Writeup For M4

### Test 1: testTowerAttack()
    In this test, the Tower class was checked to see if if it properly updated gold. It compares User gold after an enemy died to an expected value.

### Test 2: testDeath()
    The following test, tests to see if the monument isAlive variable changes when it reaches 0, by comparing it to the expected value of false.

### Test 3: testBuy()
    This test, checks to see if buying a tower properly deducts gold. The User buys a tower and a getter is used to see if the UserGold was deducted properly

### Test 4: testEnemyAttack()
    Tests if monument hp attribute is altered properly after being attacked by comparing to an expected value. 
### Test 5: testMonumentAttack()
    Tests to see if attackEnemy() works properly and deducts enemy health accordingly.

### Test 6: testEnemyDead()
    Checks to see if if Alive attribute is false if enemy is dead.

### Test 7: testCanAttack()
    Tests to see if enemyCanAttack(). Checks to see if enemy canAttack attribute is true based on if enemy is in range of tower or not or hp is less than or equal to 0.
### Test 8: testTowerDamage()
    Tests if the AttackDamage attribute of tower is correct for all 3 towers by initializing and comparing values to expected values.

### Test 9:  testTowerAttackSped()
    Tests if the AttackSpeed attribute of tower is correct for all 3 towers by initializing and comparing values to expected values.

### Test 10: testScore() 
    Tests to see if score attribute of User() updates properly when Enemy health attribute becomes less than equal to 0. 
    
