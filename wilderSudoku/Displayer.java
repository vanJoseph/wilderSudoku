package wilderSudoku;


/**
 * Displayer is an abstract class that is used by the Sudoku system for output. This class
 * provides basic functions essential for the output of a sudoku puzzle.
 * 
 * @author Donovan J. Wilder
 * @since 2016-07-17
 * @see {@link wilderSudoku.Sudoku Sudoku}
 */
public abstract class Displayer {
	protected Puzzle puzzle;
	/**
	 * Receives and loads the puzzle
	 * @param puzzle
	 * @see {@link wilderSudoku.Puzzle Puzzle}
	 */
	public void connect(Puzzle puzzle){
		this.puzzle=puzzle;
	}
	/**
	 * This is the main method for displayer it is called by the Sudoku system in order to generate
	 * to initiate output
	 */
	public abstract void display();
}
