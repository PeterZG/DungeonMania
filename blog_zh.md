# 任务 1

## a) 从 DRY 到设计模式

### i. 查看 `src/main/java/dungeonmania/entities/enemies`。你能在哪些地方注意到重复代码的实例？记录下相关的行/方法/字段。

`ZombieToast` 和 `Mercenary` 中的 `move` 方法包含了大量重复的代码。例如，它们的随机移动，以及在玩家的 `EffectivePotion` 为 `InvincibilityPotion` 时的移动模式是相同的。

### ii. 可以使用什么设计模式来改进代码质量，避免重复？通过将场景与所选设计模式的关键特性联系起来，证明你的选择。

可以使用策略设计模式来避免这种重复。策略模式的关键特性包括：

- 定义一个抽象策略接口，如 `MoveStrategy`，包含与移动相关的方法，例如 `move()`。
- 为不同的移动逻辑实现不同的策略类，如 `RandomMoveStrategy`、`ChasePlayerStrategy` 等，这些类实现 `MoveStrategy` 接口。
- 在 `ZombieToast` 和 `Mercenary` 类中，包含一个类型为 `MoveStrategy` 的成员变量，并通过构造函数初始化它。
- 在 `ZombieToast` 和 `Mercenary` 类中的 `move()` 方法，委托给策略对象调用，例如 `strategy.move()`。
- 在需要的地方，动态地传递不同的策略对象给 `ZombieToast` 和 `Mercenary`，以改变它们的移动逻辑，避免代码重复。

### iii. 使用你选择的设计模式重构代码，消除重复。

- 参见包 `dungeonmania.entities.enemies.movestrategy` 中的代码。

## b) 观察者模式

观察者模式用于 `Bomb` 和 `Switch` 实体之间的交互。

观察者模式的关键特性包括：

- 主题（可观察对象）和观察者之间的“一对多”关系。
- 观察者注册到主题上，接收主题状态变化的通知。
- 主题维护一个观察者列表，并通过调用观察者的 `update()` 方法通知它们状态变化。
  
这在 `Bomb` 和 `Switch` 类中得到了实现：

- `Switch` 是主题，`Bomb` 是观察者。
- `Bomb` 可以通过调用 `subscribe()` 方法将自己注册到 `Switch` 上（例如，`subscribe(this)`）。
- `Switch` 会维护一个订阅的 `Bomb` 列表。
- 当 `Switch` 被激活（其状态发生变化）时，会调用每个已订阅的 `Bomb` 上的 `notify()` 方法。
- `Bomb` 的 `notify()` 方法会调用 `explode()`，从而改变 `Bomb` 的状态。

## c) 继承设计

### i. 代码气味是重复代码。`Exit`、`Door`、`Wall` 和其他一些实体子类重写了 `onOverlap`、`onMovedAway` 和 `onDestroy` 方法，但提供的空方法体只是返回什么都不做。

其他表现出这种代码气味的子类包括：

- Boulder
- Switch
- Treasure
- Wood
- Arrow
- Bomb
- Potion
- Sword

这表明这些子类可能不应该重写这些方法，因为它们没有定义任何特定的行为。

## d) 更多代码气味

### i. 描述的代码气味是“散弹手术”。更改代码的某一部分需要在多个类中做许多小的修改。这表明职责没有得到适当的封装。

## e) 开放-封闭原则目标

### i. 在我看来，当前 `goals` 包的设计并未完全符合开放-封闭原则。`GoalFactory` 类包含一个大的 `switch` 语句，它检查目标类型并创建相应的目标实例。这意味着添加一个新的目标类型将需要修改 `GoalFactory` 类，这违反了开放-封闭原则。

## f) 开放重构

违反了“迪米特法则”和“里氏替换原则”

我创建了一个接口 `InventoryInterface`，所有其他类都引用该接口，而不是直接引用 `Inventory` 类。

# 任务 2

## 微进化 - 敌人目标

### 设计

1. 在 `src/main/java/dungeonmania/goals/EnemyGoal.java` 添加敌人目标类。
2. 在 `src/main/java/dungeonmania/goals/GoalFactory.java` 添加目标工厂类的处理。

### 一些细节

敌人目标可以通过目标工厂来创建。

### 测试列表

1. 测试通过目标工厂创建目标，输入 JSON 文件并检查工厂是否正确创建了目标。
2. 创建敌人目标，创建一个玩家，测试玩家是否达成目标。
3. 创建敌人目标，创建一个玩家，测试玩家是否未达成目标。

# 逻辑开关

### 设计

1. **src/main/resources/skins/default.json** 添加 `light_bulb_on`、`light_bulb_off` 和 `wire`。
2. **src/main/java/dungeonmania/entities/logical/AndLogicStrategy.java** 添加与和逻辑规则相关的类。
3. **src/main/java/dungeonmania/entities/logical/CoAndLogicStrategy.java** 添加与协同与逻辑规则相关的类。
4. **src/main/java/dungeonmania/entities/logical/LogicalRuleStrategy.java** 添加逻辑规则策略接口。
5. **src/main/java/dungeonmania/entities/logical/LogicalRuleStrategyBase.java** 添加逻辑规则基础类。
6. **src/main/java/dungeonmania/entities/logical/OrLogicStrategy.java** 添加与或逻辑规则相关的类。
7. **src/main/java/dungeonmania/entities/logical/XorLogicStrategy.java** 添加与异或逻辑规则相关的类。
8. **src/main/java/dungeonmania/entities/LightBulb.java** 添加光源类。
9. **src/main/java/dungeonmania/entities/Wire.java** 添加电线类。
10. **src/main/java/dungeonmania/entities/Switch.java** 修改开关类。
11. **src/main/java/dungeonmania/entities/EntityFactory.java** 修改实体工厂类。
12. **src/main/java/dungeonmania/util/NameConverter.java** 更新光源的名称转换器。

### 一些细节

`EntityFactory` 可以创建电线、开关和光源，使用 JSON 配置来创建游戏元素，通过 DMC 控制玩家操作，开关控制光源的开关，以检查逻辑开关部分的正确性。

### 测试列表

1. 测试空迭代。
2. 测试踩到电线。
3. 测试光源的或逻辑。
4. 测试光源的异或逻辑。
5. 测试光源的与逻辑。
6. 测试光源的协同与逻辑。

# 太阳石

### 设计

1. **src/main/resources/skins/default.json** 添加太阳石图片。
2. **src/main/java/dungeonmania/entities/collectables/SunStone.java** 添加太阳石类。
3. **src/main/java/dungeonmania/entities/EntityFactory.java** 添加太阳石构造函数。
4. **src/main/java/dungeonmania/map/GraphNodeFactory.java** 添加太阳石节点创建。

### 一些细节

由于太阳石是特殊形式的宝物，我让 `SunStone` 继承了 `Treasure` 类。因此，在使用 Java 的反射机制获取 `Treasure` 对象时，重要的是要过滤掉 `SunStone` 类。

### 测试列表

由于太阳石是可收集的元素，因此我没有为其编写测试类，而是在其他测试中通过测试太阳石。

# 权杖

### 设计

1. **src/main/resources/skins/default.json** 添加权杖图片。
2. **src/main/java/dungeonmania/entities/buildables/Sceptre.java** 添加权杖类。
3. **src/main/java/dungeonmania/entities/EntityFactory.java** 添加权杖构造函数。
4. **src/main/java/dungeonmania/map/GraphNodeFactory.java** 添加权杖节点创建。
5. 修改配置文件。由于权杖可以控制敌人的心智，应该在配置文件中加入 `mind_control_duration` 字段，并由程序读取。
6. 添加 `mind_controlled` 函数，并设置持续时间。

### 一些细节

由于贿赂和心灵控制是两种使雇佣兵或刺客成为盟友的方式，因此应有一个属性来区分这两种方式。（心灵控制持续若干时间，而贿赂则不受时间限制）。

### 测试列表

1. 测试制作权杖。
2. 测试通过权杖控制敌人心智。
3. 测试权杖心智控制的时间限制。

# 午夜盔甲

### 设计

1. **src/main/resources/skins/default.json** 添加午夜盔甲图片。
2. **src/main/java/dungeonmania/entities/buildables/MidnightArmour.java** 添加午夜盔甲类。
3. **src/main/java/dungeonmania/entities/EntityFactory.java** 添加午夜盔甲构造函数。
4. **src/main/java/dungeonmania/map/GraphNodeFactory.java** 添加午夜盔甲节点创建。
5. 修改配置文件。午夜盔甲可以提供额外的攻击伤害和防护，因此应该在配置文件中添加 `midnight_armour_attack` 和 `midNightArmourDefence` 字段。
6. 设置午夜盔甲的增益效果。

### 测试列表

1. 测试没有僵尸的情况下构建午夜盔甲。
2. 测试午夜盔甲增加玩家的攻击并减少敌人的攻击。（合并到 `BattleTest.java` 中）

