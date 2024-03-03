# Tetris-Game-OOP
This program implements the classis Tetris Game using Object Oriented Design Principles such as:
- Graphical User Interfaces
- Implementing multiple Listeners
- Custom Graphics
- Animation

The TetricBrick class is my abstract class with the following subclasses:
- SquareBrick.
- LongBrick
- EssBrick
- ZeeBrick
- ElBrick
- JayBrick
- StackBrick
These subclasses describe the attributes of each brick in my game.

  TetrisWindow Class: This is the driver class
  TetrisDisplay Class: This class contains code for all Key Events and Listeners that are critical to the function of the tetris game. This class is also responsible for the methods that create the background (well) for the game, as displays the current status of the game.
  TetrisGame Class: This class contains the code that initializes the 'well' (becomes an attribute in the GAME class), and the code controlling the movements of the bricks.

  Thank you!
