package wilderSudoku;

import exceptions.ForeignPuzzleException;

/**
 * This is the class that is used for connecting to displayer, loaders, and solvers..
 * 
 * @author Donovan J. Wilder
 * @since 2016-07-08
 */
public class Sudoku {
	private Puzzle puzzle;
	private Loader loader;
	private Displayer displayer;
	private Solver solver;


	
	/**
	 * Sets the puzzle loader
	 * @param loader
	 */
	public void setLoader(Loader loader){
		this.loader=loader;
		this.load();
	}
	/**
	 * Sets the Displayer. Passes a clone of the puzzle to the Displayer
	 * @param displayer
	 */
	public void setDisplayer(Displayer displayer) {
		this.displayer=displayer;
		assert this.puzzle instanceof Puzzle:"setDisplayer() no puzzle in this.puzzle";
		this.displayer.connect((Puzzle)this.puzzle.clone());
	}
	/**
	 * Sets the Solver. Passes a clone of the puzzzle to the Solver.
	 * @param solver
	 */
	public void setSolver(Solver solver){
		this.solver=solver;
		solver.connect((Puzzle)this.puzzle.clone());
	}
	
	
	/**
	 * Activates the load method of of the {@link wilderSudoku.Loader Loader}
	 */
	private void load(){
		assert loader.load() instanceof Puzzle: "The loader has no puzzle";
		this.puzzle=loader.load();
	}
	/**
	 * Activate the solve method of the {@link wilderSudoku.Solver Solver} and return the number solved
	 * @return the amount solved.
	 * 
	 */
	public int solve()throws ForeignPuzzleException{
		int solved=solver.solve();
		Puzzle solvedPuzzle= solver.getPuzzle();
		if(validatePuzzle(solvedPuzzle)==false){
			throw new ForeignPuzzleException();
		}
		
		this.puzzle= solvedPuzzle;
		return solved;
	}
	/**
	 * Activate the display method of the {@link wilderSudoku.Displayer Displayer}
	 */
	public void display(){
		displayer.connect((Puzzle)this.puzzle.clone());//added
		displayer.display();
	}
	/**
	 * Returns the currently loaded puzzle
	 * @return
	 */
	public Puzzle getpuzzle(){
		return this.puzzle;
	}
	
	
	public boolean validatePuzzle(Puzzle testPuzzle){
		//check to see if the locked squares in the original puzzle and the values are the same matches the
		//check squares in the testPuzzle if the equal return true if they don't return false;
		for(int y =0; y<9; y++){
			for(int x=0; x<9; x++){
				if(this.puzzle.getSquareLocked(x, y)){
					if(this.puzzle.getSquareValue(x, y).equals(testPuzzle.getSquareValue(x, y))){
						continue;
						
					}else
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Returns a String of the puzzle used.
	 * @see {@link wilderSudoku.Puzzle Puzzle}
	 */
	@Override
	public String toString(){
		return puzzle.toString();
	}
	
}