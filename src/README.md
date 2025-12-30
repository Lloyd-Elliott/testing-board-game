# Frustration Board Game

I have developed a Frustration style board game, that accommodates a number of different concepts and patterns from Software Design and Architecture. This Read Me discusses the elements 

## Classes

#### Application Code 

In the application code I have included all the required elements for business logic. They are as follows 

### Board

[Board](applicationcode/Board/Board.java) is a decorator that delegates to [BoardStrategy](applicationcode/Board/BoardStrategy.java) with concrete strategies of [LargeBoard](applicationcode/Board/LargeBoard.java) and [SmallBoard](applicationcode/Board/SmallBoard.java) . There is also a [BoardObserver](applicationcode/Board/BoardObserver.java) that handles logging requirements.

A [Board](applicationcode/Board/Board.java) is required for a [Game](applicationcode/Game/Game.java) and Board size is interchangeable, adding new strategies without the requirement to change any other classes for example ExtraLargeBoard

### Dice 

[Dice](applicationcode/Dice/Dice.java) is a final value object that delegates to [DiceRollStrategy](applicationcode/Dice/DiceRollStrategy.java) with concrete strategies of [OneDiceStrategy](applicationcode/Dice/OneDiceStrategy.java) and [TwoDiceStrategy](applicationcode/Dice/TwoDiceStrategy.java)OneDiceStrategy that utilise 'random' to determine the dice roll and [FixedDiceStrategy](applicationcode/Dice/FixedDiceStrategy.java) that allows predetermined rolls which can be used to easily test the application based on expected outcomes.

[Dice](applicationcode/Dice/Dice.java) is required for a [Board](applicationcode/Board/Board.java) creation and is injected into Board strategies.

### Player

[Player](applicationcode/Player/Player.java) is a mutable object and represents a state in the game, Player turns, starting position, current position etc. Player is created with a name and the Game updates the state,while the [PlayerMovementObserver](applicationcode/Player/PlayerMovementObserver.java) provides updates for logging.

Player(s) are created using the [PlayerFactory](applicationcode/Player/PlayerFactory.java) and are required for a [Game](applicationcode/Game/Game.java).

### Rules

[Rules](applicationcode/Rules/Rules.java) is a decorator that delegates business logic to [RulesStrategy](applicationcode/Rules/RulesStrategy.java) with concrete strategies of [BasicRules](applicationcode/Rules/BasicRules.java), [BounceRules](applicationcode/Rules/BounceRules.java), [ExactEndRules](applicationcode/Rules/ExactEndRules.java) and [ExactAndBounceRules](applicationcode/Rules/ExactAndBounceRules.java).

These Rules apply the complex business logic to populate a Value Object of [MoveResult](applicationcode/Rules/MoveResult.java) which carries the details of each move. 

These [Rules](applicationcode/Rules/Rules.java) also determine the winner of a [Game](applicationcode/Game/Game.java).

These variations are testable and interchangeable. 

### Game

[Game](applicationcode/Game/Game.java) pulls together all the game components as described above. It also determines a Players starting position based on the size of the Board and number of Players.

[PlayGame](applicationcode/Game/PlayGame.java) takes a Game object and acts as a Facade to orchestrate the whole game. 

It has a while loop that rolls dice, delegates move calculation to rules, then based on [MoveResult](applicationcode/Rules/MoveResult.java) updates the [Player](applicationcode/Player/Player.java) state and cycles through the Players. Until a Game has a winner. 

[GamePlayObserver](applicationcode/Game/GamePlayObserver.java) provides updates for logging on the flow of the game such as Player movement and, depending on which Rules are used, events like overshooting or hitting other players

## Game Flow

### Initialization Sequence

```
User/Runner
    │
    ├──> Create DiceStrategy (OneDiceStrategy/TwoDiceStrategy/FixedDiceStrategy)
    │
    ├──> Create Dice(diceStrategy)
    │
    ├──> Create BoardObserver (BoardLogger)
    │
    ├──> Create BoardStrategy (LargeBoard/SmallBoard) with (dice, boardObserver)
    │         │
    │         └──> Notifies BoardObserver.onBoardCreated()
    │
    ├──> Create Board(boardStrategy)
    │
    ├──> PlayerFactory.createPlayers(boardSize, "Player1", "Player2", ...)
    │         │
    │         └──> Returns Player[]
    │
    ├──> Create RulesStrategy (BasicRules/BounceRules/ExactEndRules/ExactAndBounceRules)
    │
    ├──> Create Rules(rulesStrategy)
    │
    ├──> Create Game(board, players, rules)
    │         │
    │         └──> Initializes player starting positions
    │
    ├──> Create GamePlayObserver (GamePlayConsoleLogger)
    │
    ├──> Create PlayGame(game)
    │
    └──> playGame.playUntilWinner()
              │
              └──> Game Loop (see below)
```

### Game Loop Sequence

```
PlayGame.playUntilWinner()
    │
    └──> WHILE not hasWinner():
              │
              ├──> dice.roll()
              │      │
              │      └──> Returns int (diceRoll)
              │
              ├──> Notify GamePlayObserver.onTurnStarted(player, diceRoll)
              │
              ├──> rules.calculateMove(player, diceRoll, board, allPlayers)
              │      │
              │      └──> Returns MoveResult
              │
              ├──> player.moveTo(newPosition)
              │      │
              │      └──> Notify PlayerMovementObserver.onPlayerMoved()
              │
              ├──> Update player state (inTail, tailPosition)
              │
              ├──> Check MoveResult flags:
              │      ├──> if collision: Notify GamePlayObserver.onPlayerCollision()
              │      ├──> if overshot: Notify GamePlayObserver.onPlayerOvershot()
              │      └──> if completed: Notify GamePlayObserver.onGameOver()
              │
              └──> nextTurn() (cycle to next player)
    
    └──> Notify GamePlayObserver.onGameComplete(winner)
```




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
