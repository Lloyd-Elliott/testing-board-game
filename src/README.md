# Frustration Board Game

I have developed a Frustration style board game, that accommodates a number of different concepts and patterns from Software Design and Architecture. This Read Me discusses the elements 

## Classes

#### Application Code 

In the application code I have included all the required elements for business logic. They are as follows 

### Board

[Board](applicationcode/Board/Board.java) is a decorator that delegates to [BoardStrategy](applicationcode/Board/BoardStrategy.java) with concrete strategies of [LargeBoard](applicationcode/Board/LargeBoard.java) and [SmallBoard](applicationcode/Board/SmallBoard.java) . There is also a [BoardObserver](applicationcode/Board/BoardObserver.java) that handles logging requirements.

A [Board](applicationcode/Board/Board.java) is required for a [Game](applicationcode/Game/Game.java) and Board size can be varied, adding new strategies without the requirement to change any other classes for example ExtraLargeBoard

### Dice 

[Dice](applicationcode/Dice/Dice.java) is a final value object that delegates to [DiceRollStrategy](applicationcode/Dice/OneDiceStrategy.java) with concrete strategies of [OneDiceStrategy](applicationcode/Dice/OneDiceStrategy.java)  and [TwoDiceStrategy](applicationcode/Dice/TwoDiceStrategy.java)OneDiceStrategy that utilise 'random' to determine the dice roll and [FixedDiceStrategy](applicationcode/Dice/FixedDiceStrategy.java) that allows predetermined rolls which can be used to easily test the application based on expected outcomes.

[Dice](applicationcode/Dice/Dice.java) is required for a [Board](applicationcode/Board/Board.java) creation and is injected into Board strategies.

### Player

[Player](applicationcode/Player/Player.java) is a mutable object and represents a state in the game, Player turns, starting position, current position etc. Player is created with a name and the Game updates the state,while the [PlayerMovementObserver](applicationcode/Player/PlayerMovementObserver.java) provides updates for logging.

Player(s) are created using the [PlayerFactory](applicationcode/Player/PlayerFactory.java) and are required for a [Game](applicationcode/Game/Game.java)




### Infrastructure Code 

## SOLID Principles

### Single Responsibility Principle (SRP)

### Open/Closed Principle (OCP)

### Liskov Substitution Principle (LSP)

### Interface Segregation Principle (ISP)

### Dependency Inversion Principle (DIP)

### Ports and Adapters

### Clean Architecture

## Areas for improvement 
