# Task 1.
## a) From DRY to Design Patterns

i. Look inside src/main/java/dungeonmania/entities/enemies. Where can you notice an instance of repeated code? Note down the particular offending lines/methods/fields.

The `move` methods in ZombieToast and Mercenary contain a lot of duplicate code. For instance, their random movement, as well as their movement patterns when the player's `EffectivePotion` is `InvincibilityPotion`, are identical.

ii. What Design Pattern could be used to improve the quality of the code and avoid repetition? Justify your choice by relating the scenario to the key characteristics of your chosen Design Pattern.

The Strategy design pattern would be a good choice here to avoid this repetition. The key characteristics of the Strategy pattern are:


- Define an abstract strategy interface, such as `MoveStrategy`, with a method related to movement, for example, `move()`.
- Implement different strategy classes for various movement logics, such as `RandomMoveStrategy`, `ChasePlayerStrategy`, etc. These classes should implement the `MoveStrategy` interface.
- In the `ZombieToast` and `Mercenary` classes, include a member variable of type `MoveStrategy`, and initialize it through a parameter in the constructor.
- Delegate the `move()` method inside the `ZombieToast` and `Mercenary` classes to the strategy object for invocation, such as `strategy.move()`.
- Where necessary, pass different strategy objects to `ZombieToast` and `Mercenary` to dynamically change their movement logic and avoid duplicate code.

iii. Using your chosen Design Pattern, refactor the code to remove the repetition.

- see code in package: dungeonmania.entities.enemies.movestrategy


## b) Observer Pattern

The Observer Pattern is used in the interaction between Bomb and Switch entities in the DungeonMania game code.

Some key characteristics of the Observer Pattern:

There is a one-to-many relationship between subjects (observables) and observers.
Observers register themselves with a subject to receive notifications when the subject changes state.
The subject keeps a list of observers and notifies them of state changes by calling their update() method.
This is implemented in the Bomb and Switch classes:

The Switch is the subject, and Bombs are observers.
A Bomb can subscribe to a Switch by calling subscribe() and passing itself as a parameter (subscribe(this)).
The Switch keeps a list of subscribed Bombs.
When the Switch is activated (its state changes), it calls notify() on each subscribed Bomb.
The Bomb's notify() method calls explode(), changing the Bomb's state.

## c) Inheritance Design

i. The code smell present is duplicated code. The Exit, Door, Wall, and several other entity subclasses override the onOverlap, onMovedAway, and onDestroy methods from the Entity superclass, but provide empty method bodies that just return without doing anything.

Other subclasses that exhibit this same code smell are:

Boulder
Switch
Treasure
Wood
Arrow
Bomb
Potion
Sword
This indicates that these subclasses likely should not override these methods at all, since they have no specific behavior to define in them.

## d) More Code Smells

i. The code smell described is Shotgun Surgery. Making a change to one part of the code requires making many small changes across multiple classes. This indicates that responsibilities are not properly encapsulated.


## e) Open-Closed Goals

i. In my opinion, the current design of the goals package does not fully comply with the open-closed principle. The GoalFactory class contains a big switch statement that checks the goal type and creates the corresponding goal instance. This means that adding a new goal type would require modifying the GoalFactory class, which violates the open-closed principle.

## f) Open Refactoring

violations of the Law of Demeter/Liskov Substitution Principle

Made an interface "InventoryInterface", all other classes refers to the interface instead of the "Inventory" class.

# Task 2.

# Microevolution - Enemy Goal
## Design
1. **src/main/java/dungeonmania/goals/EnemyGoal.java** add the class of enemy goal
2. **src/main/java/dungeonmania/goals/GoalFactory.java** add the case for goal factory

## Some Details
The enemy goal can be created by the goal factory.

### Test List
1. Test create a goal using GoalFactory, input a json for a goal and check if the factory correctly created the goal.
2. Create an enemy goal, create a player, testing for player archieve the goal
3. Create an enemy goal, create a player, testing for player not archieve the goal


# Logic Switches
## Design
1. **src/main/resources/skins/default.json** add the light_bulb_on, light_bulb_off and wire
2. **src/main/java/dungeonmania/entities/logical/AndLogicStrategy.java** add the class of and logical rule
3. **src/main/java/dungeonmania/entities/logical/CoAndLogicStrategy.java** add the class of co-and logical rule
4. **src/main/java/dungeonmania/entities/logical/LogicalRuleStrategy.java** add the interface of logical rule strategy
5. **src/main/java/dungeonmania/entities/logical/LogicalRuleStrategyBase.java** add the base class of logical rule
6. **src/main/java/dungeonmania/entities/logical/OrLogicStrategy.java** add the class of or logical rule
7. **src/main/java/dungeonmania/entities/logical/XorLogicStrategy.java** add the class of xor logical rule
8. **src/main/java/dungeonmania/entities/LightBulb.java** add the class of light bulb
9. **src/main/java/dungeonmania/entities/Wire.java** add the class of wire
10. **src/main/java/dungeonmania/entities/Switch.java** modify the class of switch
11. **src/main/java/dungeonmania/entities/EntityFactory.java** modify the class of entity factory
12. **src/main/java/dungeonmania/util/NameConverter.java** update the name coverter for light bulb


## Some Details
The EntityFactory can create wire swith and lightbulb, using json.
The tests using logical json maps to create the game, and i use the DMC to control the player to made the light bulb on or off,
to check the correctness of the logical switches part.


## Test List
1. Test empty iteration
2. Test step on wire
3. Test or logic of light bulb
4. Test xor logic of light bulb
5. Test and logic of light bulb
6. Test co-and logic of light bulb




