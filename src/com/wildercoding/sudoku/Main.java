package com.wildercoding.

/**
 * This class is used for development purposes
 * 
 * @author vanJoseph
 *
 */
public class Main {
	public static void main(String[] args) throws FileNotFoundException, InvalidCoordinateException, InitializationException, InvalidSetException, InvalidValueException, ForeignPuzzleException {
		Puzzle puzzle1 = new FileLoader("/puzzles/easy/puzzle03.txt").load();
		Puzzle puzzle2 = new FileLoader("/puzzles/easy/puzzle02.txt").load();
//		System.out.println("Puzzle 1: \n" + puzzle1+ "\n Puzzle 2: \n"+puzzle2);
//		System.out.println("Is puzzle1 equal to puzzle 2?: " +puzzle1.equals(puzzle2));
		Sudoku sudoku=new Sudoku();
		FileLoader loader=new FileLoader("/puzzles/easy/puzzle03.txt");
		sudoku.setLoader(loader);
		sudoku.setDisplayer(new ConsoleDisplayer());
		
		
		
		
		
		System.out.println("After Load:");
		sudoku.display();
		
		//This proves that i can change the puzzle in the sudoku system by chaning the puzzle in the solver
		
		DeductionSolver solver = new DeductionSolver();
		sudoku.setSolver(solver);
	
		
		// I'm changing the puzzle throught the solver
		sudoku.solve();
		System.out.println("After Solve: " );
		sudoku.display();
		
		
		
		
		
	}
}
