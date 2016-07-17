package loaders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import exceptions.DuplicateException;
import exceptions.InvalidPuzzleException;
import exceptions.InvalidValueException;
import wilderSudoku.Area;
import wilderSudoku.Loader;
import wilderSudoku.Number;
import wilderSudoku.Puzzle;
import wilderSudoku.Square;


public class ConsoleLoader implements Loader{
	Scanner scanner;
	int lineSubmission;
	Square[][]puzzle;
	ConsoleLoader() throws InvalidPuzzleException{
		lineSubmission=0;
		puzzle=new Square[9][9];
		scanner=new Scanner(System.in);
		System.out.println("Wilder Sudoku");
		System.out.println("Use '0' for blank spaces");
		System.out.println("Press Enter to submit line");
		System.out.println("=============================\n\n");
		
		while(lineSubmission<=8){
			this.enterLine();
		}
		try{
				valadatePuzzle();
		}catch(DuplicateException cause){
			InvalidPuzzleException e=new InvalidPuzzleException("Invalid Puzzle");
			e.initCause(cause);
			throw e;
		}
		
	}
	public void enterLine(){
		
		System.out.println("Enter Line " + (lineSubmission+1) + ":");
		String line= scanner.next();
		try{
		validateLine(line);
		enterSquares(line);
		lineSubmission++;
		}catch(InvalidValueException e){
			System.out.println(e );
		}
		
	}
	/**
	 * Make sure that the line is 9 characters
	 * and valid numbers by pasing them thru the Number. valueof method
	 * @param line
	 * @throws InvalidValueException
	 */
	 void validateLine(String line)throws InvalidValueException{
		if(line.length()!=9)
			throw new InvalidValueException("Invalid Lenght");
		for(int index=0;index<line.length();index++){
			try{
			Number.valueOf(line.charAt(index));
			}
			catch(InvalidValueException cause){
				InvalidValueException e=new InvalidValueException(cause.getMessage());
				e.initCause(cause);
				throw e;
			}
		}
	}
	 void enterSquares(String line){
		 for(int indexX=0; indexX<9; indexX++){
			 if(line.charAt(indexX)=='0'){
			 puzzle[indexX][lineSubmission]=new Square();}
			 else{
				 puzzle[indexX][lineSubmission]=new Square(Number.valueOf(line.charAt(indexX)));
			 }

		}
	}

	void valadatePuzzle() throws DuplicateException {
		// Find duplicate numbers amongst the different units.
		
		for (int index = 0; index < 9; index++) {
			//find duplicates for horizontals
			ArrayList<Number>duplicates=Puzzle.getHorizontal(index, puzzle);
			for (int indexVal = 1; indexVal <= 9; indexVal++) {
				if(Collections.frequency(duplicates, Number.valueOf(indexVal))>1)
					throw new DuplicateException("Duplicate values: "+indexVal);
			}
		
		//find duplicates for verticals
			duplicates=Puzzle.getVertical(index, puzzle);
			for (int indexVal = 1; indexVal <= 9; indexVal++) {
				if(Collections.frequency(duplicates, Number.valueOf(indexVal))>1)
					throw new DuplicateException("Duplicate values: "+indexVal);
			}
		
		//find duplicates for areas
			duplicates=Puzzle.getArea(Area.valueOf(index+1), puzzle);
			for (int indexVal = 1; indexVal <= 9; indexVal++) {
				if(Collections.frequency(duplicates, Number.valueOf(indexVal))>1)
					throw new DuplicateException("Duplicate values: "+indexVal);
			}
		}
	}
	
	@Override
	public Square[][] loadPuzzle() {
		// TODO Auto-generated method stub
		return puzzle;
	}

	

}
