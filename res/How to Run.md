# How to Run
- Run the Driver
- # Use the flag "- -text" for the text version
- Select the type of maze you want. Press '1' for Room maze and '2' for Wrapping maze.
- Enter the number of rows and columns for the maze.
- Enter 1 for multiplayer and 0 for single player.
- Enter the X and Y coordinates of the starting point.
- Enter the percentage of rooms which should have pits. It must be less than 30 and a positive number.
- Enter the percentage of rooms which should have bats. It must be less than 30 and a positive number.
- Your progrm now starts, and you can move your charceter in any direction (north, south, east, west) or press 'y' to shoot an arrow.
- To move press n/s/w/e, and the character will move to the closest room in that direction.
- If there is a pit or the wumpus in the next room, the character will feel a draft or a stench respectively. 
- If you want to shoot an arrow, press 'y', enter the direction [n/s/w/e], and the number of rooms it should travel. 
- The game continues till the wumpus is dead, the charcter is dead or the character runs out of arrows.
- # Use the flag "- -gui" for the GUI version
- Same details as above. There are buttons to make the character move up, down, left and right. 
- After the initial click on the screen, you can use the arrow buttons in your keyboard to make the character move around.


## List of Completed Features
- Multiplayer functionality.
- Able to move the player via the buttons on the screen and the keyboard buttons.
- Only cells being visited by any player are being displayed.
- When one player kills the wumpus the game ends.
- Option of restarting the game or beginning a new game are also present.

## Design Choices
Instead of passing only the model to the controller, I am passing both the view and the model to the controller. In order to encorporate the multiplayer functionality, I have made a Character array within the model, so that the turn of the current player can be found out just by the index. Aside from these two, the model and the controller have the same structure as before. There are two controllere, one which handles the GUI version of the game and the other handles the text version. The model, view and controller are separated, and their functionality do not overlap one another. 


