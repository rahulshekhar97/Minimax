=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: rshekhar
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Appropriately modeling state using 2-D arrays:
  	 I modelled the state of my game using 2-D arrays. The game state was depicted by the squares that 
  	 were occupied and the squares that the each player were allowed to click. Thus, after each turn the game 
  	 state would be updated. This was a major part of my game. My AI implementation was based on how the game 
  	 state would change after a couple of turns.
  	 

  2. Using I/O to parse a novel file format. 
  	 I use I/O to store and show the total number of wins each user has won. 

  3. Using inheritance/subtyping for dynamic dispatch.
  	 I have a class User which has two subclasses Player and Computer. User stores the methods and instance 
  	 variables common to both Player and Computer. For example, both Player and Computer have a label which 
  	 depicts their score. In addition, they both have an updateScore method as well. Thus, through this, 
  	 I have used inheritance/subtyping for dynamic dispatch. 

  4. Artificial Intelligence + Recursion
  	 I implemented the minimax concept. The computer will decide its move based on which will be best game position from him 4 moves out. 
  	 


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
 
HomeScreen
Creates the home screen of the game.

GameBoard
Manages the gameboard state and creates the gameboard itself. 

HighScores
Creates the screen that depicts the scores of the people who played the game.
  
User
Store the functions and attributes of a general User.

Player (extends the User Class)
It stores the instance variables and methods specific to a human player.

Computer (extends the User Class)
It stores the instance variables and methods specific to computer playing. This is where the AI is implemented.

Print
This was to continuously update the file containing the scores of people when a player won a game. This would also read the file to
display the scores.

Users
Stored a TreeMap that kept track of the number of games a player with a particular user name had won.

SquareButton 
Stored the properties and methods to be implemented by each JButton making up the GameBoard.

CellState
The game was basically a 5*5 grid of Jbuttons. This stored the state of a particular JButton - whether it had been clicked? who clicked it?
What value was stored on that cell?

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

 I had to come up with an efficient algorithm to keep updating my game state. That was difficult. In addition,
 implementing the AI was a bit taxing there were a lot of ways the game could progress at a particular game state. 
 Also, there edges cases to take care of - what if the only option remaining was to remove a square?

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
	
 I would say functionality and private state are pretty decently encapsulated. I would still refractor because I feel that the code
 can be made more cleaner and more elegant.


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.


