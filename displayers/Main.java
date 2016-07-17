package displayers;

import java.io.FileNotFoundException;
import java.io.IOException;

import exceptions.InvalidCharacterException;
import exceptions.InvalidFormatException;
import exceptions.InvalidPuzzleException;
import exceptions.LoadException;
import loaders.FileLoader;
import solvers.DeductionSolver;
import wilderSudoku.Sudoku;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
			
			Sudoku sudoku=new Sudoku();
			sudoku.setLoader(new FileLoader("../wilderSudoku/puzzles/easy/puzzle02.txt"));
			sudoku.setSolver(new DeductionSolver());
			sudoku.setDisplayer(new ConsoleDisplayer());
			sudoku.load();
		
			System.out.println("Before: ");
			sudoku.display();

			System.out.println("After: ");
			int lastSolved=0;
			boolean completed=false;
			while(!completed){
				if(completed=sudoku.getpuzzle().checkpuzzleComplete()){
					System.out.println("The Puzzle is completed");
				}
				
				int solved=sudoku.solve();
				if(solved<1){
					break;
				}else{
					lastSolved+=solved;
				}
			}
			System.out.println("Solved: " + lastSolved);
			sudoku.display();
			
	}
			

	

}
