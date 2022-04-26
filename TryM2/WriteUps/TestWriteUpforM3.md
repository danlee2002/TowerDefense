# Test Writeup

### Test 1: funUser
The primary purpose behind the first text is to test if the functionality for changing the username is valid. A key tenet to the functionality of the game is to allow for the username in the game to be saved so they can proceed with the game. This test does this by invoking an instance of the User class and using a setter for the username to set the name to a string. Then the test takes the string that was taken in as a parameter and compares it to the username to the instance of the User class.  

### Test 2: testTowerDamage() 
In this test, the primary purpose of this test was to ensure that the tower damage values of the towers differed based on tiers and were intialized to the intended values. In M3, the task of implementing towers was delegated, one of the attribute of the tower was its attackDamage. This test intends to see if the values within the testTower damage are initialized properly and vary based on tiers.

### Test 3: testMonument() 
In this test, the monument object is initalized and tested to see if the appropiate fields were initialized properly. In M2, we were told to create a graphical visualization of the Monument in game. This test intends to test to see if the class representation of the Monument is a valid representation.

### Test 4: testTowerPrice()
In this milestone we were told to create a Tower class. In this test, the tower is tested to see if the price values vary based on tiers and are initialized properly. 

### Test 5: testGoldInitial()
In this milestone, we were told to create an instance of the Player class. An attribute of the player class was the intended godl value. In this implementation, the player class is tested to see if the intended gold values of the objects are achieved. 

### Test 6: testEnemyIntialize()
In this test, the enemy class which is involved in some of the methods and attributes of the tower class is tested to see if the fields of the class have been initialized properly.

### Test 7:testEnemyAttack()
In this test, the attack() method of the Enemy is tested to see if modifies the health attribute of the Monument properly. The enemy attack method is invoked multiple times until the Monument health is below 0. Then it tests to see if appropriate health value 

### Test 8: testMonumentrAttack()
In this test, the attack() method of the tower is tested. In Milestone 2, we were told to implement a graphical representation of the Monument class and in this method the attack() a behavior of the Monument class is tested to see if if it modifies the health attribute of the enemy properly. 

### Test 9: testTowerAttack()
In this test, the attack() method of the tower is tested. In Milestone 3, we were told to implement the Tower class and in this method the attack() a behavior of the Tower class is tested to see if if it modifies the health attribute of the enemy properly. The test invokes the attack() method  on an instance of an enemy that is initialized until then health is 0 or less. 

### Test 10:
In this test, a test is written to test if the attackSpeed values of a tower are initialized properly when an instance of tower is intialized using a constructor. Three different instances of Tower of different tiers are created and the values of the attackSpeed are compared to expected value to see if they are appropriate. 


