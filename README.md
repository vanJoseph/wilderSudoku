# wilderSudoku
## About
This is a library used to create algorithms for solving 9x9 sudoku puzzles. 
## How to Play Sudoku
Sudoku is a logic game played on a 9x9 grid using the digits 1 through 9. This grid is further subdivided into nine 3x3 boxes. The goal is to fill in the grid with digits such that one and only one of each digit 1 through 9 appear in every row, column, and box.
https://www.logicgamesonline.com/sudoku/tutorial.html
## Class Structure
### Sudoku
This class carries out the operations of the solver algorithm on the puzzle. It is the the hub where the solver, loader, and displayer is connected.
### Puzzle
This class contains all the values for each location in the sudoku puzzle.
### Displayer
This class provides a way for you to display the puzzle results.
### Loader
This class provides a method for you to load a puzzle to be solved
### Building Components
These components are used to represent specific areas of of the puzzle.
#### Area
Represents one of the 9 3x3 areas of a sudoku puzzle
#### Square
Represents one square in a puzzle

#### Cord
Represents the location of a square.
### Number
A Resentation of the numbers 1-9
## How to test a Solver
<!-- TODO Create how to test a sover text-->
<!-- TODO Spell check and gramarcheck everything -->
