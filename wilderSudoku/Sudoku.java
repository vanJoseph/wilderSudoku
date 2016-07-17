package wilderSudoku;

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
	 * Sets the Displayer
	 * @param displayer
	 */
	public void setDisplayer(Displayer displayer) {
		this.displayer=displayer;
		this.displayer.connect(this.puzzle);
	}
	/**
	 * Sets the Solver
	 * @param solver
	 */
	public void setSolver(Solver solver){
		this.solver=solver;
		solver.connect(this.puzzle);
	}
	
	/**
	 * Activates the load method of of the {@link wilderSudoku.Loader Loader}
	 */
	public void load(){
		this.puzzle=loader.load();
	}
	/**
	 * Activate the solve method of the {@link wilderSudoku.Solver Solver} and return the number solved
	 * @return the amount solved.
	 * 
	 */
	public int solve(){
		int solved=solver.solve();
		this.puzzle= solver.getPuzzle();
		return solved;
	}
	/**
	 * Activate the display method of the {@link wilderSudoku.Displayer Displayer}
	 */
	public void display(){
		displayer.display();
	}
	/**
	 * Returns the currently loaded puzzle
	 * @return
	 */
	public Puzzle getpuzzle(){
		return this.puzzle;
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