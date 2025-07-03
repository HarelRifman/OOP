# Arkanoid Game

A classic Arkanoid/Breakout game implementation in Java, featuring ball physics, collision detection, and block-breaking gameplay for all OS.

## 🎮 Game Demonstration

https://github.com/user-attachments/assets/4477c36a-a49e-4893-8425-dfdf9c7ff884

*Video demonstration of the Arkanoid game in action*

## 🎯 Game Overview

This is a fully functional Arkanoid game where players control a paddle to bounce a ball and break blocks. The game features:

- **Classic Arkanoid gameplay** - Control a paddle to keep the ball in play
- **Block breaking mechanics** - Destroy blocks by hitting them with the ball
- **Score tracking** - Points are awarded for breaking blocks
- **Collision detection** - Realistic ball physics and collision handling
- **Game over conditions** - Win by clearing all blocks or lose when the ball falls

## 🏗️ Project Structure

```
├── src/
│   ├── A.java                          # Main application entry point
│   ├── Ass5Game.java                   # Game initialization and setup
│   ├── collections/                    # Game object collections
│   │   ├── CollisionInfo.java         # Collision detection data
│   │   ├── GameEnvironment.java       # Environment management
│   │   └── SpriteCollection.java      # Sprite management
│   ├── geometryPrimitives/            # Basic geometric classes
│   │   ├── Line.java                  # Line geometry
│   │   ├── Point.java                 # Point coordinates
│   │   ├── Rectangle.java             # Rectangle shapes
│   │   └── Velocity.java              # Vector physics
│   ├── Run/                           # Game execution
│   │   └── Game.java                  # Main game loop
│   ├── ScoreAndListeners/             # Event handling and scoring
│   │   ├── BallRemover.java           # Ball removal logic
│   │   ├── BlockRemover.java          # Block removal logic
│   │   ├── Collidable.java            # Collision interface
│   │   ├── Counter.java               # Score/counter utilities
│   │   ├── HitListener.java           # Hit event interface
│   │   ├── HitNotifier.java           # Event notification
│   │   └── ScoreTrackingListener.java # Score tracking
│   └── Sprites/                       # Game objects
│       ├── Ball.java                  # Ball physics and rendering
│       ├── Block.java                 # Block objects
│       ├── Paddle.java                # Player paddle
│       ├── ScoreIndicator.java        # Score display
│       └── Sprite.java                # Base sprite interface
├── biuoop-1.4.jar                     # Graphics library
├── build.xml                          # Ant build configuration
├── checkstyle-8.44-all.jar           # Code style checker
└── f.bash                             # Build script
```

## 🚀 How to Run

### Prerequisites
- Java 8 or higher
- Apache Ant

### Running the Game

1. **Compile and run:**
   ```bash
   ant run
   ```

2. **Clean build files:**
   ```bash
   ant clean
   ```

### Game Controls
- **Left/Right Arrow Keys** - Move paddle

## 🎲 Game Features

### Core Mechanics
- **Ball Physics**: Realistic ball movement with velocity and collision detection
- **Paddle Control**: Smooth paddle movement with boundary constraints
- **Block Breaking**: Different block types with varying point values
- **Score System**: Points awarded for each block destroyed

### Technical Features
- **Collision Detection**: Precise geometric collision algorithms
- **Event System**: Observer pattern for game events (hits, removals, scoring)
- **Sprite Management**: Efficient rendering and updating of game objects
- **Modular Architecture**: Clean separation of concerns across packages

## 🏛️ Architecture

The game follows object-oriented design principles with clear separation of responsibilities:

- **Collections**: Manage groups of game objects and handle collision detection
- **Geometry Primitives**: Provide mathematical foundations for game physics
- **Sprites**: Implement visual game objects with update and draw methods
- **Event System**: Handle game events like collisions and scoring
- **Game Loop**: Coordinate game state updates and rendering

## 🛠️ Build System

The project uses Apache Ant for build management:
- **Compilation**: Compiles Java source files
- **Execution**: Runs the game with proper classpath
- **Cleanup**: Removes generated files
- **Code Style**: Integrates checkstyle for code quality

## 📋 Dependencies

- **biuoop-1.4.jar**: Custom graphics and GUI library
- **checkstyle-8.44-all.jar**: Code style checking tool

## 🎯 Learning Objectives

This project demonstrates:
- Object-oriented programming principles
- Game loop implementation
- Collision detection algorithms
- Event-driven programming
- **Listener design pattern** implementation for game events
- Java graphics and GUI development
- Build automation with Ant

## 🚧 Development Notes

- The game uses a custom graphics library (biuoop) for rendering
- Collision detection is implemented using geometric algorithms
- The architecture supports easy extension for new block types and power-ups
- Code follows consistent style guidelines enforced by checkstyle

## 💻 System Requirements
- **Java**: 8 or higher
- **Build Tool**: Apache Ant
- **Platforms**: Windows, Linux, macOS

---

*Enjoy playing Arkanoid! 🎮*
