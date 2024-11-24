# Task 2.
# Sun Stone

## Design

1. **src/main/resources/skins/default.json** add the picture of sun_stone
2. **src/main/java/dungeonmania/entities/collectables/SunStone.java** add the class of sun_stone
3. **src/main/java/dungeonmania/entities/EntityFactory.java** add the constructure of sun_stone
4. **src/main/java/dungeonmania/map/GraphNodeFactory.java** add the creation of sun_stone node

## Some Details

Now that sun_stone is a special form of treasure, I make SunStone extend Treasure. So when using the reflection mechanism in Java to retrieve a Treasure object, it's important to filter out the SunStone class.

### Test List

Since sun_stone is a form of collectable element, I didn't write a test class because I tested SunStone by the way in other tests.

# Sceptre

## Design

1. **src/main/resources/skins/default.json** add the picture of sceptre
2. **src/main/java/dungeonmania/entities/buildables/Sceptre.java** add the class of Sceptre
3. **src/main/java/dungeonmania/entities/EntityFactory.java** add the constructure of Sceptre
4. **src/main/java/dungeonmania/map/GraphNodeFactory.java** add the creation of sceptre node
5. Modify config files. Since the sceptre can be used to control enemies' minds, "mind_control_duration" should be added to config files and read by the procedure.
6. add the mind_controlled function and set the duration ticks.

## Some Details

Now that bribing and mind controlling are two different ways to make mercenaries or assassins to become allies, there should be an attribute to determine the two ways. (mind controlling last for certain ticks, while bribing not).

## Test List

1. Test crafting a sceptre
2. Test mind controlled by sceptre.
3. Test the tcks limitation of mind control by sceptre

## Midnight Armour

## Design

1. **src/main/resources/skins/default.json** add the picture of midnight armour
2. **src/main/java/dungeonmania/entities/buildables/MidnightArmour.java** add the class of MidnightArmour
3. **src/main/java/dungeonmania/entities/EntityFactory.java** add the constructure of MidnightArmour
4. **src/main/java/dungeonmania/map/GraphNodeFactory.java** add the creation of Midnight Armour node
5. Modify config files. Midnight armour can provide extra attack damage as well as protection, so "midnight_armour_attack" and "midNightArmourDefence" should be added to config files.
6. set the buff of midnight_armour.

## Test List

1. Test MidnightArmour buildable without zombie.
2. Test MidnightArmour buildable without zombie.
3. Test midnightArmour reduces enemy attack and add player's attack. (merged into BattleTest.java)