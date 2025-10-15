src/
│
├── board/                        ← **Model**
│   └── Cell.java
│
├── user/                         ← **Model (joueurs)**
│   ├── Player.java
│   ├── Opponent.java
│   └── ArtificialPlayer.java
│
├── game/                         ← **View (interface + types de jeux)**
│   ├── InteractionUtilisateur.java
│   ├── Menu.java
│   ├── TicTacToe.java
│   ├── Gomoku.java
│   └── Puissance4.java
│
├── controller/                   ← **Controller**
│   ├── GameController.java
│   └── GameLogic.java
│
└── Main.java                     ← **Point d’entrée du programme**




+--------+        +----------------+       +---------------------+       +-------------------------+
| Main   |        | GameController |       | TicTacToe (Model)   |       | InteractionUtilisateur  |
+--------+        +----------------+       +---------------------+       +-------------------------+
|                      |                          |                               |
| new GameController() |                          |                               |
|--------------------->|                          |                               |
|                      | new TicTacToe()          |                               |
|                      |------------------------->|                               |
|                      | new InteractionUtilisateur()                             |
|                      |------------------------------------------->|             |
|                      | startGame()                               |             |
|                      |------------------------------------------>|             |
|                      |                                          |             |
|                      | runGameLoop()                            |             |
|                      |------------------------------------------>|             |



+----------------+       +----------------------+        +------------------+       +--------------------------+
| GameController |       | InteractionUtilisateur |       | TicTacToe (Model) |       | Player                   |
+----------------+       +----------------------+        +------------------+       +--------------------------+
|                           |                           |                           |
| askForMove()              |                           |                           |
|-------------------------->|                           |                           |
|           [x,y]           |                           |                           |
|<--------------------------|                           |                           |
| playTurn(x,y)             |                           |                           |
|------------------------------------------------------>|                           |
|                           |        mise à jour        |                           |
|                           |<---------------------------|                           |
| checkVictory()            |                           |                           |
|------------------------------------------------------>|                           |
| résultat (true/false)     |                           |                           |
|<------------------------------------------------------|                           |
| displayBoard()            |                           |                           |
|-------------------------->|                           |                           |
| displayMessage(resultat)  |                           |                           |
|-------------------------->|                           |                           |








+---------------------------------------------------+
|                     Model                         |
+---------------------------------------------------+
| Cell                  Player                      |
| - row:int             - name:String               |
| - col:int             - symbol:char               |
| - value:char          + getSymbol():char          |
| + isEmpty():boolean   + playMove(Cell):void       |
|                                                   |
| TicTacToe                                          |
| - board:Cell[][]                                   |
| - currentPlayer:Player                             |
| + playTurn(int,int):boolean                        |
| + checkVictory():boolean                           |
| + isBoardFull():boolean                            |
+---------------------------------------------------+

          ▲
          |
          |  manipule
          |
+---------------------------------------------------+
|                  Controller                        |
+---------------------------------------------------+
| GameController                                    |
| - model:TicTacToe                                 |
| - view:InteractionUtilisateur                     |
| + startGame():void                                |
| + processMove(int,int):void                       |
+---------------------------------------------------+

          ▲
          |
          |  communique avec
          |
+---------------------------------------------------+
|                     View                          |
+---------------------------------------------------+
| InteractionUtilisateur                            |
| + displayBoard(Cell[][]):void                     |
| + askForMove():int[]                              |
| + displayMessage(String):void                     |
|                                                   |
| Menu                                              |
| + displayMainMenu():int                           |
+---------------------------------------------------+
