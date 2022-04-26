# Test Writeup For M4

### Test 1: testMaxTowerNum()
    In this test, we will create three instances of User class. Difficulty levels for each user are easy, moderate, and difficult. Therefore, this test will check if the maximum number of towers user can have is properly stored according to the difficulty level.

### Test 2: testIfMonumentDead()
    This test creates an instance of the Monument class and explicitly sets the hp (hitpoint) of the Monument to 0 so we can test the life status. We can test it by calling on to the public boolean variable in the class: isAlive. So, we set the hp to 0 and we should see that isAlive would return false since the hp indicates that the Monument has lost all of its HP.

### Test 3: testMonumentScore()
    Score is the number of enemies that have passed through the Monument. In other words, the number of enemies that have dealt damage to the Monument and also gotten killed by "passing through" it. For this, we create two instances of the Enemy class: enemy1 and enemy2. We also create an instance of Monument class to test the enemies going through the monument. Monument's damage is set to a 1000 so we can kill the enemies instantly (for this test case purpose). We make the monument attack enemy1 and enemy2 with the attackEnemy() function in the Monument class. Since the attack would have to instant kill the enemies, our score should be 2 now since we have killed both enemies. Which is what the asserEquals tests.

### Test 4: Tests Tower.enemyAttack()
    Tests if hp attribute is valid after tower attacks enemy
### Test 5: Tests moveHorizontal()
    The primary purpose behind this test is to test to see if the moveHorizontal() function works properly for the enemy. The enemies in the game are assigned coordinates based on their location on the screen using pixels. This test ensures the method used to translate the enemies accross the screen works properly by calling the moveHorizontal() method and comparing it to the expected value it is expected to have after the method finishes. 

### Test 6: Tests moveVertical()
    The primary purpose behind this test is to see if the moveVertical() methods works properly for the enemy.  The enemies in the game are assigned coordinates based on their location on the screen using pixels. This test ensures the method used to translate the enemies accross the screen works properly by calling the moveVertical() method and comparing it to the expected value it is expected to have after the method finishes. Through this test, we can see if the enemy is translated properly and the x coordinate attribute is preserved by the test. 

### Test 7: Tests attackEnemy()
    The primary purpose behind this test is to see if the attackEnemy() method works properly for the Monument class. The method compares the health values with the expected health values which change based off of if the Monument is in attack range or not.  
### Test 8: Tests EnemyLocation()
    Tests if the enemy is on a appropriateiate location.

### Test 9: Tests testTile()
    This test checks the tile x and y according to the device widthPixels.

### Test 10: Tests to see if maxTower count is intialized properly
    This test check to see if the maxTower count is initialized properly based on difficult. It compares expected value to 
    actual maxTower count.
