# Dungeon Mania

《Dungeon Mania》是一款互动式地牢冒险游戏，旨在满足游戏行业对高自由度和战略性玩法的需求。它展示了先进的编程技术，包括模块化设计、面向对象的原则和基于网络的交互。游戏支持动态关卡生成和多结局系统，确保灵活性和可扩展性。游戏状态由 `DungeonManiaController` 管理，Dijkstra 算法增强了敌人的 AI 路径寻找能力。此外，使用 Spark 框架提供基于 RESTful API 的 Web 交互和控制。

## 特性

1. **游戏管理**：
   - 使用 `DungeonManiaController` 控制游戏状态。
   - 支持自定义配置的动态游戏初始化。

2. **战斗模拟**：
   - 包含 `battles` 模块中的战斗逻辑。
   - 处理不同实体的交互（例如，敌人、陷阱等）。

3. **实体管理**：
   - 通过 `entities` 模块管理不同的实体（例如，玩家、NPC、物品）。
   - 为独特的实体属性实现模块化行为。

4. **地图与目标**：
   - `map` 模块管理地牢布局和实体位置。
   - `goals` 模块追踪任务目标和进度。

5. **Web 交互**：
   - 提供基于 Spark 框架的轻量级 HTTP 服务器。
   - 通过 RESTful API 实现游戏控制。

## 设计原则

1. **控制器设计模式**：
   - `DungeonManiaController` 管理游戏状态和用户交互。

2. **建造者模式**：
   - `GameBuilder` 促进灵活且可扩展的游戏初始化。

3. **封装性**：
   - 实体行为和状态被隔离以便于维护。

4. **模块化**：
   - 游戏逻辑被划分为独立的模块（例如，地图、战斗、目标等）。

## 技术栈

### 后端：
- **语言**：Java
- **框架**：Spark（用于 HTTP 服务）
- **序列化**：Gson（用于 JSON 处理）
- **设计模式**：
  - 控制器模式（例如，`DungeonManiaController`）。
  - 建造者模式（例如，`GameBuilder`）。

## 文件结构

```
src/
├── main/
│   ├── java/
│   │   ├── App.java              # 主入口
│   │   ├── dungeonmania/         # 核心游戏模块
│   │   │   ├── DungeonManiaController.java
│   │   │   ├── Game.java
│   │   │   ├── GameBuilder.java
│   │   │   ├── battles/          # 战斗逻辑
│   │   │   ├── entities/         # 游戏实体
│   │   │   ├── goals/            # 任务目标
│   │   │   ├── map/              # 地牢地图逻辑
│   │   │   ├── response/         # API 响应模型
│   │   │   ├── util/             # 工具类
│   ├── resources/                # 静态配置和资源
├── test/
│   ├── java/
│   │   ├── dungeonmania/         # 单元测试模块
```
## 使用方法

### 编译
使用提供的 Gradle 构建工具编译项目：
```bash
./gradlew build

## 使用方法

### 编译
使用提供的 Gradle 构建工具编译项目：
```bash
./gradlew build
```

### 运行游戏
启动 Spark 服务器，通过 RESTful API 与游戏进行交互
```bash
./gradlew run
```

### 示例 API 使用
1. **开始新游戏**:
   ```bash
   curl -X POST -d '{"dungeonName": "maze", "gameMode": "standard"}' http://localhost:4567/start
   ```

2. **移动玩家**:
   ```bash
   curl -X POST -d '{"direction": "UP"}' http://localhost:4567/move
   ```

3. **获取游戏状态**:
   ```bash
   curl -X GET http://localhost:4567/state
   ```


## 测试
运行测试套件以验证游戏逻辑和交互：
```bash
./gradlew test
```

## 未来改进

1. **增强的 AI**:
   - 引入更智能的敌人行为和自适应难度。

2. **改进的地图生成**:
   - 添加过程化地牢生成，增加游戏的多样性。

3. **基于 Web 的 UI**:
   - 开发图形化前端，提供沉浸式的游戏体验。


## 致谢

本项目的开发展示了现代软件工程的一些常见技术，侧重于模块化设计、面向对象编程原则和动态游戏机制。

