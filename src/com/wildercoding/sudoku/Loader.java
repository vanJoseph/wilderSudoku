package com.wildercoding.sudoku;
/**
 * Provides operation that supports different puzzle loading methods. It is used
 * by the Sudoku system for different loading operations.
 * 
 * @author Donovan J. Wilder
 * @since 2016-07-17
 * @see { @link wilderSudoku.Sudoku Sudoku}
 */
public abstract class Loader {
	protected Puzzle puzzle;

	/**
	 * The main method used by loaders to return the puzzle to the Sudoku
	 * system.
	 * 
	 * @return a {@linkplain wilderSudoku.Puzzle puzzle}
	 * @see {@link wilderSudoku.Puzzle Puzzle}
	 */
	public abstract Puzzle load();
}
