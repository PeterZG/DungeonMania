
# Dungeon Mania

Dungeon Mania is an interactive simulation game that showcases advanced programming techniques such as modular design, object-oriented principles, and web-based interactions. This project was developed to fulfill the requirements of COMP2511 Assignment 2.

## Features

1. **Game Management**:
   - Control game states using the `DungeonManiaController`.
   - Support for dynamic game initialization with custom configurations.

2. **Battle Simulation**:
   - Includes combat logic in the `battles` module.
   - Handles various entity interactions (e.g., enemies, traps).

3. **Entity Management**:
   - Manage different entities (e.g., players, NPCs, items) through the `entities` module.
   - Implement modular behaviors for unique entity properties.

4. **Map and Goals**:
   - `map` module manages the dungeon layout and entity positions.
   - `goals` module tracks mission objectives and progression.

5. **Web Interaction**:
   - Provides a lightweight HTTP server using the Spark framework.
   - Enables game control via RESTful API.

## Design Principles

1. **Controller Design Pattern**:
   - `DungeonManiaController` manages game state and user interactions.

2. **Builder Pattern**:
   - `GameBuilder` facilitates flexible and extensible game initialization.

3. **Encapsulation**:
   - Entity behaviors and states are isolated for maintainability.

4. **Modularity**:
   - Game logic divided into independent modules (e.g., map, battles, goals).

## Technology Stack

### Backend:
- **Language**: Java
- **Framework**: Spark (for HTTP services)
- **Serialization**: Gson (for JSON handling)
- **Design Patterns**:
  - Controller Pattern (e.g., `DungeonManiaController`).
  - Builder Pattern (e.g., `GameBuilder`).

## File Structure

```
src/
├── main/
│   ├── java/
│   │   ├── App.java              # Main entry point
│   │   ├── dungeonmania/         # Core game modules
│   │   │   ├── DungeonManiaController.java
│   │   │   ├── Game.java
│   │   │   ├── GameBuilder.java
│   │   │   ├── battles/          # Battle logic
│   │   │   ├── entities/         # Game entities
│   │   │   ├── goals/            # Mission objectives
│   │   │   ├── map/              # Dungeon map logic
│   │   │   ├── response/         # API response models
│   │   │   ├── util/             # Utility classes
│   ├── resources/                # Static configurations and assets
├── test/
│   ├── java/
│   │   ├── dungeonmania/         # Unit tests for game modules
```

## Usage

### Compile
Compile the project using the provided Gradle build tool:
```bash
./gradlew build
```

### Run the Game
Start the Spark server to interact with the game via RESTful API:
```bash
./gradlew run
```

### Example API Usage
1. **Start a New Game**:
   ```bash
   curl -X POST -d '{"dungeonName": "maze", "gameMode": "standard"}' http://localhost:4567/start
   ```

2. **Move the Player**:
   ```bash
   curl -X POST -d '{"direction": "UP"}' http://localhost:4567/move
   ```

3. **Retrieve Game State**:
   ```bash
   curl -X GET http://localhost:4567/state
   ```

## Testing
Run the test suite to verify game logic and interactions:
```bash
./gradlew test
```

## Future Improvements

1. **Enhanced AI**:
   - Introduce smarter enemy behaviors and adaptive difficulty.

2. **Improved Map Generation**:
   - Add procedural dungeon generation for greater variety.

3. **Web-Based UI**:
   - Develop a graphical frontend for an immersive gaming experience.

## Acknowledgments

This project was developed as part of COMP2511 at UNSW, showcasing advanced software engineering techniques.
